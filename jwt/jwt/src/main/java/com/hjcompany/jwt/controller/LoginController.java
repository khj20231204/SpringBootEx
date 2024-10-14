package com.hjcompany.jwt.controller;

import org.springframework.web.bind.annotation.RestController;

import com.hjcompany.jwt.constants.SecurityConstants;
import com.hjcompany.jwt.domain.AuthenticationRequest;
import com.hjcompany.jwt.prop.JwtProp;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import lombok.extern.slf4j.Slf4j;

import java.sql.Date;
import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;



@Slf4j 
@RestController
public class LoginController {

   @Autowired
   private JwtProp jwtProp;
   
   /*
    👓 JWT 을 생성하는 Login 요청
    [POST] - /login
    body : 
      {
         "username" : "user",
         "password" : "1234"
      }  
    */

   //ResponseEntity : HTTP의 응답을 나타내는 클래스
   //AuthenticationRequest : domain에 정의한 클래스
   @PostMapping("login")
   public ResponseEntity<?> login(@RequestBody AuthenticationRequest request) { 
       // /login 경로가 실행되면 AuthenticationRequest에 정의된 username, password로 request를 받는다

       String username = request.getUsername();
       String password = request.getPassword();

       log.info("username : " + username);
       log.info("password : " + password);

      //사용자 권한
      List<String> roles = new ArrayList<>();
      roles.add("ROLE_USER");
      roles.add("ROLE_ADMIN");

      //시크릿키 -> 바이트
      //시크릿키를 실제로 사용할 때는 바이트로 사용을 한다
      String secretKey = jwtProp.getSecretKey();
      byte[] signingKey = jwtProp.getSecretKey().getBytes();

      //log.info("secretKey : " + secretKey);
      //log.info("signingKey : " + signingKey);

      //토큰 생성
      String jwt = Jwts.builder()
                        //.signWith(시크릿키, 알고리즘)
                        .signWith(Keys.hmacShaKeyFor(signingKey), Jwts.SIG.HS512) //시그니처에 사용할 비밀키, 알고리즘 설정
                        .header()   //Header 설정
                        .add("typ", SecurityConstants.TOKEN_TYPE) // "type" : "jwt"
                        .and()
                        .expiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60*24*5)) //토큰 만료 시간 5일
                        .claim("uid", username) //claim : 페이로드에 저장할 값
                        .claim("rol", roles) //claim : 페이로드에 저장할 값
                        .compact();  //토큰 생성 메소드
                        
      log.info("jwt:"+jwt);

      /*
         HEADER 
         {
            "typ": "jwt",
            "alg": "HS256"
         }
        
        PAYLOAD:DATA
        {
            "exp": 17293448,
            "uid": "use",
            "rol": [
               "ROLE_USER",
               "ROLE_ADMIN"
            ]
         }
       */

      return new ResponseEntity<String>(jwt, HttpStatus.OK);
   }

   //토큰 해석
   @GetMapping("/user/info")
   public ResponseEntity<?> userInfo(@RequestHeader(name="Authorization") String header) {

      log.info("==========header==========");
      log.info("Authorization : " + header);

      // Authorization : Bearer : {jwt}
      

       return 
   }
   
}


