<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>ユーザー新規登録画面</title>
<link href="css/signup.css" rel="stylesheet" type="text/css">

<script>
var set=0;
function double() {
if(set==0){ set=1; } else {
alert("只今処理中です。\nそのままお待ちください。");
return false; }}
</script>

</head>
<body>

	<div class="header">
		ログイン中:<c:out value="${loginUser.name}" />
		<a href="./">ホーム</a>
		<a href="usercontrol">戻る</a>
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
			<c:remove var="errorMessages" scope="session" />
		</c:if>
	</div>

	<h1 class="main"><font color="white">新規ユーザー登録画面</font></h1>

	<form action="signup" method="post" onSubmit="return double()">
		<label for="loginId">ログインID(半角英数字：6文字以上20文字以下)</label><br> <input
			name="loginId" id="loginId" value="${newUser.loginId }" /><br /> <label
			for="password">パスワード(半角文字：6文字以上20文字以下)</label><br> <input
			name="password" type="password" id="password" /><br /> <label
			for="password">パスワード(再入力)</label><br> <input
			name="checkPassword" type="password" id="checkPassword" /><br /> <label
			for="name">名前(10文字以下)</label><br> <input name="name" id="name"
			value="${newUser.name }" /><br /> <label for="branchId">支店名</label><br>

		<select name="branchId" id="branchId">
			<option value=0><c:out value="選択してください" />
				<c:forEach items="${branches}" var="branch">
					<option value="${branch.id}"
						<c:if test="${ branch.id == user.branchId }">selected</c:if>>
						<c:out value="${branch.name}" />
				</c:forEach>
		</select><br /> <label for="possitionId">部署・役職</label><br> <select
			name="possitionId" id="possitionId">
			<option value=0><c:out value="選択してください" />
				<c:forEach items="${possitions}" var="possition">
					<option value="${possition.id}"
						<c:if test="${ possition.id == user.possitionId }">selected </c:if>>
						<c:out value="${possition.name }" />
				</c:forEach>
		</select> <br /> <br /> <input type="submit" value="登録" />

	</form>
	<div class="copyright">Copyright(c)Shintaro Nishikawa</div>
</body>
</html>

