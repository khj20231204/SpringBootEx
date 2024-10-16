package com.hjcompany.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import com.hjcompany.model.Board;
import com.hjcompany.service.BoardService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import java.util.*;

@Controller
public class BoardController {
   
   @Autowired
   private BoardService boardService;
   
   @RequestMapping("/")
   public String main(){
      System.out.println("여긴 main");
      return "main";
   }

   @RequestMapping("/boardlist")
	public String boardlist(Model model) {
      
      System.out.println("여기는 boardlist");

		List<Board> list = boardService.boardlist();
		model.addAttribute("list", list);
		
      System.out.println("list:"+list);

		return "boardlist";
	}	
}
