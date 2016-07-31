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
</head>
<body>
 <div class="navbar navbar-inverse navbar-static-top">
<div class="container">
	<a href="#" class = "navbar-brand"  id = "nav-toggle"> <i class="glyphicon glyphicon-th-list"></i></a>
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
				<li><a href="#">Latest functionalities</a></li>
			</ul>	
		</li>
		<li><a href="about.jsp">About us</a></li>
	</ul>	
	
	</div>
</div>
</div>
		<center>
         <h1><font color="green">How do I use it?</font> </h1>
         <br>
          <pre>
         <font color="green">
        
<h2>Getting Started  </h2>
<ul><div align="left">
<li>An account must be made in order to access the features outlined below</li>
<li>Once you hold an account, you may log in</li>
<li>A successful log-in will bring you to your user dashboard</li>
<li>After 20 minutes of inactivity, you will automatically be logged out</li>
</ul>
</div>

<h2>User Dashboard</h2>
<ul><div align="left">
<li>Similar to a car dashboard, a user's account dashboard displays basic information / details and provides access to different functionalities<li>
<li>Such information is gathered from the financial data the user had already previously logged for themselves</li>
<li>Naturally, a brand new user visiting their dashboard for the first time will find their current balances to be and display $0</li>
<li>To access the different features, tools, and options the website provides, simply follow the appropriate link on the dashboard</li>
</div>


<h2>Saving Your Entries and the "In" and "Out" System</h2>
<ul><div align="left">
<li>To add an entry, click on the "add" option on the page and fill out the necessary information</li>
<li>To remove an entry, select the entry and click on the "delete" option</li>

<li>The system the website divides records entries into two main categories known as "In" and "Out" which roughly translates to acquisitions and expenditures, respectively</li>
<li>These categorical types are what determines if an entry will positively or negatively affect a balance. Meanwhile, balances are constantly updated by each entry</li>
<li>"In" entries are meant to be money available for the user to use. Examples include income, deposits, and refunds</li>
<li>"Out" entries are the various expenses or costs of the user - money that has been spent. Examples include rent, bills, and tuition</li>
	</pre>
	</font>
     </center>
<div class="navbar navbar-default navbar-fixed-bottom">
	<div class="container">
	<p class="navbar-text pull-left">
	&copy;MoneyManager.com
	</p>
	<a href = "feedback.jsp" class ="navbar-btn btn-danger btn pull-right">Feedback</a>
	</div>

</div>
</body>
</html>