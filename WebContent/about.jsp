<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv = "Content-Type" content = "text/html; charset = utf-8"/>
	<meta name="viewport" content="width=device-width, initial-scale = 1.0">
	<link href="css/styles.css" rel="stylesheet">
	<link href="css/bootstrap.min.css" rel="stylesheet">

	<script src ="js/jquery-1.11.3.min.js"></script>
	<script src ="js/bootstrap.js"></script>
	<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.10.2/jquery.min.js"></script>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Instructions</title>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>About Us</title>
</head>

<body >
<div class="navbar navbar-inverse navbar-static-top">
<div class="container">

	<a href="#" class="navbar-brand">Money Manager</a>
	
	<button class="navbar-toggle" data-toggle="collapse" data-target=".navHeaderCollapse">
	<span class="icon-bar"></span>
	<span class="icon-bar"></span>
	<span class="icon-bar"></span>
	</button>
	
	<div class="collapse navbar-collapse navHeaderCollapse">
	<ul class="nav navbar-nav navbar-right">
		<li><a href="#"></a></li>
		<li><a href="HomePage.jsp">Home</a></li>
		<li class="dropdown">
		<a href="#" class ="dropdown-toggle" data-toggle="dropdown">Instructions<b class="caret"></b></a>
			<ul class="dropdown-menu">
				<li><a href="instructions.jsp">Explicit instructions</a></li>
				<li><a href="#">Latest functionalities</a></li>
			</ul>	
		</li>
			
		<li><a href="#login" data-toggle = "modal">Login</a></li>
		<li><a href="about.jsp">About us</a></li>
	</ul>	
	
	</div>
</div>
</div>
<center>
         <h1><font color="green">About</font> </h1>
         <pre style = "color:black">
        In general, the management of finances can become increasingly messy and time consuming task.
Being no strangers to this reality, we created this website with the goal of simplififying the process.
We sincerely hope you find it useful. 

Project Authors:
Aatman Togadia
Abhishek Gupta 
Neetigya Saxena
Yun Nam Lin
         </pre>
         
</center>
<div class="navbar navbar-default navbar-fixed-bottom">
	<div class="container">
	<p class="navbar-text pull-left">
	&copy;MoneyMnager.com
	</p>
	<a href = "feedback.jsp" class ="navbar-btn btn-danger btn pull-right">Feedback</a>
	</div>

</div>
<div class = "modal fade" id = "login" role = "dialog">
	<div class = "modal-dialog">
		<div class = "modal-content">
			<div class = "modal-header">
				<h4>LOGIN AREA</h4>
			</div>
			<div class = "modal-body">
				<form action = "logincontroller" method = "POST">
  					<div class="form-group">
    					<label for="exampleInputEmail1">User name</label>
    					<input type="text" class="form-control" name = "username" id="exampleInputEmail1" placeholder="User name" required = "required">
  					</div>
				    <div class="form-group">
    					<label for="exampleInputPassword1">Password</label>
    					<input type="password" class="form-control" name = "password" id="exampleInputPassword1" placeholder="Password" required = "required">
  					</div>
  					
  					<button type="submit" class="btn btn-primary">Submit</button>
  					<button type="reset" class="btn btn-default">Reset</button>
				</form>
			</div>
			<div class = "modal-footer">
				<a href = "RecoveryPassword.jsp" class = "navbar-btn btn-primary btn pull-left">Forgot Password!!</a>
				<a href = "signup.jsp" class ="navbar-btn btn-default btn pull-left">Sign Up</a>
				<a class="btn btn-danger" data-dismiss = "modal">Close</a>
			</div>
		</div>
	</div>
</div>
</body>
</html>