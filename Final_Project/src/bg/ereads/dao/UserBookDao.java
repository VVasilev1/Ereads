package bg.ereads.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import bg.ereads.classes.Book;
import bg.ereads.connection.DBConnection;

public class UserBookDao implements IUserBookDao {
	Connection conn = DBConnection.getInstance().getConn();

	@Override
	public void bookAddedToUser(String email, String title, String author, String picture) throws SQLException {

		int idUser = 0;
		PreparedStatement ps = conn.prepareStatement("SELECT * FROM user WHERE Email=?;");
		ps.setString(1, email);
		ResultSet rs = ps.executeQuery();
		while (rs.next()) {
			idUser = rs.getInt("idUser");
		}

		PreparedStatement ps1 = conn.prepareStatement("INSERT INTO user_book VALUES(?,?,?,?);");
		ps1.setInt(1, idUser);
		ps1.setString(2, picture);
		ps1.setString(3, title);
		ps1.setString(4, author);
		ps1.executeUpdate();

	}

	@Override
	public ArrayList<Book> bookToUser(String email) throws SQLException {
		ArrayList<Book> result = new ArrayList<Book>();
		int idUser = 0;
		PreparedStatement ps1 = conn.prepareStatement("SELECT * FROM user WHERE Email=?;");
		ps1.setString(1, email);
		ResultSet rs1 = ps1.executeQuery();
		while (rs1.next()) {
			idUser = rs1.getInt("idUser");
		}

		PreparedStatement ps = conn.prepareStatement("SELECT * FROM user_book WHERE idUser=?;");
		ps.setInt(1, idUser);
		rs1 = ps.executeQuery();
		while (rs1.next()) {
			Book book = new Book();
			book.setName(rs1.getString("title"));
			book.setAutor(rs1.getString("author"));
			book.setImage(rs1.getString("picture"));
			result.add(book);
		}
		return result;
	}
}
