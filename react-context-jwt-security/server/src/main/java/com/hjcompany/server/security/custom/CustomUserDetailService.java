package com.hjcompany.server.security.custom;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.hjcompany.server.dto.Users;
import com.hjcompany.server.mapper.UserMapper;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class CustomUserDetailService implements UserDetailsService {
   
   @Autowired
   private UserMapper userMapper;

   //username(사용자 아이디)으로 사용자 정보를 읽어오는 메소드
   @Override
   public UserDetails loadUserByUsername(String username) {
      log.info("login - loadUserByUsername 클래스 : " + username);

      Users users = userMapper.login(username);

      if(users == null){ //없는 사용자로 로그인 요청을 보낸 경우
         log.info("사용자 없음.. (일치하는 아이디가 없습니다)");

         throw new UsernameNotFoundException("사용자를 찾을 수 없습니다 : " + username);
      }

      log.info("users : " + users.toString());

      //인증된 사용자 정보를 관리하는 객체
   }
}
