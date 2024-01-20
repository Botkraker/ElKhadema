package Elkhadema.khadema.Service.ServiceImplemantation;

import java.util.List;
import java.util.Optional;

import Elkhadema.khadema.DAO.DAOImplemantation.UserDAO;
import Elkhadema.khadema.Service.ServiceInterfaces.UserService;
import Elkhadema.khadema.domain.User;

public class UserServiceImp implements UserService {
	UserDAO uDao=new UserDAO();
	@Override
	public User Signin(User u) {
		uDao.save(u);
		return u;
		
	}

	@Override
	public User Login(String name, String password) {
		List<User> users=uDao.getAll();		
		Optional<User> u= users.stream().filter(t -> t.getFirstname().equals(name)&&t.getPassword().equals(password)).findFirst();
		if (u.isEmpty()) return null;
		return u.get();
	}

	@Override
	public void removeUser(User u) {
		uDao.delete(u);
	}

	@Override
	public User EditUser(User u,User newT) {
		uDao.update(u, newT);
		return uDao.get(u.getId()).get();
	}

}
