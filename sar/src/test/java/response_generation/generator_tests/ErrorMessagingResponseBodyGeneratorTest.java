package response_generation.generator_tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import information_processing.boundaries.InformationIDs;
import information_processing.entities.EmptyRequestInfo;
import response_generation.boundaries.ResponseBodyUtility;
import response_generation.generators.ErrorMessagingResponseBodyGenerator;

import javax.ws.rs.core.UriInfo;

class ErrorMessagingResponseBodyGeneratorTest {
	
	Gson gson = new Gson();
	JsonObject responseBody;
	UriInfo uriInfo = Mockito.mock(UriInfo.class);
	private String URI_PATH = "/" + uriInfo.getPath();
	
	private String ERROR_DETAIL = "Testing: Error Message";
	private int STATUS_CODE = 404;
	private String expectedResponse;
	

	@Test
	void test_generateResponseBody_emptyRequestConstructor_returnsMatchForExpectedJsonReponseBody() {
		InformationIDs emptyRequestInfo = new EmptyRequestInfo(ERROR_DETAIL);
		responseBody = new JsonObject();
		
		responseBody.add(ResponseBodyUtility.ERR_RESPONSE_TYPE_KEY, gson.toJsonTree(ResponseBodyUtility.ERR_RESPONSE_TYPE_MSG));
		responseBody.add(ResponseBodyUtility.ERR_RESPONSE_TITLE_KEY, gson.toJsonTree(ResponseBodyUtility.ERR_RESPONSE_TITLE_MSG));
		responseBody.add(ResponseBodyUtility.ERR_RESPONSE_DETAIL_KEY, gson.toJsonTree(ERROR_DETAIL));
		responseBody.add(ResponseBodyUtility.ERR_RESPONSE_STATUS_KEY, gson.toJsonTree(STATUS_CODE));
		responseBody.add(ResponseBodyUtility.ERR_RESPONSE_INSTANCE_KEY, gson.toJsonTree(URI_PATH));
		expectedResponse = responseBody.toString();
		
		ErrorMessagingResponseBodyGenerator errMsgResponseBodyGenerator = new ErrorMessagingResponseBodyGenerator(uriInfo, emptyRequestInfo, STATUS_CODE);
		assertEquals(expectedResponse, errMsgResponseBodyGenerator.generateResponseBody());
	}
	
	@Test
	void test_generateResponseBody_standardConstructor_returnsMatchForExpectedJsonReponseBody() {
		responseBody = new JsonObject();
		
		responseBody.add(ResponseBodyUtility.ERR_RESPONSE_TYPE_KEY, gson.toJsonTree(ResponseBodyUtility.ERR_RESPONSE_TYPE_MSG));
		responseBody.add(ResponseBodyUtility.ERR_RESPONSE_TITLE_KEY, gson.toJsonTree(ResponseBodyUtility.ERR_RESPONSE_TITLE_MSG));
		responseBody.add(ResponseBodyUtility.ERR_RESPONSE_DETAIL_KEY, gson.toJsonTree(ERROR_DETAIL));
		responseBody.add(ResponseBodyUtility.ERR_RESPONSE_STATUS_KEY, gson.toJsonTree(STATUS_CODE));
		responseBody.add(ResponseBodyUtility.ERR_RESPONSE_INSTANCE_KEY, gson.toJsonTree(URI_PATH));
		expectedResponse = responseBody.toString();
		
		ErrorMessagingResponseBodyGenerator errMsgResponseBodyGenerator = new ErrorMessagingResponseBodyGenerator(uriInfo, ERROR_DETAIL, STATUS_CODE);
		assertEquals(expectedResponse, errMsgResponseBodyGenerator.generateResponseBody());
	}

}
