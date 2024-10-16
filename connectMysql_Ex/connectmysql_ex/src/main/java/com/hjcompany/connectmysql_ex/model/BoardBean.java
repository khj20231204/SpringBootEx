package com.hjcompany.connectmysql_ex.model;

import org.apache.ibatis.type.Alias;

import lombok.Data;
import lombok.Setter;

@Data
public class BoardBean {
   private int board_num;
	private String board_name;
	private String board_pass;
	private String board_subject; 		//글제목
	private String board_content; 		//글내용
	private int board_re_ref; 			//글그룹번호
	private int board_re_lev; 			//답변글 깊이
	private int board_re_seq; 			//답변글 출력순서
	private int board_readcount; 		//조회수
	private String board_date; 			//등록날짜
   
}
