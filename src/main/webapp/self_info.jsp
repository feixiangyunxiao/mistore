<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<link rel="stylesheet" type="text/css" href="css/login2.css">
<link rel="stylesheet" href="js/bootstrap.min.css" />
<script src="js/jquery.min.js"></script>
<script src="js/bootstrap.min.js"></script>
	<script type="text/javascript" src="js/jquery-1.8.3.js"></script>
<title>个人中心-收货地址页面</title>
<script type="text/javascript">
	function deleteAddr(id){
		var res = confirm("是否删除");
		if(res==true){
			window.location.href="http://localhost:8080/xiaomi/userAddress?flag=delete&id="+id;
		}
	}
	function defaultAddr(id){
		var res = confirm("是否设为默认");
		if(res==true){
			window.location.href="http://localhost:8080/xiaomi/userAddress?flag=default&id="+id;
			
		}
	}
</script>
</head>
<body>
<script type="application/javascript" src="js/header.js"></script>
<!--网站中间内容开始-->
<div style="clear: both"></div>
<div id="dingdanxiangqing_body" >
    <div id="dingdanxiangqing_body_big" style="float: left;">
        <div id="big_left">
           	   <p style="font-size:18px;margin-top: 15px">订单中心</p>
               <a id="big_left_a" href="dingdanxiangqing.html">我的订单</a><br/>
               <a id="big_left_a" href="">意外保</a><br/>
               <a id="big_left_a" href="">团购订单</a><br/>
               <a id="big_left_a" href="">评价晒单</a><br/>
               <p style="font-size:18px">个人中心</p>
               <a id="big_left_a" href="self_info.html">我的个人中心</a><br/>
               <a id="big_left_a" href="">消息通知</a><br/>
               <a id="big_left_a" href="">优惠券</a><br/>
               <a id="big_left_a" href="/showAllAddress.do">收货地址</a><br/>
        </div>

    </div>
</div>
	
<!-- 底部 -->
<%@ include file="footer.jsp"%>

</body>
</html>