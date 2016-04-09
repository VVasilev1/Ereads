package bg.ereads.dao;

import java.sql.SQLException;
import java.util.List;

import bg.ereads.classes.Book;
import bg.ereads.exceptions.DBException;

public interface IBookDao {
	void addBook(Book b) throws SQLException;
	void updateBook(Book b);
	void removeBook(Book b) throws SQLException;
	List<Book> getAllBook() throws SQLException, DBException;
	Book getBook(String title, String author) throws SQLException;
	List<Book> getBookByName(String title) throws SQLException;
	List<Book> getBookByGenre(String genre) throws SQLException;
	List<Book> getBookByAuthor(String name) throws SQLException;
	boolean checkBook(String title, String author) throws SQLException;
	void rateBook(String title, String rating) throws SQLException;
	void reviewBook(String title, String author, String review, String mail) throws SQLException;
}
