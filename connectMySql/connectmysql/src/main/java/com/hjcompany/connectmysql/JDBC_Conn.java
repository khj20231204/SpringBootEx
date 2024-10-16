package com.hjcompany.connectmysql;

import java.sql.Connection;
import java.sql.DriverManager;

public class JDBC_Conn {

   public void connMySql() {

   String driver ="com.mysql.cj.jdbc.Driver";
   String url = "jdbc:mysql://localhost:3306/jsptest?serverTimezone=UTC";
   
   Connection con = null;
   
   try{
         Class.forName(driver);
         con = DriverManager.getConnection(url, "jspid", "jsppass" );
         System.out.println("데이터베이스 연결 성공~!!");
      } catch(Exception e){
         System.out.println("데이터베이스 연결 실패~!!");
         e.printStackTrace();
      } finally{
         try{
         if( con != null ) con.close();
         } catch(Exception e){ e.printStackTrace(); }
      }
   }
}
