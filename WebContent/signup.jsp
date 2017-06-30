<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>ユーザー新規登録画面</title>
</head>
<body>

<div class="main-contents">
<div class="header">
	<a href="./">ホーム</a>
	<a href="usercontrol">戻る</a>
	<a href="logout">ログアウト</a>
	<br />
</div>

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

<h1>ユーザー新規登録</h1>
<form action="signup" method="post">
	<label for="loginId">ログインID(半角英数字：6文字以上20文字以下)</label><br>
	<input name="login_id" id="login_id" value="${newUser.login_id }"/><br />

	<label for="password">パスワード(半角文字：6文字以上20文字以下)</label><br>
	<input name="password" type="password" id="password"/><br/>

	<label for="password">パスワード(再入力)</label><br>
	<input name="checkPassword" type="password" id="checkPassword"/><br />

	<label for="name">名前(10文字以下)</label><br>
	<input name="name" id="name" value="${newUser.name }"/><br />

</form>
<div class="copyright">Copyright(c)Shintaro Nishikawa</div>
</body>
</html>

