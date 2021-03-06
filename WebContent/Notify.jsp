<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
   <%@page import = "model.User" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<meta name = "viewport" content= "width=device-width, initial-scale=1">
<title>Add Events</title>

<script type="text/javascript" src = "js/jquery-1.11.3.min.js"></script>
<script type="text/javascript" src = "js/bootstrap.min.js"></script>
<script type="text/javascript" src = "js/bootstrap-select.js"></script>
<link href = "css/bootstrap.min.css" rel = "stylesheet">
<link rel="stylesheet" href="js/bootstrap-select.css">
<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.10.2/jquery.min.js"></script>
</head>
<body>

<%
User user = (User)session.getAttribute("user");
%>
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

<div class ="container-fluid">
	<section class = "container">
		<div class = "container-page">
		<div class="col-md-6">
				<h3 class="dark-grey">Add Event</h3><hr>
				
				<form action="" method = "POST">
				<div class="form-group col-lg-12">
					<label>Name of Event</label>
					<input type="text" name="Event" class="form-control" id="" value="" autocomplete = "off">
				</div>
				<div class="form-group col-lg-12">
					<label>Select Date</label>
					<input type="date" name="date" class="form-control" id="" value="" autocomplete = "off">
				</div>
				<div class="form-group col-lg-12">
					<label>Repetition</label>
					<select id="maxOption2" class="selectpicker" name = "rep">
		          		<option>Yes(Same day of every Month)</option>
		          		<option>No</option>
		        	</select>
				</div>
				<div class="form-group col-lg-12">
					<label>Add Comment</label>
					<input type="text" name="comment" class="form-control" id="" value="" autocomplete = "off">
				</div>
				
				<div class = "form-group col-lg-12">
			<button type="submit" class="btn btn-primary">Add</button>
			<button type="reset" class = "btn btn-primary">Reset</button></div>
			
				</form>
		</div>
		</div>

	</section>
</div>
</div>
<div class="navbar navbar-default navbar-fixed-bottom">
	<div class="container">
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