<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/login2.css">
	<link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet">
	<style>
		.firstType{

		}
	</style>
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-1.8.3.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/bootstrap.js"></script>
<title>商品列表页</title>

</head>
<body>

<script type="application/javascript" src="${pageContext.request.contextPath}/js/header.js"></script>
   
<div class="panel panel-default" style="margin: 0 auto;width: 95%;">
	<div class="panel-heading">
	    <h3 class="panel-title"><span class="glyphicon glyphicon-th-list"></span>&nbsp;&nbsp;商品列表</h3>
	</div>
	<div class="panel-body">
	   	   <!--列表开始-->
	    <div class="row" style="margin: 0 auto;">
	    	<c:forEach items="${glist}" var="g" varStatus="i">
		    	<div class="col-sm-3" style="width: 20%;height: 400px;display: block">
				    <div class="thumbnail">
				      <img src="${g.flag == 3?"":"/mistore/photo"}/${g.photo}" style="width: 150px;height: 200px"  alt="小米6" />

				      <div class="caption">
				        <h4>商品名称<a href="showOneGoodsById.do?id=${g.id}">${g.name}</a></h4>
				        <p>热销指数
				        	<c:forEach begin="1" end="${g.score}">
				        		<img src="${pageContext.request.contextPath}/image/yellowstar.jpg" alt="star"/>
				        	</c:forEach>
				        </p>
				         <p>上架日期:${g.puttime}</p>
			             <p style="color:orange">价格:${g.price}</p>
				      </div>
				    </div>
				  </div>
	    	</c:forEach>
			  
		</div>
   	</div>
</div>
      <!-- 底部 -->
<script type="application/javascript" src="js/footer.js"></script>
</body>
</html>