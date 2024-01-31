package Elkhadema.khadema.Service.ServiceInterfaces;

import Elkhadema.khadema.domain.User;
import Elkhadema.khadema.util.Exception.UserNotFoundException;

public interface UserService {
	/**
	 * @param user user to add
	 * @param type either person or company
	 * @return the user if succesfuly added 
	 */
	public User Signin(User user, String type);

	/**
	 * @param name username of user
	 * @param password password to be encryoted later
	 * @return user if succesful
	 * @throws UserNotFoundException when the user is not in the DB
	 */
	public User Login(String name, String password) throws UserNotFoundException;

	public void removeUser(User u);

	public User EditUser(User u, User newT) throws UserNotFoundException;
}
