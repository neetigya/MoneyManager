<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@page import="model.User"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql"%>
<%@ taglib prefix="x" uri="http://java.sun.com/jsp/jstl/xml"%>
<%@ page import="java.sql.*"%>
<!DOCTYPE html>
<html>
<head>
<%
	User user = (User) session.getAttribute("user");
	
%>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet"
	href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
<script
	src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>
		<link rel = "stylesheet" href = "sidebar.css">
<title>Delete Expense</title>

</head>
<body>

	<c:if test="${not empty msg}">
		<script>
			window.addEventListener("load", function() {
				alert("${msg}");
			});
		</script>
	</c:if>

	<div class="navbar navbar-inverse navbar-static-top">
		<div class="container">

			<a href="#" class = "navbar-brand"  id = "nav-toggle"> <i class="glyphicon glyphicon-th-list"></i></a> 
			<a href="#" class="navbar-brand">Money Manager</a>

			<button class="navbar-toggle" data-toggle="collapse"
				data-target=".navHeaderCollapse">
				<span class="icon-bar"></span> <span class="icon-bar"></span> <span
					class="icon-bar"></span>
			</button>

			<div class="collapse navbar-collapse navHeaderCollapse">
				<ul class="nav navbar-nav navbar-right">
					<li><a href="#"></a></li>
					<li><a href="User.jsp">Dashboard</a></li>
					<li class="dropdown"><a href="#" class="dropdown-toggle"
						data-toggle="dropdown">Instructions<b class="caret"></b></a>
						<ul class="dropdown-menu">
							<li><a href="instructions.jsp">Explicit instructions</a></li>
							<li><a href="LatestFun.jsp">Latest functionalities</a></li>
						</ul></li>		
						
		<li class = "dropdown">
			<a href = "#" class = "dropdown-toggle" data-toggle = "dropdown">View All<b class = "caret"></b></a>
			<ul class = "dropdown-menu">
			<li><a href = "Income.jsp">View All Income</a></li>
			<li><a href = "Expense.jsp">View All Expenses</a></li>
			</ul>
		</li>
					
					<li><a href="about.jsp">About us</a></li>
					<li><a href="logout.jsp"><i class="glyphicon glyphicon-off"></i></a></li>
				</ul>

			</div>

		</div>
	</div>
	<div id = "wrapper">
	<div id = "sidebar-wrapper">
		<ul class = "sidebar-nav">
			<li><a href = "Viewnot.jsp">Notifications(<span id = "result">0</span>)</a></li>
			<li><a href = "addExpense.jsp">Add Expenses</a></li>
			<li><a href = "incomingAmount.jsp">Add Income</a></li>
			<li><a href = "deleteTran.jsp">Delete Expenses</a></li>
			<li><a href = "Profile.jsp">User Profile</a></li>
			<li><a href = "stats.jsp">View Stats</a></li>
			<li><a href = "notification.jsp">Add Reminder</a></li>
			<li><a href = "#expense" data-toggle = "modal">Share an Expense</a></li>
		</ul>
	</div>
	</div>
		<div class="container">
		<center>
			<h1>Select the transaction You Wish To Delete:</h1>
		</center>

		</div>
<div class = "container-fluid">
	<div class = "row">
	<div class = "col-lg-2"></div>
	<div class = col-lg-6>
		<div class = "panel panel-default">
		<p>
			 <c:choose>
			  <c:when test = "${sessionScope.stat eq false}">
	  			No Items retrieved
	  		  </c:when>
			  <c:otherwise>
				<form role="form" action = "DeleteTran" method = "POST">
	
				<c:forEach var = "item" items = "${sessionScope.ls}">
    				<div class = "radio">
      				&nbsp;&nbsp;<label><input type="radio" name="optradio"><b>${item.title}</b></label><br>
    				&nbsp;&nbsp;&nbsp;&nbsp;	Amount: ${item.amount}<br>
    				&nbsp;&nbsp;&nbsp;&nbsp;	Date  : ${item.date}
    				<input type = "hidden" name = "hidden" value = "${item.outid}">
    				</div><hr/>	
				</c:forEach>
				
				<div class = "form-group col-lg-12">
					<button type="submit" class="btn btn-default">Submit</button>
					<button type="reset" class = "btn btn-primary">Reset</button>
				</div>
			
				</form>
				</c:otherwise>
		   </c:choose>
			</p>
		
			<div class = "panel-body">
		</div>
		</div>	
	</div>
	</div>
	<div class = "col-lg-4"></div>
</div>
	

<div class = "navbar navbar-default navbar-fixed-bottom">
	<div class = "container">
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