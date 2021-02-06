package information_processing.parsing_tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonSyntaxException;

import information_processing.parsers.UserMessagingInfoParser;
import response_generation.boundaries.ResponseBodyUtility;

class UserMessagingInfoParserTest {
	
	private Gson gson = new Gson();
	private JsonObject requestBody;
	private UserMessagingInfoParser userMessagingInfoParser;
	private static String EMPTY_JSON = new JsonObject().toString();
	private static String MALFORMED_JSON = "{'key1': ':\\\\\\\\\\','key2':'value'}";
	
	private static final int AID = 123;
	private static final String MSG_TEXT = "Can't wait to drive again";
	
	@BeforeEach
	void init() {
		userMessagingInfoParser = new UserMessagingInfoParser();
		requestBody = new JsonObject();
	}
	
	
	@Test
	void test_parse_passedEMPTY_JSON__throwsNullPointerException() {
		assertThrows(NullPointerException.class,()->{
			userMessagingInfoParser.parse(EMPTY_JSON);
		});
	}
	
	@Test
	void test_parse_passedMALFORMED_JSON__throwsJsonSyntaxException() {
		assertThrows(JsonSyntaxException.class, ()->{
			userMessagingInfoParser.parse(MALFORMED_JSON);
		});
	}
	
	@Test
	void test_parse_passedValidJsonRequestBody__throwsNoExceptions() {
		requestBody.add(ResponseBodyUtility.ACCOUNT_ID_KEY, gson.toJsonTree(AID));
		requestBody.add(ResponseBodyUtility.MSG_TEXT_KEY, gson.toJsonTree(MSG_TEXT));
		
		assertDoesNotThrow(()->{
			userMessagingInfoParser.parse(requestBody.toString());
		});
	}


}
