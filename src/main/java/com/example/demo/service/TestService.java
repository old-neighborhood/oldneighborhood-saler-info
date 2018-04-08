package com.example.demo.service;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
@FeignClient(value = "neighborhood-validate-service")
public interface TestService {
	@RequestMapping(value="/Saler/getID",method=RequestMethod.GET)
	public String getID();
}
