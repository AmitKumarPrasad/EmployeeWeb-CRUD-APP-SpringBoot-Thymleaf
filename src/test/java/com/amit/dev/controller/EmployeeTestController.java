package com.amit.dev.controller;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.amit.dev.model.User;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
public class EmployeeTestController {

	private MockMvc mockMvc;

	@Autowired
	private WebApplicationContext wc;

	public final static User employee = User.builder().id(1).name("Amit").email("amit@gmail").country("India")
			.website("www.amitkumarprasad").build();

	List<User> employees = new ArrayList<User>();
	ObjectMapper MAPPER = new ObjectMapper();

	@Before
	public void setUp() {
		mockMvc = MockMvcBuilders.webAppContextSetup(wc).build();
	}

	@Test
	public void saveEmployee() throws JsonProcessingException, Exception {
		MvcResult result = mockMvc
				.perform(
						post("/v1/api").contentType(APPLICATION_JSON_VALUE).contentType(MAPPER.writeValueAsString(employee)))
				.andExpect(status().isOk())
				.andReturn();

		User response = MAPPER.readValue(result.getResponse().getContentAsString(), User.class);
		assertEquals(employee, response);

	}

}
