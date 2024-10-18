package com.hjcompany.server.config;

import java.util.logging.Filter;

import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Configuration
@EnableWebSecurity
public class SecurityConfig {
   
   //시쿠리티 설정
   @Bean
   public SecurityFilterChain securityFilerChain(HttpSecurity http) throws Exception{
      log.info("시큐리티 설정");

      //폼 기반 로그인 비활성화
      http.formLogin(login -> login.disable());

      //HTTP 기본 인증 비활성화
      http.httpBasic(basic -> basic.disable());

      //CSRF(Cross-Site Request Forgery) 공격 방어 기능 비활성화
      http.csrf(csrf -> csrf.disable());

      //필터 설정
      http.addFilterAt(filter, atFilter).addFilterBefore(filter,beforeFilter);

      //인가 설정
      http.authorizeHttpRequests((auth) -> auth
         .requestMatchers("/admin/**").hasRole("ADMIN")
         .requestMatchers("/user/**").hasAnyRole("USER","ADMIN")
         .requestMatchers("/", "/login").permitAll()
         .requestMatchers(PathRequest.toStaticResources().atCommonLocations()).permitAll() 
         .anyRequest().authenticated());
      /* 
      PathRequest.toStaticResources().atCommonLocations()
      일반적으로 사용되는 정적 리소스 위치 (예: classpath:/static, classpath:/public, classpath:/resources, file:/absolute/path/to/static)를 가리킵니다.  이 부분은 CSS, JavaScript, 이미지 파일 등과 같은 정적 파일들이 위치한 디렉토리를 지정하는 역할을 합니다.
      */

      //인증 방식 설정
      http.userDetailsService(null)

      return http.build();
   }

   //암호화 알고리즘 방식 : Bcrypt
   @Bean
   public PasswordEncoder passwordEncoder(){
      return new BCryptPasswordEncoder();
   }
}
