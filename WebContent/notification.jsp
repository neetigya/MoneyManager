<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
     <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql" %>
<%@ taglib prefix="x" uri="http://java.sun.com/jsp/jstl/xml" %>
 <%@ page import = "java.sql.*" %>
    <%@page import="model.User"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<meta name = "viewport" content= "width=device-width, initial-scale=1">
<title>Add Notifications</title>
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
	<a href="#" class = "navbar-brand"  id = "nav-toggle"> <i class="glyphicon glyphicon-th-list"></i></a>
	<a class="navbar-brand">&nbsp;Money Manager</a>
	
	<button class="navbar-toggle" data-toggle="collapse" data-target=".navHeaderCollapse">
	<span class="icon-bar"></span>
	<span class="icon-bar"></span>
	<span class="icon-bar"></span>
	</button>
	
	<div class="collapse navbar-collapse navHeaderCollapse">
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
		<li><a href="about.jsp">About us</a></li> <!-- Change -->
		<li><a href="logout.jsp"><i class="glyphicon glyphicon-off"></i></a></li>
		
	</ul>	
	
	</div>
	
</div>
</div>
<div class="container-fluid">
    <section class="container">
		<div class="container-page">				
			<div class="col-md-6">
				<h3 class="dark-grey">Notifications</h3><hr>
				<form action = "Notification" method = "POST">
				<div class="form-group col-lg-12">
					<label>Title</label>
					<input type="text" name="title" class="form-control" id="" value="" autocomplete = "off">
				</div>
								
				<div class="form-group col-lg-8">
					<label>Add Note</label>
					<input type="text" name="note" class="form-control" id="" value="">
				</div>
		      		
			<div class="form-group col-lg-4" id = "date">
					<label>Date-of-month</label>
					<input type="date" name="dd" class="form-control" value="" autocomplete = "off">
				</div>      
				
				<div class = "form-group col-lg-6">
				<label>Enter Choice</label><br>
        				<select id="maxOption2" class="selectpicker" name = "usersel">
          				<option>Repeat</option>
          				<option>Not Repeat</option>
		          		</select>
				</div>		
			<div class = "form-group col-lg-12">
			<button type="submit" class="btn btn-primary">Save</button>
			<button type="reset" class = "btn btn-primary">Reset</button></div>
			</form>
			</div>
		
		</div>
		
	</section>
</div>


<div class="navbar navbar-default navbar-fixed-bottom">
	<div class="container">
	<p class="navbar-text pull-left">
	&copy;MoneyManager.com
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