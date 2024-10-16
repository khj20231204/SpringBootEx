package com.hjcompany.connectmysql_ex.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class BoardController {

   @RequestMapping("/boardform")
	public String main() {
		System.out.println("HomeController 실행");
		return "boardform";
	}

	@GetMapping("/")
    public String home() {
        System.out.println("HomeController 실행");
        return "boardform";
    }
}
