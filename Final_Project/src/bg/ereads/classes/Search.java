package bg.ereads.classes;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bg.ereads.dao.BookDao;
import bg.ereads.dao.IBookDao;

/**
 * Servlet implementation class Search
 */
@WebServlet("/Search")
public class Search extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.getSession().removeAttribute("books");
		String find = request.getParameter("search");
		String option = request.getParameter("radio");
		List<Book> list = new ArrayList<Book>();
		IBookDao dao = new BookDao();
		try {
			if (option.equals("name")) {
				list = dao.getBookByName(find);
			}
			if (option.equals("author")) {
				list = dao.getBookByAuthor(find);
			}
			if (option.equals("genre")) {
				list = dao.getBookByGenre(find);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		request.setAttribute("Search", list);
		request.setAttribute("radio", option);
		request.setAttribute("searchword", find);

		// response.sendRedirect("SearchResult");
		getServletConfig().getServletContext().getRequestDispatcher("/Search.jsp").forward(request, response);

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

	}

}
