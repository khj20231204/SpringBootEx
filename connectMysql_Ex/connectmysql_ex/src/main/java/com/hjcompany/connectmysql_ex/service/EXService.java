package com.hjcompany.connectmysql_ex.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hjcompany.connectmysql_ex.dao.ExDao;
import com.hjcompany.connectmysql_ex.model.ExBean;

@Service
public class EXService {

   @Autowired
   private ExDao dao;

   public List<ExBean> getList() {
      System.out.println("============= here is EXService ===============");
      return dao.getList();
   }
}
