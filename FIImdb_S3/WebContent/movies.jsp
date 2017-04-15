<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page import="eu.ubis.fiimdb.model.Movie"%>
<%@ page import="eu.ubis.fiimdb.model.Director" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>FIIMDb</title>
<link rel="stylesheet" href="css/bootstrap.min.css">
<link rel="stylesheet" href="css/site.css">
<link rel="icon" type="image/png" href="https://static.tildacdn.com/tild6562-3030-4464-a364-633961326435/2.png">

<script src="js/jquery-3.1.1.min.js"></script>
<script src="js/bootstrap.min.js"></script>
</head>
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
							<%if ( request.isUserInRole("user")){ %>
								<li>
									<a href="UserServlet?username=<%=request.getRemoteUser() %>" class="btn btn-default center-block" role="button"> Show profile </a>
								</li>
							<%} %>
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
				<li class="active"><a href="movies">Home</a></li>
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
			<legend>Search Form</legend>

			<form action="SearchServlet" method="GET">
				<div class="col-sm-8">
					<div class="form-group">
						<% if (request.getParameter("searchedValue") == null) { %>
							<input type="text" class="form-control" placeholder="Search..."  name="searchedValue">
						<%} else { %>
							<input type="text" class="form-control" value="<%=request.getParameter("searchedValue") %>"  name="searchedValue">
						<% } %>
					</div>
				</div>

				<div class="col-sm-4">
					<button type="submit" class="btn btn-primary">Search</button>
				</div>

				<div class="form-group col-sm-8">
					<div class="col-sm-4">
						<label class="radio-inline"> 
							<input type="radio" class="form-check-input" name="searchType" value="name" checked="<%=request.getAttribute("searchType") != null && request.getAttribute("searchType").equals("name") == true ? true : false%>"/> By Name
						</label>
					</div>
					
					<div class="col-sm-4">
						<label class="radio-inline"> 
							<input type="radio" class="form-check-input" name="searchType" value="genre" <%=request.getAttribute("searchType") != null && request.getAttribute("searchType").equals("genre") == true ? "checked" : ""%>/> By Genre
						</label>
					</div>
					
					<div class="col-sm-4">
						<label class="radio-inline"> 
							<input type="radio" class="form-check-input" name="searchType" value="year" <%=request.getAttribute("searchType") != null && request.getAttribute("searchType").equals("year") == true ? "checked" : ""%>/> By Release Year
						</label>
					</div>
					
					<div class="col-sm-4">
						<label class="radio-inline"> 
							<input type="radio" class="form-check-input" name="searchType" value="director" <%=request.getAttribute("searchType") != null && request.getAttribute("searchType").equals("director") == true ? "checked" : ""%>/> By Director
						</label>
					</div>
					
					<div class="col-sm-4">
						<label class="radio-inline"> 
							<input type="radio" class="form-check-input" name="searchType" value="writer" <%=request.getAttribute("searchType") != null && request.getAttribute("searchType").equals("writer") == true ? "checked" : ""%>/> By Writer
						</label>
					</div>
					
					<div class="col-sm-4">
						<label class="radio-inline"> 
							<input type="radio" class="form-check-input" name="searchType" value="casting" <%=request.getAttribute("searchType") != null && request.getAttribute("searchType").equals("casting") == true ? "checked" : ""%>/> By Actor
						</label>
					</div>
				</div>
			</form>
		</fieldset>
		<div class="movie-container">
			<ul class="list-group">
				<%
					
					for (Movie movie : movieBean.getMovies()) {
				%>

				<li class="list-group-item">
					<div class="row">
						<a href="DetailsServlet?id=<%= movie.getId()%>"><img src="<%=movie.getPoster()%>" alt="Poster unavailable"
							class="img-thumbnail rounded float-left col-sm-2" />

						<div class="col-sm-10">
							<h3><a href="DetailsServlet?id=<%= movie.getId()%>"><%=movie.getName()%></a></h3>

							<strong>Release date</strong>:
							<%=movie.getReleaseDate()%><br />
							<strong>Director(s):</strong>
							
							<%for(Director director : movie.getDirectors()){ %>
							<a href="DirectorDetail?id=<%=director.getId()%>"> 
							<%=director.getLastName() %> <%=director.getFirstName() %>
							</a>
							<%} %>   
							
							<br />
							<strong>Writer</strong>:
							<%=movie.getWriter() %><br /> <strong>Rating</strong>:
							<%=movie.getRating()%><br /> <strong>Genre</strong>: 
							<%=movie.getGenre()%> <br /> <strong>Casting</strong>:
							<%=movie.getCasting() %><br>
							
							<p>
								<strong>Storyline</strong>:
								<%=movie.getDescription()%>
							</p>

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