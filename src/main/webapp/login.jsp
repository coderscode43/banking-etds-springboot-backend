<style>
.form-signin {
  max-width: 390px;
   background-color:rgba(0,0,0,0.2);
  border: 1px solid rgba(0,0,0,0.1);  
  
  }

	.checkbox {
	  font-weight: normal;
	}

	.form-control {
	  position: relative;
	  font-size: 16px;
	  height: auto;
	
		@include box-sizing(border-box);

		&:focus {
		  z-index: 2;
		}
	}





/* For taxosmart Logo Start */
@font-face {
	font-family: "untitled-font-2";
	src: url("static/css/fonts/untitled-font-2.eot");
	src: url("static/css/fonts/untitled-font-2.eot?#iefix") format("embedded-opentype"),
		url("static/css/fonts/untitled-font-2.woff") format("woff"),
		url("static/css/fonts/untitled-font-2.ttf") format("truetype"),
		url("static/css/fonts/untitled-font-2.svg#untitled-font-2") format("svg");
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
.shortline{
    border: 1px solid #7dc142;
    margin-left:0px;
}


.mui-card {
    background: white;
  border-radius: 2px;
  display: inline-block;
  height: 300px;
  margin: 1rem;
  position: relative;
  width:90%;
}

.xs-shadow {
    box-shadow: 0 1px 3px rgba(0,0,0,0.12), 0 1px 2px rgba(0,0,0,0.24);
  transition: all 0.3s cubic-bezier(.25,.8,.25,1);
}

.xs-shadow:hover {
  box-shadow: 0 14px 28px
  rgba(0,0,0,0.25), 0 10px 10px
  rgba(0,0,0,0.22);
}


.xxl-shadow {
    box-shadow: 0 19px 38px rgba(0,0,0,0.30), 0 15px 12px rgba(0,0,0,0.22);
}

.xxl-shadow:hover {
      box-shadow: 0 14px 28px
  rgba(0,0,0,0.25), 0 10px 10px
  rgba(0,0,0,0.22);
}

</style>

<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1  maximum-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">
    <title>Please sign in</title>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-

awesome.min.css">
    <link rel="shortcut icon" href="static/img/taxOsmart.ico" type="image/x-icon">
	<link href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta/css/bootstrap.min.css" rel="stylesheet" 

integrity="sha384-/Y6pD6FV/Vv2HJnA6t+vslU6fwYXjCFtcEpHbNJ0lyAFsXTsjBbfaDjzALeQsN6M" crossorigin="anonymous">
    <link href="https://getbootstrap.com/docs/4.0/examples/signin/signin.css" rel="stylesheet" 

crossorigin="anonymous"/>
    <!-- Latest compiled and minified CSS -->
     <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/css/bootstrap.min.css">

      <!-- jQuery library -->
     <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>

<!-- Latest compiled JavaScript -->
     <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/js/bootstrap.min.js"></script>
   
  </head>
  <body style="background-color:whitesmoke; margin:0;
  padding:0;">
     <script src="static/js/lib/bootstrap.js"></script>	
 
    <a class="icon-cog" style="line-height: 0.5%; text-decoration: none;"
				ng-click="cCctr.homepage('homepage')"> <span
				class="icon-tax-o-smart"
				style="font-size: 75px; color: #0F316D; position: relative; top: 20px;"></span>
				
				<span color="whitle"
				style="font-size: 30px; color: #0F316D; position: relative; bottom: 

10px;"><b>Tax-</b><strong
					style="color: #79BD3C;"><b>O</b></strong><b>-Smart</b> </span>

			</a> <br> <span color="whitle" class="serif"
				style="font-size: 18px; color: #79BD3C; position: relative; bottom: 15px;margin-right:150px"">&emsp;&emsp;&emsp;
				&emsp;<b>Technology empowering your business</b>
			</span>
		
    
    
    <br><br>
    <div class="row">
     <div class="container">
   
         
  <div class="col-md-6">     
       <h2  style="color:#0F316D">Document Verification System</h2>
          <hr class="shortline margin-bottom-10px">
         <p style="font-size:15px"><b>A simple solution to handle your investment proof collection & verification 

process.
             Use our intuitive application or avail our services to manage your verification process</p></b>
		<ul><br>
		<h5><span class="fa fa-check-circle" style="color:green"></span>&nbspOnline submission of 

documents</h5>
	    <h5><span class="fa fa-check-circle" style="color:green"></span>&nbspDocument preview appears in a 

single screen</h5>
		<h5><span class="fa fa-check-circle" style="color:green"></span>&nbspTagging of documents under 

respective investment categories for easy verification</h5>
		<h5><span class="fa fa-check-circle" style="color:green"></span>&nbspGenerate Form 12BB in a 

single click</h5>
		<h5><span class="fa fa-check-circle" style="color:green"></span>&nbspSend reminder mails to 

employees who have not completed the process</h5>
		
		</ul>	
			
			</div><br>
		
        <div class="col-md-6" style="padding-right: 75px;">
           <form class="form-signin mui-card xl-shadow"   method="post" action="/DocumentVerification/login" style="margin-left: 100px;height:60%;margin-bottom:20px;float:right">
            <h2 class="form-signin-heading"><center><b style="color:#79BD3C">LOGIN</b></center></h2>
          <p><br>
             <label for="username" class="sr-only">Username</label>
             <input type="text" id="username" name="username" class="form-control" placeholder="Username" 

required autofocus>
        </p><br>
        <p>
            <label for="password" class="sr-only">Password</label>
            <input type="password" id="password" name="password" class="form-control" placeholder="Password" 

required>
        </p><br><br><br>
     <p><input type='checkbox' name='remember-me'/> Remember me on this computer.</p>
       <button class="btn btn-lg btn-primary btn-block" type="submit" style="background-color:#0F316D">Sign 

in</button>
        </form>
  </div>
      
	             </div>
                     </div>
	                   </div>		
	                   
	          
	                   
   
</body></html>