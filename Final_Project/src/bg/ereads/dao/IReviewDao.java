package bg.ereads.dao;

import java.sql.SQLException;
import java.util.List;

import bg.ereads.classes.Review;

public interface IReviewDao {
	public List<Review> getReviews(String title, String author) throws SQLException;
}
