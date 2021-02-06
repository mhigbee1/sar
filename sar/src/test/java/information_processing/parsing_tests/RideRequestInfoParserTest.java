package information_processing.parsing_tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.google.gson.Gson;
import com.google.gson.JsonNull;
import com.google.gson.JsonObject;
import com.google.gson.JsonSyntaxException;

import information_processing.parsers.RideRequestInfoParser;
import response_generation.boundaries.ResponseBodyUtility;

class RideRequestInfoParserTest {
	
	private Gson gson = new Gson();
	private JsonObject requestBody;
	private RideRequestInfoParser rideRequestInfoParser;
	private static String EMPTY_JSON = new JsonObject().toString();
	private static String MALFORMED_JSON = "{'key1': ':\\\\\\\\\\','key2':'value'}";
	
	private static final int AID = 123;
	private static final int PASSENGERS = 3;
	private JsonNull NULL = JsonNull.INSTANCE;
	
	
	@BeforeEach
	void init() {
		rideRequestInfoParser = new RideRequestInfoParser();
		requestBody = new JsonObject();
	}

	@Test
	void test_parse_passedEMPTY_JSON__throwsNullPointerException() {
		assertThrows(NullPointerException.class,()->{
			rideRequestInfoParser.parse(EMPTY_JSON);
		});
	}
	
	@Test
	void test_parse_passedMALFORMED_JSON__throwsJsonSyntaxException() {
		assertThrows(JsonSyntaxException.class, ()->{
			rideRequestInfoParser.parse(MALFORMED_JSON);
		});
	}
	
	@Test
	void test_parse_passedValidJsonRideRequestBody__throwsNoExceptions() {
		requestBody.add(ResponseBodyUtility.ACCOUNT_ID_KEY, gson.toJsonTree(AID));
		requestBody.add(ResponseBodyUtility.PASSENGERS_KEY, gson.toJsonTree(PASSENGERS));
		requestBody.add(ResponseBodyUtility.RIDE_CONFIRMATION_KEY, NULL);
		requestBody.add(ResponseBodyUtility.PICKUP_CONFIRMATION_KEY, NULL);
		
		assertDoesNotThrow(()->{
			rideRequestInfoParser.parse(requestBody.toString());
		});
	}
	
	@Test
	void test_parse_RideConfirmationSetToTrue__throwsNoExceptions() {
		requestBody.add(ResponseBodyUtility.ACCOUNT_ID_KEY, gson.toJsonTree(AID));
		requestBody.add(ResponseBodyUtility.PASSENGERS_KEY, gson.toJsonTree(PASSENGERS));
		requestBody.add(ResponseBodyUtility.RIDE_CONFIRMATION_KEY, gson.toJsonTree(true));
		requestBody.add(ResponseBodyUtility.PICKUP_CONFIRMATION_KEY, NULL);
		
		assertDoesNotThrow(()->{
			rideRequestInfoParser.parse(requestBody.toString());
		});
	}
	
	@Test
	void test_parse_RideConfirmationSetToFalse__throwsNoExceptions() {
		requestBody.add(ResponseBodyUtility.ACCOUNT_ID_KEY, gson.toJsonTree(AID));
		requestBody.add(ResponseBodyUtility.PASSENGERS_KEY, gson.toJsonTree(PASSENGERS));
		requestBody.add(ResponseBodyUtility.RIDE_CONFIRMATION_KEY, gson.toJsonTree(false));
		requestBody.add(ResponseBodyUtility.PICKUP_CONFIRMATION_KEY, NULL);
		
		assertDoesNotThrow(()->{
			rideRequestInfoParser.parse(requestBody.toString());
		});
	}
	
	@Test
	void test_parse_PickUpConfirmationSetToTrue__throwsNoExceptions() {
		requestBody.add(ResponseBodyUtility.ACCOUNT_ID_KEY, gson.toJsonTree(AID));
		requestBody.add(ResponseBodyUtility.PASSENGERS_KEY, gson.toJsonTree(PASSENGERS));
		requestBody.add(ResponseBodyUtility.RIDE_CONFIRMATION_KEY, NULL);
		requestBody.add(ResponseBodyUtility.PICKUP_CONFIRMATION_KEY, gson.toJsonTree(true));
		
		assertDoesNotThrow(()->{
			rideRequestInfoParser.parse(requestBody.toString());
		});
	}

}
