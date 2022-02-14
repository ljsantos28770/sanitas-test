package io.corp.calculator;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.io.IOException;

import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.corp.calculator.model.Operation;
import io.corp.calculator.model.Result;

@AutoConfigureMockMvc
@SpringBootTest
public class CalculatorV1MockMvcTest {

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private ObjectMapper objectMapper;

	private static String URL = "/api/v1/calculator/calculate";

	@Test
	void calculate() throws Exception {
		Operation operation = createOperation();

		Result result = createResult();

		MvcResult execute = mockMvc.perform(
				post(URL).contentType(MediaType.APPLICATION_JSON).content(objectMapper.writeValueAsString(operation)))
				.andExpect(status().isOk()).andReturn();

		String content = execute.getResponse().getContentAsString();
		
		Assert.assertEquals(objectMapper.readTree(content), objectMapper.convertValue(result, JsonNode.class));

	}

	private Operation createOperation() {
		return Operation.builder().firstNumber("3").secondNumber("5").operator("+").build();
	}

	private Result createResult() {
		return Result.builder().result(8).build();
	}

	public <T> T convertJsonToPOJO(String jsonString, Class<?> target) {

		try {
			return (T) objectMapper.readValue(jsonString, Class.forName(target.getName()));
		} catch (ClassNotFoundException | IOException e) {
			return null;
		}
	}
}
