<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>新規投稿</title>
	<link href="./css/style.css" rel="stylesheet" type="text/css">
</head>
<body>

<h1>新規投稿画面</h1>


<div class="header">
	<c:out value="${loginUser.name}" />
	<a href="./">ホーム</a>
	<a href="logout">ログアウト</a>
	<br />
</div>

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
</div>


<h1>新規投稿</h1>

<form action="newpost" method="post">
	<label for="title">件名(30文字以下)</label>
	<input name="title"  id="title" value="${post.title}" /> <br />

	<label for="category">カテゴリー(10文字以下)</label>
	<input name="category"  id="category" value="${post.category}" /><br />


	<label for="text">本文(1000文字以下)</label>
	<textarea name="text" cols="50" rows="20" id="text" maxlength="1000"><c:out value="${post.text}" /></textarea> <br />


	<input type="submit" value="登録" />
</form>
<br />

<div class="copyright">Copyright(c)Shintaro Nishikawa</div>
</body>
</html>