package com.hjcompany.connectmysql_ex.dao;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class BoardDao {
   
   @Autowired
   private SqlSession session;
}