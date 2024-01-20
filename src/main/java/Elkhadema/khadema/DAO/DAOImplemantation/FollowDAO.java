package Elkhadema.khadema.DAO.DAOImplemantation;

import java.util.List;
import java.util.Optional;

import Elkhadema.khadema.DAO.DAOInterfaces.FollowerDAOINT;
import Elkhadema.khadema.domain.Follow;

public class FollowDAO implements FollowerDAOINT{

	@Override
	public Optional<Follow> get(long id) {
		// TODO Auto-generated method stub
		return Optional.empty();
	}

	@Override
	public List<Follow> getAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void save(Follow t) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(Follow t, Follow newT) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(Follow t) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Follow> getfollowersByuserid(long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Follow> getfollowingByuserid(long id) {
		// TODO Auto-generated method stub
		return null;
	}

}
