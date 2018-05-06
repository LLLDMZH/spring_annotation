package com.atguigu.tx;

import javax.sql.DataSource;

import org.springframework.aop.framework.autoproxy.InfrastructureAdvisorAutoProxyCreator;
import org.springframework.context.annotation.AutoProxyRegistrar;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.AnnotationTransactionAttributeSource;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.ProxyTransactionManagementConfiguration;
import org.springframework.transaction.annotation.TransactionManagementConfigurationSelector;

import com.mchange.v2.c3p0.ComboPooledDataSource;

/**
 * 
 * ����ʽ����
 * 	1�������������
 * 		����Դ�����ݿ�������Spring jdbcģ��
 * 	2����������Դ��JdbcTemplate��Spring�ṩ�ļ����ݿ�����Ĺ��ߣ��������ݿ�
 *  3���������ϱ�ע@Transactional ��ʾ��ǰ������һ�����񷽷�
 *  4��@EnableTransactionManagement ��������ע�����������ܣ�
 *  5�������������������������    PlatformTransactionManager
 *  		��ǰ��xml�л�����DataSourceTransactionManager jdbcTemplate��MyBatis
 *  					  JtaTransactionManager  Hibernate
 *  					
 *  
 *  ԭ��
 *  1��@EnableTransactionManagement
 *  	@Import(TransactionManagementConfigurationSelector.class)
 *      1������Selector�ĵ������
 *      @EnableTransactionManagement ����һ������mode ��Ҫһ��AdviceMode Ĭ��ΪAdviceMode.PROXY
 *      	���Իᵼ���������	
 *      	AutoProxyRegistrar   
 *      	ProxyTransactionManagementConfiguration
 *  2��AutoProxyRegistrar��
 *  	��������ע��һ�� InfrastructureAdvisorAutoProxyCreator 
 *  	���ú��ô��������� �ڶ��󴴽��Ժ󣬰�װ���󣬷���һ�����������ǿ�������������ִ�з������������������е��á�
 *  
 *  3��ProxyTransactionManagementConfiguration
 *  	��������ע�������
 *  	1����������ע��������ǿ�� transactionAdvisor
 *  			1��������ǿ��Ҫ������ע����Ϣ: new AnnotationTransactionAttributeSource();
 *  			2������������  advisor.setAdvice(transactionInterceptor());
 *  				transactionInterceptor������������������Ϣ�����������
 *  				����һ������������ MethodInterceptor	
 *  				��Ŀ�귽��ִ�е�ʱ��
 *  						ִ����������
 *  						������������
 *  							1���Ȼ�ȡ������ص�����
 *  							2����ȡPlatformTransactionManager���������û�����ָ���κ�transactionManager
 *  								���ջ�������а������ͻ�ȡһ��PlatformTransactionManager
 *  							3��ִ��Ŀ�귽����
 *  								����쳣 ��ȡ���������������������������ع���β���
 *  								txInfo.getTransactionManager().rollback(txInfo.getTransactionStatus());
 *  								������� ��������������ύ����
 *  					            txInfo.getTransactionManager().commit(txInfo.getTransactionStatus());
 */

@EnableTransactionManagement
@Configuration
@ComponentScan("com.atguigu.tx")
public class TxConfig {
	
	// ����Դ
	@Bean
	public DataSource dataSource() throws Exception {
		ComboPooledDataSource dataSource = new ComboPooledDataSource();
		dataSource.setUser("root");
		dataSource.setPassword("123456");
		dataSource.setJdbcUrl("jdbc:mysql://127.0.0.1:3306/test");
		dataSource.setDriverClass("com.mysql.jdbc.Driver");
		return dataSource;
	}
	
	@Bean //ע�����ַ�ʽ ��һ�� ֱ�Ӵ���������ڶ��ֵ��������bean����
	public JdbcTemplate jdbcTemplate() throws Exception {
		// Spring��@Configuration������⴦���������м�����ķ�������ε���
		// ֻ�Ǵ������������
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource());
		return jdbcTemplate;
	}
	
	// ����Ҫһ��PlatformTransactionManager
	// ע�������������������
	@Bean
	public PlatformTransactionManager transactionManager() throws Exception {
		return new DataSourceTransactionManager(dataSource());
	}
}
