package com.hjcompany.server.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hjcompany.server.dto.CustomUser;
import com.hjcompany.server.dto.Users;

import lombok.extern.slf4j.Slf4j;

/**
 * JwtReuestFilter에서 이미 jwt 토큰 해석을 하고 유효한지 체크 후 SecurityContextHolder의 SecurityContext에 등록한 상태
 * 여기에 등록된 CustomUser를 AuthenticationPrincipal가 가져온다
 */

@Slf4j
@RestController
@RequestMapping("/users")
public class UserController {
   
   //@Autowired
   //private UserService userService;

   /*
    * 사용자 정보 조회 
    * @param customUser
    * @return
    */
   @GetMapping("/info")
   public ResponseEntity<?> userInfo(@AuthenticationPrincipal CustomUser customUser) {
      log.info("UserController.java");
      log.info("customUser : " + customUser);

      Users user = customUser.getUser();
      log.info("user : " + user);

      //인증된 사용자 정보
      if(user != null){
         return new ResponseEntity<>(user, HttpStatus.OK);
      }

      //인증 되지 않음
      return new ResponseEntity<>("UNAUTHORIZED", HttpStatus.UNAUTHORIZED);
   }
    
}
