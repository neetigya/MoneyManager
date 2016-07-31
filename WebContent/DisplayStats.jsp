<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
             <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql" %>
<%@ taglib prefix="x" uri="http://java.sun.com/jsp/jstl/xml" %>
    <%@ page import = "java.sql.*" %>
    <%@page import="model.User"%>
    <%@page import="java.util.*" %>
    
<!DOCTYPE html>
<head>
<%	User user = (User)session.getAttribute("user");
	List<Map> list = (ArrayList<Map>)session.getAttribute("stats");
%>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Displaying Stats</title>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Welcome ${user.getUsername()}</title>
<link href="css/styles.css" rel="stylesheet">
	<link href="css/bootstrap.min.css" rel="stylesheet">

	<script src ="js/jquery-1.11.3.min.js"></script>
	<script src ="js/bootstrap.min.js"></script>
	<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.10.2/jquery.min.js"></script>
	<link rel = "stylesheet" href = "sidebar.css">
	<script type="text/javascript" src="https://www.google.com/jsapi"></script>
    </head>
<body>
<noscript><div class = "noscript">Javascript disabled</div></noscript>
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
 		<div class = "col-md-6">
			<div class = "panel panel-default">
				<div class = "panel-body">
					<div class = "page-header">
					<h3>Stats Overview</h3>
					</div>
	  	  <p>	
	  <c:choose>
	  <c:when test = "${sessionScope.s eq false}">
	  	<h3>No Items</h3>
	  	<h3>Total sum: &nbsp; 0<h3>
	  </c:when>
		<c:otherwise>
			<c:forEach var = "item" items = "${sessionScope.item1}">
			${item.title}: &nbsp;	${item.Expense}<br><hr>
			</c:forEach>
			<h3>Total sum: &nbsp; ${sessionScope.sum}<h3>
		</c:otherwise>
	  </c:choose>
	  			
					</p>

				</div>
			</div>
		</div>
	</div>
<div class = "navbar navbar-default navbar-fixed-bottom">
	<div class = "container">
	<p class="navbar-text pull-left">
		&copy;MoneyMnager.com
	</p>
	<a href = "feedback.jsp" class ="navbar-btn btn-danger btn pull-right">Feedback</a>
	</div>
</div>
$("#nav-toggle").click(function(e) {
     e.preventDefault();
     $("#wrapper").toggleClass("toggled");
 });
</script>
</body>
</html>