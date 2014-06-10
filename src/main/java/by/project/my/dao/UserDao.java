package by.project.my.dao;

import java.util.List;

import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import by.project.my.model.User;

@Repository
public class UserDao {

	@Autowired
	MongoOperations mongoOperations;

	public boolean addUser(User user) {
		if (!isExist(user.getUsername())) {
			String pwd = DigestUtils.sha256Hex(user.getPassword() + ","
					+ user.getUsername());
			user.setPassword(pwd);
			mongoOperations.insert(user);
			return true;
		} else {
			System.out
					.println("Username already in use: " + user.getUsername());
			return false;
		}
	}

	public User findByUsername(String username) {
		return mongoOperations.findOne(
				Query.query(Criteria.where("_id").is(username)), User.class);
	}

	public List<User> getAllUsers() {
		return mongoOperations.findAll(User.class);
	}

	public void updateUser(User user) {
		mongoOperations.save(user);
	}

	public void deleteUser(String username) {
		mongoOperations.remove(Query.query(Criteria.where("_id").is(username)),
				User.class);
	}

	public boolean isExist(String username) {
		if (findByUsername(username) != null) {
			return true;
		} else {
			return false;
		}
	}

}
