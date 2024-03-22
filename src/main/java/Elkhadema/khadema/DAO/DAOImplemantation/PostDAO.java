package Elkhadema.khadema.DAO.DAOImplemantation;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


import Elkhadema.khadema.domain.Post;
import Elkhadema.khadema.domain.User;
import Elkhadema.khadema.util.ConexDB;
// TODO pls add a way to get posts by parent post to replace comments
public class PostDAO {
	private static Connection connection = ConexDB.getInstance();

	public List<Post> getPostsById(long id) {
		String sql = "SELECT * FROM `user` , `posts` WHERE posts.user_id = user.user_id And user.user_id=" + id;
		List<Post> post = new ArrayList<>();
		try {
			ResultSet rs = connection.createStatement().executeQuery(sql);
			while (rs.next()) {
				post.add(new Post(
						new User(rs.getInt("user_id"), null, null, rs.getString("username"), rs.getDate("creationdate"),
								rs.getDate("last_login"), rs.getString("photo"), rs.getBoolean("banned"),
								rs.getBoolean("is_active")),
						rs.getString("content"), null, rs.getInt("post_parent"), rs.getString("type"), rs.getTimestamp("posts.creationdate"),
						rs.getLong("post_id")));
			}

		} catch (Exception e) {
			System.out.println(e);

		}
		return post;
	}

	public List<Post> getAllPostsUnderParent(long idparent) {
		String sql = "SELECT * FROM `user` , `posts` WHERE posts.user_id = user.user_id And posts.post_parent=" + idparent;
		List<Post> post = new ArrayList<>();
		try {
			ResultSet rs = connection.createStatement().executeQuery(sql);
			while (rs.next()) {
				post.add(new Post(
						new User(rs.getInt("user_id"), null, null, rs.getString("firstname"), rs.getDate("last_login"),
								rs.getDate("creationdate"), rs.getString("lastname"), rs.getBoolean("banned"),
								rs.getBoolean("is_active")),
						rs.getString("content"), null, rs.getInt("post_parent"), rs.getString("type"), rs.getDate("creationdate"),
						rs.getLong("post_id")));
			}

		} catch (Exception e) {
			System.out.println(e);

		}
		return post;
	}


	public void save(Post t) {
		PreparedStatement pstmt = null;
		try {
			pstmt = connection.prepareStatement(
					"INSERT INTO `khademadb`.`posts` (`post_id`, `user_id`, `type`, `creationdate`, `content`,`post_parent`) VALUES (NULL, ?, ?, ?, ?,?);",
					Statement.RETURN_GENERATED_KEYS);
			pstmt.setInt(1, t.getUser().getId());
			pstmt.setString(2, t.getType());
			System.out.println(new Timestamp(t.getCreationDate().getTime()));
			pstmt.setTimestamp(3, new Timestamp(t.getCreationDate().getTime()));
			pstmt.setString(4, t.getContent());
			pstmt.setLong(5, t.getParentPostId());
			pstmt.executeUpdate();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}

	}


	public void update(Post t, Post newT) {
		try {
			String sql = "UPDATE `khademadb`.`posts` SET  `type` = ?, `creationdate` = ?, `content` = ?,`post_parent`=? WHERE `posts`.`post_id` = "
					+ t.getId() + ";";

			PreparedStatement p = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			p.setString(1, newT.getType());
			p.setDate(2, (Date) newT.getCreationDate());
			p.setString(3, newT.getContent());
			p.setLong(4, newT.getParentPostId());
			p.executeUpdate();
			p.getGeneratedKeys();
		} catch (Exception e) {
			System.out.println(e);
		}
	}


	public void delete(Post t) {
		try {
			connection.createStatement().execute("DELETE FROM `posts` WHERE `posts`.post_id =" + t.getId());

		} catch (Exception e) {
			System.out.println(e);
		}
	}


	public Optional<Post> get(long id) {
		String sql = "SELECT * FROM `user` , `posts` WHERE posts.user_id = user.user_id And post_id=" + id;
		Post post = null;
		try {
			ResultSet rs = connection.createStatement().executeQuery(sql);
			while (rs.next()) {
				post =new Post(
						new User(rs.getInt("user_id"), null, null, rs.getString("firstname"), rs.getDate("last_login"),
								rs.getDate("creationdate"), rs.getString("lastname"), rs.getBoolean("banned"),
								rs.getBoolean("is_active")),
						rs.getString("content"), null, rs.getInt("post_parent"), rs.getString("type"), rs.getDate("creationdate"),
						rs.getLong("post_id"));
			}

		} catch (Exception e) {
			System.out.println(e);

		}
		return Optional.ofNullable(post);
	}



}
