<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <title>购物车</title>
    <link rel="stylesheet" type="text/css" href="css/login2.css">
    <link href="css/bootstrap.min.css" rel="stylesheet">
    <script type="text/javascript" src="js/jquery.min.js"></script>
    <script type="text/javascript" src="js/jquery-1.8.3.js"></script>
    <script type="text/javascript" src="js/bootstrap.js"></script>

    <script type="text/javascript">

            function pNum(cid, gid, no) {

                $.ajax({
                    url: "updateCartNum.do?cid=" + cid + "&gid=" + gid + "&num=1",
                    method: "get",
                    success: function (data) {
                        if (data.code == 3000)
                            location.href = "showCart.do";
                        else
                            alert("添加失败了");
                    },
                    error: function () {
                        alert("服务器异常");
                    }
                })
            }

            function mNum(cid, gid, no) {
                var num = -1; //数量
                var nums = $("#num_count" + no).val();
                if (Number(nums) <= 1) {
                    if (confirm("确认要删除吗?")) {
                        $.ajax({
                            url:"clearCart.do?gid=" + gid + "&cid=" + cid,
                            method: "get",
                            success: function (data) {
                                if (data.code == 3000)
                                    location.href = "showCart.do";
                                else
                                    alert("添加失败了");
                            },
                            error: function () {
                                alert("服务器异常");
                            }
                        })
                    } else {
                        return;
                    }
                } else {
                    $.ajax({
                        url:"updateCartNum.do?cid=" + cid + "&gid=" + gid + "&num=" + num ,
                        method: "get",
                        success: function (data) {
                            if (data.code == 3000)
                                location.href = "showCart.do";
                            else
                                alert("添加失败了");
                        },
                        error: function () {
                            alert("服务器异常");
                        }
                    })
                }

            }

            function clearCart(gid,cid) {
                if (confirm("确认要删除吗")) {
                    location.href = "clearCart.do?gid=" + gid + "&cid=" + cid;
                }
            }

            $("#all").click(function () {
                $("[type='checkbox']").checked(true);
            })
            $(function () {
                var sum = 0;
                var data = "";
                var gmoney = "";
                var gmoneycount = "";
                $("[type='checkbox']").click(function () {

                    $("[type='checkbox']").each(function() {

                        if ($(this).attr("checked")) {
                            sum = sum + parseInt($(this).attr("price"));
                            data = $(this).attr("id") + data;
                            gmoneycount = $("#num_count" + $(this).parent().attr("id")).attr("title") + gmoneycount;
                            gmoney = $("#gsum" + $(this).parent().attr("id")).text() + gmoney;
                        }
                    })
                    $("#gmoneyOrder").val(gmoney);
                    $("#countToOrder").val(gmoneycount);
                    $("#cidToOrder").val(data);
                    $("#moneyToOrder").val(sum);
                    $("#priceSum").text(sum);

                    sum = 0;
                    data = "";
                    gmoneycount = "";
                    gmoney = "";
                })
            })

    </script>
</head>
<body style="background-color:#f5f5f5">
<script type="application/javascript" src="js/header.js"></script>
<div class="container" style="background-color: white;">
    <div class="row" style="margin-left: 40px">
        <h3>我的购物车<small>温馨提示：产品是否购买成功，以最终下单为准哦，请尽快结算</small></h3>
    </div>
    <div class="row" style="margin-top: 40px;">
        <div class="col-md-10 col-md-offset-1">
            <table class="table table-bordered table-striped table-hover">
                <tr>
                    <th><input type="checkbox" id="all"></th>
                    <th>序号</th>
                    <th>商品名称</th>
                    <th>价格</th>
                    <th>数量</th>
                    <th>小计</th>
                    <th>操作</th>
                </tr>
                <c:set value="0" var="sum"></c:set>

                <c:forEach items="${carts}" var="c" varStatus="i">
                    <c:set var="goodsSum" value="${c.goods.price * c.count}"></c:set>
                    <tr>
                        <th id="${i.count}">
                            <input type="checkbox" id="cid${c.id}" price="${goodsSum}">
                        </th>

                        <th>
                            ${i.count}
                        </th>
                        <th>${c.goods.name}</th>
                        <th>${c.goods.price}</th>
                        <th width="100px">
                            <div class="input-group">
		 						<span class="input-group-btn">
		 							<button class="btn btn-default" type="button" onclick="mNum(${c.cid},${c.goods.id},${i.count})">-</button>
		 						</span>
                                <input type="text" class="form-control" title="count${c.count}" id="num_count${i.count}" value="${c.count}" readonly="readonly" style="width:40px">
                                <span class="input-group-btn">
		 							<button class="btn btn-default" type="button" onclick="pNum(${c.cid},${c.goods.id},${i.count})">+</button>
		 						</span>
                            </div>
                        </th>

                        <th id="gsum${i.count}">￥${goodsSum}</th>
                        <th>
                            <button type="button" class="btn btn-default" onclick="clearCart(${c.goods.id},${c.cid})">删除</button>
                        </th>
                    </tr>
                    <c:set var="sum" value="${sum + goodsSum}"></c:set>
                </c:forEach>
            </table>
        </div>
    </div>
    <hr>
    <div class="row">
        <div class="pull-right" style="margin-right: 40px;">
            <div>
                <a id="removeAllProduct" href="javascript:clearCart(0)" class="btn btn-default btn-lg">清空购物车</a>
                &nbsp;&nbsp;
                <form action="addOrder.do" method="post">
                    <input type="hidden" id="cidToOrder" name="cid"/>
                    <input type="hidden" id="moneyToOrder" name="money" />

                    <input type="hidden" id="countToOrder" name="gcount">
                    <input type="hidden" id="gmoneyOrder" name="gmoney">

                    <input type="submit" class="btn  btn-danger btn-lg" value="去下单" />
                </form>
            </div>
            <br><br>
            <div style="margin-bottom: 20px;">
                商品金额总计：<span id="total" class="text-danger"><b id="priceSum">￥&nbsp;&nbsp;0</b></span>
            </div>
        </div>
    </div>
</div>

<!-- 底部 -->
<script type="application/javascript" src="js/footer.js"></script>
</body>
</html>