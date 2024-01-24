package Elkhadema.khadema.DAO.DAOImplemantation;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import Elkhadema.khadema.DAO.DAOInterfaces.Dao;
import Elkhadema.khadema.domain.ContactInfo;
import Elkhadema.khadema.domain.User;
import Elkhadema.khadema.util.ConexDB;

public class UserDAO implements Dao<User> {
	private static Connection connection = ConexDB.getInstance();

	@Override
	public Optional<User> get(long id) {
		String sql = "SELECT *  FROM `user`,`contact_info` WHERE `user_id` = " + id+" and user.`contact_info_id`=contact_info.contact_info_id";
		User user = null;
		try {
			ResultSet rs = connection.createStatement().executeQuery(sql);
			while (rs.next()) {
				user = new User(rs.getInt("user_id"), rs.getString("password_encrypted"),new ContactInfo(rs.getInt("contact_info_id"),rs.getString("email"), rs.getString("address"), rs.getInt("phone number")),rs.getString("firstname"),rs.getString("lastname"),rs.getDate("creationdate"),rs.getDate("last_login"),rs.getBoolean("banned"),rs.getBoolean("is_active"));
			}

		} catch (Exception e) {
			System.out.println(e);

		}
		return Optional.ofNullable(user);
	}

	@Override
	public List<User> getAll() {
		Statement stmt=null;
		ResultSet rs=null;
		List<User>users=new ArrayList<>();
		String SQL="SELECT * FROM `user`,`contact_info` WHERE user.`contact_info_id`=contact_info.contact_info_id";
		try {
			stmt=connection.createStatement();
			rs=stmt.executeQuery(SQL);
			while (rs.next()) {
				int id=rs.getInt("user_id");
				String password=rs.getString("password_encrypted");
				ContactInfo cf=new ContactInfo(rs.getInt("contact_info_id"),rs.getString("email"), rs.getString("address"), rs.getInt("phone number"));
				users.add(new User(id, password, cf,rs.getString("firstname"),rs.getString("lastname"),rs.getDate("creationdate"),rs.getDate("last_login"),rs.getBoolean("banned"),rs.getBoolean("is_active")));
			}
		} catch (SQLException e) {
			System.out.println(e);
		}
		return users;
	}

	@Override
	public void save(User t) {
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		try {
			int cfid = 0;
			pstmt=connection.prepareStatement("INSERT INTO `khademadb`.`contact_info` (`contact_info_id`, `email`, `phone number`, `address`) VALUES (NULL, ?, ?, ?);",Statement.RETURN_GENERATED_KEYS);
			pstmt.setString(1,t.getContactInfo().getEmail());
			pstmt.setInt(2,t.getContactInfo().getPhoneNumber());
			pstmt.setString(3, t.getContactInfo().getAddress());
			pstmt.executeUpdate();
			rs=pstmt.getGeneratedKeys();
			if(rs.next()) {
				cfid=rs.getInt(1);
			}
			pstmt=connection.prepareStatement("INSERT INTO `khademadb`.`user` (`password_encrypted`, `contact_info_id`, `firstname`, `lastname`, `creationdate`, `last_login`, `banned`, `is_active`) VALUES (?, ?, ?, ?, ?, ?, ?, ?);",Statement.RETURN_GENERATED_KEYS);
			pstmt.setString(3,t.getFirstname());
			pstmt.setString(4,t.getLastname());
			pstmt.setString(1,t.getPassword());
			pstmt.setDate(5,(Date)t.getCreationDate());
			pstmt.setLong(2,cfid);
			pstmt.setDate(6,(Date)t.getLastLoginDate());
			pstmt.setBoolean(7, false);
			pstmt.setBoolean(8, false);
			pstmt.executeUpdate();
			rs=pstmt.getGeneratedKeys();
			if(rs.next()) {
				t.setId(rs.getInt(1));
			}


		} catch (SQLException e) {
		System.out.println(e.getMessage());
		}
		
	}

	@Override
	public void update(User t, User newT) {
		try {
			String sql="UPDATE `khademadb`.`user` SET `password_encrypted` = ?, `firstname` = ?, `lastname` = ?, `creationdate` = ?, `last_login` = ?, `banned` = ?, `is_active` = ? WHERE `user`.`user_id` = "+t.getId()+";";
			
			PreparedStatement p=connection.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
			p.setString(2,newT.getFirstname());
			p.setString(3,newT.getLastname());
			p.setString(1,newT.getPassword());
			p.setDate(4,(Date)t.getCreationDate());
			p.setDate(5,(Date)t.getLastLoginDate());
			p.setBoolean(6, newT.is_banned);
			p.setBoolean(7, newT.is_active);
			p.executeUpdate();
			p.getGeneratedKeys();
		} catch (Exception e) {
			System.out.println(e);
	}
	}
	
	public void updateContactinfo(User t,ContactInfo ci) {
		try {
			String sql="UPDATE `khademadb`.`contact_info` SET `email` = ?, `phone number` = ?, `address` = ? WHERE `contact_info`.`contact_info_id` = "+t.getId()+";";
			
			PreparedStatement p=connection.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
			p.setString(1,t.getContactInfo().getEmail());
			p.setInt(2,t.getContactInfo().getPhoneNumber());
			p.setString(3,t.getContactInfo().getAddress());
			p.executeUpdate();
			p.getGeneratedKeys();
		} catch (Exception e) {
			System.out.println(e);
	}
		
	}

	@Override
	public void delete(User t) {
		try {
			connection.createStatement().execute("DELETE FROM `contact_info` WHERE `contact_info_id` ="+t.getId());

		} catch (Exception e) {
			System.out.println(e);
		}
	}
	public Optional<User> Login(String firstname,String password) {
		String sql = "SELECT *  FROM `user` WHERE `password_encrypted`="+firstname+" AND `firstname`="+password;
		User user = null;
		try {
			ResultSet rs = connection.createStatement().executeQuery(sql);
			while (rs.next()) {
				user = new User(rs.getInt("user_id"), rs.getString("password_encrypted"),new ContactInfo(rs.getInt("contact_info_id"),rs.getString("email"), rs.getString("address"), rs.getInt("phone number")),rs.getString("firstname"),rs.getString("lastname"),rs.getDate("creationdate"),rs.getDate("last_login"),rs.getBoolean("banned"),rs.getBoolean("is_active"));
			}

		} catch (Exception e) {
			System.out.println(e);

		}
		return Optional.ofNullable(user);
	}
}
