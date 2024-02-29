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


import Elkhadema.khadema.domain.Message;
import Elkhadema.khadema.domain.MessageReceiver;


import Elkhadema.khadema.domain.User;
import Elkhadema.khadema.util.*;
public class MessageDAO{
	private static Connection connection = ConexDB.getInstance();
	public void save(Message t,MessageReceiver mr) {
		PreparedStatement pstmt = null;
		try {
			pstmt = connection.prepareStatement(
					"INSERT INTO `khademadb`.`messages` (`message_id`, `content`, `sender_id`, `creation_date`, `parent_message_id`) VALUES (NULL, ?, ?, ?, ?)",
					Statement.RETURN_GENERATED_KEYS);
			pstmt.setLong(1, t.getId());
			pstmt.setString(2, t.getContent());
			pstmt.setInt(3, t.getSender().getId());
			pstmt.setDate(4, (Date)t.getCreationDate());
			pstmt.setInt(5, t.getParentMessageId());
			pstmt.executeUpdate();
			ResultSet rs = pstmt.getGeneratedKeys();
			if (rs.next()) {
				t.setId(rs.getInt(1));
			}
			pstmt = connection.prepareStatement(
					"INSERT INTO `khademadb`.`message_receiver` (`id`, `user_id`, `message_id`, `is_read`) VALUES (NULL, ?, ?, ?)",
					Statement.RETURN_GENERATED_KEYS);
			pstmt.setLong(1, mr.getId());
			pstmt.setInt(2, mr.getUser().getId());
			pstmt.setInt(3, mr.isRead());
			pstmt.executeUpdate();
			

		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}

	public void update(Message t, Message newT) {
		try {
			String sql = "UPDATE `khademadb`.`messages` SET  `content` = ?, `creation_date` = ?,`parent_message_id`=? WHERE `messages`.`message_id` = "
					+ t.getId() + ";";

			PreparedStatement p = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			p.setString(1, newT.getContent());
			p.setDate(2, (Date) newT.getCreationDate());
			p.setString(3, newT.getContent());
			p.setLong(4, newT.getParentMessageId());
			p.executeUpdate();
			p.getGeneratedKeys();
		} catch (Exception e) {
			System.out.println(e);
		}

	}
	public void updateReciverMessage(MessageReceiver mr,MessageReceiver newmr) {
		try {
			String sql = "UPDATE `khademadb`.`message_receiver` SET  `is_read` = ?, `user_id` = ?,`message_id`=?  WHERE `messages`.`message_id` = "
					+mr.getMessage().getId() + ";";

			PreparedStatement p = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			p.setInt(1, newmr.isRead());
			p.setInt(2, newmr.getUser().getId());
			p.setLong(3, newmr.getMessage().getId());
			p.executeUpdate();
			p.getGeneratedKeys();
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	public void delete(Message t) {
		try {
			connection.createStatement().execute("DELETE FROM `messages` WHERE  `messages`.`message_id` =" + t.getId());

		} catch (Exception e) {
			System.out.println(e);
		}

	}

	public List<Message> getconversation(User u1,User u2) {
		String sql ="SELECT * FROM messages m INNER JOIN message_receiver mr ON m.message_id = mr.message_id WHERE (m.sender_id = "+u1.getId()+"OR m.sender_id = "+u2.getId()+") AND (mr.user_id = 'user1' OR mr.user_id = 'user2') ORDER BY creation_date DESC";
		List<Message> messages = new ArrayList<>();
		try {
			ResultSet rs = connection.createStatement().executeQuery(sql);
			while (rs.next()) {
				messages.add(new Message(rs.getLong("message_id"),new User(rs.getInt("sender_id"),"", ""),rs.getString("content"),rs.getDate("creation_date"),rs.getInt("parent_message_id")));
			}

		} catch (Exception e) {
			System.out.println(e);

		}
		return messages;
	}

	public Optional<MessageReceiver> getRecieverMessagesById(Message m) {
		String sql = "SELECT * FROM `message_receiver` WHERE `message_id`="+m.getId();
		MessageReceiver message = null;
		try {
			ResultSet rs = connection.createStatement().executeQuery(sql);
			while (rs.next()) {
				message = new MessageReceiver(m,new User(rs.getInt("user_id"), "", ""),rs.getInt("is_read"),rs.getInt("id"));
			}

		} catch (Exception e) {
			System.out.println(e);

		}
		return Optional.ofNullable(message);
	}
	

}
