package com.atguigu.jdbc.template;

import java.util.List;

import org.springframework.jdbc.core.support.JdbcDaoSupport;

public class StudentDao extends JdbcDaoSupport{
	
	public void insertStudent(Student student) {
		//��ɾ�Ķ���ͨ��update��ɵ�
		String sql = "insert into student (name,age) values(?,?)";
		 this.getJdbcTemplate().update(sql,student.getName(),student.getAge());
	}

	public void deleteStudent(int id) {
		String sql = "delete from student where id=?";
		this.getJdbcTemplate().update(sql,id);
	}

	public void updateStudent(Student student) {
		String sql = "update student set name =?, age=? where id=?";
		this.getJdbcTemplate().update(sql,student.getName(),student.getAge(),student.getId());
	}

	public String selectStudentNameById(int id) {
		String sql = "select name from student where id=?";
		return this.getJdbcTemplate().queryForObject(sql, String.class,id);
	}

	public List<String> selectStudentNames() {
		String sql = "select name from student ";
		return this.getJdbcTemplate().queryForList(sql,String.class);
	}

	public Student selectStudentById(int id) {
		String sql = "select id,name,age from student where id=?";	//�����ѯ�������ֶθ��� �����Mapper�е�һ�� ��Ȼrs �ò���
		return (Student) this.getJdbcTemplate().queryForObject(sql,new StudentRowMapper(),id);
	}

	public List<Student> selectStudents() {
		String sql = "select id,name,age from student";
		return this.getJdbcTemplate().query(sql,new StudentRowMapper());
	}

}
