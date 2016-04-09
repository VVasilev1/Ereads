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

public class BookDao implements IBookDao{
	Connection conn = null;
	private static IBookDao instance;
	public synchronized static IBookDao getInstance() {
		if (instance == null) {
			instance = new BookDao();
		}
		return instance;
	}
	
	@Override
	public void addBook(Book b) throws SQLException {
		conn =DBConnection.getInstance().getInstance().getConn();
		PreparedStatement ps = conn.prepareStatement("INSERT INTO Book VALUES (null, ?, ?, ?,'0',?,?,?,'0');", com.mysql.jdbc.PreparedStatement.RETURN_GENERATED_KEYS);
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
	public void updateBook(Book b) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void removeBook(Book b) throws SQLException {
		conn = DBConnection.getInstance().getInstance().getConn();
		PreparedStatement ps = conn.prepareStatement("DELETE FROM Book WHERE Name=? AND Author=? AND image=? AND Description=? AND Genre=? AND LinkToBuy=?;", com.mysql.jdbc.PreparedStatement.RETURN_GENERATED_KEYS);
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
		conn = DBConnection.getInstance().getInstance().getConn();
		Statement stm = conn.createStatement();
		List<Book> list = new ArrayList<Book>();
		Book book = null;
		ResultSet rs = null;
		try {
			
	
		 rs = stm.executeQuery("Select * FROM Book");
		 
		while(rs.next()){
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
		conn = DBConnection.getInstance().getInstance().getConn();
		PreparedStatement ps = conn.prepareStatement("SELECT * FROM Book WHERE name LIKE ?");
		ps.setString(1, "%"+title+"%");
		List<Book> books = new ArrayList<Book>();
		Book book = null;
		ResultSet rs = ps.executeQuery();
		while(rs.next()) {
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
		conn = DBConnection.getInstance().getInstance().getConn();
		PreparedStatement ps = conn.prepareStatement("SELECT * FROM Book WHERE Genre LIKE ?");
		ps.setString(1, "%"+genre+"%");
		List<Book> books = new ArrayList<Book>();
		Book book = null;
		ResultSet rs = ps.executeQuery();
		while(rs.next()) {
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
		conn = DBConnection.getInstance().getInstance().getConn();
		PreparedStatement ps = conn.prepareStatement("SELECT * FROM Book WHERE Autor LIKE ?");
		ps.setString(1, "%"+name+"%");
		List<Book> books = new ArrayList<Book>();
		Book book = null;
		ResultSet rs = ps.executeQuery();
		while(rs.next()) {
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
		Connection conn = DBConnection.getInstance().getConn();
		PreparedStatement ps = conn.prepareStatement("SELECT * FROM book WHERE Name = ? AND Autor = ?");
		ps.setString(1, title);
		ps.setString(2, author);
		ResultSet result = ps.executeQuery();
		if(result.next()==false) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	public void rateBook(String title, String rating) throws SQLException {
		Connection conn = DBConnection.getInstance().getConn();
		PreparedStatement ps = conn.prepareStatement("SELECT * FROM book WHERE Name = ?");
		ResultSet rs = ps.executeQuery();
		int sumOfVotes = rs.getInt("SumOfVotes");
		System.out.println(sumOfVotes);
		int NumberOfVotes = rs.getInt("NumberOfVotes");
		PreparedStatement ps2 = conn.prepareStatement("UPDATE book SET SumOfVotes = ? AND NumberOfVotes = ? WHERE Name = ?");
	}	
	
	@Override
	public void reviewBook(String title, String author, String review, String mail) throws SQLException {
		Connection conn = DBConnection.getInstance().getConn();
		int idUser = 0;
		int idBook = 0;
		PreparedStatement ps = conn.prepareStatement("SELECT idUser FROM user WHERE Email = ?;");
		ps.setString(1, mail);
		ResultSet rs = ps.executeQuery();
		if(rs.next()){
			idUser = rs.getInt("idUser");
		}
		PreparedStatement ps1 = conn.prepareStatement("SELECT idBook FROM book WHERE Name = ? AND Autor = ?;");
		ps1.setString(1, title);
		ps1.setString(2, author);
		rs = ps1.executeQuery();
		if(rs.next()){
			idBook = rs.getInt("idBook");
		}
		PreparedStatement ps2 = conn.prepareStatement("INSERT INTO reviews VALUES(null, ?, ?, ?);");
		ps2.setString(1, review);
		ps2.setInt(2, idUser);
		ps2.setInt(3, idBook);
		ps2.executeUpdate();
	}

	@Override
	public Book getBook(String title, String author) throws SQLException {
		conn = DBConnection.getInstance().getInstance().getConn();
		Statement stm = conn.createStatement();
		Book book = null;
		ResultSet rs = null;
		PreparedStatement ps1 = conn.prepareStatement("SELECT * FROM book WHERE Name = ? AND Autor = ?;");
		ps1.setString(1, title);
		ps1.setString(2, author);
		rs = ps1.executeQuery();
		if(rs.next()){
			book = new Book();
			book.setName(rs.getString("Name"));
			book.setAutor(rs.getString("Autor"));
			book.setImage(rs.getString("image"));
			book.setGenre(rs.getString("Genre"));
			book.setSumOfVotes(rs.getInt("SumOfVotes"));
			book.setNumberOfVotes(rs.getInt("NumberOfVotes"));
			book.setDescription(rs.getString("Description"));
		}
		return book;
	}
	
}
