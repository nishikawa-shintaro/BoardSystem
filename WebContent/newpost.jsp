<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<html>

<script>
var set=0;
function double() {
if(set==0){ set=1; } else {
alert("只今処理中です。\nそのままお待ちください。");
return false; }}
</script>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>新規投稿</title>
	<link href="./css/post.css" rel="stylesheet" type="text/css">
</head>
<body>


<div class="header">
			ログイン中:<c:out value="${loginUser.name}" />
			<a href="./">ホーム</a>
			<a href="logout">ログアウト</a>
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

<h1 class="main"><font color="white">ユーザー新規登録</font></h1>


<form action="newpost" method="post" onSubmit="return double()">
	<label for="title">件名(30文字以下)</label><br>
	<textarea name="title" cols="35" rows="5" id="title" maxlength="30"><c:out value="${post.title}" /></textarea><br />

	<label for="category">カテゴリー(10文字以下)</label><br>
	<input name="category"  id="category" maxlength="10" value="${post.category}" /><br />

	<label for="text">本文(1000文字以下)</label><br>
	<textarea name="text" cols="50" rows="20" id="text" maxlength="1000"><c:out value="${post.text}" /></textarea> <br />


	<input type="submit" value="登録" />
</form>
<br />

<div class="copyright">Copyright(c)Shintaro Nishikawa</div>
</body>
</html>