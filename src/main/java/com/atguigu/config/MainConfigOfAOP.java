package com.atguigu.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

import com.atguigu.aop.LogAspects;
import com.atguigu.aop.MathCalculator;

/**
 * AOP: ����̬����
 * 		ָ�ڳ��������ڼ䶯̬�Ľ�ĳ�δ������뵽
 * 		ָ������ ָ��λ�ý������еı�̷�ʽ��
 * 1������aopģ�飻Spring AOP;(spring-aspects)
 * 2������һ��ҵ���߼���(MathCalculate)
 * 		��ҵ���߼����е�ʱ����־���д�ӡ(dx����֮ǰ���������н��������������쳣��
 * 3������һ����־������(LogAsprcts)������������ķ�����Ҫ��̬��֪MathCalculate.div���е�����
 * 		֪ͨ������
 * 			ǰ��֪ͨ��logStart ��Ŀ�귽��(div)����֮ǰ����
 * 			����֪ͨ��logEnd ��Ŀ�귽��(div)���н���֮������(���۷����������������쳣����)
 * 			����֪ͨ��logReturn ��Ŀ�귽��(div)��������֮������
 * 			�쳣֪ͨ��logException ��Ŀ�귽��(div)�����쳣֮������
 * 			����֪ͨ����̬�����ֶ��ƽ�Ŀ�귽������(Joinpoint.proceed())
 * 
 * 4�����������Ŀ�귽�� ��ע��ʱ�ε�����(֪ͨע��)
 * 5�����������ҵ���߼��ࣨĿ�귽�����ڵ��ࣩ�����뵽������
 * 6���������Spring�ĸ����������ࣨ���������һ��ע�⣩
 * 7�����������м�@EnableAspectJAutoProxy     ����������ע���aopģʽ��
 *	  ��Spring���кܶ��@EnableXXX;
 *		����ĳһ��� �����ǰ�����á�
 *
 * ������
 * 	1������ҵ���߼�����������඼���뵽�����У�����Spring�ĸ���������(@Aspect)
 * 	2�������������ϵ�ÿһ��֪ͨ�����ϱ�ע֪ͨע�⣬����Spring��ʱ�ε����У��������ʽ��
 *  3������������ע���AOPģʽ@EnableAspectJAutoProxy  
 *  
 *  
 * AOPԭ��
 * 		@EnableAspectJAutoProxy
 * 1��@EnableAspectJAutoProxy��ʲô��
 * 		@Import(AspectJAutoProxyRegistrar.class) �������е���AspectJAutoProxyRegistrar
 * 			����AspectJAutoProxyRegistrar�Զ����������ע��bean�� 
 * 			ע��beanDefinition Bean�Ķ�����Ϣ:
 * 	       	internalAutoProxyCreator =  AnnotationAwareAspectJAutoProxyCreator
 * 	��������ע����һ��AnnotationAwareAspectJAutoProxyCreator
 * 
 * 2��AnnotationAwareAspectJAutoProxyCreator
 * 		AnnotationAwareAspectJAutoProxyCreator
 * 		->AspectJAwareAdvisorAutoProxyCreator
 * 			->AbstractAdvisorAutoProxyCreator
 * 				->AbstractAutoProxyCreator extends ProxyProcessorSupport
 * 					implements SmartInstantiationAwareBeanPostProcessor,BeanFactoryAware
 * 					��ע���ô�����(��Bean��ʼ�����ǰ����������)���Զ�װ��BeanFactory
 * 
 * 	AbstractAutoProxyCreator.setBeanFactory()
 *  AbstractAutoProxyCreator.�к��ô��������߼�		
 *  
 *  AbstractAdvisorAutoProxyCreator.13() -> initBeanFactory
 *  
 *  AnnotationAwareAspectJAutoProxyCreator.initBeanFactory()
 *  
 * 
 * 
����
	ע����ô����� ��AnnotationAwareAspectJAutoProxyCreatorΪ��
	1.���������࣬����IOC����
	2.ע�������࣬����refresh����ˢ��������
	3.registerBeanPostProcessors(beanFactory);ע��Bean�ĺ��ô����� ��ֹBean�Ĵ���
		1�����Ȼ�ȡioc�����Ѿ������˵���Ҫ�������������BeanPostProcessor ��ע���д��������������ж���ġ�
		2�����������мӱ��BeanPostProcessor
		3��������ע��ʵ��PriorityOrdered�ӿڵ�BeanPostProcessor
		4�����ٸ�����ע��ʵ����Ordered�ӿڵ�BeanPostProcessor �������������ʵ��������ӿڵ�
		5����ע��û��ʵ�����ȼ��ӿڵ�BeanPostProcessor
		6����ע��BeanPostProcessor ʵ���Ͼ��Ǵ���BeanPostProcessor���󣬱�����������
			����internalAutoProxyCreator��BeanPostProcessor��AnnotationAwareAspectJAutoProxyCreator��
			1.����Beanʵ��
			2.populateBean(beanName, mbd, instanceWrapper);��Bean�����Ը�ֵ
			3.initializeBean��ʼ��bean
				1.invokeAwareMethods������aware�ӿڵķ����ص�
				2.applyBeanPostProcessorsBeforeInitialization��Ӧ�ú��ô�������postProcessBeforeInitialization
				3.invokeInitMethodsִ�г�ʼ������
				4.applyBeanPostProcessorsAfterInitialization��ִ�к��ô�������postProcessAfterInitialization
			4.BeanPostProcessor(AnnotationAwareAspectJAutoProxyCreator)	�����ɹ� ӵ��aspectJAdvisorsBuilder
		7��.��BeanPostProcessorע�ᵽBeanFactory��
			beanFactory.addBeanPostProcessor(postProcessor)
			
======================�����Ǵ�����ע��AnnotationAwareAspectJAutoProxyCreator�Ĺ���=================================
		AnnotationAwareAspectJAutoProxyCreator���������͵�InstantiationAwareBeanPostProcessor
	4.finishBeanFactoryInitialization(beanFactory);���BeanFactory��ʼ������������ʣ�µĵ�ʵ��Bean
	1)��������ȡ���������е�Bean�����δ�������getBean��beanName��
		getBean->doGetBean()->getSingleton()->
	2)������Bean
		AnnotationAwareAspectJAutoProxyCreator������Bean����֮ǰ����һ�����أ������InstantiationAwareBeanPostProcessor��postProcessBeforeInstantiation
		1�����ȴӻ����л�ȡ��ǰbean������ܻ�ȡ����˵��bean����֮ǰ���������ģ�ֱ��ʹ�ã������ڴ���
			ֻҪ�����õĶ��ᱻ��������
		2����createBean()����bean��   AnnotationAwareAspectJAutoProxyCreator�����κ�bean����֮ǰ���Է���ʵ��
			[BeanPostProcessor]����Bean���󴴽���ɳ�ʼ��ǰ����õ�
			[InstantiationAwareBeanPostProcessor]���ڴ���Beanʵ��֮ǰ�ȳ����ú��ô��������ض����
			1��resolveBeforeInstantiation(beanName, mbdToUse);ϣ�����ô���������һ���������
				bean = applyBeanPostProcessorsBeforeInstantiation(targetType, beanName);
				 // �õ����еĺ��ô����� �����InstantiationAwareBeanPostProcessor ��ִ��postProcessBeforeInstantiation
					if (bean != null) {
						bean = applyBeanPostProcessorsAfterInitialization(bean, beanName);
					}
			����ܷ��ش�������ʹ�ã�������ܾͼ���2
			2��doCreateBean(beanName, mbdToUse, args);����ȥ����һ��beanʵ����3.6����һ��
			
			
	AnnotationAwareAspectJAutoProxyCreator��InstantiationAwareBeanPostProcessor�������ã�
	1��ÿһ��bean����֮ǰ����postProcessBeforeInstantiation����
		����MathCalculator��LogAspect�Ĵ���
		1������ǰBean�Ƿ���advisedBeans�У�������������Ҫ��ǿ��bean��
		2�����жϵ�ǰbean�Ƿ��ǻ�������Advice.class Pointcut.class Advisor.class AopInfrastructureBean.class �����Ƿ�������	this.aspectJAdvisorFactory.isAspect(beanClass));
		3�����ж��Ƿ���Ҫ����
			1������ȡ��ѡ����ǿ�������������֪ͨ������LinkedList<Advisor> candidateAdvisors = findCandidateAdvisors();
				ÿһ����װ����ǿ����InstantiationModelAwarePointcutAdvisor��
				�ж�ÿһ����ǿ���Ƿ�AspectJPointcutAdvisor������true
			2����������Զ����false
	2����������
	3�� postProcessAfterInitialization
			wrapIfNecessary
			1����ȡ��ǰbean��������ǿ����֪ͨ������Object[] specificInterceptors
			   1���ҵ���ѡ��������ǰ��������Щ֪ͨ������Ҫ���뵱ǰbean�����ģ�
			   2����ȡ������beanʹ�õ���ǿ��
			   3������ǿ������
			2�����浱ǰBean ����advisedBeans��
			3�������ǰbean��Ҫ��ǿ �����������	
				1����ȡ������ǿ����֪ͨ������
				2�����浽proxyFactory 
				3�������������Spring�Զ�����
					JdkDynamicAopProxy(config);jdk��̬���� �нӿ�
					ObjenesisCglibAopProxy(config);cglib��̬���� ����ǿ��ʹ��
			4�����������ص�ǰ���ʹ��cglib��ǿ�Ĵ������
			5���Ժ������л�ȡ���ľ����������Ĵ������ִ��Ŀ�귽����ʱ�򣬴������ͻ�ִ��֪ͨ���������̡�
	3��ִ��Ŀ�귽��	
			�����б���������Ĵ������(cglib��ǿ��Ķ��󣬱����� ����ǿ����Ŀ�����xxx��)
			1��CglibAopProxy.intercept();����Ŀ�귽����ִ��
			2������ProxyFactory�����ȡ��Ҫִ�е�Ŀ�귽������������
					List<Object> chain = this.advised.getInterceptorsAndDynamicInterceptionAdvice(method, targetClass);
					1����List<Object> interceptorList �������е������� 5��
						һ��Ĭ�ϵ�ExposeInvocationInterceptor ��  4����ǿ��
					2�����������е���ǿ��������תΪInterceptor
						Interceptor[] interceptors = registry.getInterceptors(advisor);
					3��������ǿ��תΪList<MethodInterceptor>
						�����MethodInterceptor ֱ�Ӽ��뼯���С�
						������� ʹ��AdvisorAdapter����ǿ��תΪMethodInterceptor
						ת����ɷ���MethodInterceptor����
			3�����û������������ֱ��ִ��Ŀ�귽��
				����������ÿһ��֪ͨ�����ֱ���װΪ����������������MethodInterceptor����
			4�������������������Ҫ����CglibMethodInvocation ��ִ����proceed()����
				retVal = new CglibMethodInvocation(proxy, target, method, args, targetClass, chain, methodProxy).proceed();
			5�����������Ĵ�������
				1�����û�������� ִ��Ŀ�귽���������������������������������С-1һ����ִ�е������һ���������� ִ��Ŀ�귽��
				2����ʽ��ȡÿһ����������������ִ��invoke������ÿһ���������ȴ���һ����������ִ����ɷ�����ִ�С�
					�����������ƣ���֤֪ͨ������Ŀ�귽����ִ��
	
�ܽ᣺
	1��@EnableAspectJAutoProxy ����AOP����
	2��@EnableAspectJAutoProxy �������ע��һ�����AnnotationAwareAspectJAutoProxyCreator
	3��AnnotationAwareAspectJAutoProxyCreator��һ�����ô�����
	4�������������̡�
		1����registerBeanPostProcessors����ע����ô����� ����AnnotationAwareAspectJAutoProxyCreator
		2����finishBeanFactoryInitialization������ʼ��ʣ�µĵ�ʵ��bean
			1������ҵ���߼����������
			2��AnnotationAwareAspectJAutoProxyCreator
				1��ÿһ��bean����֮ǰ����postProcessBeforeInstantiation����
				2����������
				3�� postProcessAfterInitialization-�� wrapIfNecessary �ж�����Ƿ���Ҫ��ǿ
					�ǣ������֪ͨ���� ��װ����ǿ��Advisor����ҵ���߼��������һ���������(cglib)
	5.ִ��Ŀ�귽��	
		1���������ִ��Ŀ�귽����
		2��CglibAopProxy.intercept()��
			1���õ�Ŀ�귽��������������������ǿ����װ��������MethodInterceptor��
			2����������������ʽ��ʽ�����ν���ÿһ������������ִ��
			3��Ч����
				ǰ��֪ͨ-->Ŀ�귽��--->����֪ͨ--->����֪ͨ
				ǰ��֪ͨ-->Ŀ�귽��--->����֪ͨ--->�쳣֪ͨ
 */

//�൱�������ļ�	<aop:aspectj-autoproxy/>

@EnableAspectJAutoProxy 
@Configuration
public class MainConfigOfAOP {
	
	// ҵ���߼�����뵽������
	@Bean
	public MathCalculator calculator() {
		return new MathCalculator();
	}
	
	// ��������뵽������
	@Bean
	public LogAspects logAspects() {
		return new LogAspects();
	}
	
}
