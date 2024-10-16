package com.hjcompany.connectmysql_ex.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hjcompany.connectmysql_ex.dao.BoardDao;

@Service
public class BoardService {
   
   @Autowired
   private BoardDao dao;

}
