package bg.ereads.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.mysql.jdbc.Statement;

import bg.ereads.classes.User;
import bg.ereads.connection.DBConnection;
import bg.ereads.exceptions.DBException;
import bg.ereads.exceptions.InvalidUserException;


public class UserDao implements IUserDao {
	private static IUserDao instance;
	public synchronized static IUserDao getInstance() {
		if (instance == null) {
			instance = new UserDao();
		}
		return instance;
	}
	@Override
	public User loginUser(String email, String password) throws DBException, InvalidUserException {
		Connection conn = null;
		try {
			conn = DBConnection.getInstance().getConn();
			PreparedStatement ps = conn.prepareStatement("SELECT * FROM user WHERE Email = ? AND Password = ?");
			ps.setString(1, email);
			ps.setString(2, password);
			ResultSet result = ps.executeQuery();
			if(result.next() == false){
				return new User();
			}
			
			return new User(result.getString(2), result.getString(3), result.getString(4), result.getString(5), result.getString(6));
		}
		catch(Exception e){
			e.printStackTrace();
			throw new DBException("Can not connect to the datebase right now. Sorry for the problems.", e);
		}

	}
	
	public List<User> getAllUsers() throws SQLException, DBException{
		Connection conn1 = DBConnection.getInstance().getConn();
		java.sql.Statement stm = conn1.createStatement();
		List<User> users = new ArrayList<User>();
		User user = null;
		ResultSet rs = null;
		try{
			rs = stm.executeQuery("SELECT * FROM user");
			while(rs.next()){
				user = new User();
				user.seteMail(rs.getString("Email"));
				users.add(user);
			}
		}catch(SQLException e){
			throw new DBException("Can;t show right now.", e);
		}
		return users;
	}
	@Override
	public boolean checkEmail(String email) throws SQLException {
		Connection conn = DBConnection.getInstance().getConn();
		PreparedStatement ps = conn.prepareStatement("SELECT * FROM user WHERE Email = ?");
		ps.setString(1, email);
		ResultSet result = ps.executeQuery();
		if(result.next()==false) {
			return true;
		} else {
			return false;
		}
	}
	
	@Override
	public void registerUser(String firstName, String lastName, String email, String password) throws SQLException {
		Connection conn = DBConnection.getInstance().getConn();
		PreparedStatement ps = conn.prepareStatement("INSERT INTO user VALUES (null, ?,?,?,'default.jpg',?)");
		ps.setString(1, firstName);
		ps.setString(2, lastName);
		ps.setString(3, email);
		ps.setString(4, password);
		ps.executeUpdate();
		
	}
	@Override
	public void changeProfileImage(String path, String email) throws SQLException {
		Connection conn = DBConnection.getInstance().getConn();
		PreparedStatement ps = conn.prepareStatement("UPDATE user SET ProfileImage= ? WHERE Email=?");
		ps.setString(1, path);
		ps.setString(2, email);
		ps.executeUpdate();		
		
		
	}
	@Override
	public User getUser(String image) throws SQLException {
		Connection conn = DBConnection.getInstance().getConn();
		PreparedStatement ps = conn.prepareStatement("SELECT * from user WHERE ProfileImage=?");
		ps.setString(1, image);
		ResultSet result = ps.executeQuery();
		if(result.next() == false){
			return new User();
		}
		
		return new User(result.getString(2), result.getString(3), result.getString(4), result.getString(5), result.getString(6));
	}	
	
}
