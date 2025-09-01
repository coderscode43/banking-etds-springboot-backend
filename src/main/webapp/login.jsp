<!DOCTYPE html>
<html lang="en" ng-app="myApp">
<head>
<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1 maximum-scale=1">
<meta name="description" content="">
<meta name="author" content="">
<base href="/" />
<title>Please sign in</title>
<link rel="stylesheet" type="text/css" href="styles.css">
<link rel="stylesheet" href="static/css/font-awesome.min.css">
<link href="static/css/bootstrap.min.css" rel="stylesheet">
<link href="static/css/signin.css" rel="stylesheet"
	crossorigin="anonymous" />
<link rel='stylesheet'
	href='static/js/lib/angular-loading-bar-master/build/loading-bar.min.css'
	type='text/css' media='all' />
<script src="static/js/jquery.min.js"></script>
<script src="static/js/bootstrap.min.js"></script>
<link rel="shortcut icon" type="image/x-icon"
	href="static/img/favicon.ico" />
<script src="static/js/lib/bootstrap.js"></script>
<script src="static/js/lib/angular-http-loader.js"></script>
<script type='text/javascript'
	src="static/assets/js/lib/angular-loading-bar-master/build/loading-bar.min.js"></script>
<script src="assets/cdn/js/jquery.min.js"></script>
<style>
/* Center the body using Flexbox */
body {
	display: flex;
	justify-content: center;
	align-items: center;
	/* height: 100vh; */
	/* margin: 0; */
	background-color: white;
}

/* Form container styling */
.box1 {
	display: flex;
	justify-content: center;
	align-items: center;
	width: 100%;
	height: 100%;
}

.box {
	display: flex;
	flex-direction: row;
	justify-content:center;
	align-items:center;
	width: 500px; /* Fixed width */
	height: auto; /* Fixed height */
	border-radius: 10px;
	padding: 20px;
	box-sizing: border-box;
}

/* .form-signin {
	/* width: 400px; */
	background-color: rgba(0, 0, 0, 0.2);
	border: 1px solid rgba(0, 0, 0, 0.1);
} */

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

.form-signin-heading {
	text-align: center;
	color: #79BD3C;
}

.btn-block {
	background-color: #0F316D;
}

/* Footer styling */
footer {
	margin-top: 20px;
	text-align: right;
}

.checkbox {
	font-weight: normal;
}
</style>
</head>

<body>
	<div class="box1">
		<div class="box">
			<div class="text-center">
				<!-- <img src="static/img/tds.png" alt="tdsosmart" height="400px"
					width="400px"> -->
				<!-- <div class="text-left">
					<p style="font-size: 15px">
						<b>Powered By</b>
					</p>
					<a class="icon-cog"
						style="line-height: 0.9%; text-decoration: none"
						ng-click="cCctr.homepage('homepage')"> <img
						src="static/img/TOS.png" alt="Tax O smart" style="width: 300px;">
					</a>
				</div> -->
			</div>
			<br>

			<div style="display:flex; justify-content: center; align-items: center; margin-top:10px; flex-direction:column" class="form-signin mui-card xl-shadow">
				<form method="post" style="width:400px; background-color:#e1e1e1; gap:10px; padding:15px; border-radius:10px"  autocomplete="off" action="/login">
					<!-- CSRF Token -->
					<input type="hidden" name="${_csrf.parameterName}"
						value="${_csrf.token}" />
					<!-- CSRF Token -->

					<img src="static/img/${applicationScope.projectName}.png" alt=""
						style="width: 90%; margin-left: 5%; height: 75px;">
					<h3 class="form-signin-heading">
						<b>LOGIN</b>${applicationScope.projectName}
					</h3>

					<p style="color: red;">
						<b><span id="exceptionMessage">${sessionScope["SPRING_SECURITY_LAST_EXCEPTION"].message}</span></b>
					</p>
					<p>
						<label for="fy" class="sr-only">Financial Year</label> <select
							id="websiteDropdown" class="form-control" ng-required="true"
							required>
							<option value="/login.jsp" onclick="openSelectedWebsite()"
								selected>2025-26</option>
						</select>
					</p>
					<br>
					<p>
						<label for="username" class="sr-only">Username</label> <input
							autocomplete="off" type="text" id="username" name="username"
							class="form-control" placeholder="Username" required >
					</p>
					<br>
					<p>
						<label for="password" class="sr-only">Password</label> <input
							type="password" id="password" name="password" autocomplete="off"
							class="form-control" placeholder="Domain Password" required>
					</p>
					<br>
					<button class="btn btn-lg btn-primary btn-block" type="submit">Sign
						in</button>
					<br>
				</form>
				<div class="text-center" style="margin-top:10px;">
					<p style="font-size: 15px">
						<b>Powered By</b>
					</p>
					<a class="icon-cog"
						style="line-height: 0.9%; text-decoration: none"
						ng-click="cCctr.homepage('homepage')"> <img
						src="static/img/TOS.png" alt="Tax O smart" style="width: 200px;">
					</a>
				</div>
				<footer>
					<div>
						<p style="Float: right; ">Version 1.0</p>
					</div>
				</footer>
			</div>
		</div>
	</div>

	<script>
		$(document).ready(function() {
			window.history.pushState(null, "", window.location.href);
			window.onpopstate = function() {
				window.history.pushState(null, "", window.location.href);
			};
		});

		function openSelectedWebsite() {
			var dropdown = document.getElementById("websiteDropdown");
			var selectedValue = dropdown.options[dropdown.selectedIndex].value;

			// Check if a valid option is selected
			if (selectedValue !== "") {
				window.open(selectedValue, "_blank"); // Opens the selected URL in a new tab/window
			}
		}

		$(document).ready(function() {
			var messageElement = document.querySelector('#exceptionMessage');
			var message = messageElement.textContent;
			var modal = document.getElementById('PasswordModal');
			if (message.includes('ResetPassword')) {
				modal.style.display = 'flex';
			} else {
				modal.style.display = 'none';
			}
		})

		function modalClose() {
			document.getElementById("PasswordModal").style.display = "none";
		}
	</script>
</body>
</html>
