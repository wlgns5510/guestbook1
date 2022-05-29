package com.javaex.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class GuestBookDao {
	//필드
			private Connection conn = null;
			private PreparedStatement pstmt = null;
			private ResultSet rs = null;
			
			private String driver = "oracle.jdbc.driver.OracleDriver";
			private String url = "jdbc:oracle:thin:@localhost:1521:xe";
			private String id = "webdb";
			private String pw = "webdb";
	//생성자
	//메소드 gs
	//메소드 일반
}
