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
		String email = user.getEmail();
	%>
	
</head>
<noscript><div class = "noscript">Javascript disabled</div></noscript>
<body>
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
<div id = "page-content-wrapper"></div>
<div class = "container-fluid">
	<div class = "row">
	<div class = "col-lg-1"></div>
	<div class = col-lg-8>
		<div class = "panel panel-default">
			<div class = "panel-body">
	  					<div class = "page-header">
	  					<h3>New Notifications</h3>
	  					</div><!-- Also check if there are new notifications -->
	  		<p>
	  		<c:choose>
	  		<c:when test = "${not empty msg}">
	  			<b>Bill is settled...Other user will be notified</b><br>
	  				No New Notifications
	  		</c:when>
		  	<c:otherwise>
				<c:choose>
				<c:when test = "${!empty sessionScope.list1}">
					<c:forEach var = "item" items = "${sessionScope.list1}">
					<c:set var = "Cur_date2" value = "${item.Date}"></c:set>
					<c:choose>
					<c:when test="${Cur_date2 eq sessionScope.Cur_date}">
						<!-- Values will be added in printed date -->
						<h4>Message:&nbsp;${item.Message}</h4><br>		
						<h5>To&nbsp;Pay:&nbsp;${item.email}</h5><br>
					</c:when>
					<c:otherwise>
						<!-- Date will be printed along with values -->
						<c:set var = "Cur_date" value = "${item.Date}" scope = "session"></c:set>
						<h4><span style = "color:grey;">Date:&nbsp;${item.Date}</span></h4><hr>
						<h4>Message:&nbsp;${item.Message}</h4>		<br>
						<h5>To&nbsp;Pay:&nbsp;${item.email}</h5><br>
					</c:otherwise>
					</c:choose>
				</c:forEach>	
				
				</c:when>
				<c:otherwise>
					No New Bills
				</c:otherwise>
				</c:choose>
			 	
	  		</c:otherwise>
	  		</c:choose>
			</p>
	  		</div>
		</div>	
	</div>
	<div class = "col-lg-3"></div>
	<div class = "row">
		<div class = "col-lg-7"></div>
		<div class = "col-lg-5">
			<form action = "ViewBills" method = "POST">
					<input type = "hidden" name = "email" value = <%= email%>>
  					<button type="submit" class="btn btn-danger">View All Bills >></button>
				</form>
		</div>
	</div>
</div></div>
<%
	session.removeAttribute("list1");
	
%>
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