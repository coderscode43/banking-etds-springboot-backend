
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1  maximum-scale=1">
<meta name="description" content="">
<meta name="author" content="">
<base href="/bankingETDS/" />
<title>Please sign in</title>
<link rel="stylesheet" href="static/css/font-awesome.min.css">
<link href="static/css/bootstrap.min.css" rel="stylesheet">
<link href="static/css/signin.css" rel="stylesheet"
	crossorigin="anonymous" />
<!-- Latest compiled and minified CSS -->
<!-- jQuery library -->
<script src="static/js/jquery.min.js"></script>
<!-- Latest compiled JavaScript -->
<script src="static/js/bootstrap.min.js"></script>
<link rel="shortcut icon" type="image/x-icon"
	href="static/img/favicon.ico" />
<script src="static/js/lib/bootstrap.js"></script>
<style>
form {
	height: 50%;
	margin-left: 5%;
	border-radius: 7px !important;
	box-shadow: rgba(0, 0, 0, 0.07) 0px 1px 2px, rgba(0, 0, 0, 0.07) 0px 2px
		4px, rgba(0, 0, 0, 0.07) 0px 4px 8px, rgba(0, 0, 0, 0.07) 0px 8px 16px,
		rgba(0, 0, 0, 0.07) 0px 16px 32px, rgba(0, 0, 0, 0.07) 0px 32px 64px;
}

.form-signin {
	max-width: 390px;
	background-color: rgba(0, 0, 0, 0.2);
	border: 1px solid rgba(0, 0, 0, 0.1);
}

.checkbox {
	font-weight: normal;
}

.form-control {
	position: relative;
	font-size: 16px;
	height: auto;
	@
	include
	box-sizing(border-box);
	&:
	focus
	{
	z-index
	:
	2;
}

}

/* For taxosmart Logo Start */
@font-face {
	font-family: "untitled-font-2";
	src: url("static/css/fonts/untitled-font-2.eot");
	src: url("static/css/fonts/untitled-font-2.eot?#iefix")
		format("embedded-opentype"),
		url("static/css/fonts/untitled-font-2.woff") format("woff"),
		url("static/css/fonts/untitled-font-2.ttf") format("truetype"),
		url("static/css/fonts/untitled-font-2.svg#untitled-font-2")
		format("svg");
	font-weight: normal;
	font-style: normal;
}

[data-icon]:before {
	font-family: "untitled-font-2" !important;
	content: attr(data-icon);
	font-style: normal !important;
	font-weight: normal !important;
	font-variant: normal !important;
	text-transform: none !important;
	speak: none;
	line-height: 1;
	-webkit-font-smoothing: antialiased;
	-moz-osx-font-smoothing: grayscale;
}

[class^="icon-"]:before, [class*=" icon-"]:before {
	font-family: "untitled-font-2" !important;
	font-style: normal !important;
	font-weight: normal !important;
	font-variant: normal !important;
	text-transform: none !important;
	speak: none;
	line-height: 1;
	-webkit-font-smoothing: antialiased;
	-moz-osx-font-smoothing: grayscale;
}

.icon-tax-o-smart:before {
	content: "\61";
}

.serif {
	font-family: "Times New Roman", Times, serif;
}

.icon-tax-o-smart {
	text-align: center;
}
/* For taxosmart Logo End */
.shortline {
	border: 1px solid #7dc142;
	margin-left: 0px;
}

.mui-card {
	background: white;
	border-radius: 2px;
	display: inline-block;
	height: 300px;
	margin: 1rem;
	position: relative;
	width: 90%;
}

.xs-shadow {
	box-shadow: 0 1px 3px rgba(0, 0, 0, 0.12), 0 1px 2px rgba(0, 0, 0, 0.24);
	transition: all 0.3s cubic-bezier(.25, .8, .25, 1);
}

.xs-shadow:hover {
	box-shadow: 0 14px 28px rgba(0, 0, 0, 0.25), 0 10px 10px
		rgba(0, 0, 0, 0.22);
}

.xxl-shadow {
	box-shadow: 0 19px 38px rgba(0, 0, 0, 0.30), 0 15px 12px
		rgba(0, 0, 0, 0.22);
}

.xxl-shadow:hover {
	box-shadow: 0 14px 28px rgba(0, 0, 0, 0.25), 0 10px 10px
		rgba(0, 0, 0, 0.22);
}

/* //css for blur select box */
select:required:invalid {
	color: gray;
}

option[value=""][disabled] {
	display: none;
}

