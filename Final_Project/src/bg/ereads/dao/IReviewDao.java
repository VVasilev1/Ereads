package bg.ereads.dao;

import java.sql.SQLException;
import java.util.List;

public interface IReviewDao {
	public List<String> getReviews(String title, String author) throws SQLException;
}
