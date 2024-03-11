package Elkhadema.khadema.Service.ServiceImplemantation;

import java.util.Date;
import java.util.Optional;

import Elkhadema.khadema.DAO.DAOImplemantation.CompanyDAO;
import Elkhadema.khadema.DAO.DAOImplemantation.PersonDAO;
import Elkhadema.khadema.DAO.DAOImplemantation.UserDAO;
import Elkhadema.khadema.Service.ServiceInterfaces.UserService;
import Elkhadema.khadema.domain.Company;
import Elkhadema.khadema.domain.Person;
import Elkhadema.khadema.domain.User;
import Elkhadema.khadema.util.PasswordEncryptor;
import Elkhadema.khadema.util.Session;
import Elkhadema.khadema.util.Exception.UserNotFoundException;

public class UserServiceImp implements UserService {
	UserDAO userDao = new UserDAO();
	PersonDAO personDao = new PersonDAO();
	CompanyDAO companyDao = new CompanyDAO();

	@Override
	public User SignUp(User user, String type) {
		if (userDao.get(user.getId()).isPresent()) {
			return null;
		}
		user.setLastloginDate(new Date());
		String encryptedPassword=PasswordEncryptor.encryptPassword(user.getUserName(), user.getPassword());
		user.setPassword(encryptedPassword);
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
		Optional<User> user = userDao.Login(name);
		if (!user.isPresent()) {
			throw new UserNotFoundException();
		}
		User user2=user.get();
		if (!PasswordEncryptor.verifyPassword(name, password, user2.getPassword())|| user2.isIs_banned()) {
			return null;
		}
		user2.setLastloginDate(new Date());
		user2.setIs_active(true);
		userDao.update(user2, user2);
		Session.setUser(user2);
		return user2;
	}

	@Override
	public void removeUser(User u) {
		userDao.delete(u);
	}

	@Override
	public User EditUser(User u, User newUser) throws UserNotFoundException {
		Optional<User> user=userDao.get(u.getId());
		if (!user.isPresent()) {
			throw new UserNotFoundException();
		}
		userDao.update(u, newUser);
		return newUser;
	}

}
