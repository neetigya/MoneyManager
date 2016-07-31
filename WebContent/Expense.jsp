<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
	<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
	<%@ taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql" %>
	<%@ taglib prefix="x" uri="http://java.sun.com/jsp/jstl/xml" %>
    <%@page import="model.User"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>View Expenses</title>
<link href="css/styles.css" rel="stylesheet">
	<link href="css/bootstrap.min.css" rel="stylesheet">
	<link rel = "stylesheet" href = "sidebar.css">
	
	<script src ="js/jquery-1.11.3.min.js"></script>
	<script src ="js/bootstrap.min.js"></script>
	<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.10.2/jquery.min.js"></script>
	<%
		User user = (User)session.getAttribute("user");	
	%>
	
<style>
table{
width:60%;
}
table, th, td {
    border: 1px solid black;
    border-collapse: collapse;
}
th, td {
    padding: 5px;
    text-align: left;
}
table#t01 th	{
    background-color: #ed3935;
    color: white;
}
</style>
</head>
<noscript><div class = "noscript">Javascript disabled</div></noscript>
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
	<form class="navbar-form navbar-left" role="search" action = "ViewExpenses" method = "POST">
    	<div class="form-group">
        	<input type="date" class="form-control" name="datepick"
						placeholder="YYYY/MM/DD">
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
				<li><a href="#">Latest functionalities</a></li>
			</ul>	
		</li>	
		<li class = "dropdown">
			<a href = "#" class = "dropdown-toggle" data-toggle = "dropdown">View All<b class = "caret"></b></a>
			<ul class = "dropdown-menu">
			<li><a href = "#">View All Income</a></li>
			<li><a href = "#">View All Expenses</a></li>
			</ul>
		</li>
		<li><a href="logout.jsp">Logout</a></li>
		<li><a href="about.jsp">About us</a></li>
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
			<li><a href = "#">User Profile</a></li>
			<li><a href = "stats.jsp">View Stats</a></li>
			<li><a href = "notification.jsp">Add Reminder</a></li>
			<li><a href = "#expense" data-toggle = "modal">Share an Expense</a></li>
		</ul>
	</div>
<div id = "page-content-wrapper"></div>

<div class = "container-fluid">
	<div class = "row">
	<div class = "col-lg-1"></div>
	<div class = col-lg-10>
		<div class = "panel panel-default">
			<div class = "panel-body">
			
				<c:choose>
	  				<c:when test = "${sessionScope.print eq true}">
	  					<div class = "page-header">
	  					<h3>Recent Transactions</h3>
	  					</div>
	  					<p>
			 <c:choose>
			  <c:when test = "${sessionScope.status eq false}">
	  			No Items retrieved
	  		  </c:when>
			  <c:otherwise>
				<table id = "t01">
				 <col style="width:20%">
        		 <col style="width:20%">
        		 <col style="width:40%">
        		 <col style="width:20%">
				<thead>
				<tr>
					<th>Category</th>
					<th>Amount</th>
					<th>Comment</th>
					<th>Date</th>
				</tr>
				</thead>
				<tbody>
				<c:forEach var = "item" items = "${sessionScope.ls}">
				<tr>
					<td>${item.title}</td>
					<td>${item.amount}</td>	
					<td>${item.comment}</td>	
					<td>${item.date}</td>
				</tr>
				</c:forEach>
				</tbody>
				</table><br>
			 </c:otherwise>
		   </c:choose>
			</p>
	  				</c:when>
					<c:otherwise>
						<div class = "page-header">
						<h3>Transactions on date</h3>
						</div>
								<p>
			 <c:choose>
			  <c:when test = "${sessionScope.stat eq false}">
	  			No Items retrieved
	  		  </c:when>
			  <c:otherwise>
				<table id = "t01">
				 <col style="width:20%">
        		 <col style="width:20%">
        		 <col style="width:40%">
        		 <col style="width:20%">
				<thead>
				<tr>
					<th>Category</th>
					<th>Amount</th>
					<th>Comment</th>
					<th>Date</th>
				</tr>
				</thead>
				<tbody>
				<c:forEach var = "item" items = "${sessionScope.ls1}">
				<tr>
					<td>${item.title}</td>
					<td>${item.amount}</td>	
					<td>${item.comment}</td>	
					<td>${item.date}</td>
				</tr>
				</c:forEach>
				</tbody>
				</table><br>
			 </c:otherwise>
		   </c:choose>
			</p>		
					</c:otherwise>
				</c:choose>
				
					</div>
		</div>	
	</div>
	</div>
	<div class = "col-lg-1"></div>
</div>
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