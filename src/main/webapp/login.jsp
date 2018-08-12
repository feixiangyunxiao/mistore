<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<link rel="stylesheet" type="text/css" href="css/login.css">
	<link rel="stylesheet" type="text/css" href="css/verify.css">
<script type="text/javascript" src="js/jquery.min.js"></script>
	<script type="text/javascript" src="js/verify.min.js"></script>
<title>登录</title>
<script type="text/javascript">
	$(function(){
		//1.验证用户名是否存在
		$("#username").change(function(){
			$.get("/checkUserName.do","username="+this.value,function(data){
				if(data.code == 0){
					$("#nameMsg").html("用户名不存在").css("color","red");
				}else{
					$("#nameMsg").html("用户名可用").css("color","white");
				}
			})
		});
		//2.点击验证码 跟新验证码
		$("#pagecode").click(function(){
			$("#pagecode").attr("src","code?"+Math.random());
		});
		//3.验证输入的验证码 是否正确
		$("#vcode").change(function(){
			$.get("checkCode","code="+this.value,function(data){
				if(data==0){
					$("#checkMsg").html("<font color='green'>OK</font>");
					$("#btn").removeAttr("disabled");
				}else{
					$("#checkMsg").html("<font color='red'>ERROR</font>");
					$("#pagecode").attr("src","code?"+Math.random());
					$("#btn").Attr("disabled",true);
				}
			})
		});
		//4.两周以内自动登录  友好提示 
		$("#autoLogin").click(function(){
			if(this.checked){
				$("#autoLoginMsg").html("公司电脑请勿勾选此项").css("color","red");
			}else{
				$("#autoLoginMsg").html("");
			}
		})

        //滑动验证码
        $('#mpanel4').pointsVerify({
            defaultNum : 6, //默认的文字数量
            checkNum : 3, //校对的文字数量
            vSpace : 3, //间隔
            imgName : [ '1.jpg', '2.jpg' ],
            imgSize : {
                width : '250px',
                height : '100px',
            },
            barSize : {
                width : '200px',
                height : '30px',
            },
            ready : function() {
            },
            success : function() {
                //......后续操作
                $("#btn").attr("disabled", false);
            },
            error : function() {
            }
        });

		$("#btn").click(function() {
		    var name = $("[name='username']").val();
		    var password = $("[name='password']").val();
		    $.ajax({
				type:"get",
				url:"login.do?name="+name + "&password="+password,
				async:true,
				success:function(data) {
				    if (data.code == 1000) {
				        window.location.href = "index.jsp";
					} else if(data.code == 1002) {
				      alert("用户未激活，请先去邮箱进行激活");
					} else {
				        alert("用户账户或密码错误");
					}
				}
			})
		})
	})
</script>
</head>
<body>
		<!-- login -->
		<div class="top center">
			<div class="logo center">
				<a href="${pageContext.request.contextPath }/index.jsp" target="_blank"><img src="./image/mistore_logo.png" alt=""></a>
			</div>
		</div>
		<form  method="post" action="//login.do" class="form center" id="userLogin" >
		<div class="login">
			<div class="login_center">
				<div class="login_top">
					<div class="left fl">会员登录</div>
					<div class="right fr">您还不是我们的会员？<a href="${pageContext.request.contextPath }/register.jsp" target="_self">立即注册</a></div>
					<div class="clear"></div>
					<div class="xian center"></div>
				</div>
				<div class="login_main center">
					<div class="username">
						<div class="left fl">用户名:&nbsp;</div>
						<div class="right fl">
						<input class="shurukuang" type="text" name="username" id="username"  placeholder="请输入你的用户名"/>
						<label id="nameMsg"></label>
						</div>
					</div>
					<div class="username">
						<div class="left fl">密&nbsp;&nbsp;&nbsp;&nbsp;码:&nbsp;</div>
						<div class="right fl">
						<input class="shurukuang" type="password" name="password"  id="pwd"  placeholder="请输入你的密码"/>	
						</div>
					</div>
					<div class="username feri-code">
						<div id="mpanel4"></div>
					</div>
					<div class="username">
						<div class="left fl">&nbsp;&nbsp;&nbsp;&nbsp;</div>
						<div class="right fl"><label id="checkMsg"></label></div>
					</div>
					<br>
					<br>

					<div class="username">
						<input id="autoLogin" name="auto" type="checkbox" />&nbsp;&nbsp;两周以内自动登录
						<span id="autoLoginMsg"></span>
					</div>
					<div class="login_submit">
						<input class="submit" type="button" name="submit" value="立即登录" id="btn" disabled >
					</div>
					<span style="color:red">${msg}</span>
				</div>	
			</div>
		</div>
		</form>
		<footer>
			<div class="copyright">简体 | 繁体 | English | 常见问题</div>
			<div class="copyright">小米公司版权所有-京ICP备10046444-<img src="./image/ghs.png" alt="">京公网安备11010802020134号-京ICP证110507号</div>

		</footer>
	</body>
</html>