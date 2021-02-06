package information_processing.parsing_tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonSyntaxException;

import information_processing.boundaries.InformationIDsParser;
import information_processing.parsers.UpdateRideRequestInfoParser;
import response_generation.boundaries.ResponseBodyUtility;


class UpdateRideRequestInfoParserTest {
	
	private Gson gson = new Gson();
	private InformationIDsParser updateRideRequestInfoParser;
	private JsonObject updatedRideConfirmation;
	private JsonObject updatedPickUpConfirmation;
	private static final int AID = 123;
	private static String EMPTY_JSON = new JsonObject().toString();
	private static String MALFORMED_JSON = "{'key1': ':\\\\\\\\\\','key2':'value'}";

	@BeforeEach
	void init() {
		updateRideRequestInfoParser = new UpdateRideRequestInfoParser();
	}
	
	@Test
	void test_parse_passedEMPTY_JSON__isEmptyRequestReturnsTrue() {
		assertThrows(NullPointerException.class,()->{
			updateRideRequestInfoParser.parse(EMPTY_JSON);
		});
	}
	
	@Test
	void test_parse_passedMALFORMED_JSON_isEmptyRequestReturnTrue() {
		assertThrows(JsonSyntaxException.class, ()->{
			updateRideRequestInfoParser.parse(MALFORMED_JSON);
		});
	}

	@Test
	void test_parse_passedValideJsonResponseBody__throwsNoExceptions() {
		updatedRideConfirmation = new JsonObject();
		updatedRideConfirmation.add(ResponseBodyUtility.ACCOUNT_ID_KEY, gson.toJsonTree(AID));
		updatedRideConfirmation.add(ResponseBodyUtility.RIDE_CONFIRMATION_KEY, gson.toJsonTree(true));
		
		updatedPickUpConfirmation = new JsonObject();
		updatedPickUpConfirmation.add(ResponseBodyUtility.ACCOUNT_ID_KEY, gson.toJsonTree(AID));
		updatedPickUpConfirmation.add(ResponseBodyUtility.PICKUP_CONFIRMATION_KEY, gson.toJsonTree(true));
		
		assertFalse(updateRideRequestInfoParser.parse(updatedRideConfirmation.toString()).isEmptyRequest());
		assertFalse(updateRideRequestInfoParser.parse(updatedPickUpConfirmation.toString()).isEmptyRequest());
	}

}
