package com.test.springcloud.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.test.springcloud.entities.Dept;
import com.test.springcloud.service.DeptService;

@RestController
@RequestMapping("/dept")
public class DeptController {
	@Autowired
	private DeptService deptService;
	@Autowired
	private DiscoveryClient client;

	@RequestMapping(value = "/add",method=RequestMethod.POST)
	public boolean add(@RequestBody Dept dept) {
		return deptService.add(dept);
	}

	@RequestMapping(value = "/get/{id}",method=RequestMethod.GET)
	@HystrixCommand(fallbackMethod="processHystrix_Get")
	//@GetMapping("/get/{id}")
	public Dept get(@PathVariable Long id) {
		Dept dept = deptService.get(id);
		if (null == dept) {
			throw new RuntimeException("该ID" + id + "没有对应的信息");
		}
		return dept;
	}

	@RequestMapping("/list")
	public List<Dept> list(){
		return deptService.list();
	}
	
	public Dept processHystrix_Get(@PathVariable("id") Long id) {
		/*return new Dept().setDeptno(id).setDname("该ID：" + id + "没有没有对应的信息,null--@HystrixCommand")
				.setDb_source("no this database in MySQL");*/
		return new Dept(id,"该ID：" + id + "没有没有对应的信息,null--@HystrixCommand","no this database in MySQL");
	}

	@RequestMapping(value = "/discovery", method = RequestMethod.GET)
	public Object discovery() {
		List<String> list = client.getServices();
		System.out.println("********" + list);

		//MICROSERVICECLOUD-DEPT
		List<ServiceInstance> serList = client.getInstances("MICROSERVICECLOUD-DEPT");
		for (ServiceInstance element : serList) {
           System.out.println(element.getServiceId() + "\t" + element.getHost() + "\t" + element.getPort() + "\t" + element.getUri());
		}
		return this.client;
	}
}
