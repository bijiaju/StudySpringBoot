package com.bee.springboot;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.MultipartConfigFactory;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

import javax.servlet.MultipartConfigElement;
import javax.sql.DataSource;

@SpringBootApplication //核心注解，主要目的是开启自动注解，源码内部是组合配置
@MapperScan("com.cwh.springbootMybatis.mapper")//添加这个，就不用在dao添加mapper注解了
@EnableCaching//开启缓存，也是尽量放在service层
@EnableAspectJAutoProxy//开启aop自动代理
public class Application implements CommandLineRunner{

	//@Qualifier("")
	@Qualifier("dataSource")
	@Autowired
    DataSource dataSourceSource;

	/**
	 * 程序启动的入口
	 * @param args
	 */
	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}
	
	/**
	 * 查看用了什么数据库连接池
	 */
	public void run(String... args) throws Exception {
        System.out.println("DATASOURCE = " + dataSourceSource);
    }

	/**
	 * 文件上传配置
	 * @return
	 */
	@Bean
	public MultipartConfigElement multipartConfigElement() {
		MultipartConfigFactory factory = new MultipartConfigFactory();
		//单个文件最大
		factory.setMaxFileSize("10240KB"); //KB,MB
		/// 设置总上传数据总大小
		factory.setMaxRequestSize("102400KB");
		return factory.createMultipartConfig();
	}
}
