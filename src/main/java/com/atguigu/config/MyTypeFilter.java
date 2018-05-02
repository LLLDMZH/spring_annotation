package com.atguigu.config;

import java.io.IOException;

import org.springframework.core.io.Resource;
import org.springframework.core.type.AnnotationMetadata;
import org.springframework.core.type.ClassMetadata;
import org.springframework.core.type.classreading.MetadataReader;
import org.springframework.core.type.classreading.MetadataReaderFactory;
import org.springframework.core.type.filter.TypeFilter;

/**
 * 
 * @author lenovo
 *
 */
public class MyTypeFilter implements TypeFilter {

	/**
	 * Determine whether this filter matches for the class described by the given
	 * metadata.
	 * 
	 * @param metadataReader
	 *            ��ȡ��ǰ�����Ϣ
	 * @param metadataReaderFactory
	 *            ���Ի�ȡ�����κ������Ϣ for other classes (such as superclasses and
	 *            interfaces)
	 * @return whether this filter matches
	 * @throws IOException
	 *             in case of I/O failure when reading metadata
	 */
	@Override
	public boolean match(MetadataReader metadataReader, MetadataReaderFactory metadataReaderFactory)
			throws IOException {
		// ��ȡ��ǰ���ע����Ϣ
		AnnotationMetadata annotationMetadata = metadataReader.getAnnotationMetadata();
		// ��ȡ��ǰ�������Ϣ 
		ClassMetadata classMetadata = metadataReader.getClassMetadata();
		// ��ȡ��ǰ�����Դ�����·���ȣ�
		Resource resource = metadataReader.getResource();
		
		String className = classMetadata.getClassName();
		System.out.println("------->" + className);
		
		// ָ��һ������ ����Service,Controller,Filter,Person
		if (className.contains("er")) {
			return true;
		}
		return false;
	}

}
