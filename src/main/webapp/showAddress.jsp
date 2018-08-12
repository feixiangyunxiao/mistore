<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2018/8/9 0009
  Time: 22:27
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8"%>
<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" type="text/css" href="css/login2.css">
    <style>
        .firstType{
        }
    </style>
    <link href="css/bootstrap.min.css" rel="stylesheet">
    <script type="text/javascript" src="js/jquery.min.js"></script>
    <script type="text/javascript" src="js/jquery-1.8.3.js"></script>
    <script type="text/javascript" src="js/bootstrap.js"></script>
</head>
<body>
<script type="application/javascript" src="js/header.js"></script>
<div id="big_right" style="height: 500px;">
    <div style="margin:0 20px;">
        <h3>收货地址</h3>
        <hr>
        <table class="table table-striped table-hover table-bordFered">
            <tr>
                <th>序号</th><th>收件人</th>
                <th>手机号</th><th>地址</th>
                <th>操作</th>
            </tr>
            <c:forEach var="one" items="${address}" varStatus="i" >
                <tr>
                    <Td>${i.count}</Td>
                    <td>${one.name}</td>
                    <td>${one.phone}</td>
                    <td>${one.detail}</td>
                    <td>
                        <button onclick="deleteAddr(${one.id})" class="btn btn-danger btn-sm">删除</button>&nbsp;&nbsp;
                        <button class="btn btn-default btn-sm" data-toggle="modal" data-target="#myModal${one.id}">修改</button>&nbsp;&nbsp;
                        <!-- 弹出模态框 -->

                        <div class="modal fade" tabindex="-1" role="dialog" id="myModal${one.id}">
                            <div class="modal-dialog" role="document">
                                <div class="modal-content">
                                    <div class="modal-header">
                                        <button type="button" class="close" data-dismiss="modal">
                                            <span>&times;</span>
                                        </button>
                                        <h4 class="modal-title">修改地址</h4>
                                    </div>
                                    <form action="userAddress?flag=update" method="post" class="form-horizontal">
                                        <div class="motal-body">
                                            <div class="form-group">
                                                <label class="col-sm-2 control-label">收件人</label>
                                                <div class="col-sm-10">
                                                    <input type="hidden" name="id" value="${one.id}">
                                                    <input type="hidden" name="level" value="${one.orderlevel}">
                                                    <input type="text" name="name" class="form-control" value="${one.name}">
                                                </div>
                                            </div>
                                            <div class="form-group">
                                                <label class="col-sm-2 control-label">电话</label>
                                                <div class="col-sm-10">
                                                    <input type="text" name="phone" class="form-control" value="${one.phone}">
                                                </div>
                                            </div>
                                            <div class="form-group">
                                                <label class="col-sm-2 control-label">收件人</label>
                                                <div class="col-sm-10">
                                                    <input type="text" name="detail" class="form-control" value="${one.detail}">
                                                </div>
                                            </div>

                                        </div>
                                        <div class="motal-footer">
                                            <button type="submit" class="btn btn-primary">修改</button>
                                        </div>
                                    </form>
                                </div>
                            </div>
                        </div>
                        <button onclick="defaultAddr(${one.id})" class="btn btn-primary btn-sm">设为默认</button>
                        <c:if test="${one.orderlevel==1}">
                            <span class="badge" style="background-color:red;">默认</span>
                        </c:if>
                        <c:if test="${one.orderlevel==0}">
                            <span class="badge">普通</span>
                        </c:if>
                        <input type="radio" name="selectAid" id="${one.id}">
                    </td>
                </tr>
            </c:forEach>
        </table>
    </div>
    <div style="clear: both"></div>
    <a href="self_info.jsp" class="btn  btn-danger btn-lg" style="float: left;margin-left: 20%">返回列表</a>
    <a href="addAddress.jsp" class="btn  btn-danger btn-lg" style="float: right;margin-right: 20%">添加收货地址</a>

</div>
<div style="clear: both;"></div>
<script type="application/javascript" src="js/footer.js"></script>
</body>

</html>
