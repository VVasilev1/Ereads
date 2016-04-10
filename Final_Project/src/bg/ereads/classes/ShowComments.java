package bg.ereads.classes;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bg.ereads.dao.IReviewDao;
import bg.ereads.dao.ReviewDao;

/**
 * Servlet implementation class ShowComments
 */
@WebServlet("/ShowComments")
public class ShowComments extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		Book book = (Book) request.getSession().getAttribute("book");
		IReviewDao dao = new ReviewDao();
		List<Review> reviews = null;
		try {
			 reviews = dao.getReviews(book.getName(), book.getAutor());
			 request.getSession().setAttribute("reviews", reviews);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		response.sendRedirect("BookInfo.jsp");
	} 

}
