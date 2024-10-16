package com.hjcompany.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.hjcompany.model.Board;

@Repository
public class BoardDao {
   
   @Autowired
   private SqlSession session;

   public List<Board> boardlist() {
      return session.selectList("list");
   }
   
}
