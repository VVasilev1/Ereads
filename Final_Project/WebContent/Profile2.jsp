<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" import="bg.ereads.classes.Book"
	import="bg.ereads.classes.User" import="java.util.ArrayList"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd" >
<html>
<head>
<meta http-equiv="Content-Type"
	content="text/html; charset=windows-1251">
<title>eReads.Profile</title>
<link rel="stylesheet" type="text/css" href="css/Profile.css">
<link rel="stylesheet" type="text/css" href="css/main.css">
<link rel="stylesheet" type="text/css" href="css/lightbox.css">
</head>
<body>

	<%
		response.setHeader("Pragma", "No-cache");
		response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
		response.setDateHeader("Expires", -1);
		if (request.getSession(false).getAttribute("user") == null) {
			response.sendRedirect("Login.jsp");
			return;
		}
		User user = (User) request.getSession(false).getAttribute("user");
		session.removeAttribute("user");
		session.setAttribute("user", user);
		ArrayList<String> photos = (ArrayList<String>) request.getSession().getAttribute("photos");
		ArrayList<Book> books = (ArrayList<Book>) request.getSession().getAttribute("userBooks");
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
				<input type="text" value="" name="search" style="width: 250px;" /> <br>
				<label> <input type="radio" name="radio" value="name"
					checked="checked">Name
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
			<a href="Login.jsp"> <%
 	if (request.getSession(false).getAttribute("user") != null) {
 		out.println("Log Out");
 	} else {
 		out.println("Log in");
 	}
 %>
			</a>
		</div>
	</div>

	<div id="profileBox">
		<div style="display: table-row;">
			<div style="display: table-cell; width: 400px; vertical-align: top;">
				Name:<b><%=user.getFirstName()%></b> <br> Last Name:<b><%=user.getLastName()%></b>
				<br> Email:<b><%=user.geteMail()%></b>
				<hr>
				Add new book:
				<form action="AddBook.jsp">
					<input type="submit" value="Add book">
				</form>
				<hr>
				Update Profile Picture:
				<form action="./UpdatePicture" method="post"
					enctype="multipart/form-data">
					<input name="mail" type="hidden" value="<%=user.geteMail()%>">
					<input name="image" type="file" /> <input type="submit"
						value="Update Picture">
				</form>
				<hr>
				Add new picture in gallery:
				<form action="./AddPicture" method="post"
					enctype="multipart/form-data">
					<input name="mail" type="hidden" value="<%=user.geteMail()%>">
					<input name="image" type="file" /> <input type="submit"
						value="AddPicture">
				</form>
				<hr>
				<%
				if (request.getAttribute("errorMessageGallery") != null) {
					out.println("<p style='color:red'> " + request.getAttribute("errorMessageGallery") + "</p>");
				}
				if (request.getAttribute("errorMessageProfile") != null) {
					out.println("<p style='color:red'> " + request.getAttribute("errorMessageProfile") + "</p>");
				}
				%>
			</div>
			<div style="display: table-cell; width: 300px;"
				action="./DisplayPicture" method="get">
				<a href="./DisplayPicture?picture=<%=user.getProfilePicture()%>"
					data-lightbox="<%=user.getProfilePicture()%>"> <img
					src="./DisplayPicture" width="300" height="320"></a> <br>
			</div>
		</div>
		<div style="display: table-row;">
			<h3>Uploaded Books</h3>
			<%
				for (Book k : books) {
			%>
			<a href="./GetBook?title=<%=k.getName()%>&author=<%=k.getAutor()%>"><img
				src="./DisplayPictureBook?picture=<%=k.getImage()%> " width="50"
				height="50"> <%
 	}
 %>
		</div>
		<div id="pictues" style="display: table-row;">
			<h3>Photos</h3>
			<%
				for (String s : photos) {
			%>
			<a href="UserPhotos.jsp"><img
				src="./DisplayPicture?picture=<%=s%> " width="50" height="50">
			</a>
			<%
				}
			%>
		</div>
	</div>

	<div id="footer"></div>
	<script src="js/lightbox-plus-jquery.js"></script>
</body>
</html>