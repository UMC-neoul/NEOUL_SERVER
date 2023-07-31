package com.example.neoul.global.jwt;

import com.example.neoul.dto.GenerateToken;
import com.example.neoul.entity.user.User;
import com.example.neoul.repository.UserRepository;
import com.example.neoul.service.CustomUserDetailsService;
import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import java.security.Key;
import java.util.Date;


import static com.example.neoul.global.jwt.JwtFilter.AUTHORIZATION_HEADER;

@Component
public class TokenProvider implements InitializingBean {

    private final Logger logger = LoggerFactory.getLogger(TokenProvider.class);

    private static final String AUTHORITIES_KEY = "auth";

    private final String secret;
//    private final long tokenValidityInMilliseconds;
    private final UserRepository userRepository;
    private final String refreshSecret;
    private final CustomUserDetailsService customUserDetailsService;
    private final long accessTime;
    private final long refreshTime;

    private Key key;


    public TokenProvider(
            @Value("${jwt.secret}") String secret,
            @Value("${jwt.refresh}") String refreshSecret,
            UserRepository userRepository,
            CustomUserDetailsService customUserDetailsService,
            @Value("${jwt.access-token-seconds}") long accessTime,
            @Value("${jwt.refresh-token-seconds}")long refreshTime) {

        this.secret = secret;
        this.userRepository = userRepository;
        this.refreshSecret=refreshSecret;
        this.customUserDetailsService=customUserDetailsService;
        this.accessTime = accessTime*1000;
        this.refreshTime = refreshTime*1000;
    }



    @Override
    public void afterPropertiesSet() {
        byte[] keyBytes = Decoders.BASE64.decode(secret);
        this.key = Keys.hmacShaKeyFor(keyBytes);
    }

//    public String createToken(Authentication authentication) {
//        String authorities = authentication.getAuthorities().stream()
//                .map(GrantedAuthority::getAuthority)
//                .collect(Collectors.joining(","));
//
//        long now = (new Date()).getTime();
//        Date validity = new Date(now + this.tokenValidityInMilliseconds);
//
//        return Jwts.builder()
//                .setSubject(authentication.getName())
//                .claim(AUTHORITIES_KEY, authorities)
//                .signWith(key, SignatureAlgorithm.HS512)
//                .setExpiration(validity)
//                .compact();
//    }

    public String createToken(Long userId) {

        long now = (new Date()).getTime();
        Date validity = new Date(now + this.accessTime);

        return Jwts.builder()
                //.setSubject(authentication.getName())
                .claim("userId",userId)
                .setIssuedAt(new Date())
                //.claim(AUTHORITIES_KEY, authorities)
                .signWith(SignatureAlgorithm.HS512, secret)
                .setExpiration(validity)
                .compact();
    }

    public String createRefreshToken(Long userId) {

        long now = (new Date()).getTime();
        Date validity = new Date(now + this.refreshTime);

        return Jwts.builder()
                //.setSubject(authentication.getName())
                .claim("userId",userId)
                .setIssuedAt(new Date())
                //.claim(AUTHORITIES_KEY, authorities)
                .signWith(SignatureAlgorithm.HS512, refreshSecret)
                .setExpiration(validity)
                .compact();
    }


    public GenerateToken createAllToken(Long userId){
        String accessToken = createToken(userId);
        String refreshToken = createRefreshToken(userId);

//        //redis에 리프레시토큰 저장
//        redisService.saveToken(String.valueOf(userId),refreshToken, (System.currentTimeMillis()+ refreshTime*1000));

        return new GenerateToken(accessToken,refreshToken);
    }


    public Authentication getAuthentication(String token) {
        Claims claims = Jwts
                .parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token)
                .getBody();
//
//        Collection<? extends GrantedAuthority> authorities =
//                Arrays.stream(claims.get(AUTHORITIES_KEY).toString().split(","))
//                        .map(SimpleGrantedAuthority::new)
//                        .collect(Collectors.toList());

        Long userIdx = claims.get("userId",Long.class);

        User user = userRepository.findUserByUserId(userIdx).get();
        String userName = user.getUsername();

        UserDetails userDetails = customUserDetailsService.loadUserByUsername(userName);

        //User principal = new User(claims.getSubject(), "", authorities);

        return new UsernamePasswordAuthenticationToken(userDetails, token, userDetails.getAuthorities());
    }


    public String getJwt(){
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
        return request.getHeader(AUTHORIZATION_HEADER);
    }

    public Long getUserIdx() {
        String accessToken = getJwt();

        Jws<Claims> claims;
        claims = Jwts.parser()
                .setSigningKey(secret)
                .parseClaimsJws(accessToken);
        //String expiredAt= redisService.getValues(accessToken);

        Long userIdx = claims.getBody().get("userIdx",Long.class);


//        if(expiredAt==null){
//            return userIdx;
//        }
//        if(expiredAt.equals(String.valueOf(userIdx))){
//            //throw new BaseException(JWT_HIJACK_ACCESS_TOKEN);
//        }

        return userIdx;
    }



    public boolean validateToken(ServletRequest servletRequest, String token) {
        try {
            //Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token);

            Jws<Claims> claims;
            claims = Jwts.parser()
                    .setSigningKey(secret)
                    .parseClaimsJws(token);
            Long userIdx = claims.getBody().get("userIdx",Long.class);
            //String expiredAt= redisService.getValues(token);
//
//            if(expiredAt==null){
//                return true;
//            }
//            if(expiredAt.equals(String.valueOf(userIdx))){
//                servletRequest.setAttribute("exception","HijackException");
//                return false;
//            }

            return true;
        } catch (io.jsonwebtoken.security.SecurityException | MalformedJwtException e) {
            logger.info("잘못된 JWT 서명입니다.");
        } catch (ExpiredJwtException e) {
            logger.info("만료된 JWT 토큰입니다.");
        } catch (UnsupportedJwtException e) {
            logger.info("지원되지 않는 JWT 토큰입니다.");
        } catch (IllegalArgumentException e) {
            logger.info("JWT 토큰이 잘못되었습니다.");
        }
        return false;
    }


    public void logOut(Long userId, String accessToken) {
        long expiredAccessTokenTime=getExpiredTime(accessToken).getTime() - new Date().getTime();
        //Redis 에 액세스 토큰값을 key 로 가지는 userId 값 저장
        //redisService.saveToken(accessToken,String.valueOf(userId),expiredAccessTokenTime);
        //Redis 에 저장된 refreshToken 삭제
        //redisService.deleteValues(String.valueOf(userId));
    }
    public Date getExpiredTime(String token){
        //받은 토큰의 유효 시간을 받아오기
        return Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody().getExpiration();
    }


}