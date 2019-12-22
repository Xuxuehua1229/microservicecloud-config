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
	//@GetMapping("/get/{id}")
	public Dept get(@PathVariable Long id) {
		return deptService.get(id);
	}

	@RequestMapping("/list")
	public List<Dept> list(){
		return deptService.list();
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
