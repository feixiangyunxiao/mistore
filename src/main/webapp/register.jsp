<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
		<meta http-equiv="X-UA-Compatible" content="IE=edge">
		<meta name="viewport" content="width=device-width, initial-scale=1">
		<link href="css/bootstrap.min.css" rel="stylesheet">
		<script type="text/javascript" src="js/jquery.min.js"></script>
		
<link rel="stylesheet" type="text/css" href="css/login.css">
	<script type="text/javascript" src="js/jquery-1.8.3.js"></script>
<script type="text/javascript">
	$(function(){
	    var flag1 = false;
	    var flag2 = false;
	    var flag3 = false;
	    // 设置检测是否可以注册
	    function checkToRegist() {
	        if (flag1 && flag2 && flag3) {
	            $("#registerBtn").removeAttr("disabled");
			}
		}
	    // 只有三个条件都满足才能将注册按钮设置为可点击，否则设置为不可点击
	    // 验证用户名是否正确
		$("#username").change(function(){
			//使用ajax 做username 的异步验证 checkUsername?username=xxxx
			$.get("checkUserName.do","username="+this.value,function(data){
				if(data.code==1){
					$("#usernameMsg").html("用户名已经存在").css("color","red");
					flag1 = true;
					checkToRegist();
				}else{
					$("#usernameMsg").html("用户名可用").css("color","green");
					$("#registerBtn").removeAttr("disabled");
					flag1 = false;
				}
			})
		});

		// 验证两次的密码是否一致
		$("[name='password']").change(function(){
            var reg = /^[0-9a-zA-Z]{6,13}$/;

			if (reg.test($("[name='password']").val())) {
				$("#helpBlock1").html("密码可用").css("color","green");
			} else {
                $("#helpBlock1").html("请输入6~13位字母或者数字哦").css("color","red");
			}
		});

		$("#repassword").change(function() {
		    var pass = $("[name='password']").val();
		    var repass = $("#repassword").val();
		    if (pass == repass) {
		        $("#helpBlock2").html("密码可用").css("color","green");
		        flag2 = true;
		        checkToRegist();
			} else {
		        $("#helpBlock2").html("两次输入要一致哦").css("color","red");
		        flag2 = false;
			}
		})

		// 验证邮箱格式是否正确
		$("[name='email']").change(function() {
		    var emailFormat = /^[a-zA-Z0-9_.-]+@[a-zA-Z0-9-]+(\.[a-zA-Z0-9-]+)*\.[a-zA-Z0-9]{2,6}$/;
		    var email = $("[name='email']").val();
		    if (emailFormat.test(email)) {
		        $("#helpBlock3").html("亲，您的邮箱可用");
		        flag3 = true;
		        checkToRegist();
			} else {
                $("#helpBlock3").html("亲，您的邮箱格式不正确哦");
                flag3 = false;
            }
		})
		
		// 设定注册之后的操作
		$("#registerBtn").click(function () {
		    var name = $("[name='username']").val();
		    var password = $("[name='password']").val();
		    var gender = $("[name='gender']").val();
		    var email = $("[name='email']").val();
			$.ajax({
                type:"post",
                async:true,
                url:"regist.do",
				data:{
                    name:name,
					gender:gender,
					password:password,
					email:email
				},
				success:function(data) {
					if (data.code == 1000) {

					    window.location.href = "registerSuccess.jsp";

					} else if (data.code == 1002) {
					    alert("邮箱已被注册");

					} else {
                        alert("注册失败，请重新注册")
					}
				}
			})
        })

	})
</script>
<title>注册</title>
</head>
<body>
	<div class="regist">
		<div class="regist_center">
			<div class="regist_top">
				<div class="left fl"><span class="glyphicon glyphicon-user"></span>&nbsp;&nbsp;会员注册</div>
				<div class="right fr">
					<a href="index.jsp" target="_black">锋米之家</a>
				</div>
				<div class="clear"></div>
				<div class="xian center"></div>
			</div>
			<div class="center-block" style="margin-top: 80px;">
				<form class="form-horizontal" action="/regist.do" method="post">

					<div class="form-group">
						<label class="col-sm-2 control-label">用户名</label>
						<div class="col-sm-8" style="width: 40%">
							<input type="text" id="username" name="username" class="form-control col-sm-10"
								placeholder="Username" />
						</div>
						<div class="col-sm-2">
						<p class="text-danger"><span class="help-block " id="usernameMsg"></span></p>
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-2 control-label">密码</label>
						<div class="col-sm-8" style="width: 40%;">
							<input type="password" name="password"
								class="form-control col-sm-10" placeholder="请输入6~13位字母或者数字" />
						</div>
						<div class="col-sm-2">
						<p class="text-danger"><span id="helpBlock1" class="help-block "></span></p>
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-2 control-label">确认密码</label>
						<div class="col-sm-8" style="width: 40%">
							<input type="password" id="repassword" class="form-control col-sm-10"
								placeholder="Password Again" />
						</div>
						<div class="col-sm-2">
						<p class="text-danger"><span id="helpBlock2" class="help-block "></span></p>
						</div>
					</div>

					<div class="form-group">
						<label class="col-sm-2 control-label">邮箱</label>
						<div class="col-sm-8" style="width: 40%">
							<input type="text" name="email" class="form-control col-sm-10"
								placeholder="Email" />
						</div>
						<div class="col-sm-2">
						<p class="text-danger"><span id="helpBlock3" class="help-block "></span></p>
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-2 control-label">性别</label>
						<div class="col-sm-8" style="width: 40%">
							<label class="radio-inline"> <input type="radio"
								name="gender" value="男" checked> 男
							</label> <label class="radio-inline"> <input type="radio"
								name="gender" value="女" > 女
							</label>
						</div>
						<div class="col-sm-2">
						<p class="text-danger"><span id="helpBlock" class="help-block ">你是帅哥 还是美女</span></p>
						</div>
					</div>
					<hr>
					<div class="form-group">
						<div class="col-sm-7 col-sm-push-2">
							<input id="registerBtn" type="button" value="注册" class="btn btn-primary  btn-lg" style="width: 200px;" disabled/> &nbsp; &nbsp;
							<input type="reset" value="重置" class="btn btn-default  btn-lg" style="width: 200px;"  />
						</div>
					</div>
					<div>${session.registerMsg}</div>
				</form>

			</div>
		</div>
	</div>
	
</body>
</html>