<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql" %>
<%@ taglib prefix="x" uri="http://java.sun.com/jsp/jstl/xml" %>
<%@ page import="model.User" %>
<!DOCTYPE HTML>

<html>
<head>
	<title>Money Manager</title>
	<meta http-equiv = "Content-Type" content = "text/html; charset = utf-8"/>
	<meta name="viewport" content="width=device-width, initial-scale = 1.0">
	<link href="css/styles.css" rel="stylesheet">
	<link href="css/bootstrap.min.css" rel="stylesheet">

	<script src ="js/jquery-1.11.3.min.js"></script>
	<script src ="js/bootstrap.js"></script>
	<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.10.2/jquery.min.js"></script>
	<%
		User user = (User)session.getAttribute("user");
		String msg = (String)request.getParameter("msg");
		if(!(user == null)){
			request.getRequestDispatcher("User.jsp").forward(request, response);
		}else{
	%>	
</head>
<body>
<noscript><div class = "noscript">Javascript disabled</div></noscript>
 <c:if test="${not empty msg}">
    <script>
    window.addEventListener("load",function(){
         alert("${msg}");
    });
    </script>
</c:if>
<div class="navbar navbar-inverse navbar-static-top">
<div class="container">

	<a class="navbar-brand">Money Manager</a>
	
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
				<li><a href="LatestFun.jsp">Latest functionalities</a></li>
			</ul>	
		</li>
			
		<li><a href="#login" data-toggle = "modal">Login</a></li>
		<li><a href="about.jsp">About us</a></li>
	</ul>	
	
	</div>
</div>
</div>
<div class="container">
	<div class="row">
		<div class="com-md-12 jumbotron">
			<div class="text-left">
			<h1>Money Manager</h1>
			<p>
			Manage your budget, track and view<br> transactions online,
			get statistics and more
			</p>
			</div>

		</div>
	</div>
</div>

<div class="container">
	<div class="row">
		<div class="col-md-4">
		<h3><a>Managing budget</a></h3>
		<p>Split expenses with other users<br>
			 Log your spending history<br>
			 Access your transaction entries 24/7<br></p>
		</div>

		<div class="col-md-4">
		<h3><a>Organize Efficiently</a></h3>
		<p>Simple, intuitive interface<br>
			 Sort transactions into and search by categories<br>
			 Set up notifications for yourself<br></p>
		</div>

		<div class="col-md-4">
		<h3><a>Analyze Spending</a></h3>
		<p>
		Visualize transactions through charts and graphs<br>
		Generate statistics of your transactions<br>
		Get a projection of your future spending based of your transaction history<br></p>
		</div>

	</div>
</div>

<div class="navbar navbar-default navbar-fixed-bottom">
	<div class="container">
	<p class="navbar-text pull-left">
	&copy;MoneyManager.com
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
<%}%>
</html>