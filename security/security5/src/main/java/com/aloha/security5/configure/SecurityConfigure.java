package com.aloha.security5.configure;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

//Security 설정 파일

@Configuration             //스프링 빈 설정 클래스로 지정
@EnableWebSecurity         //스프링 시큐리티 설정 빈으로 등록
public class SecurityConfigure {
   //기본 설정
   
   /*
      인메모리 방식 인증
      - 기본 사용자를 메모리에 등록
      - user / 1234
      - admin /1234 
   */ 

   @Bean
   public UserDetailsService userDetailsService(){
      UserDetails user = User.builder()
                              .username("user")          //아이디
                              .password(passwordEncoder().encode("1234"))
                              /* .password("1234")          //패스워드 */
                              .roles("USER")             //권한
                              .build();

      UserDetails admin = User.builder()
                              .username("admin")         //아이디
                              .password(passwordEncoder().encode("1234"))
                              // .password("1234")          //패스워드
                              .roles("USER","ADMIN")     //권한
                              .build();

      return new InMemoryUserDetailsManager(user, admin);
   }

   //AuthenticationManager 빈 등록
   @Bean
   public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception{
      return authenticationConfiguration.getAuthenticationManager();
   }

   //암호화 방식 빈 등록
   @Bean
   public PasswordEncoder passwordEncoder(){
      return new BCryptPasswordEncoder();
   }

}
