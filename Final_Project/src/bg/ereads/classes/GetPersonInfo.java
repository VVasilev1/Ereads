package bg.ereads.classes;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bg.ereads.dao.IPictureDao;
import bg.ereads.dao.IUserBookDao;
import bg.ereads.dao.PictureDao;
import bg.ereads.dao.UserBookDao;
import bg.ereads.dao.UserDao;

@WebServlet("/GetPersonInfo")
public class GetPersonInfo extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String image = request.getParameter("image");
		UserDao dao = new UserDao();
		User user = null;
		try {
			user = dao.getUser(image);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		IPictureDao dao1 = new PictureDao();
		IUserBookDao dao2 = new UserBookDao();
		ArrayList<String> pictures = new ArrayList<String>();
		ArrayList<Book> books = new ArrayList<Book>();
		try {
			pictures = dao1.photos(user.geteMail());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			books = dao2.bookToUser(user.geteMail());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		request.getSession().setAttribute("personBooks", books);
		request.getSession().setAttribute("personBooks", pictures);
		request.getSession().setAttribute("person", user);
		response.sendRedirect("./UserProfile.jsp");
	}

}
