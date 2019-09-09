package com.lzj.springbatch.tasklet;

import java.util.List;

import javax.sql.DataSource;

import com.lzj.utils.RedisClient;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;

import com.lzj.mybatis.dao.UserDao;
import com.lzj.springbatch.model.User;

public class MyTasklet implements Tasklet {
	@Autowired
	private RedisClient redisClient;

	private DataSource dataSource;
	
	private UserDao userDao;
	
	public UserDao getUserDao() {
		return userDao;
	}

	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}

	public DataSource getDataSource() {
		return dataSource;
	}

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	@Override
	public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {

		try {
			String result = redisClient.get("test");
			System.out.println(result);
		} catch (Exception e) {
			e.printStackTrace();
		}
		User user = new User();
		user.setId(0);
		List<User> users = userDao.select(user);
		for(User user1 : users){
			System.out.println(user1);
		}
		return RepeatStatus.FINISHED;
	}

}
