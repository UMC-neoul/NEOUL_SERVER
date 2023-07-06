package com.example.neoul.service;


import com.example.neoul.dto.GenerateToken;
import com.example.neoul.dto.TokenRes;
import com.example.neoul.dto.UserReq;
import com.example.neoul.dto.UserRes;
import com.example.neoul.entity.Authority;
import com.example.neoul.entity.User;
import com.example.neoul.global.jwt.JwtFilter;
import com.example.neoul.global.jwt.TokenProvider;
import com.example.neoul.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


@RequiredArgsConstructor
@Service
public class UserService {

    private final UserRepository userRepository;
    private final AuthenticationManagerBuilder authenticationManagerBuilder;
    private final PasswordEncoder passwordEncoder;
    private final TokenProvider tokenProvider;

    private final CustomUserDetailsService customUserDetailsService;



    @SneakyThrows
    public User findNowLoginUser(){

        String username = SecurityUtil.getCurrentUsername().orElse(null);

        if(username != null){
            return userRepository.findUserByUsername(username).get();
        }

        return null;
    }

    public List<Authority> giveUSER(){
        Authority roleUser = new Authority("ROLE_USER");

        List<Authority> authorityList = new ArrayList<>();
        authorityList.add(roleUser);

        return authorityList;
    }

    public List<Authority> giveADMIN(){
        Authority roleAdmin = new Authority("ROLE_ADMIN");
        Authority roleUser = new Authority("ROLE_USER");


        List<Authority> authorityList = new ArrayList<>();
        authorityList.add(roleAdmin);
        authorityList.add(roleUser);

        return authorityList;
    }


    public List<Authority> giveWRITER(List<Authority> authorityList){
        Authority roleWriter = new Authority("ROLE_WRITER");
        authorityList.add(roleWriter);

        return authorityList;
    }


    @Transactional
    public User signup(UserReq.SignupUserReq signupUserReq) {
        if (userRepository.findUserWithAuthoritiesByUsername(signupUserReq.getUsername()).orElse(null) != null) {
            throw new RuntimeException("이미 가입되어 있는 유저입니다.");
        }

        Authority authority = Authority.builder()
                .authorityName("ROLE_USER")
                .build();

        User user = User.builder()
                .username(signupUserReq.getUsername())
                .password(passwordEncoder.encode(signupUserReq.getPassword()))
                .name(signupUserReq.getName())
                .imageUrl(signupUserReq.getImageUrl())
                .authorities(Collections.singletonList(authority))
                .status(1)
                .build();

        userRepository.save(user);

        return user;
    }



    public User signupADMIN(UserReq.SignupUserReq signupUserReq) {
        if (userRepository.findUserWithAuthoritiesByUsername(signupUserReq.getUsername()).orElse(null) != null) {
            throw new RuntimeException("이미 가입되어 있는 관리자입니다.");
        }

        Authority roleAdmin = new Authority("ROLE_ADMIN");
        Authority roleUser = new Authority("ROLE_USER");


        List<Authority> authorityList = giveADMIN();


        User user = User.builder()
                .username(signupUserReq.getUsername())
                .password(passwordEncoder.encode(signupUserReq.getPassword()))
                .name(signupUserReq.getName())
                .imageUrl(signupUserReq.getImageUrl())
                //.authorities(Collections.singletonList(authority))
                .authorities(authorityList)
                .status(1)
                .build();

        userRepository.save(user);

        return user;

    }

    @Transactional
    public TokenRes login(UserReq.LoginUserReq loginUserReq){

        //이메일 존재 여부 체크

        //비밀번호 동일 여부 체크


        //인증토큰 생성
        UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken(loginUserReq.getUsername(), loginUserReq.getPassword());

        Authentication authentication = authenticationManagerBuilder.getObject().authenticate(authenticationToken);
        SecurityContextHolder.getContext().setAuthentication(authentication); //SecurityContext에 저장

        //String jwt = tokenProvider.createToken(authentication); //인증토큰으로 jwt토큰 생성

        User loginUser = userRepository.findUserByUsername(loginUserReq.getUsername()).get();
        Long userIdx = loginUser.getId();
        GenerateToken generateToken = tokenProvider.createAllToken(userIdx);


        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add(JwtFilter.AUTHORIZATION_HEADER, "Bearer " + generateToken.getAccessToken());

        return new TokenRes(loginUser.getUsername(), generateToken.getAccessToken(), generateToken.getRefreshToken());
    }



    public String withdrawal() {
        User user = findNowLoginUser();

        user.setStatus(0);
        userRepository.save(user);

        return "회원 탈퇴가 완료되었습니다";
    }
}
