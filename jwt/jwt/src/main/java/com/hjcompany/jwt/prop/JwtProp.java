
package com.hjcompany.jwt.prop;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import lombok.Data;

//application.properties의 secret-key를 가져와서 사용

@Data
@Component
@ConfigurationProperties("com.hjcompany.jwt") //com.hjcompany.jwt 경로 하위 속성들을 지정
public class JwtProp {

   //com.hjcompany.jwt.secret-key=khjcompnay
   private String secretKey;
}
