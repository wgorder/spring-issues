package com.test.example;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("TestService1")
public class TestService1Impl implements TestService1 {
	private final TestService2 testService2;

	@Autowired
	public TestService1Impl(TestService2 testService2) {
		this.testService2 = testService2;
	}

	@Override
	public void doWork(String string1, String string2) {
		testService2.doMoreWork(string1, string2);
	}

}
