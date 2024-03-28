package Elkhadema.khadema.Service.ServiceImplemantation;

import java.io.IOException;
import java.util.Date;
import java.util.Optional;

import Elkhadema.khadema.DAO.DAOImplemantation.CompanyDAO;
import Elkhadema.khadema.DAO.DAOImplemantation.ContactInfoDAO;
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
	ContactInfoDAO contactInfoDAO = new ContactInfoDAO();

	@Override
	public User SignUp(User user) {
		if (userDao.get(user.getId()).isPresent()) {
			return null;
		}
		user.setCreationDate(new Date());
		user.setLastloginDate(new Date());
		String encryptedPassword = PasswordEncryptor.encryptPassword(user.getUserName(), user.getPassword());
		user.setPassword(encryptedPassword);
		try {
			userDao.save(user);
		} catch (IOException e) {
			e.printStackTrace();
		}
		if (user instanceof Company) {
			Company company = (Company) user;
			companyDao.save(company);
		} else {
			Person person = (Person) user;
			personDao.save(person);

		}

		return user;

	}

	@Override
	public User Login(String name, String password) throws UserNotFoundException {
		Optional<User> user = userDao.Login(name, PasswordEncryptor.encryptPassword(name, password));
		System.out.println("houni");
		if (!user.isPresent()) {
			throw new UserNotFoundException();
		}
		User user2 = user.get();
		if (!PasswordEncryptor.verifyPassword(name, password, user2.getPassword()) || user2.isIs_banned()) {
			return null;
		}
		user2.setContactInfo(contactInfoDAO.get(user2.getContactInfo().getId()).get());
		user2.setIs_active(true);
		userDao.update(user2, user2);
		Session.setUser(user2);
		return user2;
	}

	@Override
	public User getUserById(User user) {
		if (companyDao.get(user.getId()).isPresent()) {
			return companyDao.get(user.getId()).get();
		}
		return personDao.get(user.getId()).get();
	}

	@Override
	public void logOut(User user) {
		User user2 = userDao.get(user.getId()).get();
		user.setIs_active(false);
		user.setLastloginDate(new Date());
		userDao.update(user2, user);

	}

	@Override
	public void removeUser(User u) {
		userDao.delete(u);
	}

	@Override
	public User EditUser(User u, User newUser) throws UserNotFoundException {
		Optional<User> user = userDao.get(u.getId());
		if (!user.isPresent()) {
			throw new UserNotFoundException();
		}
		userDao.update(u, newUser);
		if (u instanceof Person) {
			personDao.update(((Person) u), ((Person) newUser));
		}
		return newUser;
	}

}
