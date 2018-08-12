<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/login2.css">
	<link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet">
	<style>
		.firstType{
		}
	</style>
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-1.8.3.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/bootstrap.js"></script>
<title>商品详情页</title>

</head>
<body>
<script type="application/javascript" src="js/header.js"></script>
  
    	<div style="margin: 0 auto;width: 90%;">
	   <ol class="breadcrumb">
		  <li><a href="#">锋米之家</a></li>
		  <li><a href="#">手机</a></li>
		  <li class="active"><a href="getGoodsListByTypeId?typeid="></a></li>
	   </ol>
   </div>
   
	<div class="container">
		<div class="row">
		  <div class="col-xs-6 col-md-6">
		    <a href="#" class="thumbnail">
		      <img src="${goods[0].flag == 3?"":"/mistore/photo"}/${goods[0].photo}"  style="width: 200px; height: 300px;" alt="${goods[0].name}" />
		    </a>
		  </div>
		  <div class="col-xs-6 col-md-6">
		   	<div class="panel panel-default" style="height: 560px">
			  <div class="panel-heading">商品详情</div>
			  <div class="panel-body">
			    <h3>产品名称:<small>${goods[0].name}</small></h3>
			    <div style="margin-left: 10px;">
				    
				   <p>市场价格:&nbsp;&nbsp;&nbsp;<span class="text-danger" style="font-size: 15px;">${goods[0].price}</span>&nbsp;&nbsp;&nbsp;<span class="glyphicon glyphicon-yen"></span></p>
				    <p>上市时间:&nbsp;&nbsp;&nbsp;${goods[0].puttime}</p>
				     <p>热销指数:&nbsp;&nbsp;&nbsp;
						<c:forEach begin="1" end="${goods[0].score}">
				        		<img src="${pageContext.request.contextPath}/image/star_red.gif" alt="star"/>
				        	</c:forEach>
					</p>
				    <p>详细介绍:</p>
				    <p>&nbsp;&nbsp;${goods[0].describe }</p>
				    <a href="/addGoodsToCart.do?gid=${goods[0].id}" class="btn btn-warning">加入购物车&nbsp;&nbsp;&nbsp;<span class="glyphicon glyphicon-shopping-cart"></span></a>&nbsp;&nbsp;&nbsp;
				    <button class="btn btn-danger">直接购买</button>
			    </div>
			  </div>
			</div>
		  </div>
		</div>
	</div>
   <!-- 底部 -->
   <%@ include file="footer.jsp"%>


</body>
</html>