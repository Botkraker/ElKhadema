package Elkhadema.khadema.Service.ServiceImplemantation;

import java.util.Optional;

import Elkhadema.khadema.DAO.DAOImplemantation.CompanyDAO;
import Elkhadema.khadema.DAO.DAOImplemantation.PersonDAO;
import Elkhadema.khadema.DAO.DAOImplemantation.UserDAO;
import Elkhadema.khadema.Service.ServiceInterfaces.UserService;
import Elkhadema.khadema.domain.Company;
import Elkhadema.khadema.domain.Person;
import Elkhadema.khadema.domain.User;
import Elkhadema.khadema.util.Session;
import Elkhadema.khadema.util.Exception.UserNotFoundException;

public class UserServiceImp implements UserService {
	UserDAO userDao = new UserDAO();
	PersonDAO personDao = new PersonDAO();
	CompanyDAO companyDao = new CompanyDAO();

	@Override
	public User Signin(User user, String type)  {
		if (userDao.get(user.getId()).isPresent())  {
			return null;
		}
		userDao.save(user);
		switch (type) {
			case "company":
				Company company = (Company) user;
				companyDao.save(company);
				break;
			case "person":
				Person person = (Person) user;
				personDao.save(person);
				break;
		}
		return user;

	}

	@Override
	public User Login(String name, String password) throws UserNotFoundException {
		Optional<User> user = userDao.Login(name, password);
		if (!user.isPresent()) {
			throw new UserNotFoundException();
		}
		Session.setUser(user.get());
		return user.get();
	}

	@Override
	public void removeUser(User u) {
		userDao.delete(u);
	}

	@Override
	public User EditUser(User u, User newUser) {
		userDao.update(u, newUser);
		return userDao.get(u.getId()).get();
	}

}
