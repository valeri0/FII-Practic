<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page import="eu.ubis.fiimdb.model.User"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<jsp:useBean id="userBean" class="eu.ubis.fiimdb.controller.UserBean" scope="request"></jsp:useBean>
	

<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>User details</title>
<link rel="stylesheet" href="css/bootstrap.min.css">
<link rel="stylesheet" href="css/site.css">
<link rel="icon" type="image/png" href="https://static.tildacdn.com/tild6562-3030-4464-a364-633961326435/2.png">

<script src="js/jquery-3.1.1.min.js"></script>
<script src="js/bootstrap.min.js"></script>
</head>
<body>

	<nav class="navbar navbar-default">
	<div class="container-fluid">
		<div class="navbar-header">
			<div class="navbar-brand">
				<a href="#"> Java Awesome Training
				</a>
			</div>
		</div>
		
		<div class="nav navbar-nav navbar-right">
						<div class="dropdown">
							<button class="btn btn-primary dropdown-toggle" type="button" data-toggle="dropdown"><%=request.getRemoteUser() %>
  							<span class="caret"></span></button>
							<ul class="dropdown-menu" >
								<li>
									<form action="<%=response.encodeURL("UserServlet?action=logout") %>"  method="post">
		                    				<button type="submit" class="btn btn-default center-block">Logout</button>
		                				</form>	
								</li>
							</ul>
						</div>
			</div>
		
		<div class="collapse navbar-collapse"
			id="bs-example-navbar-collapse-1">
			<ul class="nav navbar-nav">
				<li><a href="movies">Home</a></li>
				<%if ( request.isUserInRole("admin")){ %>
				<li><a href="movie-insert.jsp">Insert movie</a></li>
				<li><a href="UpdateOrDelete">Update/Delete</a></li>
				<% } %>
		</div>
	</nav>

	<div class="navbar-header">
		
	</div>

	<div class="container">
		<fieldset>
			<legend>Your profile</legend>

			<form method="post" action="UpdateUser">
				
				<div class="form-group">
					<label for="username">Username:</label> 
					<input type="text" name="username" class="form-control" id="username"  readonly value=<%=userBean.getUserDetails().getUsername() %>>
				</div>
				
				<div class="form-group">
					<label for="firstName">First name:</label> 
					<input type="text" name="firstName" class="form-control" id="firstName" value=<%=userBean.getUserDetails().getFirstName() %>>
				</div>
				
				<div class="form-group">
					<label for="lastName">Last name:</label> 
					<input type="text" name="lastName" class="form-control" id="lastName" value=<%=userBean.getUserDetails().getLastName()%>>
				</div>
				
				
				
				<div class="form-group">
					<label for="email">E-mail:</label> 
					<input type="text" name="email" class="form-control" id="email" value=<%=userBean.getUserDetails().getEmail()%>>
				</div>
		

				<button type="submit" class="btn btn-primary">Update</button>
			</form>

		</fieldset>
		
		</div>
		
		
	
</body>
</html>