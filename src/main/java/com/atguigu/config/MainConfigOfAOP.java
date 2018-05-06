package com.atguigu.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

import com.atguigu.aop.LogAspects;
import com.atguigu.aop.MathCalculator;

/**
 * AOP: 【动态代理】
 * 		指在程序运行期间动态的将某段代码切入到
 * 		指定方法 指定位置进行运行的编程方式；
 * 1、导入aop模块；Spring AOP;(spring-aspects)
 * 2、定义一个业务逻辑类(MathCalculate)
 * 		在业务逻辑运行的时候将日志进行打印(dx方法之前，方法运行结束、方法出现异常）
 * 3、定义一个日志切面类(LogAsprcts)：切面类里面的方法需要动态感知MathCalculate.div运行到哪里
 * 		通知方法：
 * 			前置通知：logStart 在目标方法(div)运行之前运行
 * 			后置通知：logEnd 在目标方法(div)运行结束之后运行(无论方法正常结束还是异常结束)
 * 			返回通知：logReturn 在目标方法(div)正常返回之后运行
 * 			异常通知：logException 在目标方法(div)出现异常之后运行
 * 			环绕通知：动态代理，手动推进目标方法运行(Joinpoint.proceed())
 * 
 * 4、给切面类的目标方法 标注何时何地运行(通知注解)
 * 5、将切面类和业务逻辑类（目标方法所在的类）都加入到容器中
 * 6、必须告诉Spring哪个类是切面类（给切面类加一个注解）
 * 7、给配置类中加@EnableAspectJAutoProxy     【开启基于注解的aop模式】
 *	  在Spring中有很多的@EnableXXX;
 *		开启某一项功能 替代以前的配置。
 *
 * 三步：
 * 	1）、将业务逻辑组件和切面类都加入到容器中，告诉Spring哪个是切面类(@Aspect)
 * 	2）、在切面类上的每一个通知方法上标注通知注解，告诉Spring何时何地运行（切入点表达式）
 *  3）、开启基于注解的AOP模式@EnableAspectJAutoProxy  
 *  
 *  
 * AOP原理：
 * 		@EnableAspectJAutoProxy
 * 1、@EnableAspectJAutoProxy是什么？
 * 		@Import(AspectJAutoProxyRegistrar.class) 给容器中导入AspectJAutoProxyRegistrar
 * 			利用AspectJAutoProxyRegistrar自定义给容器中注入bean； 
 * 			注册beanDefinition Bean的定义信息:
 * 	       	internalAutoProxyCreator =  AnnotationAwareAspectJAutoProxyCreator
 * 	给容器中注册了一个AnnotationAwareAspectJAutoProxyCreator
 * 
 * 2、AnnotationAwareAspectJAutoProxyCreator
 * 		AnnotationAwareAspectJAutoProxyCreator
 * 		->AspectJAwareAdvisorAutoProxyCreator
 * 			->AbstractAdvisorAutoProxyCreator
 * 				->AbstractAutoProxyCreator extends ProxyProcessorSupport
 * 					implements SmartInstantiationAwareBeanPostProcessor,BeanFactoryAware
 * 					关注后置处理器(在Bean初始化完成前后做的事情)、自动装配BeanFactory
 * 
 * 	AbstractAutoProxyCreator.setBeanFactory()
 *  AbstractAutoProxyCreator.有后置处理器的逻辑		
 *  
 *  AbstractAdvisorAutoProxyCreator.13() -> initBeanFactory
 *  
 *  AnnotationAwareAspectJAutoProxyCreator.initBeanFactory()
 *  
 * 
 * 
流程
	注册后置处理器 以AnnotationAwareAspectJAutoProxyCreator为例
	1.传入配置类，创建IOC容器
	2.注册配置类，调用refresh（）刷新容器；
	3.registerBeanPostProcessors(beanFactory);注册Bean的后置处理器 阻止Bean的创建
		1）、先获取ioc容器已经定义了的需要创建对象的所有BeanPostProcessor 【注解中创建或者是容器中定义的】
		2）、给容器中加别的BeanPostProcessor
		3）、优先注册实现PriorityOrdered接口的BeanPostProcessor
		4）、再给容器注册实现了Ordered接口的BeanPostProcessor 而我们这个就是实现了这个接口的
		5）、注册没有实现优先级接口的BeanPostProcessor
		6）、注册BeanPostProcessor 实际上就是创建BeanPostProcessor对象，保存在容器中
			创建internalAutoProxyCreator的BeanPostProcessor【AnnotationAwareAspectJAutoProxyCreator】
			1.创建Bean实例
			2.populateBean(beanName, mbd, instanceWrapper);给Bean的属性赋值
			3.initializeBean初始化bean
				1.invokeAwareMethods：处理aware接口的方法回调
				2.applyBeanPostProcessorsBeforeInitialization：应用后置处理器的postProcessBeforeInitialization
				3.invokeInitMethods执行初始化方法
				4.applyBeanPostProcessorsAfterInitialization：执行后置处理器的postProcessAfterInitialization
			4.BeanPostProcessor(AnnotationAwareAspectJAutoProxyCreator)	创建成功 拥有aspectJAdvisorsBuilder
		7）.把BeanPostProcessor注册到BeanFactory中
			beanFactory.addBeanPostProcessor(postProcessor)
			
======================以上是创建和注册AnnotationAwareAspectJAutoProxyCreator的过程=================================
		AnnotationAwareAspectJAutoProxyCreator是这种类型的InstantiationAwareBeanPostProcessor
	4.finishBeanFactoryInitialization(beanFactory);完成BeanFactory初始化工作；创建剩下的单实例Bean
	1)、遍历获取容器中所有的Bean，依次创建对象getBean（beanName）
		getBean->doGetBean()->getSingleton()->
	2)、创建Bean
		AnnotationAwareAspectJAutoProxyCreator在所有Bean创建之前会有一个拦截，会调用InstantiationAwareBeanPostProcessor的postProcessBeforeInstantiation
		1）、先从缓存中获取当前bean，如果能获取到，说明bean是在之前被创建过的，直接使用，否则在创建
			只要创建好的都会被缓存起来
		2）、createBean()创建bean；   AnnotationAwareAspectJAutoProxyCreator会在任何bean创建之前尝试返回实例
			[BeanPostProcessor]是在Bean对象创建完成初始化前后调用的
			[InstantiationAwareBeanPostProcessor]是在创建Bean实例之前先尝试用后置处理器返回对象的
			1、resolveBeforeInstantiation(beanName, mbdToUse);希望后置处理器返回一个代理对象。
				bean = applyBeanPostProcessorsBeforeInstantiation(targetType, beanName);
				 // 拿到所有的后置处理器 如果是InstantiationAwareBeanPostProcessor 就执行postProcessBeforeInstantiation
					if (bean != null) {
						bean = applyBeanPostProcessorsAfterInitialization(bean, beanName);
					}
			如果能返回代理对象就使用，如果不能就继续2
			2、doCreateBean(beanName, mbdToUse, args);真正去创建一个bean实例和3.6流程一样
			
			
	AnnotationAwareAspectJAutoProxyCreator【InstantiationAwareBeanPostProcessor】的作用：
	1、每一个bean创建之前调用postProcessBeforeInstantiation（）
		关心MathCalculator和LogAspect的创建
		1）、当前Bean是否在advisedBeans中（保存了所有需要增强的bean）
		2）、判断当前bean是否是基础类型Advice.class Pointcut.class Advisor.class AopInfrastructureBean.class 或者是否是切面	this.aspectJAdvisorFactory.isAspect(beanClass));
		3）、判断是否需要跳过
			1）、获取候选的增强器（切面里面的通知方法）LinkedList<Advisor> candidateAdvisors = findCandidateAdvisors();
				每一个封装的增强器是InstantiationModelAwarePointcutAdvisor；
				判断每一个增强器是否AspectJPointcutAdvisor；返回true
			2）、但是永远返回false
	2、创建对象
	3、 postProcessAfterInitialization
			wrapIfNecessary
			1、获取当前bean的所有增强器（通知方法）Object[] specificInterceptors
			   1、找到候选的所有增前器（找哪些通知方法需要切入当前bean方法的）
			   2、获取到能在bean使用的增强器
			   3、给增强器排序
			2、保存当前Bean 放入advisedBeans：
			3、如果当前bean需要增强 创建代理对象。	
				1、获取所有增强器（通知方法）
				2、保存到proxyFactory 
				3、创建代理对象：Spring自动决定
					JdkDynamicAopProxy(config);jdk动态代理 有接口
					ObjenesisCglibAopProxy(config);cglib动态代理 可以强制使用
			4、给容器返回当前组件使用cglib增强的代理对象。
			5、以后容器中获取到的就是这个组件的代理对象，执行目标方法的时候，代理对象就会执行通知方法的流程。
	3、执行目标方法	
			容器中保存了组件的代理对象(cglib增强后的对象，保存了 【增强器，目标对象，xxx】)
			1、CglibAopProxy.intercept();拦截目标方法的执行
			2、根据ProxyFactory对象获取将要执行的目标方法拦截器链。
					List<Object> chain = this.advised.getInterceptorsAndDynamicInterceptionAdvice(method, targetClass);
					1）、List<Object> interceptorList 保存所有的拦截器 5个
						一个默认的ExposeInvocationInterceptor 和  4个增强器
					2）、遍历所有的增强器，将其转为Interceptor
						Interceptor[] interceptors = registry.getInterceptors(advisor);
					3）、将增强器转为List<MethodInterceptor>
						如果是MethodInterceptor 直接加入集合中。
						如果不是 使用AdvisorAdapter将增强器转为MethodInterceptor
						转换完成返回MethodInterceptor数组
			3、如果没有拦截器链，直接执行目标方法
				拦截器链：每一个通知方法又被包装为方法拦截器，利用MethodInterceptor机制
			4、如果有拦截器链，需要构造CglibMethodInvocation 并执行其proceed()方法
				retVal = new CglibMethodInvocation(proxy, target, method, args, targetClass, chain, methodProxy).proceed();
			5、拦截器链的触发过程
				1、如果没有拦截器 执行目标方法。或者拦截器的索引和拦截器数组大小-1一样（执行到了最后一个拦截器） 执行目标方法
				2、链式获取每一个拦截器，拦截器执行invoke方法，每一个拦截器等待下一个拦截器的执行完成返回再执行。
					拦截器链机制，保证通知方法与目标方法的执行
	
总结：
	1、@EnableAspectJAutoProxy 开启AOP功能
	2、@EnableAspectJAutoProxy 会给容器注册一个组件AnnotationAwareAspectJAutoProxyCreator
	3、AnnotationAwareAspectJAutoProxyCreator是一个后置处理器
	4、容器创建流程。
		1）、registerBeanPostProcessors（）注册后置处理器 创建AnnotationAwareAspectJAutoProxyCreator
		2）、finishBeanFactoryInitialization（）初始化剩下的单实例bean
			1、创建业务逻辑组件和切面
			2、AnnotationAwareAspectJAutoProxyCreator
				1、每一个bean创建之前调用postProcessBeforeInstantiation（）
				2、创建对象
				3、 postProcessAfterInitialization-》 wrapIfNecessary 判断组件是否需要增强
					是：切面的通知方法 包装成增强器Advisor；给业务逻辑组件创建一个代理对象(cglib)
	5.执行目标方法	
		1、代理对象执行目标方法。
		2、CglibAopProxy.intercept()；
			1、得到目标方法的拦截器链（就是增强器包装成拦截器MethodInterceptor）
			2、利用拦截器的链式形式，依次进入每一个拦截器进行执行
			3、效果：
				前置通知-->目标方法--->后置通知--->返回通知
				前置通知-->目标方法--->后置通知--->异常通知
 */

//相当于配置文件	<aop:aspectj-autoproxy/>

@EnableAspectJAutoProxy 
@Configuration
public class MainConfigOfAOP {
	
	// 业务逻辑类加入到容器中
	@Bean
	public MathCalculator calculator() {
		return new MathCalculator();
	}
	
	// 切面类加入到容器中
	@Bean
	public LogAspects logAspects() {
		return new LogAspects();
	}
	
}
