<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" import="bg.ereads.classes.User"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=windows-1251">
<title>Insert title s</title>
<link rel="stylesheet" type= "text/css" href="css/Profile.css">
<link rel="stylesheet" type= "text/css" href="css/main.css">

</head>
<body>

<%
response.setHeader("Pragma", "No-cache");
response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
response.setDateHeader("Expires", -1);
%>

<% 
if (request.getSession(false).getAttribute("user") == null) { 
	response.sendRedirect("Login.jsp");
	return;
}
User user = (User) request.getSession(false).getAttribute("user");
session.removeAttribute("user");
session.setAttribute("user", user);
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
            <input type="text" value="" name = "search" style="width:250px;"/><br />

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
		<a href="Login.jsp"><% if(request.getSession(false).getAttribute("user") != null) {
			out.println("Log Out");
		} 
		else {
			out.println("Log in");
		}
			
			
			
			
			%></a>
	</div>
</div>
	

<div id="profileBox">
	<div style="display:table-row;">
    	<div style="display:table-cell;width:400px;vertical-align:top;">
        	Name:<b><%=user.getFirstName() %></b><br>
            Last Name:<b><%=user.getLastName() %></b><br>
            Email:<b><%=user.geteMail() %></b><br><br>
			<form action="AddBook.jsp">
				
            	<input type="submit" value="Add book">
            </form>
            <form action="./UpdatePicture" method="post" enctype="multipart/form-data">
            	<input name="mail" type="hidden" value="<%=user.geteMail() %>">
        		<input name="image" type="file"/>
        		<input type="submit" value="Update Picture">
        	</form>
        </div>
    	<div style="display:table-cell;width:400px;" action="./DisplayPicture" method="get">
        	<img src="./DisplayPicture" width="400" height="350"><br>
        	
        </div>
    </div>
	<div style="display:table-row;">
    	<h3>Readed books.</h3>
        book 1<br>
        book 2
    </div>
	<div style="display:table-row;">
    	<h3>Reviewed.</h3>
        book 1<br>
        book 2
    </div>
</div>
	
<div id="footer">
</div>
</body>
</html>