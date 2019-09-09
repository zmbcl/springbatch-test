package com.lzj.mybatis.dao;

import java.util.List;

import com.lzj.springbatch.model.User;

public interface UserDao {
	
	public List<User> select(User user);

	Integer insert(User user);

}
