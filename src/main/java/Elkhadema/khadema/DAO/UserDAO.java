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
		try {
			ResultSet rs = connection.createStatement().executeQuery(sql);
			while (rs.next()) {
				User user = new User(rs.getString(4), rs.getArray(2), rs.getLong(0), new Profile(null, null, null));

			}

		} catch (Exception e) {
			System.out.println(e);

		}
		return rs;
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
