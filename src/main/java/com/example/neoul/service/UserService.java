package com.example.neoul.service;


import com.example.neoul.dto.user.GenerateToken;
import com.example.neoul.dto.user.TokenRes;
import com.example.neoul.dto.user.UserReq;
import com.example.neoul.dto.user.UserRes;
import com.example.neoul.entity.user.Authority;
import com.example.neoul.entity.user.User;
import com.example.neoul.global.exception.BadRequestException;
import com.example.neoul.global.exception.ServerErrorException;
import com.example.neoul.global.jwt.TokenProvider;
import com.example.neoul.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


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

        if(username == null){
            throw new ServerErrorException("accessToken이 없습니다");
        }

        return userRepository.findUserByUsername(username).get();
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



    @Transactional
    public UserRes.UserDetailDto signup(UserReq.SignupUserDto signupUserDto) {
        if (userRepository.findUserWithAuthoritiesByUsername(signupUserDto.getUsername()).orElse(null) != null) {
            throw new BadRequestException("이미 가입되어 있는 유저입니다.");
        }

        Authority authority = Authority.builder()
                .authorityName("ROLE_USER")
                .build();

        User user = User.builder()
                .username(signupUserDto.getUsername())
                .password(passwordEncoder.encode(signupUserDto.getPassword()))
                .name(signupUserDto.getName())
                .phone(signupUserDto.getPhone())
                .imageUrl(signupUserDto.getImageUrl())
                .authorities(Collections.singletonList(authority))
                .build();

        userRepository.save(user);

        return UserRes.UserDetailDto.toDto(user);
    }

    //전화번호 양식 체크
    public boolean validationEmail(String email){
        Pattern pattern = Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$");
        Matcher matcher = pattern.matcher(email);
        if (matcher.matches()) {
            return true;
        } else {
            return false;
        }
    }

    // 회원정보 수정
    public User userInfoEdit(UserReq.UserInfoEditReq userInfoEditReq){
        User user = findNowLoginUser();
        if (userInfoEditReq.getUsername() != null ){
            user.setName(userInfoEditReq.getUsername());
        }
        if (userInfoEditReq.getBirth() != null ){
            user.setBirth(userInfoEditReq.getBirth());
        }
        if (userInfoEditReq.getPhone() != null ){
            user.setPhone(userInfoEditReq.getPhone());
        }
        userRepository.save(user);

        return findNowLoginUser();
    }


//    public User signupADMIN(UserReq.SignupUserDto signupUserDto) {
//        if (userRepository.findUserWithAuthoritiesByUsername(signupUserDto.getUsername()).orElse(null) != null) {
//            throw new RuntimeException("이미 가입되어 있는 관리자입니다.");
//        }
//
//        Authority roleAdmin = new Authority("ROLE_ADMIN");
//        Authority roleUser = new Authority("ROLE_USER");
//
//
//        List<Authority> authorityList = giveADMIN();
//
//
//        User user = User.builder()
//                .username(signupUserDto.getUsername())
//                .password(passwordEncoder.encode(signupUserDto.getPassword()))
//                .name(signupUserDto.getName())
//                .imageUrl(signupUserDto.getImageUrl())
//                //.authorities(Collections.singletonList(authority))
//                .authorities(authorityList)
//                .build();
//
//        userRepository.save(user);
//
//        return user;
//
//    }

    @Transactional
    public TokenRes login(UserReq.LoginUserDto loginUserDto){
        Optional<User> optionalUser = userRepository.findUserByUsername(loginUserDto.getUsername());
        if(optionalUser.isPresent()) {
            User user = optionalUser.get();
            if(passwordEncoder.matches(loginUserDto.getPassword(), user.getPassword())) {

                //인증토큰 생성
                UsernamePasswordAuthenticationToken authenticationToken =
                        new UsernamePasswordAuthenticationToken(loginUserDto.getUsername(), loginUserDto.getPassword());

                Authentication authentication = authenticationManagerBuilder.getObject().authenticate(authenticationToken);
                SecurityContextHolder.getContext().setAuthentication(authentication); //SecurityContext에 저장



                User loginUser = optionalUser.get();
                Long userId = loginUser.getUserId();
                GenerateToken generateToken = tokenProvider.createAllToken(userId);

                boolean isFirstLogin = user.isFirstLogin();
                user.setFirstLogin(false); //첫 로그인 이후는 전부 0으로 바꾸기


//                HttpHeaders httpHeaders = new HttpHeaders();
//                httpHeaders.add(JwtFilter.AUTHORIZATION_HEADER, "Bearer " + generateToken.getAccessToken());
//


                return TokenRes.builder()
                        .username(loginUser.getUsername())
                        .firstLogin(isFirstLogin)
                        .accessToken(generateToken.getAccessToken())
                        .refreshToken(generateToken.getRefreshToken())
                        .build();
            } else{
                throw new BadRequestException("비밀번호를 다시 입력해주세요");
            }
        } else{
            throw new BadRequestException("존재하지 않는 회원입니다");
        }
    }



    public String withdrawal() {
        User user = findNowLoginUser();
        
        userRepository.save(user);

        return "회원 탈퇴가 완료되었습니다";
    }
}
