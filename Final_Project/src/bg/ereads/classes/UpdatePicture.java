package bg.ereads.classes;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import bg.ereads.dao.UserDao;


@WebServlet("/UpdatePicture")
@MultipartConfig
public class UpdatePicture extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
  
   

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("hi");
		UserDao dao = new UserDao();
		PrintWriter out = response.getWriter();
		Part part = request.getPart("image");
		String mail = request.getParameter("mail");
		mail = mail.replace('.', '0');
		String fileName = null;
        String name = part.getName();
        System.out.println(name+"here");
        String contentType = part.getContentType();
        System.out.println(contentType);
        if(!contentType.contains("image") ) {
            out.println("Only png format supported!");
            
        } else {
	        InputStream is = request.getPart(name).getInputStream();
	      
    		String contextPath = "E:\\ProjectFiles\\profile_images";
	        File uploadDir = new File(contextPath);
	        
	        File file = File.createTempFile(mail, ".jpg", uploadDir);
	        File file2 = new File(contextPath);
	        
	         fileName = file.getName();
	         System.out.println(fileName);
	         System.out.println(mail);
	        FileOutputStream fos = new FileOutputStream(file);
	
	        int data = 0;
	        while ((data = is.read()) != -1) {
	            fos.write(data);
	        }
	        try {
				dao.changeProfileImage(fileName,request.getParameter("mail"));
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	       file.renameTo(file);
	       
	       
	       User s= (User) request.getSession().getAttribute("user");
	       s.setProfilePicture(fileName);
	       request.getSession().invalidate();
	       request.getSession().setAttribute("user",s);
	        
	        fos.close();
	        response.sendRedirect("Profile2.jsp");
	       
        }
	}

}

