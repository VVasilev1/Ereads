<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" import="bg.ereads.classes.User"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>eReads.AddBook</title>
<link rel="stylesheet" type="text/css" href="css/main.css">
<link rel="stylesheet" type="text/css" href="css/AddBook.css">
</head>
<body>

	<%
		if (request.getSession(false).getAttribute("user") == null) {
			response.sendRedirect("Login.jsp");
			return;
		}
		User user = (User) request.getSession(false).getAttribute("user");
		response.setHeader("Pragma", "No-cache");
		response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
		response.setDateHeader("Expires", -1);
	%>

	<div id="searchBar">
		<div>
			<img src="Logo.jpg" width="120px" height="60px">
		</div>
		<div>
			<h1>eReads</h1>
		</div>
		<div>
			<form action="./Search" method="get">
				<input type="text" name="search" style="width: 250px;" value="" />
				<br> <label> <input type="radio" name="radio"
					value="name" checked="checked">Name
				</label> <label> <input type="radio" name="radio" value="author">Author
				</label> <label> <input type="radio" name="radio" value="genre">Genre
				</label>
			</form>
		</div>
		<div>
			<div action="./DisplayPicture" method="get">
				<img src="./DisplayPicture" width="30" height="30"> <br>
			</div>
		</div>
		<div>
			<a href="Profile2.jsp"><%=user.getFirstName()%></a>
		</div>
		<div>
			<a href="BooksByGenre.jsp">Books</a>
		</div>
		<div>
			<a href="Login.jsp">Logout</a>
		</div>
	</div>

	<div id="addBox">
		<form action="./AddBook" method="post" enctype="multipart/form-data">
			<%
				if (request.getAttribute("invalidAdd") != null) {
					out.println("<p style='color:red'> " + request.getAttribute("invalidAdd") + "</p>");
				}
				if (request.getAttribute("errorMessageBook") != null) {
					out.println("<p style='color:red'> " + request.getAttribute("errorMessageBook") + "</p>");
				}
				if (request.getAttribute("success") != null) {
					out.println("<p style='color:green'> " + request.getAttribute("success") + "</p>");
				}
			%>
			<label><input name="title" value="" type="text"
				required="required" />Title</label> <br> <label><input
				name="author" value="" type="text" required="required" />Author</label> <br>
			<label> <select name="genre">
					<option value="fantasy">Fantasy</option>
					<option value="action">Action</option>
					<option value="drama">Drama</option>
					<option value="graphicNovel">Graphic novel</option>
					<option value="western">Western</option>
					<option value="mystery">Mystery</option>
					<option value="comedy">Comedy</option>
					<option value="education">Education</option>
			</select>Genre
			</label> <br> <label><input name="linkToBuy" value=""
				type="text" required="required" />Online Stores</label> <br> <label>Description</label><br>
			<textarea name="description" cols="100" rows="5" required></textarea>
			<br> <label for="file"><input name="image" type="file" />Choose
				a image file</label> <br> <br> <input type="submit"
				value="AddBook" class="submitButton" /><br>
		</form>
	</div>

	<div id="footer"></div>

</body>
</html>