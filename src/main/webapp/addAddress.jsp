<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2018/8/9 0009
  Time: 22:41
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
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
    <script type="text/javascript">
        $(function() {
            // 显示省
            $.ajax({
                type:"get",
                url:"showProvince.do",
                async:true,
                success:function(data) {
                    var resultList = data.data;
                    $("#province option").not(":first").remove();
                    for (i = 0; i < resultList.length; i++) {
                        $("#province").append(new Option(resultList[i].name,resultList[i].id));
                    }
                }
            })

            // 显示市
            $("#province").change(function () {
                var pid = $("#province").val();
                $("#country option").not(":first").remove();
                $.ajax({
                    type:"get",
                    url:"showCity.do?pid="+pid,
                    async:true,
                    success:function(data) {
                        var resultList = data.data;
                        $("#city option").not(":first").remove();
                        for (i = 0; i < resultList.length; i++) {

                            $("#city").append(new Option(resultList[i].name,resultList[i].id));
                        }

                    }
                })
            })

            // 显示县/区
            $("#city").change(function () {
                var cid = $("#city").val();
                $.ajax({
                    type:"get",
                    url:"showCountry.do?cid="+ cid,
                    async:true,
                    success:function(data) {
                        var resultList = data.data;

                        $("#country option").not(":first").remove();
                        for (i = 0; i < resultList.length; i++) {
                            $("#country").append(new Option(resultList[i].name,resultList[i].id));
                        }

                    }
                })
            })
            $("#back").click(function() {
                var todo = window.location.search.split("=")[1]
                alert(todo);
                if (todo == "dingdan") {
                    window.location.href = "backToOrder.do";
                } else {
                    window.location.href = "/showAllAddress.do";
                }

            })
            // 设置表单提交事件
            $("#submit").click(function() {
                var name = $("[name='name']").val();
                var phone = $("[name='phone']").val();
                var pid = $("#province").val();
                var cityid = $("#city").val();
                var countryid = $("#country").val();
                var detail = $("#province option:selected").text() + $("#city option:selected").text() + $("#country option:selected").text() + $("[name='detail']").val();
                var orderlevel = 2;

                $.ajax({
                    type:"post",
                    url:"addNewAddress.do",
                    data:{
                        name:name,
                        phone : phone,
                        pid : pid,
                        cityid : cityid,
                        countryid : countryid,
                        detail : detail,
                        orderlevel : orderlevel
                    },
                    async:true,
                    success:function(data) {
                        var result = data.code;
                        if (result == 3000) {
                            alert("添加成功！")
                        } else {
                            alert("添加失败！")
                        }
                    }
                })
            })
        })
    </script>
</head>
<body>
<script type="application/javascript" src="js/header.js"></script>
<div style="clear: both"></div>
<br>
<br>
<div class="container" style="width:960px;">
    <form action="userAddress?flag=add" method="post" class="form-horizontal">
        <div class="form-group">
            <label class="col-sm-2 form-label">收件人</label>
            <div class="col-sm-3">
                <input type="text" class="form-control" name="name"/>
            </div>
        </div>
        <div class="form-group">
            <label class="col-sm-2 form-label">手机号</label>
            <div class="col-sm-3">
                <input type="text" class="form-control" name="phone"/>
            </div>
        </div>
        <select id="province" name="province" class="form-control" style="width: 30%;">
            <option value="-1" >--请选择省份--</option>
        </select>
        <select id="city" name="city" class="form-control" style="width: 30%;">
            <option value="-1">--请选择城市--</option>
        </select>
        <select id="country" name="country" class="form-control" style="width: 30%;">
            <option value="-1">--请选择县/区--</option>
        </select>

        <div class="form-group">
            <label class="form-label">详细地址</label>
            <textarea rows="3" class="form-control" name="detail"></textarea>
        </div>
        <div class="form-group col-md-12">
            <input id="back" type="button" class="btn btn-primary" value="返回">
        </div>
        <div class="form-group col-md-12">
            <input id="submit" type="button" class="btn btn-primary" value="添加地址">
        </div>

    </form>
</div>
<script type="application/javascript" src="js/footer.js"></script>
</body>
</html>
