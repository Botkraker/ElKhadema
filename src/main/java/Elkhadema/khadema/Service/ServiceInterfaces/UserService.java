package Elkhadema.khadema.Service.ServiceInterfaces;

import Elkhadema.khadema.domain.User;
import Elkhadema.khadema.util.Exception.UserNotFoundException;

public interface UserService {
	public User Signin(User u, String type);

	public User Login(String name, String password) throws UserNotFoundException;

	public void removeUser(User u);

	public User EditUser(User u, User newT);
}
