package com.test.example;

import static org.hamcrest.Matchers.instanceOf;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.asyncDispatch;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.request;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

import java.nio.charset.Charset;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.web.context.WebApplicationContext;

import com.test.example.ApplicationTests.TestConfig;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = {Application.class, TestConfig.class})
@WebAppConfiguration
public class ApplicationTests {

	private static final MediaType APPLICATION_JSON_UTF8 = new MediaType(
			MediaType.APPLICATION_JSON.getType(),
			MediaType.APPLICATION_JSON.getSubtype(), Charset.forName("utf8"));

	@Configuration
	static class TestConfig {

		@Bean
		public TestService2 testService2() {
			return Mockito.mock(TestService2.class);
		}
	}

	@Autowired
	private WebApplicationContext wac;

	@Autowired
	private TestService2 testService2;

	private MockMvc mockMvc;

	@Before
	public void setup() {
		/*
		 * This will throw an exception when the second test case runs. Running
		 * one test case works both together will fail. If the @Validated
		 * annotation is removed from TestService2 it will pass.
		 */
		Mockito.reset(testService2);
		this.mockMvc = webAppContextSetup(this.wac).build();
	}

	@Test
	public void testEndpoint() throws Exception {
		MvcResult mvcResult = this.mockMvc
				.perform(get("/").accept(APPLICATION_JSON_UTF8))
				.andExpect(request().asyncStarted())
				.andExpect(request().asyncResult(instanceOf(String.class)))
				.andReturn();

		this.mockMvc.perform(asyncDispatch(mvcResult))
				.andExpect(status().isOk())
				.andExpect(content().contentType(APPLICATION_JSON_UTF8))
				.andExpect(content().string("Hello World"));
		verify(testService2).doMoreWork("hello", "world");
	}

	@Test
	public void testEndpoint2() throws Exception {
		MvcResult mvcResult = this.mockMvc
				.perform(get("/").accept(APPLICATION_JSON_UTF8))
				.andExpect(request().asyncStarted())
				.andExpect(request().asyncResult(instanceOf(String.class)))
				.andReturn();

		this.mockMvc.perform(asyncDispatch(mvcResult))
				.andExpect(status().isOk())
				.andExpect(content().contentType(APPLICATION_JSON_UTF8))
				.andExpect(content().string("Hello World"));
		verify(testService2).doMoreWork("hello", "world");
	}
}
