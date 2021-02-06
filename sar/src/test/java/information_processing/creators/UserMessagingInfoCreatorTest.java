package information_processing.creators;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.google.gson.Gson;
import com.google.gson.JsonNull;
import com.google.gson.JsonObject;

import information_processing.boundaries.InformationIDsCreator;
import response_generation.boundaries.ResponseBodyUtility;

class UserMessagingInfoCreatorTest {
	private Gson gson = new Gson();
	private JsonObject requestBody;
	private InformationIDsCreator userMessagingInfoCreator;
	private static String EMPTY_JSON = new JsonObject().toString();
	private static String MALFORMED_JSON = "{'key1': ':\\\\\\\\\\','key2':'value'}";
	private static final int AID = 123;
	private static final String MSG_TEXT = "Pick me up on Pine & Walker";
	private JsonNull NULL = JsonNull.INSTANCE;
	
	@BeforeEach
	void init() {
		userMessagingInfoCreator = new UserMessagingInfoCreator();
		requestBody = new JsonObject();
	}
	
	@Test
	void test_create_passedEMPTY_JSON__isEmptyRequestReturnsTrue() {
		assertTrue(userMessagingInfoCreator.create(EMPTY_JSON).isEmptyRequest());
	}
	
	
	@Test
	void test_create_passedMALFORMED_JSON_isEmptyRequestReturnTrue() {
		assertTrue(userMessagingInfoCreator.create(MALFORMED_JSON).isEmptyRequest());
	}
	

	@Test
	void test_create_passedValidUserMessagingInfo__isEmptyRequestReturnsFalse() {
		requestBody.add(ResponseBodyUtility.ACCOUNT_ID_KEY, gson.toJsonTree(AID));
		requestBody.add(ResponseBodyUtility.MSG_TEXT_KEY, gson.toJsonTree(MSG_TEXT));
		
		assertFalse(userMessagingInfoCreator.create(requestBody.toString()).isEmptyRequest());
	}
	
	
	@Test
	void test_create_passedNullAID__isEmptyRequestReturnsTrue() {
		requestBody.add(ResponseBodyUtility.ACCOUNT_ID_KEY, NULL);
		requestBody.add(ResponseBodyUtility.MSG_TEXT_KEY, gson.toJsonTree(MSG_TEXT));
		
		assertTrue(userMessagingInfoCreator.create(requestBody.toString()).isEmptyRequest());
	}
	
	
	@Test
	void test_create_passedNullMessageText__isEmptyRequestReturnsTrue() {
		requestBody.add(ResponseBodyUtility.ACCOUNT_ID_KEY, gson.toJsonTree(AID));
		requestBody.add(ResponseBodyUtility.MSG_TEXT_KEY, NULL);
		
		assertTrue(userMessagingInfoCreator.create(requestBody.toString()).isEmptyRequest());
	}

}
