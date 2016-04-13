package bg.ereads.classes;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import org.apache.tomcat.util.http.fileupload.FileItem;
import org.apache.tomcat.util.http.fileupload.FileItemFactory;
import org.apache.tomcat.util.http.fileupload.FileUploadException;
import org.apache.tomcat.util.http.fileupload.RequestContext;
import org.apache.tomcat.util.http.fileupload.disk.DiskFileItemFactory;
import org.apache.tomcat.util.http.fileupload.servlet.ServletFileUpload;
import org.apache.tomcat.util.http.fileupload.servlet.ServletRequestContext;

import bg.ereads.dao.BookDao;
import bg.ereads.dao.IBookDao;
import bg.ereads.dao.IUserBookDao;
import bg.ereads.dao.UserBookDao;

@WebServlet("/AddBook")
@MultipartConfig
public class AddBook extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String PATH = "/images";

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		IBookDao dao = new BookDao();
		IUserBookDao dao1 = new UserBookDao();
		User user = (User) request.getSession().getAttribute("user");
		String email = user.geteMail();
		String title = request.getParameter("title");

		String author = request.getParameter("author");
		String description = request.getParameter("description");
		String genre = request.getParameter("genre");
		String linkToBuy = request.getParameter("linkToBuy");
		PrintWriter out = response.getWriter();
		Part part = request.getPart("image");
		String fileName = null;
		String name = part.getName();

		String contentType = part.getContentType();

		if (!contentType.contains("image")) {
			RequestDispatcher dispatcher = request.getRequestDispatcher("./AddBook.jsp");
			request.setAttribute("errorMessageBook", "You can't add that file as profile picture!");
			dispatcher.forward(request, response);
		} else {
			InputStream is = request.getPart(name).getInputStream();

			// to implement later(geting path from servlet

			String contextPath = "E:\\ProjectFiles\\book_images";

			File uploadDir = new File(contextPath);
			File file = File.createTempFile(title, ".jpg", uploadDir);
			fileName = file.getName();
			FileOutputStream fos = new FileOutputStream(file);

			int data = 0;
			while ((data = is.read()) != -1) {
				fos.write(data);
			}
			fos.close();
			file.renameTo(file);
			if (fileName == null || fileName.equals("")) {
				fileName = "default.jpg";
			}
			Book book = new Book(title, author, fileName, description, genre, linkToBuy);

			try {
				if (dao.checkBook(title, author) == true) {
					dao.addBook(book);
					dao1.bookAddedToUser(email, title, author, fileName);
					RequestDispatcher dispatcher = request.getRequestDispatcher("./AddBook.jsp");
					request.setAttribute("success", "The book is added!");
					ArrayList<Book> userBooks = (ArrayList<Book>) request.getSession().getAttribute("userBooks");
					userBooks.add(book);
					ArrayList<String> photos = (ArrayList<String>) request.getSession().getAttribute("photos");
					request.getSession().invalidate();
					request.getSession().setAttribute("user", user);
					request.getSession().setAttribute("photos", photos);
					request.getSession().setAttribute("userBooks", userBooks);
					dispatcher.forward(request, response);
				} else {
					RequestDispatcher dispatcher = request.getRequestDispatcher("./AddBook.jsp");
					request.setAttribute("invalidAdd", "The book is already added!");
					dispatcher.forward(request, response);
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		
	}

}
