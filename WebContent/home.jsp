<%@page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page isELIgnored="false"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
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

	<!--新規投稿表示-->
	<c:forEach items="${posts}" var="post">
		<div class="title">件名:<c:out value="${post.title}"/></div>
		<div class="text">本文<c:out value="${post.text}"/></div>
		<div class="category">カテゴリー:<c:out value="${post.category}" /></div>
		<div class="registrant">
		<c:forEach items="${userList}" var="user">
			<c:if test="${ user.id == post.userId }">
				投稿者 : <c:out value="${user.name}" /> <br /></c:if>
		</c:forEach>
			<div class="insertdate">
				投稿日時:<fmt:formatDate value="${post.insertdate}" pattern="yyyy/MM/dd HH:mm:ss" /></div>
		</div>

		<!-- コメント投稿 -->
		<form action="newComment" method="post">
			<label for="commentText">コメント</label>
			<input name="commentText" id="commentText"value="${commentText}" />
			<input type="hidden" name="postId" id="postId" value="${post.id}" />
			<input type="submit" value="コメントする" />
		</form>
	</c:forEach>
</div>
<div class="copyright">Copyright(c)Shintaro Nishikawa</div>

</body>
</html>