package bg.ereads.classes;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class DisplayPicture
 */
@WebServlet("/DisplayPicture")
public class DisplayPicture extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("image/jpeg");

		String picture = null;
		if (request.getSession().getAttribute("user") == null) {
			picture = "default.jpg";
		} else {
			User user = (User) request.getSession().getAttribute("user");
			picture = user.getProfilePicture();
		}
		if (request.getParameter("picture") != null) {
			picture = request.getParameter("picture");
		}

		ServletOutputStream out;
		out = response.getOutputStream();

		FileInputStream fin = new FileInputStream("E:\\ProjectFiles\\profile_images\\" + picture);

		BufferedInputStream bin = new BufferedInputStream(fin);
		BufferedOutputStream bout = new BufferedOutputStream(out);
		int ch = 0;
		;
		while ((ch = bin.read()) != -1) {
			bout.write(ch);
		}

		bin.close();
		fin.close();
		bout.close();
		out.close();

	}

}
