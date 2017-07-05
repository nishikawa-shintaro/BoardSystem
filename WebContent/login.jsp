<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page isELIgnored="false"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>ログイン画面</title>
<link href="css/style.css" rel="stylesheet" type="text/css">
</head>
<body>
<h1>ログイン画面</h1>

<div class="main-contents">
<c:if test="${ not empty errorMessages }">
	<div class="errorMessages">
		<ul>
			<c:forEach items="${errorMessages}" var="message">
				<li><c:out value="${message}" />
			</c:forEach>
		</ul>
	</div>
	<c:remove var="errorMessages" scope="session"/>
</c:if>

<form action="login" method="post">
	<div>
		<label for="loginId">ログインID</label><br>
		<input name="loginId" id="loginId"value="${loginId}"/><br>
	</div>
	<div>
		<label for="password">パスワード</label><br>
		<input name="password" type="password" id="password"value="${password}"/><br>
	</div><br>
	<input type="submit" value="ログイン" /><br />
</form>

<div class="copyright">Copyright(c)Nishikawa Shintaro</div>

</div>
</body>
</html>