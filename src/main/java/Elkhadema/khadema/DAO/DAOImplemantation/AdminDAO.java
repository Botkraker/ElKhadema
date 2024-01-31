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

import Elkhadema.khadema.domain.Admin;
import Elkhadema.khadema.domain.User;
import Elkhadema.khadema.util.ConexDB;

public class AdminDAO {

	private static Connection connection = ConexDB.getInstance();
	//is admin checks if the user is admin ,if yes return the user as an optional of  admin and if not will return an empty optional
	public Optional<Admin> isAdmin(User user) {
		String sql = "SELECT *  FROM `user`,`admin` WHERE `user_id` = " + user.getId();
		Admin admin = null;
		try {
			ResultSet rs = connection.createStatement().executeQuery(sql);
			while (rs.next()) {
				admin = new Admin(user.getId(),user.getPassword(),user.getContactInfo(),user.getUserName(),user.getCreationDate(),user.getLastloginDate(),user.getPhoto(),user.is_banned,user.is_active,rs.getInt("admin_level"));
			}

		} catch (Exception e) {
			System.out.println(e);

		}
		return Optional.ofNullable(admin);
	}
	public void makeAdmin(User user,int level) {
		try {
			PreparedStatement ptsm=connection.prepareStatement("INSERT INTO `khademadb`.`admin` (`user_id`, `admin_level`) VALUES (?, ?)");
			ptsm.setLong(1, user.getId());
			ptsm.setInt(2, level);
			ptsm.executeUpdate();

		} catch (Exception e) {
			System.out.println(e);
		}
	}
	public void changeAdminLevel(User user,int level) {
		try {
			PreparedStatement ptsm=connection.prepareStatement("UPDATE `khademadb`.`admin` SET `admin_level` = ? WHERE `user_id` ="+user.getId());
			ptsm.setInt(1, level);
			ptsm.executeUpdate();

		} catch (Exception e) {
			System.out.println(e);
		}
	}
}
