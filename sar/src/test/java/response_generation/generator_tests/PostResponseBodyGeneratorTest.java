package response_generation.generator_tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import response_generation.boundaries.ResponseBodyUtility;
import response_generation.generators.PostResponseBodyGenerator;

class PostResponseBodyGeneratorTest {
	private Gson gson;
	private JsonObject responseBody;
	
	private final int ID_TEST_VALUE = 123;
	private String parameterKey;
	private String expectedResponse;
	
	@BeforeEach
	void init() {
		gson = new Gson();
		responseBody = new JsonObject();
	}

	@Test
	void test_generateResponseBody_passedValidJsonNewAccountPost_returnsTrueForMatchingExpectedOutput() {
		parameterKey = ResponseBodyUtility.ACCOUNT_ID_KEY;
		responseBody.add(parameterKey, gson.toJsonTree(ID_TEST_VALUE));
		expectedResponse = responseBody.toString();
		ResponseBodyUtility postResponseBodyGenerator = new PostResponseBodyGenerator(parameterKey,ID_TEST_VALUE);
		
		assertEquals(expectedResponse, postResponseBodyGenerator.generateResponseBody());
	}
	
	@Test
	void test_generateResponseBody_passedValidJsonForNewMessagePost_returnsTrueForMatchingExpectedOutput() {
		parameterKey = ResponseBodyUtility.MESSAGE_ID_KEY;
		responseBody.add(parameterKey, gson.toJsonTree(ID_TEST_VALUE));
		expectedResponse = responseBody.toString();
		ResponseBodyUtility postResponseBodyGenerator = new PostResponseBodyGenerator(parameterKey,ID_TEST_VALUE);
		
		assertEquals(expectedResponse, postResponseBodyGenerator.generateResponseBody());
	}
	
	@Test
	void test_generateResponseBody_passedValidJsonForNewRatingPost_returnsTrueForMatchingExpectedOutput() {
		parameterKey = ResponseBodyUtility.RATING_ID_KEY;
		responseBody.add(parameterKey, gson.toJsonTree(ID_TEST_VALUE));
		expectedResponse = responseBody.toString();
		ResponseBodyUtility postResponseBodyGenerator = new PostResponseBodyGenerator(parameterKey,ID_TEST_VALUE);
		
		assertEquals(expectedResponse, postResponseBodyGenerator.generateResponseBody());
	}
	
	@Test
	void test_generateResponseBody_passedValidJsonForNewRidePost_returnsTrueForMatchingExpectedOutput() {
		parameterKey = ResponseBodyUtility.RIDE_ID_KEY;
		responseBody.add(parameterKey, gson.toJsonTree(ID_TEST_VALUE));
		expectedResponse = responseBody.toString();
		ResponseBodyUtility postResponseBodyGenerator = new PostResponseBodyGenerator(parameterKey,ID_TEST_VALUE);
		
		assertEquals(expectedResponse, postResponseBodyGenerator.generateResponseBody());
	}
	
	@Test
	void test_generateResponseBody_passedValidJsonForNewRideRequestPost_returnsTrueForMatchingExpectedOutput() {
		parameterKey = ResponseBodyUtility.RIDE_REQUEST_ID_KEY;
		responseBody.add(parameterKey, gson.toJsonTree(ID_TEST_VALUE));
		expectedResponse = responseBody.toString();
		ResponseBodyUtility postResponseBodyGenerator = new PostResponseBodyGenerator(parameterKey,ID_TEST_VALUE);
		
		assertEquals(expectedResponse, postResponseBodyGenerator.generateResponseBody());
	}

}
