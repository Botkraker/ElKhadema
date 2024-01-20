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

import Elkhadema.khadema.DAO.DAOInterfaces.ReactionDAOINT;
import Elkhadema.khadema.domain.ContactInfo;
import Elkhadema.khadema.domain.Post;
import Elkhadema.khadema.domain.Reaction;
import Elkhadema.khadema.domain.User;
import Elkhadema.khadema.util.ConexDB;

public class ReactionDAO implements ReactionDAOINT {
	private static Connection connection = ConexDB.getInstance();
	@Override
	public Optional<Reaction> get(long id) {
		String sql = "SELECT  post_reaction . * , firstname, lastname FROM `post_reaction` , user WHERE `post_id` ="+id+" AND post_reaction.`user_id` = user.user_id";
		Reaction reaction = null;
		try {
			ResultSet rs = connection.createStatement().executeQuery(sql);
			while (rs.next()) {
				reaction = new Reaction(new User(rs.getString("firstname"), rs.getString("lastname"), rs.getInt("user_id")),new Post(rs.getInt("post_id")), rs.getString("reactiontype"),  rs.getDate("creationdate"));
			}

		} catch (Exception e) {
			System.out.println(e);

		}
		return Optional.ofNullable(reaction);
	}

	@Override
	public List<Reaction> getAll() {
		Statement stmt=null;
		ResultSet rs=null;
		List<Reaction>reactions=new ArrayList<>();
		String SQL="SELECT  post_reaction . * , firstname, lastname  FROM user,post_reaction WHERE post_reaction.`user_id` = user.user_id  ORDER BY post_id";
		try {
			stmt=connection.createStatement();
			rs=stmt.executeQuery(SQL);
			while (rs.next()) {
				reactions.add(new Reaction(new User(rs.getString("firstname"), rs.getString("lastname"), rs.getInt("user_id")),new Post(rs.getInt("post_id")), rs.getString("reactiontype"),  rs.getDate("creationdate")));
			}
		} catch (SQLException e) {
			System.out.println(e);
		}
		return reactions;
	}

	@Override
	public void save(Reaction t) {
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		try {
			int cfid = 0;
			pstmt=connection.prepareStatement("INSERT INTO `khademadb`.`post_reaction` (`post_id`, `user_id`, `reactiontype`, `creationdate`) VALUES (?,?,?,?);",Statement.RETURN_GENERATED_KEYS);
			pstmt.setLong(1,t.getPost().getId());
			pstmt.setLong(2,t.getUser().getId());
			pstmt.setString(3, t.getType());
			pstmt.setDate(3,(Date) t.getCreationDate());
			pstmt.executeUpdate();
		} catch (SQLException e) {
		System.out.println(e.getMessage());
		}
		
	}

	@Override
	public void update(Reaction t, Reaction newT) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(Reaction t) {
		// TODO Auto-generated method stub
		
	}

}
