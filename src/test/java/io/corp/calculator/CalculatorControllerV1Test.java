package io.corp.calculator;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.corp.calculator.model.Operation;
import io.corp.calculator.model.Result;
import io.corp.calculator.service.CalculatorService;

@SpringBootTest
@AutoConfigureMockMvc
public class CalculatorControllerV1Test {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private CalculatorService service;

	@Autowired
	private ObjectMapper objectMapper;

	@Test
	public void controller() throws Exception {

		Operation operation = createOperation();
		
		Result result = createResult();

		given(service.operate(operation)).willReturn(result);

		MvcResult execute = mockMvc.perform(
				MockMvcRequestBuilders.post("/api/v1/calculator/calculate")
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(operation)))
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

}
