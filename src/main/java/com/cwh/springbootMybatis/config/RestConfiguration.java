package com.cwh.springbootMybatis.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/**
 * Rest Configuration. 是spring自带的http请求工具
 * 
 * @since 1.0.0 2017年11月22日
 * 我们可以通过Spring Boot快速开发REST接口，同时也可能需要在实现接口的过程中，通过Spring Boot调用内外部REST接口完成业务逻辑。

在Spring Boot中，调用REST Api常见的一般主要有两种方式，通过自带的RestTemplate或者自己开发http客户端工具实现服务调用。

RestTemplate基本功能非常强大，不过某些特殊场景，我们可能还是更习惯用自己封装的工具类，比如上传文件至分布式文件系统、处理带证书的https请求等。

本文以RestTemplate来举例，记录几个使用RestTemplate调用接口过程中发现的问题和解决方案。
 */
@Configuration
public class RestConfiguration {
	
	@Autowired
	private RestTemplateBuilder builder;

	@Bean
	public RestTemplate restTemplate() {
		return builder.build();
	}
	
}
