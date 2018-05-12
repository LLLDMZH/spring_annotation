package com.atguigu.service;

import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.atguigu.jdbc.template.MyException;
import com.atguigu.jdbc.template.Student;


public class SomeServiceImpl implements SomeService{

	@Override
	@Transactional(isolation = Isolation.DEFAULT, propagation = Propagation.REQUIRED,
	readOnly = true,rollbackFor = {MyException.class})
	public void doSome() {
		System.out.println("SomeServiceImpl---> doSome");
	}

	@Override
	public void doOther() {
		System.out.println("SomeServiceImpl---> doOther");
	}

}
