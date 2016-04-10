package bg.ereads.dao;

import java.sql.SQLException;
import java.util.ArrayList;

public interface IPictureDao {
			
	
	
	void addPicture(String path, String email) throws SQLException;
	ArrayList<String> photos(String email) throws SQLException;
}
