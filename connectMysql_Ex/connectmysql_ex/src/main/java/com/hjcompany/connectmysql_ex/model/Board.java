package com.hjcompany.connectmysql_ex.model;

import org.apache.ibatis.type.Alias;

import lombok.Data;
import lombok.Setter;

@Data
public class Board {
   private int board_num;
   private String board_name;
   
}
