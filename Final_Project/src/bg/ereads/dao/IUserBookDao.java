package bg.ereads.dao;

import java.sql.SQLException;
import java.util.ArrayList;

import bg.ereads.classes.Book;

public interface IUserBookDao {
	void bookAddedToUser(String email, String title, String author, String picture) throws SQLException;

	ArrayList<Book> bookToUser(String email) throws SQLException;
}
