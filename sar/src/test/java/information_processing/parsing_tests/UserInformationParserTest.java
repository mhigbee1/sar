package information_processing.parsing_tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonSyntaxException;

import information_processing.parsers.UserInformationParser;
import response_generation.boundaries.ResponseBodyUtility;

class UserInformationParserTest {
	
	private Gson gson = new Gson();
	private JsonObject requestBody;
	private UserInformationParser userInformationParser;
	private static String EMPTY_JSON = new JsonObject().toString();
	private static String MALFORMED_JSON = "{'key1': ':\\\\\\\\\\','key2':'value'}";
	
	private static String FIRST_NAME = "Mark";
	private static String LAST_NAME = "Gomez";
	private static String PHONE_NUM = "555-555-5555";
	private static String USER_PIC = "http://example.com/user_pic.jpg";
	private static boolean ACTIVE_STATUS = false;
	
	
	@BeforeEach
	void init() {
		userInformationParser = new UserInformationParser();
		requestBody = new JsonObject();
	}

	@Test
	void test_parse_passedEMPTY_JSON__throwsNullPointerException() {
		assertThrows(NullPointerException.class,()->{
			userInformationParser.parse(EMPTY_JSON);
		});
	}
	
	@Test
	void test_parse_passedMALFORMED_JSON__throwsJsonSyntaxException() {
		assertThrows(JsonSyntaxException.class, ()->{
			userInformationParser.parse(MALFORMED_JSON);
		});
	}
	

	@Test
	void test_parse_passedValidJsonRequestBody__throwsNoExceptions() {
		requestBody.add(ResponseBodyUtility.FIRST_NAME_KEY, gson.toJsonTree(FIRST_NAME));
		requestBody.add(ResponseBodyUtility.LAST_NAME_KEY, gson.toJsonTree(LAST_NAME));
		requestBody.add(ResponseBodyUtility.PHONE_NUMBER_KEY, gson.toJsonTree(PHONE_NUM));
		requestBody.add(ResponseBodyUtility.USER_PIC_KEY, gson.toJsonTree(USER_PIC));
		requestBody.add(ResponseBodyUtility.ACTIVE_STATUS_KEY, gson.toJsonTree(ACTIVE_STATUS));
		
		assertDoesNotThrow(()->{
			userInformationParser.parse(requestBody.toString());
		});
	}
}
