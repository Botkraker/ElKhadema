package Elkhadema.khadema.DAO;

import java.sql.Connection;
import java.sql.ResultSet;
import java.util.List;
import java.util.Optional;

import Elkhadema.khadema.domain.Profile;
import Elkhadema.khadema.domain.User;
import Elkhadema.khadema.util.ConexDB;

public class UserDAO implements Dao<User> {
	private static Connection connection = ConexDB.getInstance();

	@Override
	public Optional<User> get(long id) {
		String sql = "SELECT *  FROM `user` WHERE `user_id` = " + id;
		User user = null;
		try {
			ResultSet rs = connection.createStatement().executeQuery(sql);
			while (rs.next()) {
				user = new User(rs.getString(4), rs.getString(2), rs.getInt(0));
			}

		} catch (Exception e) {
			System.out.println(e);

		}
		return Optional.ofNullable(user);
	}

	@Override
	public List<User> getAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void save(User t) {
		// TODO Auto-generated method stub

	}

	@Override
	public void update(User t, User newT) {
		// TODO Auto-generated method stub

	}

	@Override
	public void delete(User t) {
		// TODO Auto-generated method stub

	}

}
