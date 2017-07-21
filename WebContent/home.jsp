<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page isELIgnored="false"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<!DOCTYPE html >
<html>
<!-- 多重クリック対策 -->
<script>
var set=0;
function double() {
if(set==0){ set=1; } else {
alert("只今処理中です。\nそのままお待ちください。");
return false; }}
</script>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>ホーム</title>
<link href="./css/sukeruton.css" rel="stylesheet" type="text/css">

</head>

<h1 class="main"><font color="white">わったい菜BBSSystem</font></h1>

<div class="header">
	<c:out value="${loginUser.name}" />
	<a href="./">ホーム</a> <a href="newpost">新規投稿</a> <a href="usercontrol">ユーザー管理</a>
	<a href="logout">ログアウト</a> <br />
</div>

<body>


	<c:if test="${ not empty errorMessages }">
		<div class="errorMessages">
			<ul>
				<c:forEach items="${errorMessages}" var="message">
					<li><c:out value="${message}" />
				</c:forEach>
			</ul>
		</div>
		<c:remove var="errorMessages" scope="session" />
	</c:if>


	<!-- 絞込み検索の実装 -->

	<form action="index.jsp" method="get">
		<div class="search">
			<label for="searchCategory">絞込み検索機能</label>
			<select name="category">
				<option value="">カテゴリーを選択して下さい</option>
				<c:forEach items="${categories}" var="category">
					<option value="${category}" <c:if test="${category == searchcategory}">selected</c:if>>
					<c:out value="${category}"></c:out></option>
				</c:forEach>
			</select>
		</div>
		<div class="seachDate">
			<label>日付 <input type="date" name="startdate"
			value="${startdate}"></label>
			<input type="date" name="enddate" value="${enddate }">
			<input type="submit" value="検索">
		</div>
	</form>

<div class="main-contents">

		<div class="posts">
		<!--投稿表示-->
		<c:forEach items="${userposts}" var="userposts">
			<div class="title">件名:<c:out value="${userposts.title}" /></div>
			<div class="text">本文:
				<c:forEach var="str" items="${fn:split(userposts.text,'
')}" >		<c:out value="${str}" /><br></c:forEach></div>
				<div class="category">カテゴリー:<c:out value="${userposts.category}" /></div>
				<div class="user">投稿者 : <c:out value="${userposts.name}" /></div>
				<div class="insertdate">投稿日時:<c:out value="${userposts.insertdate}"/></div>

			<!-- 新規投稿削除 -->
			<form action="postDelete" method="post">
				<input type="hidden" name="postId" id="postId" value="${userposts.id }" />
				<c:choose>
					<c:when test="${userposts.userId==loginUser.id }">
						<input type="submit" value="投稿削除" /></c:when>
					<c:when test="${loginUser.branchId==1 && loginUser.possitionId==2}">
						<input type="submit" value="投稿削除" /></c:when>
					<c:when test="${loginUser.possitionId == 3 && userposts.possitionId == 4 && loginUser.branchId == userposts.branchId}">
						<input type="submit" value="投稿削除" /></c:when>
					</c:choose>
			</form>

			<!-- コメント投稿 -->
			<form action="newComment" method="post" onSubmit="return double()">
				<label for="text">コメント</label>
				<textarea name="text" id="text" cols="50" rows="20" id="text" maxlength="500"><c:out value="${text}" /></textarea> <br />
				<input type="hidden" name="postId"  value="${userposts.id}" />
				<input type="submit" value="コメントする" />
			</form>

			<!-- コメントを表示する -->
			<c:forEach items="${commentList }" var="comment">
				<c:if test="${ userposts.id == comment.postId }">
						投稿者 : <c:out value="${userposts.name}" /><br />
					<div class="text">本文:
					<c:forEach var="str" items="${fn:split(comment.text,'
 	')}" >			<c:out value="${str}" /><br></c:forEach></div>
					<div class="date">投稿日時 :
					<fmt:formatDate value="${comment.insertdate}" pattern="yyyy/MM/dd HH:mm:ss" /><br /></div>

					<!-- コメントの削除 -->
					<c:if test="${loginUser.possitionId == 2|| comment.userId ==loginUser.id
						||loginUser.possitionId == 3 && loginUser.branchId == comment.branchId}">

						<form action="commentDelete" method="post">
						<input type="hidden" name="commentId" id="commentId" value="${comment.id}" />
						<input type="submit" value="コメント削除" />
						</form>
					</c:if>
				</c:if>
			</c:forEach>

		</c:forEach>
	</div>
</div>

<div class="copyright">Copyright(c)Shintaro Nishikawa</div>
</body>
</html>