package com.hjcompany.server.security.jwt.filter;

import java.io.IOException;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;


//필터 생성 클래스
//스프링 시큐리티와 연결이 되기 위해서 UsernamePasswordAuthenticationFilter를 상속
@Slf4j
public class JwtAuthenticationFilter extends UsernamePasswordAuthenticationFilter{
   
   /* 
   요청이 오면 필터를 통해 거르게 되는데 attemptAuthentication에서 /login 경로로만 요청이 왔을 때 거르는 기능으로 설정,
   request에서 사용자 정보를 가져와 인증을 하는 기능까지 작성
   : /login 경로로 요청하면, 필터로 걸러서 인증을 시도
    */
   //인증 시도 필터 메소드
   @Override
   public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
         throws AuthenticationException {

      String username = request.getParameter("username");
      String password = request.getParameter("password");

      log.info("JwtAuthenticationFilter username : " + username);
      log.info("JwtAuthenticationFilter password : " + password);

      //사용자 인증정보 객체 생성
      //아이디와 비번이 있으면 토큰을 만들 수 있다
      Authentication authentication = new UsernamePasswordAuthenticationToken(username, password);

      //사용자 인증 (로그인) - 위에서 만든 authentication 토큰으로 사용자 인증 진행   
      authentication = authenticationM


      return super.attemptAuthentication(request, response);
   }

   //인증이 성공 됐을 때 실행될 메소드
   @Override
   protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain,
         Authentication authResult) throws IOException, ServletException {
      // TODO Auto-generated method stub
      super.successfulAuthentication(request, response, chain, authResult);
   }

   
   
}
