package com.hjcompany.jwt.domain;

import lombok.Data;

//요청 객체 username과 pass를 파라미터로 받아올 때 바인딩 할 클래스
@Data
public class AuthenticationRequest {
   private String uid;
   private String password;
}