option {
	color: black;
}
</style>
</head>
<body style="background-color: white">
	<div class="row">
		<div class="container-fluid">
			<div class="col-md-4">
				<img src="static/img/tds.png" alt="tdsosmart" height="400px"
					width="100%">

			</div>
			<br>

			<div class="col-md-4" style="">
				<p style="font-size: 14px">
					<b>&nbsp;&nbsp;&nbsp;&nbsp;TDS Software is an Intelligent TDS
						Return Filing Software built in state-of-the-art technology,
						complying with TDS/TCS prescribed as per Income Tax Laws of India.

					</b>
				</p>
				<br>
				<ul>
					<li style="font-size: 20px; color: green">
						<p style="font-size: 17px; color: black">&nbsp;Supports
							Unlimited Deductees</p>
					</li>
					<li style="font-size: 20px; color: green"><p
							style="font-size: 17px; color: black">&nbsp;Completely
							Automated Features</p></li>
					<li style="font-size: 20px; color: green"><p
							style="font-size: 17px; color: black">&nbsp;Easy to
							Import/Export files From Ms-Excel</p></li>
					<li style="font-size: 20px; color: green"><p
							style="font-size: 17px; color: black">&nbsp;More Than 500
							types of TRACES level Checks</p></li>
					<li style="font-size: 20px; color: green"><p
							style="font-size: 17px; color: black">&nbsp;File Error Free
							TDS Returns in Just Few Minutes</p></li>
				</ul>
			</div>

			<div class="col-md-4" style="">
				<form class="form-signin mui-card xl-shadow" method="post"
					action="login" style="height: 50%; margin-left: 5%">
					<!-- <img src="static/img/KBlogo.png" alt="Karnataka Bank"
						style="width: 100%;"></img> -->
					<!-- <img src="static/img/nialogo.png" alt="New India Assurance"
						style="width: 100%;"></img> -->
					<!-- /////////////UCO BANK//////////// -->
					<img src="static/img/UcoBank.png" alt="Uco Bank"
						style="width: 90%; margin-left: 5%"></img>
					<h3 class="form-signin-heading">
						<center>
							<b style="color: #79BD3C">LOGIN</b>
						</center>
					</h3>

					<p style="color: red;">
						<b><span>${sessionScope["SPRING_SECURITY_LAST_EXCEPTION"].message}</span></b>
					</p>
					<p>
						<label for="fy" class="sr-only">Financial Year</label><select
							id="dropdown" class="form-control" ng-required="true" required>
							<option value="/2022-23/login.jsp">2022-23</option>
							<option value="/login.jsp" Selected>2023-24</option>
						</select>
					</p>
					<br>
					<p>
						<label for="username" class="sr-only">Username</label> <input
							type="text" id="username" name="username" class="form-control"
							placeholder="Username" required autofocus>
					</p>
					<br>
					<p>
						<label for="password" class="sr-only">Password</label> <input
							type="password" id="password" name="password"
							class="form-control" placeholder="Password" required>
					</p>
					<br>
					<button class="btn btn-lg btn-primary btn-block" type="submit"
						style="background-color: #0F316D">Sign in</button>
					<br>
				</form>
			</div>
		</div>
	</div>


	<div class="row">
		<div class="col-md-6" style="margin-top: -35px;">
			<p style="margin-left: 78%; font-size: 18px">Powered By</p>
		</div>
	</div>



	<div class="row">
		<div class="col-md-6" style="margin-top: -5px">
			<a class="icon-cog" style="line-height: 0.9%; text-decoration: none"
				ng-click="cCctr.homepage('homepage')"> <img
				src="static/img/TOS.png" alt="Tax O smart"
				style="width: 40%; margin-left: 88%;"></img></a>
		</div>
	</div>
	<footer>
		<div>
			<p style="Float: right; margin-right: 2%;">Version 1.0</p>
		</div>
	</footer>
	<!-- <script>
		$(document).ready(function() {
			window.history.pushState(null, "", window.location.href);
			window.onpopstate = function() {
				window.history.pushState(null, "", window.location.href);
			};
		});
	</script> -->
	<script type='text/javascript'>
		(function() {
			if (window.localStorage) {
				if (!localStorage.getItem('firstLoad')) {
					localStorage['firstLoad'] = true;
					window.location.reload();
					/* var url = window.location.href;
					window.location.replace(url) */
				} else
					localStorage.removeItem('firstLoad');
			}
		})();
	</script>
	<script>
		document.getElementById("dropdown").addEventListener("change",
				function() {
					var selectedOption = this.value;
					if (selectedOption !== '') {
						/*   window.location.href = selectedOption; */// Redirect to the selected URL
						window.open(selectedOption, '_blank');
					}
				});
	</script>
</body>
</html>




