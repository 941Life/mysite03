<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>방명록</title>
<link href="<%=request.getContextPath()%>/assets/css/user.css"
	rel="stylesheet" type="text/css">
</head>
<body>
	<div id="container">
		<jsp:include page="/WEB-INF/views/include/header.jsp" />
		<div id="content">
			<form method="post"
				action="${pageContext.servletContext.contextPath}/guestbook/delete">

				<input type="hidden" name="no" value="${no}">
				<table>
					<tr>
						<td>비밀번호</td>
						<td><input type="password" name="pwd"></td>
						<td><input type="submit" value="확인"></td>
						<td><a href="${pageContext.servletContext.contextPath}/list">메인으로
								돌아가기</a></td>
					</tr>
				</table>
			</form>
		</div>
		<jsp:include page="/WEB-INF/views/include/nav.jsp" />
		<jsp:include page="/WEB-INF/views/include/footer.jsp" />
	</div>
</body>
</html>