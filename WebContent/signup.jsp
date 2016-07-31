<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
	 <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql" %>
<%@ taglib prefix="x" uri="http://java.sun.com/jsp/jstl/xml" %>
<%@ page import="model.User" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<meta name = "viewport" content= "width=device-width, initial-scale=1">

<title>Member Signup</title>
<script type="text/javascript" src = "js/jquery-1.11.3.min.js"></script>
<script type="text/javascript" src = "js/bootstrap.min.js"></script>
<script type="text/javascript" src = "js/bootstrap-select.js"></script>
<link href = "css/bootstrap.min.css" rel = "stylesheet">
<link rel="stylesheet" href="js/bootstrap-select.css">
<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.10.2/jquery.min.js"></script>
<%
User user = (User)session.getAttribute("user");
String msg = (String)request.getAttribute("msg");
%>
<!-- 
<script type="text/javascript">
$('.selectpicker').selectpicker({
    style: 'btn-info',
    size: 4
});
</script> -->
</head>
<noscript><div class = "noscript">Javascript disabled</div></noscript>
<body>
<c:if test = "${not empty msg}">
	<script>
		windows.addeventListener("load", function(){
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
			
		<li><a href="about.jsp">About us</a></li>
	</ul>	
	
	</div>
</div>
</div>

<div class="container-fluid">
    <section class="container">
		<div class="container-page">				
			<div class="col-md-6">
				<h3 class="dark-grey">Registration</h3><hr>
				<form action = "Signupcontroller" method = "POST">
				<div class="form-group col-lg-12">
					<label>Username</label>
					<input type="text" name="username" class="form-control" id="" value="" autocomplete = "off" required><br>
				</div>
				
				<div class="form-group col-lg-6">
					<label>Password</label>
					<input type="password" name="pass1" class="form-control" id="" value="" required>
				</div>
				
				<div class="form-group col-lg-6">
					<label>Repeat Password</label>
					<input type="password" name="pass2" class="form-control" id="" value="" required>
				</div>
								
				<div class="form-group col-lg-8">
					<label>Email Address</label>
					<input type="email" name="email" class="form-control" id="" value="" required>
				</div>
				    <div class="form-group col-lg-6">
        				<label>Enter Your Question</label><br>
        				<select id="maxOption2" class="selectpicker" name = "usersel" required>
          				<option>Nothing Selected</option>
          				<option>What's Your favorite color?</option>
		          		<option>In which city you were born?</option>
		          		<option>What's your mother's maiden name?</option>
		          		<option>What was your first cell number?</option>
		        		</select>
		      		</div>
			<div class="form-group col-lg-6">
					<label>Answer</label>
					<input type="text" name="answer" class="form-control" id="" value="" autocomplete = "off" required>
				</div>
			
			<div class = "form-group col-lg-12">
				<label>Add your initial Balance</label>
				<input type = "number" name="bal" class="form-control" id="" value="0.00" required>
			</div>		      		
			
			<div class = "form-group col-lg-12">
			<button type="submit" class="btn btn-primary">Register</button>
			<button type="reset" class = "btn btn-danger">Reset</button></div>
			</form>
			</div>
		
		</div>
		
	</section>
</div>
<pre><b>
						-->a digit must occur at least once<br>
						-->a lower case letter must occur at least once<br>
						-->an upper case letter must occur at least once<br>		
						-->no whitespace allowed in the entire string<br>
						-->at least 5 characters<br>
</b>
</pre>
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