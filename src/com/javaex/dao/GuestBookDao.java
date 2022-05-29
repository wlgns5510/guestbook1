package com.javaex.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.javaex.vo.PersonVo;

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
			//DB연결 메소드
			 private void getConnection() {
				 try {
					// 1. JDBC 드라이버 (Oracle) 로딩
						Class.forName(driver);

					// 2. Connection 얻어오기			
						conn = DriverManager.getConnection(url, id, pw);
				}
				catch (ClassNotFoundException e) {
					System.out.println("error: 드라이버 로딩 실패 - " + e);
				} 
				catch (SQLException e) {
					System.out.println("error:" + e);
				}
			}
		
			 //자원정리 메소드
			 private void close() {
					try {
						if (rs != null) {				
							rs.close();
						} 
						if (pstmt != null) {
							pstmt.close();
						}
						if (conn != null) {
							conn.close();
						}
					} 
					catch (SQLException e) {
							System.out.println("error:" + e);
					}
				}	
			 
			 //등록 메서드
			 public int personInsert(PersonVo personVo) {
				
				int count = -1;
				getConnection();
				
				try {
				// 3. SQL문 준비 / 바인딩 / 실행
					//SQL문 준비
					String query = "";
					query += " insert into guestbook ";
					query += " values(seq_guestbook_id.nextval, ?, ?, ?, ?) ";
					
					//바인딩
					pstmt = conn.prepareStatement(query);
					pstmt.setString(1, personVo.getName());
					pstmt.setString(2, personVo.getPassword());
					pstmt.setString(3, personVo.getContent());
					pstmt.setString(4, "sysdate");
					//실행
					count = pstmt.executeUpdate();
					
					// 4.결과처리
					System.out.println("["+ count + "건 등록되었습니다.]");
				}
				catch (SQLException e) {
					System.out.println("error:" + e);
				}
				
				close();
				
				return count;
			}

			 //전체출력
			 public List<PersonVo> personSelect(){
					
					//리스트로 만들기
					List<PersonVo> personList = new ArrayList<PersonVo>();

					getConnection();
					
					try {

						// 3. SQL문 준비 / 바인딩 / 실행
						//SQL문 준비
						String query = "";
						
						query += " select no 식별번호, ";
						query += "        name 이름, ";
						query += "        password 비밀번호, ";
						query += "        content 본문, ";
						query += "        to_char(reg_date, 'yyyy-mm-dd hh:mi:ss') 등록일 ";
						
						//바인딩
						pstmt = conn.prepareStatement(query);
						
						//실행
						//RsultSet가져오기
						rs = pstmt.executeQuery();
						
						// 4.결과처리
						//반복문으로 Vo만들기 list에 추가
						while(rs.next()) {
							int no = rs.getInt(1);
							String name = rs.getString(2);
							String password = rs.getString(3);
							String content = rs.getString(4);
							String date = rs.getString(5);
							
							PersonVo allVo = new PersonVo(no, name, password, content, date);
							
							personList.add(allVo);
						}
					}
					catch (SQLException e) {
						System.out.println("error:" + e);
					} 
						close();
						return personList;
					}
}
