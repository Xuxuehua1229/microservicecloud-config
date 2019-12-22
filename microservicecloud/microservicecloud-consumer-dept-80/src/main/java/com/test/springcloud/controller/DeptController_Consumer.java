package com.test.springcloud.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.test.springcloud.entities.Dept;

@RestController
public class DeptController_Consumer {
	//private static final String REST_URL = "http://localhost:8001";
	private static final String REST_URL = "http://MICROSERVICECLOUD-DEPT";
	/*
	 使用restTemplate访问restful接口非常的简单粗暴无脑
	 （url,requestMap,ResponseBean.class）这三个参数分别代表REST请求地址、请求参数、HTTP响应被转换成的对象类型
	 
	 */
	@Autowired
    private RestTemplate restTemplate;
	
	@RequestMapping("/consumer/dept/add")
	public boolean add(Dept dept) {
		return restTemplate.postForObject(REST_URL + "/dept/add", dept, Boolean.class);
	}
	
	@RequestMapping("/consumer/dept/get/{id}")
	public Dept get(@PathVariable Long id) {
		return restTemplate.getForObject(REST_URL + "/dept/get/"+id, Dept.class);
	}
	
	@SuppressWarnings("unchecked")
	@RequestMapping("/consumer/dept/list")
	public List<Dept> list(){
		return restTemplate.getForObject(REST_URL + "/dept/list",List.class);
	}
	
	//测试 @EnableDiscoveryClient,消费端可以调用服务发现
	@RequestMapping("/consumer/dept/discovery")
	public Object discovery() {
		return restTemplate.getForObject(REST_URL + "/dept/discovery", Object.class);
	}
	
}
