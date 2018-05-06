package com.atguigu.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.EmbeddedValueResolverAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.PropertySource;
import org.springframework.util.StringValueResolver;

import com.atguigu.bean.Yellow;
import com.mchange.v2.c3p0.ComboPooledDataSource;

/**
 * Profile��
 * 		SpringΪ�����ṩ�Ŀ��Ը��ݵ�ǰ�ƾ�����̬������л�һϵ������Ĺ��ܣ�
 * 
 * �������������Ի�������������
 * ����Դ�� (/A)(/B)(/C)
 * @Profile:ָ��������ĸ�����������²��ܱ�ע�ᵽ�����У���ָ�����κλ����¶���ע����������
 * 1)�����˻�����ʶ��bean ֻ����������������ʱ�����ע�ᵽ������Ĭ����Default����
 * 2)��д���������ϣ�ֻ������ָ���Ļ�����ʱ������������������������ò�����Ч
 * 3)��û�б�ע������ʶ��bean���κλ����¶��Ǽ��صġ�ǰ����������������Ч������¡�
 */
//@Profile("test")
@PropertySource(value = {"classpath:dbconfig.properties"})
@Configuration
public class MainConfigOfProfile implements EmbeddedValueResolverAware{
	
	@Value("${db.user}")
	private String user;
	
	private StringValueResolver stringValueResolver;
	
	private String driverClass;
	
	
	@Profile("test")
	@Bean
	public Yellow yellow() {
		return new Yellow();
	}
	
	
	@Profile("test")
	@Bean("testDataSource")
	public DataSource dataSourceTest(@Value("${db.password}") String password) throws Exception {
		ComboPooledDataSource dataSource = new ComboPooledDataSource();
		dataSource.setUser(user);
		dataSource.setPassword(password);
		dataSource.setJdbcUrl("jdbc:mysql://127.0.0.1:3306/test");
		dataSource.setDriverClass("com.mysql.jdbc.Driver");
		return dataSource;
	}
	
	@Profile("dev")
	@Bean("devDataSource")
	public DataSource dataSourceDev(@Value("${db.password}") String password) throws Exception {
		ComboPooledDataSource dataSource = new ComboPooledDataSource();
		dataSource.setUser(user);
		dataSource.setPassword(password);
		dataSource.setJdbcUrl("jdbc:mysql://127.0.0.1:3306/suning");
		dataSource.setDriverClass("com.mysql.jdbc.Driver");
		return dataSource;
	}
	
	@Profile("prod")
	@Bean("prodDataSource")
	public DataSource dataSourceProd(@Value("${db.password}") String password) throws Exception {
		ComboPooledDataSource dataSource = new ComboPooledDataSource();
		dataSource.setUser(user);
		dataSource.setPassword(password);
		dataSource.setJdbcUrl("jdbc:mysql://127.0.0.1:3306/srtp");
		driverClass = this.stringValueResolver.resolveStringValue("${db.driverClass}");
		dataSource.setDriverClass(driverClass);
		return dataSource;
	}

	@Override
	public void setEmbeddedValueResolver(StringValueResolver resolver) {
		this.stringValueResolver = resolver;
	}
	
}
