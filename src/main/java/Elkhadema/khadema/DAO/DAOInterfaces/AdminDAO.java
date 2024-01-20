package Elkhadema.khadema.DAO.DAOInterfaces;

import java.util.List;
import java.util.Optional;

import Elkhadema.khadema.domain.User;

public class AdminDAO implements AdminDAOINT {

	@Override
	public Optional<User> get(long id) {
		// TODO Auto-generated method stub
		return Optional.empty();
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

	@Override
	public void banUserByid(long id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deletePostByid(long id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteComment(long id) {
		// TODO Auto-generated method stub
		
	}

}
