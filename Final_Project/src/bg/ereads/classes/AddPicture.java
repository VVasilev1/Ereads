package bg.ereads.classes;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import bg.ereads.dao.IPictureDao;
import bg.ereads.dao.PictureDao;
import bg.ereads.dao.UserDao;

@WebServlet("/AddPicture")
@MultipartConfig
public class AddPicture extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		IPictureDao dao = new PictureDao();
		PrintWriter out = response.getWriter();
		Part part = request.getPart("image");
		String mail = request.getParameter("mail");
		mail = mail.replace('.', '0');
		String fileName = null;
		String name = part.getName();

		String contentType = part.getContentType();

		if (!contentType.contains("image")) {
			

			RequestDispatcher dispatcher = request.getRequestDispatcher("./Profile2.jsp");
			request.setAttribute("errorMessageGallery", "You can't add that file to the gallery!");
			dispatcher.forward(request, response);

		} else {
			InputStream is = request.getPart(name).getInputStream();

			String contextPath = "E:\\ProjectFiles\\profile_images";
			File uploadDir = new File(contextPath);

			File file = File.createTempFile(mail, ".jpg", uploadDir);
			File file2 = new File(contextPath);

			fileName = file.getName();

			FileOutputStream fos = new FileOutputStream(file);

			int data = 0;
			while ((data = is.read()) != -1) {
				fos.write(data);
			}
			try {
				dao.addPicture(fileName, request.getParameter("mail"));
			} catch (SQLException e) {
				e.printStackTrace();
			}
			file.renameTo(file);

			fos.close();
			User user = (User) request.getSession().getAttribute("user");
			ArrayList<String> photos = (ArrayList<String>) request.getSession().getAttribute("photos");
			ArrayList<Book> books = (ArrayList<Book>) request.getSession().getAttribute("userBooks");
			photos.add(fileName);
			request.getSession().invalidate();
			request.getSession().setAttribute("user", user);
			request.getSession().setAttribute("photos", photos);
			request.getSession().setAttribute("userBooks", books);
			response.sendRedirect("Profile2.jsp");

		}
	}

}
