package com.hjcompany.connectmysql_ex.dao;

import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import com.hjcompany.connectmysql_ex.model.BoardBean;

@Mapper
public interface BoardDao {
   
	// 게시판 저장 
	public void insertBoard(BoardBean board);
	// 데이터 갯수	 
	public int getListCount();
		
	// 게시판 목록  
	public  List<BoardBean> getBoardList(int page);
	
	// 상세 페이지 
	public BoardBean getBoardCont(int board_num);
	
	// 조회수 증가  
	public void boardHit(int board_num);

	// 게시판 수정 
	public void boardEdit(BoardBean board);
	
	// 게시판 삭제 
	public void boardDelete(int board_num);
	
	// 답변글 레벨 증가 
	public void refEdit(BoardBean board);
	
	// 답변글 저장 
	public void boardReplyOk(BoardBean board);
}
