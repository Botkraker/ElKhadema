package Elkhadema.khadema.DAO.DAOImplemantation;


import Elkhadema.khadema.domain.User;

public interface AdminDAOINT extends Dao<User> {
	public void banUserByid(long id);
	public void deletePostByid(long id);
	public void deleteComment(long id);
	
}
