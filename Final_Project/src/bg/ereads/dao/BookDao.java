package bg.ereads.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import bg.ereads.classes.Book;
import bg.ereads.classes.User;
import bg.ereads.connection.DBConnection;
import bg.ereads.exceptions.DBException;
import bg.ereads.exceptions.InvalidUserException;

public class BookDao implements IBookDao {

	private Connection conn = DBConnection.getInstance().getConn();
	private static IBookDao instance;

	public synchronized static IBookDao getInstance() {
		if (instance == null) {
			instance = new BookDao();
		}
		return instance;
	}

	@Override
	public void addBook(Book b) throws SQLException {
		PreparedStatement ps = conn.prepareStatement("INSERT INTO Book VALUES (null, ?, ?, ?,'0',?,?,?,'0');",
				com.mysql.jdbc.PreparedStatement.RETURN_GENERATED_KEYS);
		ps.setString(1, b.getName());
		ps.setString(2, b.getAutor());
		ps.setString(3, b.getImage());
		ps.setString(4, b.getDescription());
		ps.setString(5, b.getGenre());
		ps.setString(6, b.getLinkToBuy());
		ps.executeUpdate();
		ResultSet result = ps.getGeneratedKeys();
		result.next();
	}

	@Override
	public void removeBook(Book b) throws SQLException {
		PreparedStatement ps = conn.prepareStatement(
				"DELETE FROM Book WHERE Name=? AND Author=? AND image=? AND Description=? AND Genre=? AND LinkToBuy=?;",
				com.mysql.jdbc.PreparedStatement.RETURN_GENERATED_KEYS);
		ps.setString(1, b.getName());
		ps.setString(2, b.getAutor());
		ps.setString(3, b.getImage());
		ps.setString(4, b.getDescription());
		ps.setString(5, b.getGenre());
		ps.setString(6, b.getLinkToBuy());
		ps.executeUpdate();
		ResultSet result = ps.getGeneratedKeys();
		result.next();
	}

	@Override
	public List<Book> getAllBook() throws SQLException, DBException {
		Statement stm = conn.createStatement();
		List<Book> list = new ArrayList<Book>();
		Book book = null;
		ResultSet rs = null;
		try {
			rs = stm.executeQuery("Select * FROM Book");
			while (rs.next()) {
				book = new Book();
				book.setName(rs.getString("Name"));
				book.setAutor(rs.getString("Autor"));
				book.setImage(rs.getString("image"));
				book.setGenre(rs.getString("Genre"));
				book.setSumOfVotes(rs.getInt("SumOfVotes"));
				book.setNumberOfVotes(rs.getInt("NumberOfVotes"));
				list.add(book);
			}
		} catch (SQLException e) {
			throw new DBException("Ne stava. Ae chao");
		}
		return list;
	}

	@Override
	public List<Book> getBookByName(String title) throws SQLException {
		PreparedStatement ps = conn.prepareStatement("SELECT * FROM Book WHERE name LIKE ?");
		ps.setString(1, "%" + title + "%");
		List<Book> books = new ArrayList<Book>();
		Book book = null;
		ResultSet rs = ps.executeQuery();
		while (rs.next()) {
			book = new Book();
			book.setName(rs.getString("Name"));
			book.setAutor(rs.getString("Autor"));
			book.setImage(rs.getString("image"));
			book.setGenre(rs.getString("Genre"));
			book.setSumOfVotes(rs.getInt("SumOfVotes"));
			book.setNumberOfVotes(rs.getInt("NumberOfVotes"));
			books.add(book);
		}
		return books;
	}

	@Override
	public List<Book> getBookByGenre(String genre) throws SQLException {
		PreparedStatement ps = conn.prepareStatement("SELECT * FROM Book WHERE Genre LIKE ?");
		ps.setString(1, "%" + genre + "%");
		List<Book> books = new ArrayList<Book>();
		Book book = null;
		ResultSet rs = ps.executeQuery();
		while (rs.next()) {
			book = new Book();
			book.setName(rs.getString("Name"));
			book.setAutor(rs.getString("Autor"));
			book.setImage(rs.getString("image"));
			book.setGenre(rs.getString("Genre"));
			book.setSumOfVotes(rs.getInt("SumOfVotes"));
			book.setNumberOfVotes(rs.getInt("NumberOfVotes"));
			books.add(book);
		}
		return books;
	}

