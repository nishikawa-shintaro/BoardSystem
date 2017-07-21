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

<h1 class="main"><font color="white">わったいなBBSSystem</font></h1>

<div class="header">
	<nav>
		<ul>
			<li><c:out value="${loginUser.name}" /></li>
			<li><a href="./">ホーム</a></li>
			<li><a href="logout">ログアウト</a></li>
		</ul>
	</nav>
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


<h2 class="sub"><font color="white">新規投稿画面</font></h2>

<form action="newpost" method="post" onSubmit="return double()">
	<label for="title">件名(30文字以下)</label>
	<input name="title"  id="title" maxlength="30" value="${post.title}" /> <br />

	<label for="category">カテゴリー(10文字以下)</label>
	<input name="category"  id="category" maxlength="10" value="${post.category}" /><br />


	<label for="text">本文(1000文字以下)</label>
	<textarea name="text" cols="50" rows="20" id="text" maxlength="1000"><c:out value="${post.text}" /></textarea> <br />


	<input type="submit" value="登録" />
</form>
<br />

<div class="copyright">Copyright(c)Shintaro Nishikawa</div>
</body>
</html>