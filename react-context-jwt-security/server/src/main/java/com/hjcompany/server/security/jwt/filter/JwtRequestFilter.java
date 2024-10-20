package com.hjcompany.server.security.jwt.filter;


import java.io.IOException;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import com.hjcompany.server.security.jwt.constants.JwtConstants;
import com.hjcompany.server.security.jwt.provider.JwtTokenProvider;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class JwtRequestFilter extends OncePerRequestFilter {
   
    private final AuthenticationManager authenticationManager;
    private final JwtTokenProvider jwtTokenProvider;

    // 생성자
    public JwtRequestFilter( AuthenticationManager authenticationManager,  JwtTokenProvider jwtTokenProvider ) {

        log.info("JwtRequestFilter.java의 생성자");

        this.authenticationManager = authenticationManager;
        this.jwtTokenProvider = jwtTokenProvider;
    }
        
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        log.info("JwtRequestFilter.java의 doFilterInternal 메소드");
        
        // HTTP 헤더에서 토큰을 가져옴
        String header = request.getHeader(JwtConstants.TOKEN_HEADER);
        log.info("authorization : " + header);

        
        // Bearer + {jwt} 체크
        // 헤더가 없거나 형식이 올바르지 않으면 다음 필터로 진행
        if (header == null || header.length() == 0 || !header.startsWith(JwtConstants.TOKEN_PREFIX)) {
            filterChain.doFilter(request, response);
            return;
        }

        // JWT
        // Bearer + ${jwt} ➡ "Bearer " 제거
        String jwt = header.replace(JwtConstants.TOKEN_PREFIX, "");
        
        // 토큰을 사용하여 Authentication 객체 생성
        Authentication authentication = jwtTokenProvider.getAuthentication(jwt);

        // 토큰 유효 검사 (토큰이 만료되지 않았으면)
        if( jwtTokenProvider.validateToken(jwt) ) {
            log.info("유효한 JWT 토큰입니다.");
            // [로그인]
            // SecurityContextHolder(사용자 보안정보를 담는 객체)에
            // Authentication(사용자 인증 정보) 객체를 설정
            SecurityContextHolder.getContext().setAuthentication(authentication);
        }
        
        // 다음 필터로 진행
        filterChain.doFilter(request, response);
    }
}
