package com.jujie.user.server;

import java.util.List;

import com.jujie.user.User;
import com.jujie.user.dao.UserDaoImpl;
import com.jujie.util.page.Page;

public class UserServerImpl {
	
	private UserDaoImpl userDao;

	public void setUserDao(UserDaoImpl userDao) {
		this.userDao = userDao;
	}
	
	public User queryOneUser(int uuid) throws Exception{
		return userDao.queryOneUser(uuid);
	}
	
	
	public List<User> queryAllUser(Object[] cons,Page page) throws Exception{
		return userDao.queryAllUser(cons,page);
	}
	
	
	public int saveOneUser(User user) throws Exception{
		return userDao.saveOneUser(user);
	}
	
	public void editOneUser(User user) throws Exception{
		userDao.editOneUser(user);
	}
	
	public void deleteOneUser(int uuid) throws Exception{
		userDao.deleteOneUser(uuid);
	}
	
}
