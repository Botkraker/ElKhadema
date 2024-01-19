package Elkhadema.khadema.DAO.DAOInterfaces;

import java.util.List;
import java.util.Optional;

import Elkhadema.khadema.DAO.DAOImplemantation.JobofferDAOINT;
import Elkhadema.khadema.domain.JobOffre;

public class JobofferDAO implements JobofferDAOINT {

	@Override
	public Optional<JobOffre> get(long id) {
		// TODO Auto-generated method stub
		return Optional.empty();
	}

	@Override
	public List<JobOffre> getAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void save(JobOffre t) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(JobOffre t, JobOffre newT) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(JobOffre t) {
		// TODO Auto-generated method stub
		
	}

}
