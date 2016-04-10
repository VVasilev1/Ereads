<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" import="bg.ereads.classes.User" import="bg.ereads.classes.Book"
import="java.text.DecimalFormat" import="java.util.ArrayList" import="bg.ereads.classes.Review"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=windows-1251">
<title>eReads.SearchByGenre</title>
<link rel="stylesheet" type= "text/css" href="css/Profile.css">
<link rel="stylesheet" type= "text/css" href="css/main.css">
<style type="text/css">
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
</style>
</head>
<body>

<%
	User user= null;

	if (request.getSession(false).getAttribute("user") == null) { 
		user = new User();
		user.setFirstName("Profile");
	} else {
		user = (User) request.getSession(false).getAttribute("user");
	}
	
	Book book = (Book)request.getSession().getAttribute("book");
	
	if (book == null) {
		System.out.println("hi");
	}
	
	ArrayList<Review> reviews = null;
	if (request.getSession().getAttribute("reviews") == null) {
		System.out.println("prazno e");
	 reviews = new ArrayList<Review>();
	} else {
		reviews = (ArrayList<Review>) request.getSession().getAttribute("reviews");
	}
	
	DecimalFormat dRating = new DecimalFormat("#.0");
	
	response.setHeader("Pragma", "No-cache");
	response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
	response.setDateHeader("Expires", -1);
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
		<a href="Profile2.jsp"><%=user.getFirstName()%></a>
	</div>
	<div>
		<a href="BooksByGenre.jsp">Books</a>
	</div>
	<div>
		<a href="Login.jsp"><%
			if(request.getSession(false).getAttribute("user") != null) {
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
	<div style="display:table-cell;width:250px;">
    	<img src="./DisplayPictureBook?picture=<%=book.getImage() %>" width="250" height="400">
    </div>
	<div style="display:table-cell;width:400px;vertical-align:top;">
		Title:<b><%=book.getName() %></b><br>
		Author:<b><%=book.getAutor() %></b><br>
		Genre:<b><%=book.getGenre() %></b><br>
		Buy from:<b><a href=<%=book.getLinkToBuy()%>>Here</a></b><br>
		Description:<b><%=book.getDescription()%></b><br>
		Rating:<b><%=dRating.format(book.getRating()) %></b><br>
     	<%
     	if(request.getSession(false).getAttribute("user")!=null) {
			out.println("Rate the book:<b>"+
				"<form name='rating' action='./RateBook' method='post'>"+
					"Give rate:"+
					"<select name='rate' value='rate'>"+
					    "<option value='1'>1</option>"+
					    "<option value='2'>2</option>"+
					    "<option value='3'>3</option>"+
					    "<option value='4'>4</option>"+
					    "<option value='5'>5</option>"+
					"</select>"+
					"<input type='submit' value='Add rating'>"+
				"</form>"+
				"<form name='review' action='."+"/"+"AddReview' method='post'>"+
					"<label>Comment</label><br/>" +
					"<textarea name='review' cols='100' rows='5' required></textarea><br />"+
					"<input type='submit' value='Add comment'><br>"+
				"</form>");
	     }
	     %>
	     
    </div>
	</div>
</div>
<div id="Reviews">
			<h2>Comments</h2>
				<span>
				<%
				for(Review review : reviews){
					out.println(
						"<a href='./GetUser?image="+review.getPicture()+"'><img src='./DisplayPicture?picture=" + review.getPicture() + " 'width='50' height='50'>" +
						review.getName() + " :" +
						review.getComment() + "<br> </a>");
				}
				%>
			</span>
		</div>
<div id="footer">
</div>

</body>
</html>