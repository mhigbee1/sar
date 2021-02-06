package information_processing.parsing_tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.google.gson.Gson;
import com.google.gson.JsonNull;
import com.google.gson.JsonObject;
import com.google.gson.JsonSyntaxException;

import information_processing.boundaries.InformationIDsParser;
import information_processing.parsers.RideInformationParser;
import response_generation.boundaries.ResponseBodyUtility;

class RideInformationParserTest {
	
	private Gson gson = new Gson();
	private InformationIDsParser rideInformationParser;
	private JsonObject requestBody;
	private JsonObject locationInfo;
	private JsonObject dateTimeInfo;
	private JsonObject vehicleInfo;
	private JsonNull NULL = JsonNull.INSTANCE;
	private static String EMPTY_JSON = new JsonObject().toString();
	private static String MALFORMED_JSON = "{'key1': ':\\\\\\\\\\','key2':'value'}";
	
	private static final int AID = 123;
	private static final String ORIGIN_CITY = "Chicago";
	private static final String ORIGIN_ZIP = "60618";
	private static final String DESTINATION_CITY = "Phoenix";
	private static final String DESTINATION_ZIP = "85248";
	private static final String DATE = "23-Dec-2020";
	private static final String TIME = "8:30";
	private static final String VEHICLE_MAKE = "Honda";
	private static final String VEHICLE_MODEL = "Civic";
	private static final String VEHICLE_COLOR = "Black";
	private static final String VEHICLE_PLATE_NUM = "E13084";
	private static final String VEHICLE_PLATE_STATE = "IL";
	private static final Integer VEHICLE_CAPACITY = 3;
	private static final String VEHICLE_RULES = "Small bags only";
	private static final Double RATE_PER_PASSENGER = 25.00;

	@BeforeEach
	void init() {
		rideInformationParser = new RideInformationParser();
		requestBody = new JsonObject();
		locationInfo = new JsonObject();
		dateTimeInfo = new JsonObject();
		vehicleInfo = new JsonObject();
	}

	@Test
	void test_parse_passedEMPTY_JSON__throwsNullPointerException() {
		assertThrows(NullPointerException.class,()->{
			rideInformationParser.parse(EMPTY_JSON);
		});
	}
	
	@Test
	void test_parse_passedMALFORMED_JSON__throwsJsonSyntaxException() {
		assertThrows(JsonSyntaxException.class, ()->{
			rideInformationParser.parse(MALFORMED_JSON);
		});
	}
	
