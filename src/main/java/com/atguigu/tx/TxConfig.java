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
 * 声明式事务：
 * 	1、导入相关依赖
 * 		数据源、数据库驱动、Spring jdbc模块
 * 	2、配置数据源、JdbcTemplate（Spring提供的简化数据库操作的工具）操作数据库
 *  3、给方法上标注@Transactional 表示当前方法是一个事务方法
 *  4、@EnableTransactionManagement 开启基于注解的事务管理功能；
 *  5、配置事务管理器来控制事务    PlatformTransactionManager
 *  		以前在xml中会配置DataSourceTransactionManager jdbcTemplate，MyBatis
 *  					  JtaTransactionManager  Hibernate
 *  					
 *  
 *  原理
 *  1、@EnableTransactionManagement
 *  	@Import(TransactionManagementConfigurationSelector.class)
 *      1、利用Selector的导入组件
 *      @EnableTransactionManagement 中有一个属性mode 需要一个AdviceMode 默认为AdviceMode.PROXY
 *      	所以会导入两个组件	
 *      	AutoProxyRegistrar   
 *      	ProxyTransactionManagementConfiguration
 *  2、AutoProxyRegistrar：
 *  	给容器中注册一个 InfrastructureAdvisorAutoProxyCreator 
 *  	利用后置处理器机制 在对象创建以后，包装对象，返回一个代理对象（增强器），代理对象执行方法利用拦截器链进行调用。
 *  
 *  3、ProxyTransactionManagementConfiguration
 *  	给容器中注册组件：
 *  	1、给容器中注入事务增强器 transactionAdvisor
 *  			1、事务增强器要用事务注解信息: new AnnotationTransactionAttributeSource();
 *  			2、事务拦截器  advisor.setAdvice(transactionInterceptor());
 *  				transactionInterceptor：保存了事务属性信息，事务管理器
 *  				它是一个方法拦截器 MethodInterceptor	
 *  				在目标方法执行的时候
 *  						执行拦截器链
 *  						事务拦截器：
 *  							1、先获取事务相关的属性
 *  							2、获取PlatformTransactionManager，如果事先没有添加指定任何transactionManager
 *  								最终会从容器中按照类型获取一个PlatformTransactionManager
 *  							3、执行目标方法，
 *  								如果异常 获取到事务管理器，利用事务管理器回滚这次操作
 *  								txInfo.getTransactionManager().rollback(txInfo.getTransactionStatus());
 *  								如果正常 利用事务管理器提交事务
 *  					            txInfo.getTransactionManager().commit(txInfo.getTransactionStatus());
 */

@EnableTransactionManagement
@Configuration
@ComponentScan("com.atguigu.tx")
public class TxConfig {
	
	// 数据源
	@Bean
	public DataSource dataSource() throws Exception {
		ComboPooledDataSource dataSource = new ComboPooledDataSource();
		dataSource.setUser("root");
		dataSource.setPassword("123456");
		dataSource.setJdbcUrl("jdbc:mysql://127.0.0.1:3306/test");
		dataSource.setDriverClass("com.mysql.jdbc.Driver");
		return dataSource;
	}
	
	@Bean //注入两种方式 第一种 直接传入参数，第二种调用这里的bean方法
	public JdbcTemplate jdbcTemplate() throws Exception {
		// Spring对@Configuration类会特殊处理，给容器中加组件的方法，多次调用
		// 只是从容器中找组件
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource());
		return jdbcTemplate;
	}
	
	// 还需要一个PlatformTransactionManager
	// 注册事务管理器在容器中
	@Bean
	public PlatformTransactionManager transactionManager() throws Exception {
		return new DataSourceTransactionManager(dataSource());
	}
}
