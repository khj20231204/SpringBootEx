package com.hjcompany.connectmysql_ex.model;

import org.apache.ibatis.type.Alias;

import lombok.Data;

@Data
@Alias("exbean")
public class ExBean {
   private int A;
   private String B;
   private int C;
}
