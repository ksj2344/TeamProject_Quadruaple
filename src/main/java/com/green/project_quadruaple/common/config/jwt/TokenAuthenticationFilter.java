package com.green.project_quadruaple.common.config.jwt;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Slf4j
@Component
@RequiredArgsConstructor
public class TokenAuthenticationFilter extends OncePerRequestFilter {
    private final TokenProvider tokenProvider;
    private final static String HEADER_AUTHORIZATION = "Authorization";
    private final static String TOKEN_PREFIX = "Bearer ";

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        log.info("ip Address: {}", request.getRemoteAddr());
        String authorizationHeader = request.getHeader(HEADER_AUTHORIZATION);  //"Bearer 토큰값"
        log.info("authorizationHeader: {}", authorizationHeader);

        String token = getAccessToken(authorizationHeader);
        log.info("token: {}", token);

<<<<<<< HEAD
        if (token != null) {
            try {
                // 토큰에서 Authentication 객체를 얻어옴
                Authentication auth = tokenProvider.getAuthentication(token);
                if (auth != null) {
                    log.info("Authentication is set: {}", auth);
                    SecurityContextHolder.getContext().setAuthentication(auth);
                } else {
                    log.warn("Authentication is null.");
                }
            } catch (Exception e) {
                request.setAttribute("exception", e);
                log.error("Error during authentication", e);
=======
        if(token != null) {
            try {
                Authentication auth = tokenProvider.getAuthentication(token);
                SecurityContextHolder.getContext().setAuthentication(auth);
            } catch (Exception e) {
                request.setAttribute("exception", e);
>>>>>>> 01035d4 (access-token 재발행 이슈 해결 중)
            }
        }
        filterChain.doFilter(request, response);
    }

    private String getAccessToken(String authorizationHeader) {
<<<<<<< HEAD
        if (authorizationHeader != null && authorizationHeader.startsWith(TOKEN_PREFIX)) { // 문자열이 TOKEN_PREFIX 로 시작하는지 확인
=======
        if (authorizationHeader != null && authorizationHeader.startsWith(TOKEN_PREFIX)) { // 문자열이 TOKEN_PREFIX 로 시작하는 지 안하는 지
>>>>>>> 01035d4 (access-token 재발행 이슈 해결 중)
            return authorizationHeader.substring(TOKEN_PREFIX.length());
        }
        return null;
    }
}
