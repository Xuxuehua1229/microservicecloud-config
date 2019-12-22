package com.test.springcloud.service;

import java.util.List;

import org.springframework.stereotype.Component;

import com.test.springcloud.entities.Dept;

import feign.hystrix.FallbackFactory;

@Component
public class DeptClientServiceFallbackFactory implements FallbackFactory<DeptClientService>{

	@Override
	public DeptClientService create(Throwable cause) {
		return new DeptClientService() {
			@Override
			public Dept get(Long id) {
				return new Dept(id,"该ID：" + id + "没有没有对应的信息,null--@HystrixCommand","no this database in MySQL");
			}
			
			@Override
			public List<Dept> list() {
				// TODO Auto-generated method stub
				return null;
			}
			
			@Override
			public boolean add(Dept dept) {
				// TODO Auto-generated method stub
				return false;
			}
		};
	}

}
