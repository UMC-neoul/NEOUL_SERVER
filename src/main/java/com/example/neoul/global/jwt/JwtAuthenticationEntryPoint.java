package com.example.neoul.global.jwt;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
@Component
public class JwtAuthenticationEntryPoint implements AuthenticationEntryPoint {

    @Override
    public void commence(HttpServletRequest request,
                         HttpServletResponse response,
                         AuthenticationException authException) throws IOException {

        // 유효한 자격증명을 제공하지 않고 접근하려 할때 401
        log.info("Spring Security - 유효한 자격증명을 가지고 있지 않음");
        authException.printStackTrace(); // 에러 발생 시, 어떤 에러가 발생했는지 나옴.
        response.sendError(HttpServletResponse.SC_UNAUTHORIZED);
    }
}