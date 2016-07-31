<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@page import="model.User"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql"%>
<%@ taglib prefix="x" uri="http://java.sun.com/jsp/jstl/xml"%>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet"
	href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
<script
	src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>
<title>Add expenses</title>

<style>
body {
	background-image: url("http://bgfons.com/upload/light_texture2213.jpg");
	background-repeat: no-repeat;
	background-size: cover;
	background-color: #cccccc;
}
</style>
<%
		String message1 = (String)request.getParameter("message1");
%>
</head>
<body>
<div class="navbar navbar-inverse navbar-static-top">
<div class="container">

	<a href="#" class = "navbar-brand"  id = "nav-toggle"> <i class="glyphicon glyphicon-th-list"></i></a>
	<a href="#" class="navbar-brand">Money Manager</a>
	
	<button class="navbar-toggle" data-toggle="collapse" data-target=".navHeaderCollapse">
	<span class="icon-bar"></span>
	<span class="icon-bar"></span>
	<span class="icon-bar"></span>
	</button>
	
	<div class="collapse navbar-collapse navHeaderCollapse">
	<form class="navbar-form navbar-left" role="search" action = "ViewIncome" method = "POST">
    	<div class="form-group">
        	<input type="date" class="form-control" name="dp1"
						placeholder="mm/dd/yy">
    	</div>
    	<button type="submit" class="btn btn-default"><span class="glyphicon glyphicon-search"></span></button>
	</form>
	
	<ul class="nav navbar-nav navbar-right">
		<li><a href="#"></a></li>
		<li><a href="User.jsp">Dashboard</a></li>
		<li class="dropdown">
		<a href="#" class ="dropdown-toggle" data-toggle="dropdown">Instructions<b class="caret"></b></a>
			<ul class="dropdown-menu">
				<li><a href="instructions.jsp">Explicit instructions</a></li>
				<li><a href="LatestFun.jsp">Latest functionalities</a></li>
			</ul>	
		</li>	
		<li class = "dropdown">
			<a href = "#" class = "dropdown-toggle" data-toggle = "dropdown">View All<b class = "caret"></b></a>
			<ul class = "dropdown-menu">
			<li><a href = "Income.jsp">View All Income</a></li>
			<li><a href = "Expense.jsp">View All Expenses</a></li>
			</ul>
		</li>
		<li><a href="logout.jsp">Logout</a></li>
		<li><a href="about.jsp">About us</a></li>
	</ul>	
	
	</div>
	
</div>
</div>

	<c:if test="${not empty message1}">
		<script>
			window.addEventListener("load", function() {
				alert("${message1}");
			});
		</script>
	</c:if>
	<%
		User user = (User) session.getAttribute("user");
		System.out.println("USEr" + user);
	%>


	<div class="container">
		<center>
			<h1>Add Your Income :</h1>
		</center>

		<form method="post" action="AddIncome" class="form-horizontal"
			role="form">

			<div class="form-group">
				<label class="control-label col-sm-2" for="title">Title:</label>
				<div class="col-sm-5">
					<input type="text" class="form-control" id="title" name="title"
						placeholder="Enter Title">
				</div>
			</div>
			<div class="form-group">
				<label class="control-label col-sm-2" for="amount">Amount:</label>
				<div class="col-sm-5">
					<input type="text" class="form-control" id="amount" name="amount"
						placeholder="Enter Amount">
				</div>
			</div>

			<div class="form-group">
				<label class="control-label col-sm-2" for="note">Note:</label>
				<div class="col-sm-5">
					<input type="text" class="form-control" id="note" name="note"
						placeholder="description if any">
				</div>
			</div>

			<div class="form-group">
				<label class="control-label col-sm-2" for="date">Date:</label>
				<div class="col-sm-5">
					<input type="date" class="form-control" id="date" name="date"
						placeholder="YYYY/MM/DD">
				</div>
			</div>

			<div class="form-group">
				<div class="col-sm-offset-2 col-sm-5">
					<button type="submit" class="btn btn-default">Submit</button>
				</div>
			</div>

		</form>
	</div>


<div class = "navbar navbar-default navbar-fixed-bottom">
	<div class = "container">
	<p class="navbar-text pull-left">
		&copy;MoneyMnager.com
	</p>
	<a href = "feedback.jsp" class ="navbar-btn btn-danger btn pull-right">Feedback</a>
	</div>
</div>

 <script>
 $("#nav-toggle").click(function(e) {
     e.preventDefault();
     $("#wrapper").toggleClass("toggled");
 });
</script>
</body>
</html>