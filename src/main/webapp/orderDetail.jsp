<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>订单详情页</title>
	<link rel="stylesheet" type="text/css" href="css/login2.css">
	<style>
		.firstType{
		}
	</style>
	<link href="css/bootstrap.min.css" rel="stylesheet">
	<script type="text/javascript" src="js/jquery.min.js"></script>
	<script type="text/javascript" src="js/jquery-1.8.3.js"></script>
	<script type="text/javascript" src="js/bootstrap.js"></script>
<script type="text/javascript" src="js/jquery.min.js"></script>
<script type="text/javascript">
	function pay(orderId,money){
		location.href="pay.jsp?oid="+orderId+"&omoney="+money;
	}
	function payWeiXin(orderId,money){
		location.href="pay.jsp?oid="+orderId+"&money="+money;
	}
</script>
</head>
<body>
<script type="application/javascript" src="js/header.js"></script>
<div class="panel panel-default" style="margin: 0 auto;width: 95%;">
	<div class="panel-heading">
	    <h3 class="panel-title"><span class="glyphicon glyphicon-equalizer"></span>&nbsp;&nbsp;订单详情</h3>
	</div>
	<div class="panel-body">
	<table cellpadding="0" cellspacing="0" align="center" width="100%" class="table table-striped table-bordered table-hover">

		<tr>
			<td>订单编号:</td>
			<td>${od.id}</td>
			<td>订单时间:</td>
			<td>
				<fmt:formatDate value="${od.creattime}" pattern="yyyy-MM-dd"/>
					</td>
		</tr>
		<tr>
			<td>收件人:</td>
			<td>${od.recAddress.name}</td>
			<td>联系电话:</td>
			<td>${od.recAddress.phone}</td>
		</tr>
		<tr>
			<td>送货地址:</td>
			<td>${od.recAddress.detail}</td>
			<td>总价:</td>
			<td>${od.money}</td>
		</tr>
		<tr>
			<td align="center">商品列表:</td>
			<td colspan="3">
				<table align="center" cellpadding="0" cellspacing="0" width="100%"  class="table table-striped table-bordered table-hover">
					<tr align="center"  class="info">
						<th>序号</th>
						<th>商品封面</th>
						<th>商品名称</th>
						<th>商品评分</th>
						<th>商品日期</th>
						<th>商品单价</th>
						<th>购买数量</th>
						<th>小计</th>
					</tr>

					<c:forEach items="${od.goods}" var="item" varStatus="i">
						<tr align="center">
							<th>${i.count}</th>
							<th>
								<img src="${item.flag == 3?"":"/mistore/photo"}/${item.photo}" width="50px" height="50px">
							</th>
							<th>${item.name}</th>
							<th>${item.score}</th>
							<th>
									<fmt:formatDate value="${item.puttime}" pattern="yyyy-MM-dd"/>
							</th>
							<th>${item.price}</th>
							<c:if test="${fn:length(od.counts) == 1}">
								<th>${od.counts[0].count}</th>
							</c:if>
							<c:if test="${fn:length(od.counts) != 1}">
								<th>${od.counts[i.count-1].count}</th>
							</c:if>
							<c:if test="${fn:length(od.counts) == 1}">
								<th>${item.price * od.counts[0].count}</th>
							</c:if>
							<c:if test="${fn:length(od.counts) != 1}">
								<th>${item.price * od.counts[i.count-1].count}</th>
							</c:if>

						</tr>
					</c:forEach>
				</table>
			</td>
		</tr>
		<tr>
			<td align="right" colspan="4" style="margin-right: 40px;">
				<a href="${pageContext.request.contextPath }/showAllOrderList.do" class="btn btn-danger btn-sm">返回订单列表</a>
				&nbsp;&nbsp;
				<c:if test="${od.flag == 0}">
					<button type="button" onclick="pay('${od.id}','${od.money}')" class="btn btn-warning btn-sm">易付宝支付</button>
						&nbsp;&nbsp;
					<button type="button" onclick="payWeiXin('${od.id}','${od.money}')" class="btn btn-success btn-sm">微信支付</button>
				</c:if>
			</td>
		</tr>
	</table>
	</div>
</div>
<script type="application/javascript" src="js/footer.js"></script>
</body>
</html>