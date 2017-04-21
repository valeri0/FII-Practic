<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page import="eu.ubis.fiimdb.model.Movie"%>
<%@ page import="eu.ubis.fiimdb.model.Director" %>
<%@ page import="eu.ubis.fiimdb.model.Review" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<jsp:useBean id="movieBean" class="eu.ubis.fiimdb.controller.MovieBean" scope="request"></jsp:useBean>

<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title><%=movieBean.getMovieDetail().getName() %></title>
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

				<li class="list-group-item">
					<div class="row">
						<img src="<%=movieBean.getMovieDetail().getPoster()%>" alt="Poster unavailable"
							class="img-thumbnail rounded float-left col-sm-2" />

						<div class="col-sm-10">
							<h3><%=movieBean.getMovieDetail().getName()%></a></h3>
							
							<strong>Rating:</strong>
							<%= movieBean.getMovieDetail().getRating() %> <br>
							<strong>Director:</strong>
							
							<%for(Director director : movieBean.getMovieDetail().getDirectors()){ %>
							<a href="DirectorDetail?id=<%=director.getId()%>"> 
							<%=director.getLastName() %> <%=director.getFirstName() %>
							</a>
							
							<%} %> 
							 <br>
							<strong>Writer:</strong>
							<%= movieBean.getMovieDetail().getWriter() %> <br>
							<strong>Description:</strong>
							<%= movieBean.getMovieDetail().getDescription() %> <br>
		
							<strong>Genres:</strong>		
							<%= movieBean.getMovieDetail().getGenre() %> <br>
		
							<strong>Casting:</strong>
							<%=movieBean.getMovieDetail().getCasting() %><br>

						</div>
						
						
					</div>
					
				</li>
				
				<li class="list-group-item">
						<fieldset>
						<legend>Write your opinion on the movie!</legend>
						<form method="post" action="AddReview?username=<%=request.getRemoteUser()%>&movieId=<%=movieBean.getMovieDetail().getId() %>">
						
							
							
							<div class="form-group">
							<label for="rating">Rating:</label> 
							<input type="number" name="rating"  class="form-control" id="rating" value=0>
							</div>
							
							<div class="form-group">
							<label for="commentary">Comment:</label> 
							<textarea name="commentary" id="commentary" class="form-control rows="10" cols="50"></textarea>
							</div>
				
							
				
							<button type="submit" class="btn btn-primary">Add review</button>
						
						</form>
						</fieldset>
					</li>
						
					
						
						<%if(movieBean.getMovieDetail().getReviews()!=null){ %>
						<legend>Read others opinion:</legend>
						<%for(Review review : movieBean.getMovieDetail().getReviews()){ %>
						<li class="list-group-item">
							<h5><strong>Username</strong>: <%=review.getUsername() %></h5>
							<h5><strong>Rating</strong>: <%=review.getRating() %></h5>
							<h5><strong>Commentary:</strong></h5>
							<p><%=review.getCommentary() %></p>
						</li>
						<% } %>  
						
						<% } %>
			
					
			</ul>
		</div>
		
		
	
</body>
</html>