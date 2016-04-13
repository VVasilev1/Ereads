<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" import="bg.ereads.classes.User"
	import="java.util.ArrayList" import="bg.ereads.classes.Book"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type"
	content="text/html; charset=windows-1251">
<title>Insert title s</title>
<link rel="stylesheet" type="text/css" href="css/Profile.css">
<link rel="stylesheet" type="text/css" href="css/main.css">
<link rel="stylesheet" type="text/css" href="css/lightbox.css">

</head>
<body>

	<%
		response.setHeader("Pragma", "No-cache");
		response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
		response.setDateHeader("Expires", -1);
	%>

	<%
		User user = null;
		if (request.getSession(false).getAttribute("user") == null) {
			user = new User();
			user.setFirstName("Profile");
		} else {
			user = (User) request.getSession(false).getAttribute("user");
		}
		User person = (User) request.getSession().getAttribute("person");

		request.setAttribute("personPicture", person.getProfilePicture());
		ArrayList<String> pictures = (ArrayList<String>) request.getSession().getAttribute("personPictures");
		ArrayList<Book> books = (ArrayList<Book>) request.getSession().getAttribute("personBooks");
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
				<input type="text" value="" name="search" style="width: 250px;" /><br />

				<label> <input type="radio" name="radio" value="name"
					checked="checked">Name
				</label> <label> <input type="radio" name="radio" value="author">Author
				</label> <label> <input type="radio" name="radio" value="genre">Genre
				</label>
			</form>
		</div>
		<div>
			<div action="./DisplayPicture" method="get">
				<img src="./DisplayPicture" width="30" height="30"><br>

			</div>
		</div>
		<div>
			<a href="Profile2.jsp"><%=user.getFirstName()%> </a>
		</div>
		<div>
			<a href="BooksByGenre.jsp">Books</a>
		</div>
		<div>
			<a href="Login.jsp">
				<%
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
				Name:<b><%=person.getFirstName()%></b><br> Last Name:<b><%=person.getLastName()%></b><br>
				Email:<b><%=person.geteMail()%></b><br>
				<br>


			</div>
			<div style="display: table-cell; width: 400px;"
				action="./DisplayPicture?picture=<%=person.getProfilePicture()%>"
				method="get">
				<!--  <img src="./DisplayPicture?picture=<%=person.getProfilePicture()%>" width="400" height="350"><br>-->
				<a href="./DisplayPicture?picture=<%=person.getProfilePicture()%>"
					data-lightbox="<%=person.getProfilePicture()%>"> <img
					src="./DisplayPicture?picture=<%=person.getProfilePicture()%> "
					width="400" height="350"></a><br>
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
				for (String s : pictures) {
			%>
			<a href="PersonPhotos.jsp"><img
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