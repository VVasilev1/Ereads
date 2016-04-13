package bg.ereads.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import bg.ereads.connection.DBConnection;

public class PictureDao implements IPictureDao {

	Connection conn = DBConnection.getInstance().getConn();

	@Override
	public void addPicture(String path, String email) throws SQLException {

		int idUser = 0;
		PreparedStatement ps1 = conn.prepareStatement("SELECT * FROM user WHERE Email=?;");
		ps1.setString(1, email);
		ResultSet rs1 = ps1.executeQuery();
		while (rs1.next()) {
			idUser = rs1.getInt("idUser");
		}

		PreparedStatement ps = conn.prepareStatement("INSERT INTO pictures VALUES(?,?)");
		ps.setInt(1, idUser);
		ps.setString(2, path);
		ps.executeUpdate();

	}

	@Override
	public ArrayList<String> photos(String email) throws SQLException {
		ArrayList<String> result = new ArrayList<String>();
		int idUser = 0;
		PreparedStatement ps1 = conn.prepareStatement("SELECT * FROM user WHERE Email=?;");
		ps1.setString(1, email);
		ResultSet rs1 = ps1.executeQuery();
		while (rs1.next()) {
			idUser = rs1.getInt("idUser");
		}
		PreparedStatement ps = conn.prepareStatement("SELECT Text FROM pictures WHERE idUser=?;");
		ps.setInt(1, idUser);
		rs1 = ps.executeQuery();
		while (rs1.next()) {
			String path = rs1.getString("Text");
			result.add(path);
		}
		return result;
	}

}
