package bg.ereads.classes;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.common.hash.Hashing;

import bg.ereads.dao.IPictureDao;
import bg.ereads.dao.IUserBookDao;
import bg.ereads.dao.IUserDao;
import bg.ereads.dao.PictureDao;
import bg.ereads.dao.UserBookDao;
import bg.ereads.dao.UserDao;
import bg.ereads.exceptions.DBException;
import bg.ereads.exceptions.InvalidUserException;

/**
 * Servlet implementation class Log
 */
@WebServlet("/Log")
public class Log extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		IUserDao dao = UserDao.getInstance();
		IPictureDao dao2 = new PictureDao();
		IUserBookDao dao3 = new UserBookDao();
		String hashPassword = Hashing.sha256().hashString(password, StandardCharsets.UTF_8).toString();
		User user;
		try {
			user = dao.loginUser(email, hashPassword);

			if (user.geteMail() != null) {
				ArrayList<String> pictures = dao2.photos(email);
				ArrayList<Book> books = dao3.bookToUser(email);
				request.getSession().setAttribute("photos", pictures);
				request.getSession().setAttribute("userBooks", books);
				request.getSession().setAttribute("user", user);
				response.sendRedirect("Profile2.jsp");
			} else {
				RequestDispatcher dispatcher = request.getRequestDispatcher("./Login.jsp");
				request.setAttribute("errorMessage", "Invalid E-mail or password!");
				dispatcher.forward(request, response);
			}

		} catch (InvalidUserException e) {
			e.printStackTrace();
		} catch (DBException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
