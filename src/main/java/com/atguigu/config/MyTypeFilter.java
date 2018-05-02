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
	 *            获取当前类的信息
	 * @param metadataReaderFactory
	 *            可以获取其他任何类的信息 for other classes (such as superclasses and
	 *            interfaces)
	 * @return whether this filter matches
	 * @throws IOException
	 *             in case of I/O failure when reading metadata
	 */
	@Override
	public boolean match(MetadataReader metadataReader, MetadataReaderFactory metadataReaderFactory)
			throws IOException {
		// 获取当前类的注解信息
		AnnotationMetadata annotationMetadata = metadataReader.getAnnotationMetadata();
		// 获取当前类的类信息 
		ClassMetadata classMetadata = metadataReader.getClassMetadata();
		// 获取当前类的资源（类的路径等）
		Resource resource = metadataReader.getResource();
		
		String className = classMetadata.getClassName();
		System.out.println("------->" + className);
		
		// 指定一个规则 类似Service,Controller,Filter,Person
		if (className.contains("er")) {
			return true;
		}
		return false;
	}

}
