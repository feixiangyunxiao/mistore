document.writeln("    <style>");
document.writeln("        .firstType{");
document.writeln("");
document.writeln("        }");
document.writeln("    </style>");
document.writeln(" <div id=\'top\'>");
document.writeln("    	<div id=\'topdiv\'>");
document.writeln("            <span>");
document.writeln("                <a href=\'index.jsp\' class=\'a_top\' target=\'_blank\'>锋米之家</a>");
document.writeln("                <li>|</li>");
document.writeln("                <a href=\'\' class=\'a_top\'>锋米之家移动版</a>");
document.writeln("                <li>|</li>");
document.writeln("                <a href=\'\' class=\'a_top\'>问题反馈</a>");
document.writeln("            </span>");
document.writeln("            <span style=\'float:right\' id=\'isLogin\'>");
document.writeln("                    <div id=\'notLogin\' >");
document.writeln("	                <a href=\'login.jsp\' class=\'a_top\'>登录</a>");
document.writeln("	                <li>|</li>");
document.writeln("	                <a href=\'register.jsp\' class=\'a_top\'>注册</a>");
document.writeln("                    </div>");
document.writeln("                    <li>|</li>");
document.writeln("       			<div id=\'logined\' hidden=\'hidden\'>");
document.writeln("                    <a href=\'userAddress?flag=show\' id=\'userInfo\' class=\'a_top\'></a>");
document.writeln("       				<li>|</li>");
document.writeln("       				<a href=\'loginout.do\' class=\'a_top\'>注销</a>");
document.writeln("       				<li>|</li>");
document.writeln("                </div>");
document.writeln("       				<a href=\'showAllOrderList.do\' class=\'a_top\'>我的订单</a>");
document.writeln("                <li>|</li>");
document.writeln("                <a href=\'\' class=\'a_top\'>消息通知</a>");
document.writeln("                <li>|</li>");
document.writeln("                <a href=\'self_info.jsp\' class=\'a_top\'>个人中心</a>");
document.writeln("                <a href=\'/showCart.do\' id=\'shorpcar\'>购物车</a>");
document.writeln("            </span>");
document.writeln("        </div>");
document.writeln(" </div>");
document.writeln("<div id=\'second\'>");
document.writeln("    <a href=\'\' class=\'seimg\' style=\' margin-top:23px;\'><img id=\'logo\' src=\'image/logo_top.png\' width=\'55\' height=\'54\'/></a>");
document.writeln("    <a href=\'\' class=\'seimg\' style=\' margin-top:17px;\'><img id=\'gif\' src=\'image/yyymix.gif\' width=\'180\' height=\'66\' /></a>");
document.writeln("    <div id=\'goodsType\'>");
document.writeln("        <!-- 根据ajax 回调函数 填写数据 到此id中 -->");
document.writeln("    </div>");
document.writeln("    <form id=\'form1\' action=\'showGoodsByType.do\' method=\'get\'>");
document.writeln("        <input type=\'hidden\' name = \'tid\'>");
document.writeln("    </form>");
document.writeln("    <form class=\'form-inline pull-right\' style=\'margin-top: 40px;margin-right: 10px;\'>");
document.writeln("");
document.writeln("        <div class=\'form-group\'>");
document.writeln("            <input type=\'text\' class=\'form-control\' style=\'width: 400px\'  placeholder=\'搜索一下好东西...\'>");
document.writeln("        </div>");
document.writeln("        <button type=\'submit\' class=\'btn btn-warning\'><span class=\'glyphicon glyphicon-search\'></span>&nbsp;&nbsp;搜索</button>");
document.writeln("    </form>");
document.writeln("</div>");
init();
function init() {
        // 判断是否是已经登录的用户
        $.ajax({
            type:"get",
            async:true,
            url:"isLogin.do",
            success:function(data) {
                if (data.code == 1000) {
                    $("#logined").attr("hidden",false);
                    $("#notLogin").attr("hidden",true);
                    $("#userInfo").text(data.data[0].name);

                } else {
                    $("#logined").attr("hidden",true);
                    $("#notLogin").attr("hidden",false);
                }
            }
        })
        function getLength(obj){

            return Object.key(obj).length;
        }
        // 查询所有的商品的详情
        $.ajax({
            type:"get",
            async:true,
            url:"showAllGoodTypes.do",
            success:function(data) {
                //查询出所有的类型表，然后进行展示
                var goodsTypeList = data.data;

                // 代表了一级的类型有哪些，存储了{1，数码}
                var firstType = {};
                var j = 0;
                // 代表了一级商品的id{0，1}
                var typeMap = {};

                for (i = 0; i<goodsTypeList.length;i++) {

                    if (goodsTypeList[i].level == 1) {
                        firstType[goodsTypeList[i].id]=goodsTypeList[i].name;
                        typeMap[j] = goodsTypeList[i].id;
                        j++;
                    }

                }
                j=0;
                for (i = 0; i<Object.getOwnPropertyNames(firstType).length;i++) {

                    var a = $("<a class='type1'  style='cursor:pointer' >"+firstType[typeMap[i]]+"</a>");
                    var ul = $("<ul>")
                    for (k = 0; k <goodsTypeList.length; k++){
                        if (goodsTypeList[k].parentid == typeMap[j]) {
                            var li = $("<li class='firstType' style='cursor:pointer'>");
                            li.attr("name",goodsTypeList[k].id);
                            li.text(goodsTypeList[k].name);
                            ul.append(li);
                        }
                    }
                    var div = $("<div style='float: left;width: auto;margin-right: 10px' >");
                    div.append(a);
                    div.append(ul);
                    $("#goodsType").append(div);
                    j++;
                }
            }
        })
        $(".firstType").live("click",function(){
            var tid = $(this).attr("name");
            $("[name='tid']").val(tid);
            $("#form1").submit();
        });


        $(".type1").live("click",function(){
            if ($(this).next().attr("hidden")) {
                $(this).next().attr("hidden",false);
            } else {
                $(this).next().attr("hidden",true);
            }

        });

}