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

<div class="header">
	ログイン中:<c:out value="${loginUser.name}" />
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

<h1 class="main"><font color="white">ユーザー管理</font></h1>

<div class="users">

	<table align="center">
		<tr>
			<th>名前</th>
			<th>ログインID</th>
			<th>支店名</th>
			<th>役職</th>
			<th>編集</th>
			<th>復活･停止</th>
		</tr>

		<c:forEach items="${userList }" var="user">

		<tr>

				<td><div class="name"><c:out value="${user.name}"/></div></td>
				<td><div class="loginId"><c:out value="${user.loginId}" /></div></td>
				<td><div class="branch">
					<c:forEach items="${branches}" var="branch">
						<c:if test="${branch.id == user.branchId}">
							<c:out value="${branch.name}" /><br/>
						</c:if>
					</c:forEach>
				</div></td>
				<td><div class="possition">
					<c:forEach items="${possitions}" var="possition">
						<c:if test="${possition.id == user.possitionId }">
							<c:out value="${possition.name}" />
						</c:if>
					</c:forEach>
				</div></td>

				<!-- 編集するユーザーIdを渡す -->
				<td><form action="useredit" method="get">
				<input type="hidden" name="editUserId" value="${user.id}" />
				<input type="submit" value="編集" />
				</form></td>

				<!-- ユーザーの停止･復活 -->
					<td><div class ="stop">
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
					</div></td>
			</tr>
		</c:forEach>
	</table>
</div>



<div class="copyright">Copyright(c)Shintaro Nishikawa</div>
</body>
</html>