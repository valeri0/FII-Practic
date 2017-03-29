<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="eu.ubis.fiimdb.model.Movie"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>FIIMDb - delete movie</title>
<link rel="stylesheet" href="css/bootstrap.min.css">
<link rel="stylesheet" href="css/site.css">
<link rel="icon" type="image/png" href="https://static.tildacdn.com/tild6562-3030-4464-a364-633961326435/2.png">

<script src="js/jquery-3.1.1.min.js"></script>
<script src="js/bootstrap.min.js"></script>
<body>
	<jsp:useBean id="movieBean" class="eu.ubis.fiimdb.controller.MovieBean" scope="request"></jsp:useBean>

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
					<li class="active"><a href="movie-delete.jsp">Delete movie</a></li>
			</div>
	</nav>
	<div class="container">
	<h3>Delete a movie</h3>
	<div class="movie-container">
			<ul class="list-group">
			<%
					
					for (Movie movie : movieBean.getMovies()) {
			%>

				<li class="list-group-item" >
					<div class="row">
						

						<div class="col-sm-10">
							
							<%=movie.getName() %>
						<a href="DeleteMovie?id=<%=movie.getId()%>">	
						<img style="float:right; margin-right:-160px;" src="https://cdn4.iconfinder.com/data/icons/colicon/24/close_delete-128.png" height="20">
						</a>
						</div>

					</div>
				</li>
				
				<%
					}
				%>
			

			</ul>
		</div>
	</div>
	
</body>
</html>