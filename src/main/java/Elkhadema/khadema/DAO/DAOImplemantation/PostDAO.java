package Elkhadema.khadema.DAO.DAOImplemantation;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import Elkhadema.khadema.DAO.DAOInterfaces.PostDAOINT;
import Elkhadema.khadema.domain.Post;
import Elkhadema.khadema.domain.User;
import Elkhadema.khadema.util.ConexDB;

public class PostDAO implements PostDAOINT {
	private static Connection connection = ConexDB.getInstance();

	@Override
	public Optional<Post> get(long id) {
		String sql = "SELECT * FROM `user` , `posts` WHERE posts.user_id = user.user_id And user.user_id="+id;
		Post post = null;
		try {
			ResultSet rs = connection.createStatement().executeQuery(sql);
			while (rs.next()) {
				post = new Post(rs.getLong("post_id"),new User(rs.getInt("user_id"),null,null,rs.getString("firstname"),rs.getString("lastname"),rs.getDate("creationdate"),rs.getDate("last_login"),rs.getBoolean("banned"),rs.getBoolean("is_active")), rs.getString("content"), rs.getString("type"), null, null,rs.getDate("creationdate"));
			}

		} catch (Exception e) {
			System.out.println(e);

		}
		return Optional.ofNullable(post);
	}

	@Override
	public List<Post> getAll() {
		Statement stmt=null;
		ResultSet rs=null;
		List<Post>posts=new ArrayList<>();
		String SQL="SELECT * FROM `user` , `posts` WHERE posts.user_id = user.user_id";
		try {
			stmt=connection.createStatement();
			rs=stmt.executeQuery(SQL);
			while (rs.next()) {
				int id=rs.getInt("user_id");
				posts.add(new Post(rs.getLong("post_id"),new User(id, null, null,rs.getString("firstname"),rs.getString("lastname"),rs.getDate("creationdate"),rs.getDate("last_login"),rs.getBoolean("banned"),rs.getBoolean("is_active")),rs.getString("content"),rs.getString("type"),null,null,rs.getDate("creationdate")));
			}
		} catch (SQLException e) {
			System.out.println(e);
		}
		return posts;
	}

	@Override
	public void save(Post t) {
		PreparedStatement pstmt=null;
		try {
			pstmt=connection.prepareStatement("INSERT INTO `khademadb`.`posts` (`post_id`, `user_id`, `type`, `creationdate`, `content`) VALUES (NULL, ?, ?, ?, ?);",Statement.RETURN_GENERATED_KEYS);
			pstmt.setInt(1,t.getUser().getId());
			pstmt.setString(2,t.getType());
			pstmt.setDate(3, (Date)t.getCreationDate());
			pstmt.setString(4, t.getContent());
			pstmt.executeUpdate();
		} catch (SQLException e) {
		System.out.println(e.getMessage());
		}
				
	}

	@Override
	public void update(Post t, Post newT) {
		try {
			String sql="UPDATE `khademadb`.`posts` SET  `type` = ?, `creationdate` = ?, `content` = ? WHERE `posts`.`post_id` = "+t.getId()+";";
			
			PreparedStatement p=connection.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
			p.setString(1,newT.getType());
			p.setDate(2, (Date)newT.getCreationDate());
			p.setString(3,newT.getContent());
			p.executeUpdate();
			p.getGeneratedKeys();
		} catch (Exception e) {
			System.out.println(e);
	}}
	

	@Override
	public void delete(Post t) {
		try {
			connection.createStatement().execute("DELETE FROM `posts` WHERE `posts`.post_id ="+t.getId());

		} catch (Exception e) {
			System.out.println(e);
		}
	}


}
