package com.atguigu.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(value = "com.atguigu.bean")//默认的扫描规则是不会条件没有注解的bean的
public class MainConfig1 {

}
