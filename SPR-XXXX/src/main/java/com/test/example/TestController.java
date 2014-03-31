package com.test.example;

import java.util.concurrent.Callable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {
	private final TestService1 testService1;

	@Autowired
	public TestController(TestService1 testService1) {
		this.testService1 = testService1;
	}

	@RequestMapping("/")
	public Callable<String> endpoint() {

		return new Callable<String>() {
			@Override
			public String call() throws Exception {
				testService1.doWork("hello", "world");
				return "Hello World";
			}
		};
	}
}
