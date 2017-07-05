<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>ホーム</title>
	<link href="./css/style.css" rel="stylesheet" type="text/css">
</head>
<body>
<h1>わったい菜 掲示板</h1>

<div class="main-contents">

<div class="header">
	<c:out value="${loginUser.name}" />
	<a href="./">ホーム</a>
	<a href="newpost">新規投稿</a>
	<a href="usercontrol">ユーザー管理</a>
	<a href="logout">ログアウト</a>
	<br />
</div>
</div>
<div class="posts">
	<c:forEach items="${postList}" var="post">
			<div class="title">件名 : <c:out value="${post.title}" /> <br /></div>
			<div class="text"><c:out value="${post.text}"/><br/></div>
			<div class="category">カテゴリー:<c:out value="${post.category}" /></div>
			<div class="userId">名前:<c:out value="${post.name}" /></div>

	</c:forEach>
</div>

<div class="copyright">Copyright(c)Shintaro Nishikawa</div>

</body>
</html>