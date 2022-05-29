<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ page import="java.util.List" %>
<%@ page import="com.javaex.dao.GuestBookDao" %>
<%@ page import="com.javaex.vo.PersonVo" %>

<%
	GuestBookDao guestBookDao = new GuestBookDao();
	List<PersonVo> personList = guestBookDao.personSelect();
	
	System.out.println(personList);
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<form action="./insert.jsp" method="get">
		<table border="1">
			<tr>
				<td colspan="1">이름</td>
				<td colspan="4">
					<input type="text" name="name" value="">
				</td>
				<td colspan="2">비밀번호</td>
				<td colspan="4">
					<input type="text" name="password" value="">
				</td>
			</tr>
			<tr>
				<td colspan="11">
					<textarea cols="70" rows="7" name="content"></textarea>
				</td>
			</tr>
			<tr>
				<td colspan="11">
					<button type="submit">확인</button>
				</td>
			</tr>		
		</table>
	</form>
	<br>
	<%-- <table border="1">
		<tr>
			<td>
				<%=personList.get(1).getNo() %>
			</td>
			<td>
				<%=personList.get(1).getName() %>
			</td>
		</tr>
		
	</table> --%>
</body>
</html>