	@Test
	void test_parse_passedValidJsonResponseBody__throwsNoException() {
		
		requestBody.add(ResponseBodyUtility.ACCOUNT_ID_KEY, gson.toJsonTree(AID));
		
		locationInfo.add(ResponseBodyUtility.ORIGIN_CITY_KEY, gson.toJsonTree(ORIGIN_CITY));
		locationInfo.add(ResponseBodyUtility.ORIGIN_ZIP_KEY, gson.toJsonTree(ORIGIN_ZIP));
		locationInfo.add(ResponseBodyUtility.DESTINATION_CITY_KEY, gson.toJsonTree(DESTINATION_CITY));
		locationInfo.add(ResponseBodyUtility.DESTINATION_ZIP_KEY, gson.toJsonTree(DESTINATION_ZIP));
		requestBody.add(ResponseBodyUtility.LOCATION_INFO_HEADER_KEY, locationInfo);
		
		dateTimeInfo.add(ResponseBodyUtility.RIDE_DATE_KEY, gson.toJsonTree(DATE));
		dateTimeInfo.add(ResponseBodyUtility.RIDE_TIME_KEY, gson.toJsonTree(TIME));
		requestBody.add(ResponseBodyUtility.DATE_TIME_HEADER_KEY, dateTimeInfo);
		
		vehicleInfo.add(ResponseBodyUtility.VEHICLE_MAKE_KEY, gson.toJsonTree(VEHICLE_MAKE));
		vehicleInfo.add(ResponseBodyUtility.VEHICLE_MODEL_KEY, gson.toJsonTree(VEHICLE_MODEL));
		vehicleInfo.add(ResponseBodyUtility.VEHICLE_COLOR_KEY, gson.toJsonTree(VEHICLE_COLOR));
		vehicleInfo.add(ResponseBodyUtility.VEHICLE_PLATE_NUM_KEY, gson.toJsonTree(VEHICLE_PLATE_NUM));
		vehicleInfo.add(ResponseBodyUtility.VEHICLE_PLATE_STATE_KEY, gson.toJsonTree(VEHICLE_PLATE_STATE));
		requestBody.add(ResponseBodyUtility.VEHICLE_INFO_HEADER_KEY, vehicleInfo);
		
		requestBody.add(ResponseBodyUtility.VEHICLE_CAPACITY_KEY, gson.toJsonTree(VEHICLE_CAPACITY));
		requestBody.add(ResponseBodyUtility.RATE_PER_PASSENGER_KEY, gson.toJsonTree(RATE_PER_PASSENGER));
		requestBody.add(ResponseBodyUtility.VEHICLE_RULES_KEY, gson.toJsonTree(VEHICLE_RULES));
		
		assertDoesNotThrow(()-> {
			rideInformationParser.parse(requestBody.toString());
		});
	}
	
	
	@Test
	void test_parse_passedNullForRatePerPassenger__throwsNoExceptions() {
		
		requestBody.add(ResponseBodyUtility.ACCOUNT_ID_KEY, gson.toJsonTree(AID));
		
		locationInfo.add(ResponseBodyUtility.ORIGIN_CITY_KEY, gson.toJsonTree(ORIGIN_CITY));
		locationInfo.add(ResponseBodyUtility.ORIGIN_ZIP_KEY, gson.toJsonTree(ORIGIN_ZIP));
		locationInfo.add(ResponseBodyUtility.DESTINATION_CITY_KEY, gson.toJsonTree(DESTINATION_CITY));
		locationInfo.add(ResponseBodyUtility.DESTINATION_ZIP_KEY, gson.toJsonTree(DESTINATION_ZIP));
		requestBody.add(ResponseBodyUtility.LOCATION_INFO_HEADER_KEY, locationInfo);
		
		dateTimeInfo.add(ResponseBodyUtility.RIDE_DATE_KEY, gson.toJsonTree(DATE));
		dateTimeInfo.add(ResponseBodyUtility.RIDE_TIME_KEY, gson.toJsonTree(TIME));
		requestBody.add(ResponseBodyUtility.DATE_TIME_HEADER_KEY, dateTimeInfo);
		
		vehicleInfo.add(ResponseBodyUtility.VEHICLE_MAKE_KEY, gson.toJsonTree(VEHICLE_MAKE));
		vehicleInfo.add(ResponseBodyUtility.VEHICLE_MODEL_KEY, gson.toJsonTree(VEHICLE_MODEL));
		vehicleInfo.add(ResponseBodyUtility.VEHICLE_COLOR_KEY, gson.toJsonTree(VEHICLE_COLOR));
		vehicleInfo.add(ResponseBodyUtility.VEHICLE_PLATE_NUM_KEY, gson.toJsonTree(VEHICLE_PLATE_NUM));
		vehicleInfo.add(ResponseBodyUtility.VEHICLE_PLATE_STATE_KEY, gson.toJsonTree(VEHICLE_PLATE_STATE));
		requestBody.add(ResponseBodyUtility.VEHICLE_INFO_HEADER_KEY, vehicleInfo);
		
		requestBody.add(ResponseBodyUtility.VEHICLE_CAPACITY_KEY, gson.toJsonTree(VEHICLE_CAPACITY));
		requestBody.add(ResponseBodyUtility.RATE_PER_PASSENGER_KEY, gson.toJsonTree(NULL));
		requestBody.add(ResponseBodyUtility.VEHICLE_RULES_KEY, gson.toJsonTree(VEHICLE_RULES));
		
		assertDoesNotThrow(()-> {
			rideInformationParser.parse(requestBody.toString());
		});
	}

}
