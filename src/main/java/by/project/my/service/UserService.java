package by.project.my.service;

import java.util.List;

import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import by.project.my.dao.UserDao;
import by.project.my.model.User;

@Service
public class UserService {

	@Autowired
	private UserDao userDao;

	public boolean addUser(User user) {
		return userDao.addUser(user);
	}

	public User findByUsername(String username) {
		return userDao.findByUsername(username);
	}

	public List<User> getAllUsers() {
		return userDao.getAllUsers();
	}

	public void updateUser(User user) {
		userDao.updateUser(user);
	}

	public void deleteUser(String username) {
		userDao.deleteUser(username);
	}

	public User login(String username, String password)
			throws AuthenticationException {
		User user = this.userDao.findByUsername(username);
		if (user != null) {
			String pwd = DigestUtils.sha256Hex(password + "," + username);
			if (!user.getPassword().equalsIgnoreCase(pwd)) {
				throw new AuthenticationException(
						"Wrong username/password combination.",
						"invalid.password");
			}
		} else {
			throw new AuthenticationException(
					"Wrong username/password combination.", "invalid.username");
		}
		return user;
	}

}
