package com.hjcompany.jwt.controller;

import org.springframework.web.bind.annotation.RestController;

import com.hjcompany.jwt.constants.SecurityConstants;
import com.hjcompany.jwt.domain.AuthenticationRequest;
import com.hjcompany.jwt.prop.JwtProp;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
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
         "uid" : "user",
         "password" : "1234"
      }  
    */

   //ResponseEntity : HTTP의 응답을 나타내는 클래스
   //AuthenticationRequest : domain에 정의한 클래스
   @PostMapping("login")
   public ResponseEntity<?> login(@RequestBody AuthenticationRequest request) { 
       // /login 경로가 실행되면 AuthenticationRequest에 정의된 uid, password로 request를 받는다

       String uid = request.getUid();
       String password = request.getPassword();

       log.info("uid : " + uid);
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
                        .expiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60*24*5)) //토큰 만료 시간 5일 1000(1초)*60(1분)*60(1시간)*24(하루)*5(5일)
                        .claim("uid", uid) //claim : 페이로드에 저장할 값
                        .claim("rol", roles) //claim : 페이로드에 저장할 값
                        .claim("mymake", "mymakeValue")
                        .claim("uid2", uid)
                        .compact();  //토큰 생성 메소드
      /*
         HEADER 
         {
            "typ": "jwt",
            "alg": "HS512"
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
      log.info("jwt:"+jwt);

      return new ResponseEntity<String>(jwt, HttpStatus.OK);
   }

   //토큰 해석
   @GetMapping("/user/info")
   public ResponseEntity<?> userINfo(@RequestHeader(name="Authorization") String header) {
      //http헤더 부분의 Authorization을 header 변수에 담는다
      
      log.info("==========header==========");
      log.info("Authorization : " + header);

      //Authorization : Bearer : {jwt}
      //header.replace("Bearer ", "");
      String jwt = header.replace(SecurityConstants.TOKEN_PREFIX, "");
      
      byte[] stringKey = jwtProp.getSecretKey().getBytes();

      log.info("/user/info jwt : " + jwt);

      //토큰 해석
      Jws<Claims> parsedTokens = Jwts.parser() //Jwts의 parser를 통해서 토큰을 해석하겠다
                                    .verifyWith(Keys.hmacShaKeyFor(stringKey)) //시크릿 키를 알려주고 
                                    .build()
                                    .parseSignedClaims(jwt); //토큰을 주고 , parse:변환하겠다, SignedClaims:암호화된 Claims를 

      log.info("parsedTokens : " + parsedTokens);

      //username : user
      String uid = parsedTokens.getPayload().get("uid").toString();
      log.info("uid : " + uid);

      // rol : [ROLE_USER, ROLE_AMDIN]
      Claims claim = parsedTokens.getPayload();
      Object roles = claim.get("rol");
      log.info("roles : " + roles);

      return new ResponseEntity<String>(parsedTokens.toString(), HttpStatus.OK);
   }
   
}


