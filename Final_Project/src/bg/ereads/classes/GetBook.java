package bg.ereads.classes;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bg.ereads.dao.BookDao;

/**
 * Servlet implementation class GetBook
 */
@WebServlet("/GetBook")
public class GetBook extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String title = (String) request.getParameter("title");

		String author = (String) request.getParameter("author");

		String genre = request.getParameter("genre");
		String description = request.getParameter("description");
		String rating = request.getParameter("rating");
		String image = request.getParameter("image");
		BookDao dao = new BookDao();
		Book b = null;
		try {
			b = dao.getBook(title, author);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		request.getSession().setAttribute("book", b);

		response.sendRedirect("./ShowComments");
		// getServletConfig().getServletContext().getRequestDispatcher("/BookInfo.jsp").forward(request,response);
	}

}
