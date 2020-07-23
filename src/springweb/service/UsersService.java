package springweb.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import springweb.dao.User;
import springweb.dao.UsersDAO;

@Service("usersService")
public class UsersService {
	
	private UsersDAO usersDao;
	
	@Autowired
	public void setUsersDao(UsersDAO usersDao) {
		this.usersDao = usersDao;
	}
/*
	public List<User> getCurrent(){
		return usersDao.getUsers();
	}
*/	
	public void create(User user) {
		usersDao.create(user);
	}
}
