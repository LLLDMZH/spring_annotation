package com.atguigu.condition;

import java.util.Arrays;
import java.util.Set;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportSelector;
import org.springframework.core.type.AnnotationMetadata;

// 自定义逻辑 返回需要导入的组件
public class MyImportSelector implements ImportSelector{

	/**
	 * Select and return the names of which class(es) should be imported based on
	 * the {@link AnnotationMetadata} of the importing @{@link Configuration} class.
	 * 返回值：就是要导入到容器中的组件的全类名
	 * AnnotationMetadata:当前标注@Import注解的类的所有注解信息
 	 */
	@Override
	public String[] selectImports(AnnotationMetadata importingClassMetadata) {
		// 方法不要返回null
		String className = importingClassMetadata.getClassName();
		System.out.println("className:" + className);
		String enclosingClassName = importingClassMetadata.getEnclosingClassName();
		System.out.println("enclosingClassName:" + enclosingClassName);
		// 获取Import注解上下其他的注解
		Set<String> annotationTypes = importingClassMetadata.getAnnotationTypes();
		System.out.println("annotationTypes" + annotationTypes);
		String[] interfaceNames = importingClassMetadata.getInterfaceNames();
		System.out.println("interfaceNames:" + Arrays.toString(interfaceNames));
		String[] memberClassNames = importingClassMetadata.getMemberClassNames();
		System.out.println("memberClassNames:" + Arrays.toString(memberClassNames));
		String superClassName = importingClassMetadata.getSuperClassName();
		System.out.println("superClassName:" + superClassName);
		return new String[] {"com.atguigu.bean.Red", "com.atguigu.bean.Blue"};
	}

}
