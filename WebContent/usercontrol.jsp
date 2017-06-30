<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>ユーザー管理</title>
	<link href="./css/style.css" rel="stylesheet" type="text/css">
</head>
<body>

<h1>ユーザー管理画面</h1>

<div class="main-contents">

<div class="header">
	<a href="./">ホーム</a>
	<a href="signup">ユーザー新規登録</a>
	<a href="useredit">ユーザー編集</a>
	<c:out value="${loginUser.name}" />
	<a href="logout">ログアウト</a>
	<br />
</div>
</div>
<div class="copyright">Copyright(c)Shintaro Nishikawa</div>
</body>
</html>