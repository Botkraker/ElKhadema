package Elkhadema.khadema.Service.ServiceInterfaces;

import Elkhadema.khadema.domain.User;

public interface UserService {
	public User Signin(User u);

	public User Login(String name, String password);

	public void removeUser(User u);

	public User EditUser(User u,User newT);
}
