package bg.ereads.test;

import static org.junit.Assert.*;

import java.sql.SQLException;
import java.util.List;

import org.junit.Test;

import bg.ereads.classes.Book;
import bg.ereads.classes.User;
import bg.ereads.connection.DBConnection;
import bg.ereads.dao.BookDao;
import bg.ereads.dao.UserDao;
import bg.ereads.exceptions.DBException;

public class TestDao {

	@Test
	public void testConnection() {
		assertNotNull(DBConnection.getInstance().getConn());
	}

	@Test
	public void testAdd() throws SQLException {
		BookDao dao = new BookDao();
		dao.addBook(new Book("Java EE", "Niki", "java.jpg", "Good BOok", "Education", "google.bg"));
	}

	@Test
	public void testByTitle() throws SQLException {
		BookDao dao = new BookDao();
		List<Book> list = dao.getBookByName("j");
		for (Book b : list) {
			System.out.println(b);
		}
	}

	@Test
	public void testByGenre() throws SQLException {
		BookDao dao = new BookDao();
		List<Book> list = dao.getBookByGenre("tio");
		for (Book b : list) {
			System.out.println(b);
		}
	}

	@Test
	public void testByAutor() throws SQLException {
		BookDao dao = new BookDao();
		List<Book> list = dao.getBookByAuthor("ki");
		for (Book b : list) {
			System.out.println(b);
		}
	}

	@Test
	public void testShowAll() throws SQLException, DBException {
		BookDao dao = new BookDao();
		List<Book> list = dao.getAllBook();
		for (Book b : list) {
			System.out.println(b);
		}
	}

	@Test
	public void testUsers() throws SQLException, DBException {
		UserDao dao = new UserDao();
		List<User> result = dao.getAllUsers();
		for (User u : result) {
			System.out.println(u.geteMail());
		}
	}

	@Test
	public void testDelete() throws SQLException {
		BookDao dao = new BookDao();
		dao.removeBook(new Book("Java EE", "Niki", "java.jpg", "Good BOok", "Education", "google.bg"));
	}

}
