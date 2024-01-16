package Elkhadema.khadema.Service;

import Elkhadema.khadema.domain.User;

public interface UserService {
	public User Signin(User u);

	public User Login(String name, String password);

	public void removeUser(Long userid);

	public User EditUser(User u);
	public void BanUser(User u);

}
