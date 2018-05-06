package com.atguigu.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.atguigu.dao.BookDao;

@Service
public class BookService {

	// @Resource
	// @Resource(name = "bookDao2")

	// @Inject @Primary��Ч

	// ����ָ�� ����id
	@Qualifier("bookDao")
	@Autowired(required = false) // �Ҳ����Ͳ�װ��
	private BookDao bookDao;

	public void print() {
		System.out.println(bookDao);
	}

	@Override
	public String toString() {
		return "BookService [bookDao=" + bookDao + "]";
	}

	public BookService() {
		super();
		// TODO Auto-generated constructor stub
	}
	
}
