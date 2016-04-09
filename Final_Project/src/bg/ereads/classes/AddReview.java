package bg.ereads.classes;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bg.ereads.dao.BookDao;
import bg.ereads.dao.IBookDao;

/**
 * Servlet implementation class AddReview
 */
@WebServlet("/AddReview")
public class AddReview extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		IBookDao dao = new BookDao();
		Book book = (Book) request.getSession().getAttribute("book");
		User user = (User) request.getSession().getAttribute("user");
		String title = book.getName();
		String author = book.getAutor();
		String review = request.getParameter("review");
		String mail = user.geteMail();
		try {
			dao.reviewBook(title, author, review, mail);
			RequestDispatcher dispatcher = request.getRequestDispatcher("./BookInfo.jsp");
			request.setAttribute("success", "Added new review!");
			 request.getSession().invalidate();
		    request.getSession().setAttribute("user",user);
		    request.getSession().setAttribute("book",book);
		    response.sendRedirect("./ShowComments");
			
			//dispatcher.forward(request, response);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
