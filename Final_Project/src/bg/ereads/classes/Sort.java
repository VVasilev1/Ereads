package bg.ereads.classes;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/Sort")
public class Sort extends HttpServlet implements Comparator {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String type = request.getParameter("radio");
		ArrayList<Book> b = (ArrayList<Book>) request.getSession().getAttribute("books");

		if (type.equals("accending")) {
			Collections.sort(b, new Ascending());
		}
		if (type.equals("descending")) {
			Collections.sort(b, new MyComparator());
		}
		if (type.equals("accRate")) {
			Collections.sort(b, new AsendingRating());
		}
		if (type.equals("desRate")) {
			Collections.sort(b, new DescendingRating());
		}

		RequestDispatcher dispatcher = request.getRequestDispatcher("./Search.jsp");

		request.getSession().setAttribute("books", b);
		response.sendRedirect("Search.jsp");
	}

	@Override
	public int compare(Object o1, Object o2) {
		String first = ((Book) o1).getName();
		String second = ((Book) o2).getName();

		if (first.compareToIgnoreCase(second) > 0) {
			return -1;
		}
		if (first.compareToIgnoreCase(second) < 0) {
			return 1;
		}
		if (first.compareToIgnoreCase(second) == 0) {
			return 0;
		}
		return 0;
	}

}

// <% /*
// <div id="sort">
// <p>Hello</p>
// <script src="http://code.jquery.com/jquery-latest.js"></script>
// <button onclick=
// <%Collections.sort(books, new MyComparator());
// %>
// "jQuery('#searchBox').load(' #searchBox');">Reload</button>
// </div>
// */
// %>
