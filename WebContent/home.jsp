<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page isELIgnored="false"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html >
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>ホーム</title>
<link href="css/style.css" rel="stylesheet" type="text/css">

</head>

<h1>わったい菜 掲示板</h1>

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
			<label for="searchCategory">カテゴリー</label>
			<select name="category">
				<option value="">カテゴリーを選択して下さい</option>
				<c:forEach items="${categories}" var="category">
					<option value="${category}" <c:if test="${category == searchcategory}">selected</c:if>>
						<c:out value="${category}">
						</c:out></option>
				</c:forEach>
			</select>
		</div>

		<div class="seachDate">
			<label>日付 <input type="date" name="startdate"
				value="${startdate}"></label> <input type="date" name="enddate"
				value="${enddate }"> <input type="submit" value="検索">

		</div>
	</form>

<div class="main-contents">
		<div class="posts">
			<!--新規投稿表示-->
			<c:forEach items="${posts}" var="post">
				<div class="title">
					件名:
					<c:out value="${post.title}" />
				</div>
				<div class="text">
					本文
					<c:out value="${post.text}" />
				</div>
				<div class="category">
					カテゴリー:
					<c:out value="${post.category}" />
				</div>
				<div class="registrant">
					<c:forEach items="${userList}" var="user">
						<c:if test="${ user.id == post.userId }">
					投稿者 : <c:out value="${user.name}" />
							<br />
						</c:if>
					</c:forEach>
					<div class="insertdate">
						投稿日時:
						<fmt:formatDate value="${post.insertdate}"
							pattern="yyyy/MM/dd HH:mm:ss" />
					</div>
				</div>

				<!-- 新規投稿削除 -->
				<form action="postDelete" method="post">
					<input type="hidden" name="postId" id="postId" value="${post.id }" />
					<c:choose>
						<c:when test="${post.userId==loginUser.id }">
							<input type="submit" value="投稿削除" />
						</c:when>
						<c:when
							test="${loginUser.branchId==1 && loginUser.possitionId==2}">
							<input type="submit" value="投稿削除" />
						</c:when>
						<c:when
							test="${loginUser.possitionId == 3 && post.possitionId == 4 && loginUser.branchId == post.branchId}">
							<input type="submit" value="投稿削除" />
						</c:when>
					</c:choose>
				</form>



				<!-- コメント投稿 -->
				<form action="newComment" method="post">
					<label for="commentText">コメント</label> <input name="commentText"
						id="commentText" value="${commentText}" /> <input type="hidden"
						name="postId" id="postId" value="${post.id}" /> <input
						type="submit" value="コメントする" />
				</form>

				<!-- コメントを表示する -->

				<c:forEach items="${commentList }" var="comment">
					<c:if test="${ post.id == comment.postId }">
						<c:forEach items="${userList}" var="user">
							<c:if test="${ user.id == comment.userId }">
								投稿者 : <c:out value="${user.name}" />
								<br />
							</c:if>
						</c:forEach>
						<div class="text">
							本文
							<c:out value="${comment.text }"></c:out>
						</div>
						<div class="date">
							投稿日時 :
							<fmt:formatDate value="${comment.insertdate}"
								pattern="yyyy/MM/dd HH:mm:ss" />
							<br />
						</div>
					</c:if>


					<!-- コメントの削除 -->
					<form action="commentDelete" method="post">
						<input type="hidden" name="commentId" id="commentId"
							value="${comment.id}" />
						<c:choose>
							<c:when test="${comment.userId == loginUser.id}">
								<input type="submit" value="コメント削除" />
							</c:when>
							<c:when
								test="${loginUser.branchId==1 && loginUser.possitionId == 2}">
								<input type="submit" value="コメント削除" />
							</c:when>
							<c:when
								test="${loginUser.possitionId == 3 && comment.possitionId == 4 && loginUser.branchId == comment.branchId}">
								<input type="submit" value="コメント削除" />
							</c:when>
						</c:choose>
					</form>
				</c:forEach>
			</c:forEach>
		</div>
	</div>

	<div class="copyright">Copyright(c)Shintaro Nishikawa</div>
</body>
</html>