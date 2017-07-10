<%@page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page isELIgnored="false"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>ユーザー管理</title>
	<link href="./css/style.css" rel="stylesheet" type="text/css">
</head>
<body>

<h1>ユーザー管理画面</h1>
<div class="header">
	<c:out value="${loginUser.name}" />
	<a href="./">ホーム</a>
	<a href="signup">ユーザー新規登録</a>
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


<!-- DBのユーザーリストを全て表示する -->

	<div class="users">
		<c:forEach items="${userList }" var="user">
			<div class="name">名前:<c:out value="${user.name}"/></div>
			<div class="loginId">ログインID:<c:out value="${user.loginId}" /></div>
			<div class="branch">
				<c:forEach items="${branches}" var="branch">
					<c:if test="${branch.id == user.branchId}">
						支店:<c:out value="${branch.name}" /><br/>
					</c:if>
				</c:forEach>
			</div>
			<div class="possition">
				<c:forEach items="${possitions}" var="possition">
					<c:if test="${possition.id == user.possitionId }">
						役職:<c:out value="${possition.name}" />
					</c:if>
				</c:forEach>
			</div>

<!-- 編集するユーザーIdを渡す -->
			<form action="useredit" method="get">
			<button type="submit" name="useredit?id=1?" value="${user.id}">編集</button>
			</form>
		</c:forEach>
	</div>


<div class="copyright">Copyright(c)Shintaro Nishikawa</div>
</body>
</html>