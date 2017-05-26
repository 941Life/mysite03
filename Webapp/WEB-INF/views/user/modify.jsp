<%@ page contentType="text/html;charset=UTF-8" %>
<!doctype html>
<html>
<head>
<title>mysite</title>
<meta http-equiv="content-type" content="text/html; charset=utf-8">
<link href="<%=request.getContextPath()%>/assets/css/user.css" rel="stylesheet" type="text/css">
</head>
<body>
	<div id="container">
		<jsp:include page="/WEB-INF/views/include/header.jsp"/>
		<div id="content">
			<div id="user">
				<form id="modify-form" name="modifyform" method="post" action="<%=request.getContextPath()%>/user?a=modify">
					<input type="hidden" name="a" value="modify"/>
					<label class="block-label" for="name">이름</label>
					<input id="name" name="name" type="text" value="">
					
					<fieldset>
						<legend>성별</legend>
						<label>여</label> <input type="radio" name="gender" value="female" checked="checked">
						<label>남</label> <input type="radio" name="gender" value="male">
					</fieldset>
					
					<label class="block-label" for="password">비밀번호</label>
					<input id="password" name="password" type="text" value="">
					
					<input type="submit" value="회원정보 수정">
				</form>
			</div>
		</div>
		<jsp:include page="/WEB-INF/views/include/nav.jsp"/>
		<jsp:include page="/WEB-INF/views/include/footer.jsp"/>
	</div>
</body>
</html>