package com.hjcompany.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hjcompany.dao.BoardDao;
import com.hjcompany.model.Board;

@Service
public class BoardService {

   @Autowired
   private BoardDao dao;

   public List<Board> boardlist() {
      return dao.boardlist();
   }
}
