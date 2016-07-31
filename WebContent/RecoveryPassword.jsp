<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
        <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql" %>
<%@ taglib prefix="x" uri="http://java.sun.com/jsp/jstl/xml" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<meta name = "viewport" content= "width=device-width, initial-scale=1">

<title>Recovery Password</title>
<script type="text/javascript" src = "js/jquery-1.11.3.min.js"></script>
<script type="text/javascript" src = "js/bootstrap.min.js"></script>
<script type="text/javascript" src = "js/bootstrap-select.js"></script>
<link href = "css/bootstrap.min.css" rel = "stylesheet">
<link rel="stylesheet" href="js/bootstrap-select.css">
<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.10.2/jquery.min.js"></script>
<%
	String msg = (String)request.getParameter("msg");
%>	
</head>
<body>
 <c:if test="${not empty msg}">
    <script>
    window.addEventListener("load",function(){
         alert("${msg}");
    });
    </script>
</c:if>
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
				<li><a href="#">How should I use it?</a></li>
				<li><a href="#">Explicit instructions</a></li>
				<li><a href="#">Latest functionalities</a></li>
			</ul>	
		</li>
			
		<li><a href="#login" data-toggle = "modal">Login</a></li>
		<li><a href="about.jsp">About us</a></li>
	</ul>	
	
	</div>
</div>
</div>

<div class="container-fluid">
    <section class="container">
		<div class="container-page">				
			<div class="col-md-6">
				<h3 class="dark-grey">Recover your Password</h3><hr>
				<form action = "RecoveryController" method = "POST">
				<div class="form-group col-lg-12">
					<label>Enter your email id</label>
					<input type="email" name="email" class="form-control" id="" value="" autocomplete = "off">
				</div>
				
			<div class = "form-group col-lg-12">
			<button type="submit" class="btn btn-primary">Submit</button>
			<button type="reset" class = "btn btn-primary">Reset</button></div>
			</form>
			</div>
		
		</div>
		
	</section>
</div>
<div class="navbar navbar-default navbar-fixed-bottom">
	<div class="container">
	<p class="navbar-text pull-left">
	&copy;MoneyMnager.com
	</p>
	<a href = "feedback.jsp" class ="navbar-btn btn-danger btn pull-right">Feedback</a>
	</div>

</div>
</body>
</html>