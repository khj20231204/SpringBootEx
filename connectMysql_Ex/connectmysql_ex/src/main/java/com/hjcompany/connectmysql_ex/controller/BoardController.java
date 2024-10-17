package com.hjcompany.connectmysql_ex.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class BoardController {	

  // 게시판 글쓰기 폼 
	@RequestMapping(value = "/board_write")
	public String board_write() {
		return "board/board_write";
	}
}
