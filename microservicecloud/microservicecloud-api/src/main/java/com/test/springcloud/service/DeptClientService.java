package com.test.springcloud.service;

import java.util.List;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.test.springcloud.entities.Dept;

//@FeignClient("MICROSERVICECLOUD-DEPT") //服务熔断注解
@FeignClient(value="MICROSERVICECLOUD-DEPT",fallbackFactory=DeptClientServiceFallbackFactory.class)
public interface DeptClientService {
	
	@RequestMapping(value = "/dept/add",method = RequestMethod.POST)
	public boolean add(Dept dept);

	@RequestMapping(value="/dept/get/{id}",method=RequestMethod.GET)
	public Dept get(@PathVariable("id") Long id);

	@RequestMapping(value="/dept/list",method=RequestMethod.GET)
	public List<Dept> list();
}
