package com.huynhthanhnha.restExample.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.huynhthanhnha.restExample.database.JDBCPostgreSQLConnection;
import com.huynhthanhnha.restExample.entity.User;

public class UserRepository {

	private Connection conn = null;
	
	private final String getUsersSQL = "SELECT * FROM users";
	private final String getUserSQL = "SELECT * FROM users WHERE id = ?";
	private final String saveUserSQL = "INSERT INTO users(name) VALUES (?)";
	private final String updateUserSQL = "UPDATE users SET name = ? WHERE id = ?";
	private final String deleteUserSQL = "DELETE FROM users WHERE id = ?";
	
	public List<User> getUsers() {
		conn = JDBCPostgreSQLConnection.connection();
		
		List<User> users = new ArrayList<>();
		try(PreparedStatement ps = conn.prepareStatement(getUsersSQL)) {
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				int id = rs.getInt("id");
				String name = rs.getString("name");
				
				User user = new User(id, name);
				users.add(user);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return users;
	}
	
	public User getUser(int userId) {
		conn = JDBCPostgreSQLConnection.connection();
		
		ResultSet rs= null;
		User user = null;
		try(PreparedStatement ps = conn.prepareStatement(getUserSQL)) {
			ps.setInt(1, userId);
			
			rs = ps.executeQuery();
			
			if(rs.next()) {
				
				int id = rs.getInt("id");
				String name = rs.getString("name");
				user = new User(id, name);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rs, conn);
		}
		
		return user;
	}
	
	public User saveUser(User user) {
		conn = JDBCPostgreSQLConnection.connection();
		ResultSet rs = null;
		int id = 0;
		
		try(PreparedStatement ps = conn.prepareStatement(saveUserSQL, Statement.RETURN_GENERATED_KEYS)) {
			ps.setString(1, user.getName());
			
			ps.executeUpdate();
			
			rs = ps.getGeneratedKeys();

			if(rs.next()) {
				id = (int)rs.getLong(1);

			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rs, conn);
		}
		user.setId(id);
		
		return user;
	}
	
	
	public User updateUser(Integer id, User user) {
		conn = JDBCPostgreSQLConnection.connection();
		
		try(PreparedStatement ps = conn.prepareStatement(updateUserSQL)) {
			ps.setString(1, user.getName());
			ps.setInt(2, id);
			
			ps.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(null, conn);
		}
		user.setId(id);
		return user;
	}
	
	public int delete(Integer id) {
		conn = JDBCPostgreSQLConnection.connection();
		int result = 0;
		
		try(PreparedStatement ps = conn.prepareStatement(deleteUserSQL)) {
			ps.setInt(1, id);
			result = ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(null, conn);
		}
		
		return result;
	}
	
	private void close(ResultSet rs, Connection conn) {
			try {
				if(rs != null)
					rs.close();
				if(conn != null)
					conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}
}
