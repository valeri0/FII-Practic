<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page import="eu.ubis.fiimdb.model.Director"%>
<%@ page import="eu.ubis.fiimdb.model.Movie" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<jsp:useBean id="movieBean" class="eu.ubis.fiimdb.controller.MovieBean" scope="request"></jsp:useBean>
	<jsp:useBean id="directorBean" class="eu.ubis.fiimdb.controller.DirectorBean" scope="request"></jsp:useBean>

<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title><%=directorBean.getDirectorDetails().getLastName() %> <%=directorBean.getDirectorDetails().getFirstName() %></title>
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
				<li><a href="movie-insert.jsp">Insert movie</a></li>
				<li><a href="UpdateOrDelete">Update/Delete</a></li>
		</div>
	</nav>

	<div class="navbar-header">
		
	</div>

	<div class="container">
		
		
		<div class="movie-container">
			<ul class="list-group">

				<li class="list-group-item">
					<div class="row">
						

						<div class="col-sm-10">
							<h3><%=directorBean.getDirectorDetails().getLastName() %> <%=directorBean.getDirectorDetails().getFirstName() %></a></h3>
							
							<strong>Date of birth:</strong>
							<%= directorBean.getDirectorDetails().getDateOfBirth() %> <br>
							<strong>Bio:</strong>
							 <%=directorBean.getDirectorDetails().getBio() %><br>
							 <br>
							<strong>Involved in the following movies:</strong>
							<ul class="list-group">
							<%for(Movie aMovie : directorBean.getMovies(directorBean.getDirectorDetails().getId())){ %>
								<a href="DetailsServlet?id=<%=aMovie.getId() %>"> 
								<li class="list-group-item"> <%=aMovie.getName() %> </li>
								</a>
							<%} %>
							</ul>
						</div>

					</div>
				</li>

			</ul>
		</div>
		
		
	
</body>
</html>