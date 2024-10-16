<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>     
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시판 목록</title>
</head>
<body>
<a href="boardform">글작성</a> <br>
<table border=1 align=center width=800>
	<caption>게시판 목록</caption>
	<tr>
		<th>번호</th>
		<th>작성자</th>
		<th>제목</th>
		<th>날짜</th>
		<th>내용</th>
	</tr>	
	<c:forEach var="b" items="${list }">
	<tr>
		<td>${b.no}</td>
		<td>${b.name}</td>
		<td>${b.subject}</td>
		<td>
			<fmt:formatDate value="${b.register}" pattern="yyyy-MM-dd HH:mm:ss"/>
		</td>
		<td>${b.content}</td>
	</tr>
	</c:forEach>	
</table>
</body>
</html>