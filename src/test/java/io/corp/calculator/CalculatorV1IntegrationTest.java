package io.corp.calculator;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import com.fasterxml.jackson.databind.ObjectMapper;

import io.corp.calculator.model.Operation;
import io.corp.calculator.model.Result;

@SpringBootTest(classes = CalculatorApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class CalculatorV1IntegrationTest {

	private static String URL = "/api/v1/calculator/calculate";

	@Autowired
	private TestRestTemplate restTemplate;

	@Autowired
	private ObjectMapper mapper;
	
	@LocalServerPort
	private int port;
	

	@Test
	final void given_CalculatorControllerV1Input_then_return_ok() throws Exception {

        String expected = mapper.writeValueAsString(createResult());
        String input = mapper.writeValueAsString(createOperation());

		ResponseEntity<String> response = restTemplate.postForEntity(URL, getUnsecuredHttpEntity(input), String.class);
		JSONAssert.assertEquals(expected, response.getBody(), false);

	}
	
	private <T> HttpEntity<T> getUnsecuredHttpEntity(T entity) {
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);

		return new HttpEntity<T>(entity, headers);
	}

	private Operation createOperation() {
		return Operation.builder().firstNumber("3").secondNumber("5").operator("+").build();
	}
	    
	private Result createResult() {
		return Result.builder().result(8).build();
	}

}
