package com.atguigu.tx;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserService {
	@Autowired
	private UserDao userDao;
	
	
	
	@Transactional
	public void insertUser() {
		userDao.insert();
		// otherDao.other();xxx
		System.out.println("插入完成");
		int i = 10 / 0;//没有事务控制机制 即使发生异常也不会回滚
		
		// 加入事务 运行时异常回滚
	}
}
