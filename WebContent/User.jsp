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

<%	User user = (User)session.getAttribute("user");
	String msg = (String)session.getAttribute("msg");
	String MSG = (String)session.getAttribute("MSG");
	session.setAttribute("print","true");
	if(!(user == null)){
%>
<meta http-equiv = "Content-Type" content = "text/html; charset = utf-8" />
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Welcome ${user.getUsername()}</title>
<link href="css/styles.css" rel="stylesheet">
	<link href="css/bootstrap.min.css" rel="stylesheet">
	<link href=" css/hamburger.css" rel="stylesheet">
	<script src ="js/jquery-1.11.3.min.js"></script>
	<script src ="js/bootstrap.min.js"></script>
	<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.10.2/jquery.min.js"></script>
	<link rel = "stylesheet" href = "sidebar.css">
<style type="text/css">
.page-header{
margin-top:0;
}
.panel-body{
padding-top:0;
}
</style>
<script type="text/javascript">
function checkforNotifications(){
	$.ajax({
		type:"GET",
		url:"Notification",
		success:function(result){
			if(result == ""){
				
			}else{
				alert('You have ' +result+ ' new notifications');
				$('#result').html(result);
			}
		}
	});
}
</script>

</head>
<body onload = "setInterval(checkforNotifications, 10000);" >
<c:if test="${not empty msg}">
    <script>
    window.addEventListener("load",function(){
         alert("${msg}");
    });
    </script>
</c:if>
<c:if test="${not empty MSG}">
    <script>
    window.addEventListener("load",function(){
         alert("${MSG}");
    });
    </script>
</c:if>
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
<div class = "container-fluid">
	<div class = "row">
		<div class = "col-lg-1"></div>
		<div class = "col-lg-10">
          
		<br>
		<div class = "panel panel-default" style = "background-color:#0099CC">
				<div class = "panel-body">
					<div class = "page-header">
					<h3>BALANCE: ${user.getBalance()}</h3>
					</div>
					
				</div>
		</div>
		<div class = "col-md-6">
			<div class = "panel panel-default">
				<div class = "panel-body">
					<div class = "page-header">
					<h3>IN</h3>
					</div>
	  <b>Statement of Recent Transactions</b>
	  <p>	
	<!--    <c:choose>
	  <c:when test = "${sessionScope.status1 eq false}">
	  	No Items retrieved
	  </c:when>
		<c:otherwise>
			<c:forEach var = "item" items = "${sessionScope.it}">
				Title:	&nbsp;	${item.title}<br>
				Amount: &nbsp;	${item.amount}<br>
				Comment:&nbsp;	${item.comment}<br>
				Date:	&nbsp;	${item.date}<br><br>
			</c:forEach>
		</c:otherwise>
	  </c:choose>
	  -->
	  <c:choose>
	  	<c:when test = "${session.status1 eq false}">
	  		No items retrieved
	  	</c:when>
	  	<c:otherwise>
	  		<c:forEach var = "item" items = "${sessionScope.it}">
					<c:set var = "date2" value = "${item.date}"></c:set>
					<c:choose>
					<c:when test="${date2 eq sessionScope.date}">
						<!-- Values will be added in printed date -->
						Title:&nbsp;${item.title}<br>		
						Amount:&nbsp;${item.amount}<br>
						Comment:&nbsp;${item.comment}<br>
					</c:when>
					<c:otherwise>
						<!-- Date will be printed -->
						<h4><span style = "color:grey;">Date:&nbsp;${item.date}</span></h4><hr>
						<c:set var = "date" value = "${item.date}" scope = "session"></c:set>
						Title:&nbsp;${item.title}<br>		
						Amount:&nbsp;${item.amount}<br>
						Comment:&nbsp;${item.comment}<br>
					</c:otherwise>
					</c:choose>
				</c:forEach>	
	  	</c:otherwise>
	  </c:choose>
	  			</p>
				</div>
			</div>
		</div>
		
		<div class = "col-md-6">
			<div class = "panel panel-default">
				<div class = "panel-body">
					<div class = "page-header">
					<h3>OUT</h3>
					</div>
					  <b>Statement of Recent Transactions</b>
					<p>
<!-- 					<c:choose>
	  					<c:when test = "${sessionScope.status2 eq false}">
	  						No Items retrieved
					    </c:when>
						<c:otherwise>
						<c:forEach var = "item" items = "${sessionScope.it1}">
							Title:	&nbsp;	${item.title}<br>
							Amount: &nbsp;	${item.amount}<br>
							Comment:&nbsp;	${item.comment}<br>
							Date:	&nbsp;	${item.date}<br><br>
						</c:forEach>
						</c:otherwise>
	  				</c:choose>
	 --><c:choose>
	  	<c:when test = "${session.status1 eq false}">
	  		No items retrieved
	  	</c:when>
	  	<c:otherwise>
	  		<c:forEach var = "item" items = "${sessionScope.it1}">
					<c:set var = "date2" value = "${item.date}"></c:set>
					<c:choose>
					<c:when test="${date2 eq sessionScope.date}">
						<!-- Values will be added in printed date -->
						Title:&nbsp;${item.title}<br>		
						Amount:&nbsp;${item.amount}<br>
						Comment:&nbsp;${item.comment}<br>
					</c:when>
					<c:otherwise>
						<!-- Date will be printed -->
						<h4><span style = "color:grey;">Date:&nbsp;${item.date}</span></h4><hr>
						<c:set var = "date" value = "${item.date}" scope = "session"></c:set>
						Title:&nbsp;${item.title}<br>		
						Amount:&nbsp;${item.amount}<br>
						Comment:&nbsp;${item.comment}<br>
					</c:otherwise>
					</c:choose>
				</c:forEach>	
	  	</c:otherwise>
	  </c:choose>
	 					
					</p>
				</div>
			</div>
		</div>
		</div>
		<div class = "col-lg-1"></div>
	</div>
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

<div class = "modal fade" id = "expense" role = "dialog">
	<div class = "modal-dialog">
		<div class = "modal-content">
			<div class = "modal-header">
				<h4>Sharing Expense</h4>
			</div>
			<div class = "modal-body">
				<form action = "ShareExpense" method = "POST">
				<div class="form-group">
  						<label for="usr">Enter Amount</label> <!-- check for alphabets take alphabets -->
  						<input class = "form-control" id = "usr" name = "amount" placeholder = "Enter your amount"></textarea>
					</div>
  					<div class="form-group">
  						<label for="comment">Email</label>
  						<textarea class = "form-control" rows="5" cols="10" id = "comment" name = "email" placeholder = "Enter E-mails here..."></textarea>
					</div>
  					<button type="submit" class="btn btn-primary">Split Equally</button>
  					<button type = "submit" class = "btn btn-default">Reset</button>
				</form>
			</div>
			<div class = "modal-footer">
				Note: E-mail id should be comma seperated values.
				<a class="btn btn-danger" data-dismiss = "modal">Close</a>
			</div>
		</div>
	</div>
</div>


 <script>
 $("#nav-toggle").click(function(e) {
     e.preventDefault();
     $("#wrapper").toggleClass("toggled");
 });
</script>
</body>
<% }else{
	request.getRequestDispatcher("logout.jsp").forward(request, response);
}
%>
</html>