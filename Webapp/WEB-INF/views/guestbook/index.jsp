<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
	pageContext.setAttribute("newLine", "\n");
%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>방명록</title>
<link href="<%=request.getContextPath()%>/assets/css/user.css"
	rel="stylesheet" type="text/css">
</head>
<body>
<br>
	
	<div id="container">
		<jsp:include page="/WEB-INF/views/include/header.jsp" />
		<div id="content">
			 <form action="${pageContext.servletContext.contextPath}/guestbook/list" method="post">
				<input type="hidden" name="a" value="add">
				<table border="1" width="500">
					<tr>
						<td>이름</td>
						<td><input type="text" name="name"></td>
						<td>비밀번호</td>
						<td><input type="password" name="pwd"></td>
					</tr>
					<tr>
						<td colspan="4"><textarea name="message" cols="60" rows="5"></textarea></td>
					</tr>
					<tr>
						<td colspan="4" align="right"><input type="submit"
							VALUE=" 확인 "></td>
					</tr>
				</table>
			</form>
			<br>

			<c:set var="count" value="${fn:length(list)}" />
			<c:forEach items="${list}" var="vo" varStatus="status">
				<table width="510" border="1">
					<tr>
						<td>${status.count }</td>
						<td>${vo.name }</td>
						<td>${vo.date }</td>
						<td><a
							href="${pageContext.servletContext.contextPath }/guestbook/deleteform/${vo.no}">삭제</a></td>
					</tr>
					<tr>
						<td colspan="4">${fn:replace(vo.message, newLine , "<br>") }</td>
					</tr>
				</table>
			</c:forEach>
		</div>
		<jsp:include page="/WEB-INF/views/include/nav.jsp" />
		<jsp:include page="/WEB-INF/views/include/footer.jsp" />
	</div>
</body>
</html>