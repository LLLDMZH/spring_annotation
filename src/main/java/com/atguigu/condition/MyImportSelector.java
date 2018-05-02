package com.atguigu.condition;

import java.util.Arrays;
import java.util.Set;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportSelector;
import org.springframework.core.type.AnnotationMetadata;

// �Զ����߼� ������Ҫ��������
public class MyImportSelector implements ImportSelector{

	/**
	 * Select and return the names of which class(es) should be imported based on
	 * the {@link AnnotationMetadata} of the importing @{@link Configuration} class.
	 * ����ֵ������Ҫ���뵽�����е������ȫ����
	 * AnnotationMetadata:��ǰ��ע@Importע����������ע����Ϣ
 	 */
	@Override
	public String[] selectImports(AnnotationMetadata importingClassMetadata) {
		// ������Ҫ����null
		String className = importingClassMetadata.getClassName();
		System.out.println("className:" + className);
		String enclosingClassName = importingClassMetadata.getEnclosingClassName();
		System.out.println("enclosingClassName:" + enclosingClassName);
		// ��ȡImportע������������ע��
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
