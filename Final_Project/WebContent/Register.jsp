<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<meta charset="UTF-8">
	<title>Register</title>
	<link rel="stylesheet" type="text/css" href="css/Register.css">
		<link rel="stylesheet" type="text/css" href="css/main.css">
</head>
<body>

	<div id="searchBar">
		<div>
			<img src="Logo.jpg" width="120px" height="60px">
		</div>
		<div>
			<h1>eReads</h1>
		</div>
		<div>
			<a href="BooksByGenre.jsp">Books</a>
		</div>
		<div>
			<a href="Login.jsp">Login</a>
		</div>
	</div>

	<div id="registerBox" draggable="false">
		<form method="post" action="./Reg">
			<div>
				<%
					if (request.getAttribute("invalidEmail") != null) {
						out.println("<p style='color:red'> " + request.getAttribute("invalidEmail") + "</p>");
					}
					if (request.getAttribute("passwordError") != null) {
						out.println("<p style='color:red'> " + request.getAttribute("passwordError") + "</p>");
					}
				%>
				<label>First Name</label> <input name="firstname" value=""
					type="text" required="required" /> <br> <label>Last
						Name</label> <input name="lastname" value="" type="text"
					required="required" /> <br> <label>Email Address</label> <input
						name="email" value="" type="email" required="required" /> <br>
							<label>Password</label> <input name="password" value=""
							type="password" required="required" /> <br> <input
								type="submit" value="Register" class="submitButton" /> <br>
									<p>Already have an account?!?</p> <a href="Login.jsp">Log
										in here!</a>
			</div>
		</form>
	</div>

</body>
</html>