package com.atguigu.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.atguigu.dao.BookDao;

@Service
public class BookService {

	// @Resource
	// @Resource(name = "bookDao2")

	// @Inject @Primary生效

	// 可以指定 按照id
	@Qualifier("bookDao")
	@Autowired(required = false) // 找不到就不装配
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
