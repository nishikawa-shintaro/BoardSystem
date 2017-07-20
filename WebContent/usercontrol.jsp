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
	<link href="./css/user.css" rel="stylesheet" type="text/css">
</head>
<body>

<script type="text/javascript">
	function Check(){
		if(window.confirm("本当によろしいですか？")){
		location.href = "./usercontrol"
		return true;
		}
	else{
	}
	window.alert("キャンセルしました")
	return false;
	}
</script>
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
		<input type="hidden" name="editUserId" value="${user.id}" />
		<input type="submit" value="編集" />
		</form>


		<!-- ユーザーの停止･復活 -->
		<div class ="stop">
			<c:if test="${loginUser.id != user.id }">
				<form action="stop" method="post" onSubmit="return Check()">
					<input type="hidden" name="editUserId"  value="${user.id}" />
					<c:if test="${user.isStopped == 1 }">
							<input type="submit" value="復活" />
							<input type="hidden" name="isStopped" value="0"/>
						</c:if>
					<c:if test="${user.isStopped == 0 }">
						<input type="submit" value="停止" />
						<input type="hidden" name="isStopped" value="1"/>
					</c:if>
				</form>
			</c:if>
		</div>

		<!-- ユーザーの削除 -->
		<div class="delete">
			<c:if test="${loginUser.id != user.id }">
				<form action="delete" method="get">
				<input type="hidden" name="editUserId" value="${user.id }"/>
				<input type="submit" value="削除" />
				</form>
			</c:if>
		</div>

	</c:forEach>
</div>



<div class="copyright">Copyright(c)Shintaro Nishikawa</div>
</body>
</html>