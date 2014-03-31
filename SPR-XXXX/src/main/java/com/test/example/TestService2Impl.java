package com.test.example;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service("TestService2")
public class TestService2Impl implements TestService2 {
	private final Logger log = LoggerFactory.getLogger(TestService2Impl.class);

	@Override
	public void doMoreWork(String string1, String string2) {
		log.info("doing more work");
	}

}
