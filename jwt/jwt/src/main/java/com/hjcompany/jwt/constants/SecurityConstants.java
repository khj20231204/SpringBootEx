package com.hjcompany.jwt.constants;

//Security 및 JWT와 관련된 상수를 관리하는 클래스
/*
 * HTTP
 *    header : {
 *       Authoriztion : Bearer ${jwt}
 *    }
 */
public class SecurityConstants {
   //headers : { Authoriztion : Bearer ${jwt} } 이런 형태를 가져야 하는데 이 형태를 미리 정해놓고 사용하자

   //jwt 토큰을 담을 HTTP 요청 헤더 이름
   public static final String TOKEN_HEADER = "Authorization"; 

   //헤더의 접두사
   public static final String TOKEN_PREFIX = "Bearer ";

   //토큰 타입
   public static final String TOKEN_TYPE = "jwt";
}

