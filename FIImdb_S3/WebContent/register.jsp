<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Login</title>
<link rel="stylesheet" href="css/bootstrap.min.css">
<link rel="stylesheet" href="css/site.css">

<script src="js/jquery-3.1.1.min.js"></script>
<script src="js/bootstrap.min.js"></script>
<script src="http://mymaplist.com/js/vendor/TweenLite.min.js"></script>
</head>

<body>
 
		<div class="container">
		    <div class="row vertical-offset-50">
		    	<div class="col-md-4 col-md-offset-4">
		    		<div class="panel panel-default">
					  	<div class="panel-heading">
					    	<h3 class="panel-title">Register</h3>
					 	</div>
					  	<div class="panel-body">
					    	<form accept-charset="UTF-8" action="Register" method="POST">
		                    <fieldset>
					    	  	<div class="form-group">
					    		    <input class="form-control" placeholder="Username" id="username" name="username" type="text">
					    		</div>
					    		<div class="form-group">
					    			<input class="form-control" placeholder="Password" id="password" name="password" type="password" value="">
					    		</div>
					    		<div class="form-group">
					    		 
					    			<input class="form-control" placeholder="First Name" name="firstName" type="text" value="">
					    		</div>
					    		<div class="form-group">
					    			<input class="form-control" placeholder="Last Name" name="lastName" type="text" value="">
					    		</div>
					    		
					    		<div class="form-group">
					    			<input class="form-control" placeholder="email" name="email" type="text" value="">
					    		</div>
					    		
					    		<input class="btn btn-lg btn-success btn-block" type="submit" value="Register">
					    		
					    	</fieldset>
					      	</form>
					    </div>
					</div>
				</div>
			</div>
		</div>
</body>
<style type="text/css">
.vertical-offset-50 {
  min-height: 100%;  /* Fallback for browsers do NOT support vh unit */
  min-height: 100vh; /* These two lines are counted as one :-)       */

  display: flex;
  align-items: center;
}
</style>

</html>