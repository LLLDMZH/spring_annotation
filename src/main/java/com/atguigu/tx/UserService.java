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
		System.out.println("�������");
		int i = 10 / 0;//û��������ƻ��� ��ʹ�����쳣Ҳ����ع�
		
		// �������� ����ʱ�쳣�ع�
	}
}
