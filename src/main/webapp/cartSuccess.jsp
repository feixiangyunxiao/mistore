<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
	<link rel="stylesheet" type="text/css" href="css/login2.css">

	<style>
		.firstType{
		}
	</style>
	<link href="css/bootstrap.min.css" rel="stylesheet">
	<script type="text/javascript" src="js/jquery.min.js"></script>
	<script type="text/javascript" src="js/jquery-1.8.3.js"></script>
	<script type="text/javascript" src="js/bootstrap.js"></script>
<title>购物车</title>
</head>
<body>
<script type="application/javascript" src="${pageContext.request.contextPath}/js/header.js"></script>

<div class="container">
<hr>
	<div class="row" style="width: 30%;margin: 0 auto;padding-top: 20px">
		<div class="panel panel-success">
			<div class="panel-heading">
			    <h3 class="panel-title">购物车提示</h3>
			</div>
			<div class="panel-body">
			    <h3 class="text-default"><span class="glyphicon glyphicon-ok-sign"></span>&nbsp;&nbsp;&nbsp;&nbsp;添加购物车成功!!</h3>
				<hr>
				<a href="${pageContext.request.contextPath}/showCart.do" class="btn btn-primary">查看购物车</a>&nbsp;&nbsp;&nbsp;&nbsp;
				<a href="showGoodsByType.do?tid=<%=request.getParameter("tid")%>" class="btn btn-default">继续购物</a>
			</div>
		</div>
	</div>
	
</div>

<%@ include file="footer.jsp" %>
</body>
</html>