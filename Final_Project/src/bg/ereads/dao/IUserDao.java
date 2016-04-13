package bg.ereads.dao;

import java.sql.SQLException;

import bg.ereads.classes.User;
import bg.ereads.exceptions.DBException;
import bg.ereads.exceptions.InvalidUserException;

public interface IUserDao {
	User loginUser(String email, String password) throws DBException, InvalidUserException;

	boolean checkEmail(String email) throws SQLException;

	void registerUser(String firstName, String lastName, String email, String password) throws SQLException;

	void changeProfileImage(String path, String email) throws SQLException;

	User getUser(String image) throws SQLException;
}
