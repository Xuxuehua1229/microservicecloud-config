package com.test.springcloud.cfgbean;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

import com.netflix.loadbalancer.IRule;
import com.netflix.loadbalancer.RandomRule;
import com.netflix.loadbalancer.RetryRule;
import com.netflix.loadbalancer.RoundRobinRule;

@Configuration
public class ConfigBean {
	
   @Bean
   @LoadBalanced //Spring Cloud Ribbon 是基于 Netfilx Ribbon 实现的一套客户端负载均衡的工具
   public RestTemplate getRestTemplate() {
	   //复杂构造函数的使用
	   /* SimpleClientHttpRequestFactory requestFactory = new SimpleClientHttpRequestFactory();
	    requestFactory.setConnectTimeout(30000);// 设置超时
	    requestFactory.setReadTimeout(30000);
	    RestTemplate restTemplate = new RestTemplate();
	    restTemplate.setRequestFactory(requestFactory);
	    return restTemplate;*/
	   return new RestTemplate();
   }
   
   /*@Bean
   public IRule getIRule() {
	   //return new RoundRobinRule();//达到的目的，用我们重新选择的随机算法替换默认的轮询。
	   //return new RandomRule();
	   return new RetryRule();
   }*/
}

//applicationContext.xml ====>  ConfigBean 加了 @Configuration 注解
/*
 @Bean
 public UserService getUserService() {
	  return new UserServiceImpl();
}*/
//<bean id="userService" class="com.test.springboot.service.UserServiceImpl"> </bean>