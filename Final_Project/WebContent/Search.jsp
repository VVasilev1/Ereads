<%@page import="bg.ereads.classes.MyComparator" import ="java.util.Comparator"  import="java.util.Arrays" import="java.util.Collections"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" 
import="java.util.ArrayList" import="bg.ereads.classes.Book" import="java.text.DecimalFormat"
import="bg.ereads.classes.User"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=windows-1251">
<title>eReads.Search</title>
<link rel="stylesheet" type= "text/css" href="css/Profile.css">
<link rel="stylesheet" type= "text/css" href="css/main.css">
<style type="text/css">
#sort {
	position:relative;
	margin:100px auto 200px auto;
}

#searchBox {
	display:table;
	margin:150px auto 300px auto ;
	width:810px;
	min-height:200px;
	left:0px;
	right:0px;
	position:relative;
	background:#F4F1EA;
	border:1px solid #000000;
	padding:5px;
}
#searchBox hr {
	border:1px solid #999;
}
#searchBox div {
	display:table;
	border:1px solid #000;
	margin:-1px;
}
#searchBox div img, #searchBox div span {
	display:table-cell;
}
#searchBox div img {
	width:50px;
}
#searchBox div span {
	vertical-align:top;
	width:100%;
	padding:5px;
}
#searchBox div h4 {
	margin:0px;
}
</style>
</head>
<body>
<%
	response.setHeader( "Pragma", "no-cache" );
	response.setHeader( "Cache-Control", "no-cache" );
	response.setDateHeader( "Expires", 0 );
	User user= null;
	if (request.getSession(false).getAttribute("user") == null) { 
		user = new User();
		user.setFirstName("Profile");
	} else {
		user = (User) request.getSession(false).getAttribute("user");
	}
	ArrayList<Book> books = null;
	if (request.getSession().getAttribute("books") == null) {
 		books = (ArrayList<Book>) request.getAttribute("Search");
	} else {
		books = (ArrayList<Book>) request.getSession().getAttribute("books");
	}
	request.getSession().setAttribute("books", books);
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
            <input type="text"  name = "search" style="width:250px;" value="">
            <br>
            <label>
                <input type="radio" name="radio" value="name" checked="checked"
                >Name
            </label>
            <label>
                <input type="radio" name="radio" value="author"
                >Author
            </label>		
            <label>
                <input type="radio" name="radio" value="genre"
                >Genre
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

<div id="sort">
	<form action="./Sort" method="get">  
		<input type="radio" name="radio" value="accending" checked="checked">Ascending
		<input type="radio" name="radio" value="descending">Descending
		<input type="submit" value="Sort" class="submitButton"/>
		<br>
	</form>
</div>

<div id="searchBox">
	<h2>Result</h2>
	<%
		for (Book k : books) {
		double rating = ((double)k.getNumberOfVotes())/k.getSumOfVotes();
		DecimalFormat dRating = new DecimalFormat("#.0");
	%>
    <div>
		<div><%System.out.println(k.getImage());%>
        	<img src="./DisplayPictureBook?picture=<%=k.getImage()%> "width="50" height="50">
        	<br>
        </div>
        <form>
	       	<span>
		        <a href="./GetBook?title=<%= k.getName()%>&author=<%= k.getAutor()%>"><h3><%=k.getName()%></h3></a>
		        <br>
		        <h4><%=k.getAutor() %></h4>
		        <br>
		        <h4><%=k.getGenre() %></h4>
		        <br>
		    </span>
        </form>
    </div>
    <%
    	}
   	%>
   	<!--
    <span>
    	Page
    	<a href="Search.jsp?pageNumber=1">1</a>
    	<a href="Search.jsp?pageNumber=2">2</a>
    </span>
    -->
</div>

<div id="footer">
</div>

</body>
</html>