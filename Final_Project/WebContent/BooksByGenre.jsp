<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" import="bg.ereads.classes.Book"
	import="bg.ereads.classes.User"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type"
	content="text/html; charset=windows-1251">
<title>eReads.SearchByGenre</title>
<link rel="stylesheet" type="text/css" href="css/Profile.css">
<link rel="stylesheet" type="text/css" href="css/main.css">
<style type="text/css">
#searchBox {
	display: table;
	margin: 150px auto 300px auto;
	width: 810px;
	min-height: 200px;
	left: 0px;
	right: 0px;
	position: relative;
	background: #F4F1EA;
	border: 1px solid #000000;
	padding: 5px;
}
</style>
</head>
<body>

	<%
		User user = null;
		if (request.getSession(false).getAttribute("user") == null) {
			user = new User();
			user.setFirstName("Profile");
		} else {

			user = (User) request.getSession(false).getAttribute("user");
		}
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
				<input type="text" name="search" style="width: 250px;" value="" /><br>
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
			<%
				String log = null;
				if (user.getFirstName().equals("Profile")) {
					log = "Login";
				} else {
					log = "Logout";
				}
			%>
			<a href="Login.jsp"><%=log%></a>
		</div>
	</div>

	<div id="searchBox">
		<h2>Categories</h2>
		<div>
			<form action="./SearchByRadio" method="get"
				style="display: table-row;">
				<label>
					<h3>
						<input type="radio" name="radio" value="fantasy">Fantasy<input
							type="radio" name="radio" value="action">Action<br>
						<img src="./DisplayPicture?picture=Fantasy.jpg " width="135"
							height="200"> <img
							src="./DisplayPicture?picture=Action.jpg " width="135"
							height="200">
					</h3>
				</label> <label>
					<h3>
						<input type="radio" name="radio" value="drama">Drama <input
							type="radio" name="radio" value="graphicNovel">Graphic
						novel<br> <img src="./DisplayPicture?picture=Drama.jpg "
							width="135" height="200"> <img
							src="./DisplayPicture?picture=GraphicNovel.jpg " width="135"
							height="200">
					</h3>
				</label> <label>
					<h3>
						<input type="radio" name="radio" value="western">Western<input
							type="radio" name="radio" value="mystery">Mystery
					</h3> <img src="./DisplayPicture?picture=Western.jpg " width="135"
					height="200"> <img
					src="./DisplayPicture?picture=Mystery.jpg " width="135"
					height="200">
				</h3>
				</label> <label>
					<h3>
						<input type="radio" name="radio" value="comedy">Comedy <input
							type="radio" name="radio" value="education">Education
					</h3> <img src="./DisplayPicture?picture=Comedy.jpg " width="135"
					height="200"> <img
					src="./DisplayPicture?picture=Education.jpg " width="135"
					height="200">
				</h3>
				</label><br> <input type="submit" value="Search">
			</form>
		</div>
	</div>

</body>
</html>