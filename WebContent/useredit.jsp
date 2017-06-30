<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>ユーザー編集</title>
	<link href="./css/style.css" rel="stylesheet" type="text/css">
</head>
<body>
<h1>ユーザー編集画面</h1>

<div class="main-contents">

<div class="header">
	<a href="./">ホーム</a>
	<a href="usercontrol">戻る</a>

	<c:out value="${loginUser.name}" />
	<a href="logout">ログアウト</a>
</div>
</div>
<div class="copyright">Copyright(c)Shintaro Nishikawa</div>
</body>
</html>