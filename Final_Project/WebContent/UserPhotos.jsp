<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"  import="bg.ereads.classes.Book"
import="bg.ereads.classes.MyComparator" import ="java.util.Comparator"  import="java.util.Arrays" import="java.util.Collections"
import="bg.ereads.classes.User" import="java.util.ArrayList"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<link rel="stylesheet" type= "text/css" href="css/Profile.css">
<link rel="stylesheet" type= "text/css" href="css/main.css">
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
	ArrayList<String> photos = (ArrayList<String>)request.getSession().getAttribute("photos");
	ArrayList<Book> books = (ArrayList<Book>)request.getSession().getAttribute("userBooks");
%>

<div id="searchBar">
	<div>
		<img src= "Logo.jpg" width="120px" height = "60px"> 
	</div>
	<div>
		<h1>GoodBook</h1>
	</div>
	<div>
        <form action="./Search" method="get">
            <input type="text" value="" name="search" style="width:250px;"/>
            <br>
            <label>
                <input type="radio" name="radio" value="name" checked="checked">Name
            </label>
            <label>
                <input type="radio" name="radio" value="author">Author
            </label>		
            <label>
                <input type="radio" name="radio" value="genre">Genre
            </label>		
        </form>
    </div>
    <div>
		<div action="./DisplayPicture" method="get">
        	<img src="./DisplayPicture" width="30" height="30">
        	<br>
        </div>
	</div>
	<div>
		<a href="Profile2.jsp"><%=user.getFirstName()%></a>
	</div>
	<div>
		<a href="BooksByGenre.jsp">Books</a>
	</div>
	<div>
		<a href="Login.jsp">
		<% 
			if(request.getSession(false).getAttribute("user") != null) {
				out.println("Log Out");
			} 
			else {
				out.println("Log in");
			}
		%>
		</a>
	</div>
</div>
<div id="profileBox">
	<div style="display:table-row;">
    	<div style="display:table-cell;vertical-align:top;">
		<% for(String s: photos) {
		%>
			<img src="./DisplayPicture?picture=<%=s%> "width="190" height="190">
	
		<% 
		}%>
		</div>
	</div>
</div>
</body>
</html>