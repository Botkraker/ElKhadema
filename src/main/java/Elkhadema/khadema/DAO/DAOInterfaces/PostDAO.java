package Elkhadema.khadema.DAO.DAOInterfaces;

import java.util.List;
import java.util.Optional;

import Elkhadema.khadema.DAO.DAOImplemantation.PostDAOINT;
import Elkhadema.khadema.domain.Post;

public class PostDAO implements PostDAOINT {

	@Override
	public Optional<Post> get(long id) {
		// TODO Auto-generated method stub
		return Optional.empty();
	}

	@Override
	public List<Post> getAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void save(Post t) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(Post t, Post newT) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(Post t) {
		// TODO Auto-generated method stub
		
	}

}
