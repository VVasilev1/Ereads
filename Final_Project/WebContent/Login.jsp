
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" import="bg.ereads.classes.User"
	import="java.util.ArrayList" import="bg.ereads.classes.Book"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8"
	content="text/html; charset=utf-8" />
<title>Log in</title>
<link rel="stylesheet" type="text/css" href="css/Login.css">
	<link rel="stylesheet" type="text/css" href="css/main.css">
</head>

<body>

	<%
		if (request.getSession().getAttribute("user") != null) {
			//request.getSession().invalidate();} 
			request.getSession().removeAttribute("user");
		}
	%>

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
			<a href="Register.jsp">Register</a>
		</div>
	</div>

	<div id="loginBox">
		<form method="post" action="./Log">
			<%
				if (request.getAttribute("errorMessage") != null) {
					out.println("<p style='color:red'> " + request.getAttribute("errorMessage") + "</p>");
				}
				if (request.getAttribute("registered") != null) {
					out.println("<p style='color:green'> " + request.getAttribute("registered") + "</p>");
				}
				if (request.getAttribute("imageMessage") != null) {
					out.println("<p style='color:red'> " + request.getAttribute("imageMessage") + "</p>");
				}
			%>
			<label>Email Address</label> <input name="email" value="" type="text" /><br />
			<label>Password</label> <input name="password" value=""
				type="password" /><br /> <input type="submit" value="Login"
				class="submitButton" /><br />
			<p>Don't have an account?!?</p>
			<a href="Register.jsp">Register here!</a>
		</form>
	</div>

</body>
</html>