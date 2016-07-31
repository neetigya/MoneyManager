<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@page import="model.User"%>
<%@page import="dao.AuthDAO"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="/css/UserProfile.css">
<link rel="stylesheet"
	href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
<script
	src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>
<title>Insert title here</title>

<%
	User user = (User) session.getAttribute("user");
	AuthDAO authen = new AuthDAO();
	int id = user.getUserId();
	User profile = authen.getUserById(id);

	String name = profile.getUsername();
	String pass = profile.getPassword();
	String email = profile.getEmail();
	float balance = profile.getBalance();
	String ques = profile.getQuestion();
	String ans = profile.getAnswer();
%>


</head>
<body bgcolor="red">
	<div class="navbar navbar-inverse navbar-static-top">
		<div class="container">

			<a href="#" class="navbar-brand" id="menu-toggle">Menu&nbsp;</a> <a
				href="#" class="navbar-brand">Money Manager</a>

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
							<li><a href="#">Latest functionalities</a></li>
						</ul></li>

					<li><a href="logout.jsp">Logout</a></li>
					<li><a href="about.jsp">About us</a></li>
				</ul>

			</div>
		</div>
	</div>

	<h1 class="bg-primary text-center">
		Hi
		<%=name%></h1>




	<BR>
	<BR>
	<div class="container-fluid">
		<table class="table table-hover table-bordered" style="width: auto;">

			<tbody>
				<tr>

					<td><b>Name</b></td>
					<td><b><%=name%></b></td>

				</tr>
				<tr>
					<td><b>Password</b></td>
					<td><b>***********</b></td>
					<td><a class="btn btn-default" href="changePass.jsp"
						role="button">Change</a></td>

				</tr>

				<tr>
					<td><b>Email ID</b></td>
					<td><b><%=email%></b></td>

				</tr>
				<tr>
					<td><b>Current Balance</b></td>
					<td><b><%=balance%></b></td>

				</tr>
				<tr>
					<td><b>Selected Question</b></td>
					<td><b><%=ques%></b></td>
					<td><a class="btn btn-default" href="changeQues.jsp"
						role="button">Change</a></td>

				</tr>
				<tr>
					<td><b>Answer</b></td>
					<td><b><%=ans%></b></td>
					<td><a class="btn btn-default" href="changeAns.jsp"
						role="button">Change</a></td>



				</tr>
			</tbody>

		</table>

	</div>
	<br>
	<br>
	<div class="navbar navbar-default navbar-fixed-bottom">
		<div class="container">
			<p class="navbar-text pull-left">&copy;MoneyMnager.com</p>
			<a href="feedback.jsp" class="navbar-btn btn-danger btn pull-right">Feedback</a>
		</div>
	</div>
</body>
</html>