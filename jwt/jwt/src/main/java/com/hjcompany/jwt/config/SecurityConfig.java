package com.hjcompany.jwt.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfiguration;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;

//SpringSecurity 5.4이하
/*
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter{

   @Override
   public void configure(WebSecurity web) throws Exception {
      // TODO Auto-generated method stub
      super.configure(web);
   }
}*/

@Configuration //XML 설정 파일 대신 자바 코드를 이용하여 설정을 관리함
@EnableWebSecurity  // 스프링 시큐리티를 사용하는 애플리케이션에서 웹 보안 설정을 활성화
public class SecurityConfig {
   
   //SpringSecurity 5.5 이상

   @Bean
   public SecurityFilterChain securityFilterChain(HttpSecurity http)  throws Exception {
      //폼 기반 로그인 비활성화
      //http.formLogin().disable(); 파란색 줄은 람다식으로 변경
      http.formLogin((login) -> login.disable());

      //HTTP 기본 인증 비활성화
      http.httpBasic((basic) -> basic.disable());

      //CSRF 공격 방어 기능 비활성화
      http.csrf((csrf) -> csrf.disable());

      //세션 관리 정책 설정
      //세션 인증을 사용하지 않고, JWT를 사용하여 인증하기 때문에, 세션 불필요
      http.sessionManagement(management -> management.sessionCreationPolicy(SessionCreationPolicy.STATELESS));

      return http.build();
   }
}

