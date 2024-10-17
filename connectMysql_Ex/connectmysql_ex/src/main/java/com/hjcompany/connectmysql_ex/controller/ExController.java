package com.hjcompany.connectmysql_ex.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.hjcompany.connectmysql_ex.model.ExBean;
import com.hjcompany.connectmysql_ex.service.EXService;

@Controller
public class ExController {
   
   @Autowired
   private EXService exService;

   @RequestMapping("getList")
   public String getList(Model model) {

      System.out.println("=========================Here is getList=========================");

      List<ExBean> list = exService.getList();

      System.out.println("list:"+list);

      //model.addAttribute("list",list);
       return "board/getList";
   }
   
  
}
