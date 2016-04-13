package bg.ereads.classes;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.common.hash.Hashing;

import bg.ereads.dao.UserDao;

/**
 * Servlet implementation class Reg
 */
@WebServlet("/Reg")
public class Reg extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String firstName = request.getParameter("firstname");
		String lastName = request.getParameter("lastname");
		String eMail = request.getParameter("email");
		String password = request.getParameter("password");
		firstName = firstName.trim();
		lastName = lastName.trim();
		String hashPassword = Hashing.sha256().hashString(password, StandardCharsets.UTF_8).toString();
		if (password.length() < 6) {
			RequestDispatcher dispatcher = request.getRequestDispatcher("./Register.jsp");
			request.setAttribute("passwordError", "Password must me atleat 6 characters long.");
			dispatcher.forward(request, response);
		}
		User user;
		UserDao dao = new UserDao();
		try {
			if (dao.checkEmail(eMail) == true) {
				dao.registerUser(firstName, lastName, eMail, hashPassword);
				RequestDispatcher dispatcher = request.getRequestDispatcher("./Login.jsp");
				request.setAttribute("registered", "Registration complete. Log in here.");
				dispatcher.forward(request, response);
			} else {
				RequestDispatcher dispatcher = request.getRequestDispatcher("./Register.jsp");
				request.setAttribute("invalidEmail", "E-mail already in use. Please chose another one.");
				dispatcher.forward(request, response);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
