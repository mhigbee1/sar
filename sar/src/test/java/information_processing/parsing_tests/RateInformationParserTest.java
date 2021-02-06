package information_processing.parsing_tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonSyntaxException;

import information_processing.boundaries.InformationIDsParser;
import information_processing.parsers.RateInformationParser;
import response_generation.boundaries.ResponseBodyUtility;

class RateInformationParserTest {
	
	private Gson gson = new Gson();
	private JsonObject requestBody;
	private InformationIDsParser rateInformationParser;
	private static String EMPTY_JSON = new JsonObject().toString();
	private static String MALFORMED_JSON = "{'key1': ':\\\\\\\\\\','key2':'value'}";
	private static final int RID = 12;
	private static final int SENDER_ID = 34;
	private static final int RATING = 3;
	private static final String COMMENT = "Good driver";
	
	@BeforeEach
	void init() {
		rateInformationParser = new RateInformationParser();
		requestBody = new JsonObject();
	}
	
	@Test
	void test_parse_passedEMPTY_JSON__throwsNullPointerException() {
		assertThrows(NullPointerException.class,()->{
			rateInformationParser.parse(EMPTY_JSON);
		});
	}
	
	@Test
	void test_parse_passedMALFORMED_JSON__throwsJsonSyntaxException() {
		assertThrows(JsonSyntaxException.class, ()->{
			rateInformationParser.parse(MALFORMED_JSON);
		});
	}

	@Test
	void test_parse_passedValidJsonRequestBody__throwsNoExceptions() {
		requestBody.add(ResponseBodyUtility.RIDE_ID_KEY, gson.toJsonTree(RID));
		requestBody.add(ResponseBodyUtility.SENT_BY_ID_KEY, gson.toJsonTree(SENDER_ID));
		requestBody.add(ResponseBodyUtility.RATING_KEY, gson.toJsonTree(RATING));
		requestBody.add(ResponseBodyUtility.COMMENT_KEY, gson.toJsonTree(COMMENT));
		
		assertDoesNotThrow(()->{
			rateInformationParser.parse(requestBody.toString());
		});
	}

}
