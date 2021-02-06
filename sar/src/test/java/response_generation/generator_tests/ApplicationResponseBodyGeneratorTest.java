package response_generation.generator_tests;

import static org.junit.jupiter.api.Assertions.*;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.core.UriInfo;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import response_generation.boundaries.ResponseBodyUtility;
import response_generation.generators.ApplicationResponseBodyGenerator;
import response_generation.generators.ErrorMessagingResponseBodyGenerator;

class ApplicationResponseBodyGeneratorTest {
	private final Status EXPECTED_STATUS = Status.BAD_REQUEST;
	private ApplicationResponseBodyGenerator appResponseBodyGenerator;
	
	@BeforeEach
	void init() {
		appResponseBodyGenerator = new ApplicationResponseBodyGenerator(EXPECTED_STATUS);
	}

	@Test
	void test_publicConstructor_returnsTrueForMatchingStatus() {
		Response response = appResponseBodyGenerator.build();
		assertEquals(EXPECTED_STATUS.getStatusCode(),response.getStatus());
	}
	
	@Test
	void test_addResponseBody_returnsTrueForMatchingExpectedResponseBody() {
		Gson gson = new Gson();
		UriInfo URI_INFO = Mockito.mock(UriInfo.class);
		JsonObject responseBody = new JsonObject();
		String ERR_RESPONSE_DETAIL_MSG = "TESTING: Error message";
		String expectedResponse;
	
		responseBody.add(ResponseBodyUtility.ERR_RESPONSE_TYPE_KEY, gson.toJsonTree(ResponseBodyUtility.ERR_RESPONSE_TYPE_MSG));
		responseBody.add(ResponseBodyUtility.ERR_RESPONSE_TITLE_KEY, gson.toJsonTree(ResponseBodyUtility.ERR_RESPONSE_TITLE_MSG));
		responseBody.add(ResponseBodyUtility.ERR_RESPONSE_DETAIL_KEY, gson.toJsonTree(ERR_RESPONSE_DETAIL_MSG));
		responseBody.add(ResponseBodyUtility.ERR_RESPONSE_STATUS_KEY, gson.toJsonTree(EXPECTED_STATUS.getStatusCode()));
		responseBody.add(ResponseBodyUtility.ERR_RESPONSE_INSTANCE_KEY, gson.toJsonTree("/" + URI_INFO.getPath()));
		expectedResponse = responseBody.toString();
		
		appResponseBodyGenerator.addResponseBody(new ErrorMessagingResponseBodyGenerator(
				URI_INFO,
				ERR_RESPONSE_DETAIL_MSG,
				EXPECTED_STATUS.getStatusCode()));
		Response response = appResponseBodyGenerator.build();
		
		assertEquals(expectedResponse, response.getEntity());
		
	}

}
