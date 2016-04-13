package bg.ereads.classes;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bg.ereads.dao.BookDao;
import bg.ereads.dao.IBookDao;

/**
 * Servlet implementation class RateBook
 */
@WebServlet("/RateBook")
public class RateBook extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		IBookDao dao = new BookDao();
		Book book = (Book) request.getSession().getAttribute("book");
		User user = (User) request.getSession().getAttribute("user");
		ArrayList<String> photos = (ArrayList<String>) request.getSession().getAttribute("photos");
		ArrayList<Book> userBooks = (ArrayList<Book>) request.getSession().getAttribute("userBooks");
		String title = book.getName();
		String author = book.getAutor();
		String rate = request.getParameter("rate");

		try {
			dao.rateBook(title, author, rate);
			request.getSession().invalidate();
			request.getSession().setAttribute("user", user);
			book.setSumOfVotes(dao.getRate(title, author));
			book.setNumberOfVotes(dao.getVotes(title, author));
			request.getSession().setAttribute("book", book);
			request.getSession().setAttribute("photos", photos);
			request.getSession().setAttribute("userBooks", userBooks);
			response.sendRedirect("./ShowComments");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
