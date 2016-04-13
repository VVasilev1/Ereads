package bg.ereads.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import bg.ereads.classes.Review;
import bg.ereads.connection.DBConnection;

public class ReviewDao implements IReviewDao {

	private Connection conn = DBConnection.getInstance().getConn();

	public List<Review> getReviews(String title, String author) throws SQLException {
		int idBook = 0;
		Review review = null;
		List<Review> reviews = new ArrayList<Review>();
		PreparedStatement ps1 = conn.prepareStatement("SELECT idBook FROM book WHERE Name = ? AND Autor = ?;");
		ps1.setString(1, title);
		ps1.setString(2, author);
		ResultSet rs = ps1.executeQuery();
		if (rs.next()) {
			idBook = rs.getInt("idBook");
		}
		PreparedStatement ps2 = conn.prepareStatement("SELECT Text, userName, userImage FROM reviews WHERE book = ?");
		ps2.setInt(1, idBook);
		rs = ps2.executeQuery();
		while (rs.next()) {
			review = new Review(rs.getString("Text"), rs.getString("userName"), rs.getString("userImage"));
			reviews.add(review);
		}
		return reviews;
	}
}
