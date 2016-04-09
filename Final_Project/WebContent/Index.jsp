<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" import="bg.ereads.classes.User" import="java.util.ArrayList" import="bg.ereads.classes.Book"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<link rel="stylesheet" type= "text/css" href="css/main.css">
</head>
<body>
<% 
User user = null;
if(session.getAttribute("user") == null) { 
	user = new User();
	user.setFirstName("Profile");
} else {
	user = (User) session.getAttribute("user");
}
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
		<a href="Register.jsp">Register</a>
	</div>
	<div>
		<a href="Login.jsp">Login</a>
	</div>
</div>
</body>
</html>