	@Override
	public List<Book> getBookByAuthor(String name) throws SQLException {
		PreparedStatement ps = conn.prepareStatement("SELECT * FROM Book WHERE Autor LIKE ?");
		ps.setString(1, "%" + name + "%");
		List<Book> books = new ArrayList<Book>();
		Book book = null;
		ResultSet rs = ps.executeQuery();
		while (rs.next()) {
			book = new Book();
			book.setName(rs.getString("Name"));
			book.setAutor(rs.getString("Autor"));
			book.setImage(rs.getString("image"));
			book.setGenre(rs.getString("Genre"));
			book.setSumOfVotes(rs.getInt("SumOfVotes"));
			book.setNumberOfVotes(rs.getInt("NumberOfVotes"));
			books.add(book);
		}
		return books;
	}

	@Override
	public boolean checkBook(String title, String author) throws SQLException {
		PreparedStatement ps = conn.prepareStatement("SELECT * FROM book WHERE Name = ? AND Autor = ?");
		ps.setString(1, title);
		ps.setString(2, author);
		ResultSet result = ps.executeQuery();
		if (result.next() == false) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	public void rateBook(String title, String author, String rate) throws SQLException {
		int rating = Integer.parseInt(rate);
		PreparedStatement ps = conn.prepareStatement(
				"UPDATE book SET SumOfVotes = SumOfVotes + ?, NumberOfVotes = NumberOfVotes + 1 WHERE Name = ? and Autor = ?;");
		ps.setInt(1, rating);
		ps.setString(2, title);
		ps.setString(3, author);
		ps.executeUpdate();
	}

	@Override
	public int getRate(String title, String author) throws SQLException {
		int sum = 0;

		PreparedStatement ps = conn.prepareStatement("SELECT SumOfVotes FROM book WHERE Name = ? and Autor = ?;");
		ps.setString(1, title);
		ps.setString(2, author);
		ResultSet result = ps.executeQuery();
		while (result.next()) {
			sum = result.getInt("SumOfVotes");

		}
		return sum;
	}

	@Override
	public int getVotes(String title, String author) throws SQLException {
		int number = 0;
		PreparedStatement ps = conn.prepareStatement("SELECT NumberOfVotes FROM book WHERE Name = ? and Autor = ?;");
		ps.setString(1, title);
		ps.setString(2, author);
		ResultSet result = ps.executeQuery();
		while (result.next()) {
			number = result.getInt("NumberOfVotes");
		}
		return number;
	}

	@Override
	public void reviewBook(String title, String author, String review, String mail) throws SQLException {
		int idUser = 0;
		String name = null;
		String profileImage = null;
		int idBook = 0;
		PreparedStatement ps = conn
				.prepareStatement("SELECT idUser, FirstName, ProfileImage FROM user WHERE Email = ?;");
		ps.setString(1, mail);
		ResultSet rs = ps.executeQuery();
		if (rs.next()) {
			idUser = rs.getInt("idUser");
			name = rs.getString("FirstName");
			profileImage = rs.getString("ProfileImage");
		}
		PreparedStatement ps1 = conn.prepareStatement("SELECT idBook FROM book WHERE Name = ? AND Autor = ?;");
		ps1.setString(1, title);
		ps1.setString(2, author);
		rs = ps1.executeQuery();
		if (rs.next()) {
			idBook = rs.getInt("idBook");
		}
		PreparedStatement ps2 = conn.prepareStatement("INSERT INTO reviews VALUES(null, ?, ?, ?, ?, ?);");
		ps2.setString(1, review);
		ps2.setInt(2, idUser);
		ps2.setInt(3, idBook);
		ps2.setString(4, name);
		ps2.setString(5, profileImage);
		ps2.executeUpdate();
	}

	@Override
	public Book getBook(String title, String author) throws SQLException {
		Statement stm = conn.createStatement();
		Book book = null;
		ResultSet rs = null;
		PreparedStatement ps1 = conn.prepareStatement("SELECT * FROM book WHERE Name = ? AND Autor = ?;");
		ps1.setString(1, title);
		ps1.setString(2, author);
		rs = ps1.executeQuery();
		if (rs.next()) {
			book = new Book();
			book.setName(rs.getString("Name"));
			book.setAutor(rs.getString("Autor"));
			book.setImage(rs.getString("image"));
			book.setGenre(rs.getString("Genre"));
			book.setSumOfVotes(rs.getInt("SumOfVotes"));
			book.setNumberOfVotes(rs.getInt("NumberOfVotes"));
			book.setDescription(rs.getString("Description"));
			book.setLinkToBuy(rs.getString("LinkToBuy"));
		}
		return book;
	}

}
