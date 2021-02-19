package information_processing.creators;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.google.gson.Gson;
import com.google.gson.JsonNull;
import com.google.gson.JsonObject;

import information_processing.boundaries.InformationIDsCreator;
import response_generation.boundaries.ResponseBodyUtility;

class RideInformationCreatorTest {
	
	private Gson gson = new Gson();
	private InformationIDsCreator rideInformationCreator;
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
		rideInformationCreator = new RideInformationCreator();
		requestBody = new JsonObject();
		locationInfo = new JsonObject();
		dateTimeInfo = new JsonObject();
		vehicleInfo = new JsonObject();
	}
	
	@Test
	void test_create_passedEMPTY_JSON__isEmptyRequestReturnsTrue() {
		assertTrue(rideInformationCreator.create(EMPTY_JSON).isEmptyRequest());
	}
	
	@Test
	void test_create_passedMALFORMED_JSON_isEmptyRequestReturnTrue() {
		assertTrue(rideInformationCreator.create(MALFORMED_JSON).isEmptyRequest());
	}
	
	@Test
	void test_create_passedValidJsonRideRequestInfo_isEmptyRequestreturnsFalse() {
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
		
		assertFalse(rideInformationCreator.create(requestBody.toString()).isEmptyRequest());
	}
	
	
	@Test
	void test_create_passedNullAID_isEmptyRequestreturnsTrue() {
		requestBody.add(ResponseBodyUtility.ACCOUNT_ID_KEY, NULL);
		
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
		
		assertTrue(rideInformationCreator.create(requestBody.toString()).isEmptyRequest());
	}
	
	@Test
	void test_create_passedNullOriginCity_isEmptyRequestreturnsTrue() {
		requestBody.add(ResponseBodyUtility.ACCOUNT_ID_KEY, gson.toJsonTree(AID));
		
		locationInfo.add(ResponseBodyUtility.ORIGIN_CITY_KEY, NULL);
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
		
		assertTrue(rideInformationCreator.create(requestBody.toString()).isEmptyRequest());
	}
	
	@Test
	void test_create_passedNullDestinationCity_isEmptyRequestreturnsTrue() {
		requestBody.add(ResponseBodyUtility.ACCOUNT_ID_KEY, gson.toJsonTree(AID));
		
		locationInfo.add(ResponseBodyUtility.ORIGIN_CITY_KEY, gson.toJsonTree(ORIGIN_CITY));
		locationInfo.add(ResponseBodyUtility.ORIGIN_ZIP_KEY, gson.toJsonTree(ORIGIN_ZIP));
		locationInfo.add(ResponseBodyUtility.DESTINATION_CITY_KEY, NULL);
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
		
		assertTrue(rideInformationCreator.create(requestBody.toString()).isEmptyRequest());
	}
	
	
	@Test
	void test_create_passedNullRideDate_isEmptyRequestreturnsTrue() {
		requestBody.add(ResponseBodyUtility.ACCOUNT_ID_KEY, gson.toJsonTree(AID));
		
		locationInfo.add(ResponseBodyUtility.ORIGIN_CITY_KEY, gson.toJsonTree(ORIGIN_CITY));
		locationInfo.add(ResponseBodyUtility.ORIGIN_ZIP_KEY, gson.toJsonTree(ORIGIN_ZIP));
		locationInfo.add(ResponseBodyUtility.DESTINATION_CITY_KEY, gson.toJsonTree(DESTINATION_CITY));
		locationInfo.add(ResponseBodyUtility.DESTINATION_ZIP_KEY, gson.toJsonTree(DESTINATION_ZIP));
		requestBody.add(ResponseBodyUtility.LOCATION_INFO_HEADER_KEY, locationInfo);
		
		dateTimeInfo.add(ResponseBodyUtility.RIDE_DATE_KEY, NULL);
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
		
		assertTrue(rideInformationCreator.create(requestBody.toString()).isEmptyRequest());
	}
	
	
	@Test
	void test_create_passedNullRideTime_isEmptyRequestreturnsTrue() {
		requestBody.add(ResponseBodyUtility.ACCOUNT_ID_KEY, gson.toJsonTree(AID));
		
		locationInfo.add(ResponseBodyUtility.ORIGIN_CITY_KEY, gson.toJsonTree(ORIGIN_CITY));
		locationInfo.add(ResponseBodyUtility.ORIGIN_ZIP_KEY, gson.toJsonTree(ORIGIN_ZIP));
		locationInfo.add(ResponseBodyUtility.DESTINATION_CITY_KEY, gson.toJsonTree(DESTINATION_CITY));
		locationInfo.add(ResponseBodyUtility.DESTINATION_ZIP_KEY, gson.toJsonTree(DESTINATION_ZIP));
		requestBody.add(ResponseBodyUtility.LOCATION_INFO_HEADER_KEY, locationInfo);
		
		dateTimeInfo.add(ResponseBodyUtility.RIDE_DATE_KEY, gson.toJsonTree(DATE));
		dateTimeInfo.add(ResponseBodyUtility.RIDE_TIME_KEY, NULL);
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
		
		assertTrue(rideInformationCreator.create(requestBody.toString()).isEmptyRequest());
	}
	
	
	@Test
	void test_create_passedNullVehicleMake_isEmptyRequestreturnsTrue() {
		requestBody.add(ResponseBodyUtility.ACCOUNT_ID_KEY, gson.toJsonTree(AID));
		
		locationInfo.add(ResponseBodyUtility.ORIGIN_CITY_KEY, gson.toJsonTree(ORIGIN_CITY));
		locationInfo.add(ResponseBodyUtility.ORIGIN_ZIP_KEY, gson.toJsonTree(ORIGIN_ZIP));
		locationInfo.add(ResponseBodyUtility.DESTINATION_CITY_KEY, gson.toJsonTree(DESTINATION_CITY));
		locationInfo.add(ResponseBodyUtility.DESTINATION_ZIP_KEY, gson.toJsonTree(DESTINATION_ZIP));
		requestBody.add(ResponseBodyUtility.LOCATION_INFO_HEADER_KEY, locationInfo);
		
		dateTimeInfo.add(ResponseBodyUtility.RIDE_DATE_KEY, gson.toJsonTree(DATE));
		dateTimeInfo.add(ResponseBodyUtility.RIDE_TIME_KEY, gson.toJsonTree(TIME));
		requestBody.add(ResponseBodyUtility.DATE_TIME_HEADER_KEY, dateTimeInfo);
		
		vehicleInfo.add(ResponseBodyUtility.VEHICLE_MAKE_KEY, NULL);
		vehicleInfo.add(ResponseBodyUtility.VEHICLE_MODEL_KEY, gson.toJsonTree(VEHICLE_MODEL));
		vehicleInfo.add(ResponseBodyUtility.VEHICLE_COLOR_KEY, gson.toJsonTree(VEHICLE_COLOR));
		vehicleInfo.add(ResponseBodyUtility.VEHICLE_PLATE_NUM_KEY, gson.toJsonTree(VEHICLE_PLATE_NUM));
		vehicleInfo.add(ResponseBodyUtility.VEHICLE_PLATE_STATE_KEY, gson.toJsonTree(VEHICLE_PLATE_STATE));
		requestBody.add(ResponseBodyUtility.VEHICLE_INFO_HEADER_KEY, vehicleInfo);
		
		requestBody.add(ResponseBodyUtility.VEHICLE_CAPACITY_KEY, gson.toJsonTree(VEHICLE_CAPACITY));
		requestBody.add(ResponseBodyUtility.RATE_PER_PASSENGER_KEY, gson.toJsonTree(RATE_PER_PASSENGER));
		requestBody.add(ResponseBodyUtility.VEHICLE_RULES_KEY, gson.toJsonTree(VEHICLE_RULES));
		
		assertTrue(rideInformationCreator.create(requestBody.toString()).isEmptyRequest());
	}
	
	
	@Test
	void test_create_passedNullVehicleModel_isEmptyRequestreturnsTrue() {
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
		vehicleInfo.add(ResponseBodyUtility.VEHICLE_MODEL_KEY, NULL);
		vehicleInfo.add(ResponseBodyUtility.VEHICLE_COLOR_KEY, gson.toJsonTree(VEHICLE_COLOR));
		vehicleInfo.add(ResponseBodyUtility.VEHICLE_PLATE_NUM_KEY, gson.toJsonTree(VEHICLE_PLATE_NUM));
		vehicleInfo.add(ResponseBodyUtility.VEHICLE_PLATE_STATE_KEY, gson.toJsonTree(VEHICLE_PLATE_STATE));
		requestBody.add(ResponseBodyUtility.VEHICLE_INFO_HEADER_KEY, vehicleInfo);
		
		requestBody.add(ResponseBodyUtility.VEHICLE_CAPACITY_KEY, gson.toJsonTree(VEHICLE_CAPACITY));
		requestBody.add(ResponseBodyUtility.RATE_PER_PASSENGER_KEY, gson.toJsonTree(RATE_PER_PASSENGER));
		requestBody.add(ResponseBodyUtility.VEHICLE_RULES_KEY, gson.toJsonTree(VEHICLE_RULES));
		
		assertTrue(rideInformationCreator.create(requestBody.toString()).isEmptyRequest());
	}
	
	@Test
	void test_create_passedNullVehicleColor_isEmptyRequestreturnsTrue() {
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
		vehicleInfo.add(ResponseBodyUtility.VEHICLE_COLOR_KEY, NULL);
		vehicleInfo.add(ResponseBodyUtility.VEHICLE_PLATE_NUM_KEY, gson.toJsonTree(VEHICLE_PLATE_NUM));
		vehicleInfo.add(ResponseBodyUtility.VEHICLE_PLATE_STATE_KEY, gson.toJsonTree(VEHICLE_PLATE_STATE));
		requestBody.add(ResponseBodyUtility.VEHICLE_INFO_HEADER_KEY, vehicleInfo);
		
		requestBody.add(ResponseBodyUtility.VEHICLE_CAPACITY_KEY, gson.toJsonTree(VEHICLE_CAPACITY));
		requestBody.add(ResponseBodyUtility.RATE_PER_PASSENGER_KEY, gson.toJsonTree(RATE_PER_PASSENGER));
		requestBody.add(ResponseBodyUtility.VEHICLE_RULES_KEY, gson.toJsonTree(VEHICLE_RULES));
		
		assertTrue(rideInformationCreator.create(requestBody.toString()).isEmptyRequest());
	}
	
	@Test
	void test_create_passedNullPlateNum_isEmptyRequestreturnsTrue() {
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
		vehicleInfo.add(ResponseBodyUtility.VEHICLE_PLATE_NUM_KEY, NULL);
		vehicleInfo.add(ResponseBodyUtility.VEHICLE_PLATE_STATE_KEY, gson.toJsonTree(VEHICLE_PLATE_STATE));
		requestBody.add(ResponseBodyUtility.VEHICLE_INFO_HEADER_KEY, vehicleInfo);
		
		requestBody.add(ResponseBodyUtility.VEHICLE_CAPACITY_KEY, gson.toJsonTree(VEHICLE_CAPACITY));
		requestBody.add(ResponseBodyUtility.RATE_PER_PASSENGER_KEY, gson.toJsonTree(RATE_PER_PASSENGER));
		requestBody.add(ResponseBodyUtility.VEHICLE_RULES_KEY, gson.toJsonTree(VEHICLE_RULES));
		
		assertTrue(rideInformationCreator.create(requestBody.toString()).isEmptyRequest());
	}
	
	
	@Test
	void test_create_passedNullPlateState_isEmptyRequestreturnsTrue() {
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
		vehicleInfo.add(ResponseBodyUtility.VEHICLE_PLATE_STATE_KEY, NULL);
		requestBody.add(ResponseBodyUtility.VEHICLE_INFO_HEADER_KEY, vehicleInfo);
		
		requestBody.add(ResponseBodyUtility.VEHICLE_CAPACITY_KEY, gson.toJsonTree(VEHICLE_CAPACITY));
		requestBody.add(ResponseBodyUtility.RATE_PER_PASSENGER_KEY, gson.toJsonTree(RATE_PER_PASSENGER));
		requestBody.add(ResponseBodyUtility.VEHICLE_RULES_KEY, gson.toJsonTree(VEHICLE_RULES));
		
		assertTrue(rideInformationCreator.create(requestBody.toString()).isEmptyRequest());
	}
	
	
	@Test
	void test_create_passedNullVehicleCapacity_isEmptyRequestreturnsTrue() {
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
		
		requestBody.add(ResponseBodyUtility.VEHICLE_CAPACITY_KEY, NULL);
		requestBody.add(ResponseBodyUtility.RATE_PER_PASSENGER_KEY, gson.toJsonTree(RATE_PER_PASSENGER));
		requestBody.add(ResponseBodyUtility.VEHICLE_RULES_KEY, gson.toJsonTree(VEHICLE_RULES));
		
		assertTrue(rideInformationCreator.create(requestBody.toString()).isEmptyRequest());
	}
	
	
	@Test
	void test_create_passedNullVehicleRules_isEmptyRequestreturnsTrue() {
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
		requestBody.add(ResponseBodyUtility.VEHICLE_RULES_KEY, NULL);
		
		assertTrue(rideInformationCreator.create(requestBody.toString()).isEmptyRequest());
	}
	
	
	@Test
	void test_create_passedValidJsonRideRequestInfo_NoOriginZip_isEmptyRequestreturnsFalse() {
		requestBody.add(ResponseBodyUtility.ACCOUNT_ID_KEY, gson.toJsonTree(AID));
		
		locationInfo.add(ResponseBodyUtility.ORIGIN_CITY_KEY, gson.toJsonTree(ORIGIN_CITY));
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
		
		assertFalse(rideInformationCreator.create(requestBody.toString()).isEmptyRequest());
	}
	
	@Test
	void test_create_passedValidJsonRideRequestInfo_NoDestinationZip_isEmptyRequestreturnsFalse() {
		requestBody.add(ResponseBodyUtility.ACCOUNT_ID_KEY, gson.toJsonTree(AID));
		
		locationInfo.add(ResponseBodyUtility.ORIGIN_CITY_KEY, gson.toJsonTree(ORIGIN_CITY));
		locationInfo.add(ResponseBodyUtility.ORIGIN_ZIP_KEY, gson.toJsonTree(ORIGIN_ZIP));
		locationInfo.add(ResponseBodyUtility.DESTINATION_CITY_KEY, gson.toJsonTree(DESTINATION_CITY));
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
		
		assertFalse(rideInformationCreator.create(requestBody.toString()).isEmptyRequest());
	}
	
	
	@Test
	void test_create_emptyStringForOriginCity_isEmptyRequestreturnsTrue() {
		requestBody.add(ResponseBodyUtility.ACCOUNT_ID_KEY, gson.toJsonTree(AID));
		
		locationInfo.add(ResponseBodyUtility.ORIGIN_CITY_KEY, gson.toJsonTree(""));
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
		
		assertTrue(rideInformationCreator.create(requestBody.toString()).isEmptyRequest());
	}
	
	@Test
	void test_create_emptyStringForDestinationCity_isEmptyRequestreturnsTrue() {
		requestBody.add(ResponseBodyUtility.ACCOUNT_ID_KEY, gson.toJsonTree(AID));
		
		locationInfo.add(ResponseBodyUtility.ORIGIN_CITY_KEY, gson.toJsonTree(ORIGIN_CITY));
		locationInfo.add(ResponseBodyUtility.ORIGIN_ZIP_KEY, gson.toJsonTree(ORIGIN_ZIP));
		locationInfo.add(ResponseBodyUtility.DESTINATION_CITY_KEY, gson.toJsonTree(""));
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
		
		assertTrue(rideInformationCreator.create(requestBody.toString()).isEmptyRequest());
	}
	
	@Test
	void test_create_passedEmptyStringForVehicleMake_isEmptyRequestreturnsTrue() {
		requestBody.add(ResponseBodyUtility.ACCOUNT_ID_KEY, gson.toJsonTree(AID));
		
		locationInfo.add(ResponseBodyUtility.ORIGIN_CITY_KEY, gson.toJsonTree(ORIGIN_CITY));
		locationInfo.add(ResponseBodyUtility.ORIGIN_ZIP_KEY, gson.toJsonTree(ORIGIN_ZIP));
		locationInfo.add(ResponseBodyUtility.DESTINATION_CITY_KEY, gson.toJsonTree(DESTINATION_CITY));
		locationInfo.add(ResponseBodyUtility.DESTINATION_ZIP_KEY, gson.toJsonTree(DESTINATION_ZIP));
		requestBody.add(ResponseBodyUtility.LOCATION_INFO_HEADER_KEY, locationInfo);
		
		dateTimeInfo.add(ResponseBodyUtility.RIDE_DATE_KEY, gson.toJsonTree(DATE));
		dateTimeInfo.add(ResponseBodyUtility.RIDE_TIME_KEY, gson.toJsonTree(TIME));
		requestBody.add(ResponseBodyUtility.DATE_TIME_HEADER_KEY, dateTimeInfo);
		
		vehicleInfo.add(ResponseBodyUtility.VEHICLE_MAKE_KEY, gson.toJsonTree(""));
		vehicleInfo.add(ResponseBodyUtility.VEHICLE_MODEL_KEY, gson.toJsonTree(VEHICLE_MODEL));
		vehicleInfo.add(ResponseBodyUtility.VEHICLE_COLOR_KEY, gson.toJsonTree(VEHICLE_COLOR));
		vehicleInfo.add(ResponseBodyUtility.VEHICLE_PLATE_NUM_KEY, gson.toJsonTree(VEHICLE_PLATE_NUM));
		vehicleInfo.add(ResponseBodyUtility.VEHICLE_PLATE_STATE_KEY, gson.toJsonTree(VEHICLE_PLATE_STATE));
		requestBody.add(ResponseBodyUtility.VEHICLE_INFO_HEADER_KEY, vehicleInfo);
		
		requestBody.add(ResponseBodyUtility.VEHICLE_CAPACITY_KEY, gson.toJsonTree(VEHICLE_CAPACITY));
		requestBody.add(ResponseBodyUtility.RATE_PER_PASSENGER_KEY, gson.toJsonTree(RATE_PER_PASSENGER));
		requestBody.add(ResponseBodyUtility.VEHICLE_RULES_KEY, gson.toJsonTree(VEHICLE_RULES));
		
		assertTrue(rideInformationCreator.create(requestBody.toString()).isEmptyRequest());
	}
	
	
	@Test
	void test_create_passedEmptyStringForVehicleModel_isEmptyRequestreturnsTrue() {
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
		vehicleInfo.add(ResponseBodyUtility.VEHICLE_MODEL_KEY, gson.toJsonTree(""));
		vehicleInfo.add(ResponseBodyUtility.VEHICLE_COLOR_KEY, gson.toJsonTree(VEHICLE_COLOR));
		vehicleInfo.add(ResponseBodyUtility.VEHICLE_PLATE_NUM_KEY, gson.toJsonTree(VEHICLE_PLATE_NUM));
		vehicleInfo.add(ResponseBodyUtility.VEHICLE_PLATE_STATE_KEY, gson.toJsonTree(VEHICLE_PLATE_STATE));
		requestBody.add(ResponseBodyUtility.VEHICLE_INFO_HEADER_KEY, vehicleInfo);
		
		requestBody.add(ResponseBodyUtility.VEHICLE_CAPACITY_KEY, gson.toJsonTree(VEHICLE_CAPACITY));
		requestBody.add(ResponseBodyUtility.RATE_PER_PASSENGER_KEY, gson.toJsonTree(RATE_PER_PASSENGER));
		requestBody.add(ResponseBodyUtility.VEHICLE_RULES_KEY, gson.toJsonTree(VEHICLE_RULES));
		
		assertTrue(rideInformationCreator.create(requestBody.toString()).isEmptyRequest());
	}
	
	@Test
	void test_create_passedEmptyStringForVehicleColor_isEmptyRequestreturnsTrue() {
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
		vehicleInfo.add(ResponseBodyUtility.VEHICLE_COLOR_KEY, gson.toJsonTree(""));
		vehicleInfo.add(ResponseBodyUtility.VEHICLE_PLATE_NUM_KEY, gson.toJsonTree(VEHICLE_PLATE_NUM));
		vehicleInfo.add(ResponseBodyUtility.VEHICLE_PLATE_STATE_KEY, gson.toJsonTree(VEHICLE_PLATE_STATE));
		requestBody.add(ResponseBodyUtility.VEHICLE_INFO_HEADER_KEY, vehicleInfo);
		
		requestBody.add(ResponseBodyUtility.VEHICLE_CAPACITY_KEY, gson.toJsonTree(VEHICLE_CAPACITY));
		requestBody.add(ResponseBodyUtility.RATE_PER_PASSENGER_KEY, gson.toJsonTree(RATE_PER_PASSENGER));
		requestBody.add(ResponseBodyUtility.VEHICLE_RULES_KEY, gson.toJsonTree(VEHICLE_RULES));
		
		assertTrue(rideInformationCreator.create(requestBody.toString()).isEmptyRequest());
	}
	
	@Test
	void test_create_passedEmptyStringForVehiclePlateNum_isEmptyRequestreturnsTrue() {
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
		vehicleInfo.add(ResponseBodyUtility.VEHICLE_PLATE_NUM_KEY, gson.toJsonTree(""));
		vehicleInfo.add(ResponseBodyUtility.VEHICLE_PLATE_STATE_KEY, gson.toJsonTree(VEHICLE_PLATE_STATE));
		requestBody.add(ResponseBodyUtility.VEHICLE_INFO_HEADER_KEY, vehicleInfo);
		
		requestBody.add(ResponseBodyUtility.VEHICLE_CAPACITY_KEY, gson.toJsonTree(VEHICLE_CAPACITY));
		requestBody.add(ResponseBodyUtility.RATE_PER_PASSENGER_KEY, gson.toJsonTree(RATE_PER_PASSENGER));
		requestBody.add(ResponseBodyUtility.VEHICLE_RULES_KEY, gson.toJsonTree(VEHICLE_RULES));
		
		assertTrue(rideInformationCreator.create(requestBody.toString()).isEmptyRequest());
	}
	
	
	@Test
	void test_create_passedInvalidDateFormat_isEmptyRequestreturnsTrue() {
		requestBody.add(ResponseBodyUtility.ACCOUNT_ID_KEY, gson.toJsonTree(AID));
		
		locationInfo.add(ResponseBodyUtility.ORIGIN_CITY_KEY, gson.toJsonTree(ORIGIN_CITY));
		locationInfo.add(ResponseBodyUtility.ORIGIN_ZIP_KEY, gson.toJsonTree(ORIGIN_ZIP));
		locationInfo.add(ResponseBodyUtility.DESTINATION_CITY_KEY, gson.toJsonTree(DESTINATION_CITY));
		locationInfo.add(ResponseBodyUtility.DESTINATION_ZIP_KEY, gson.toJsonTree(DESTINATION_ZIP));
		requestBody.add(ResponseBodyUtility.LOCATION_INFO_HEADER_KEY, locationInfo);
		
		dateTimeInfo.add(ResponseBodyUtility.RIDE_DATE_KEY, gson.toJsonTree("31-Sep-2020"));
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
		
		System.out.format("ride-String==> %s", requestBody.toString());
		System.out.format("isEmtpy Boolean==> %s", rideInformationCreator.create(requestBody.toString()).isEmptyRequest());
		
		assertTrue(rideInformationCreator.create(requestBody.toString()).isEmptyRequest());
	}
	
	
	@Test
	void test_create_passedInvalidTimeFormat_isEmptyRequestreturnsTrue() {
		requestBody.add(ResponseBodyUtility.ACCOUNT_ID_KEY, gson.toJsonTree(AID));
		
		locationInfo.add(ResponseBodyUtility.ORIGIN_CITY_KEY, gson.toJsonTree(ORIGIN_CITY));
		locationInfo.add(ResponseBodyUtility.ORIGIN_ZIP_KEY, gson.toJsonTree(ORIGIN_ZIP));
		locationInfo.add(ResponseBodyUtility.DESTINATION_CITY_KEY, gson.toJsonTree(DESTINATION_CITY));
		locationInfo.add(ResponseBodyUtility.DESTINATION_ZIP_KEY, gson.toJsonTree(DESTINATION_ZIP));
		requestBody.add(ResponseBodyUtility.LOCATION_INFO_HEADER_KEY, locationInfo);
		
		dateTimeInfo.add(ResponseBodyUtility.RIDE_DATE_KEY, gson.toJsonTree(DATE));
		dateTimeInfo.add(ResponseBodyUtility.RIDE_TIME_KEY, gson.toJsonTree("26:00"));
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
		
		assertTrue(rideInformationCreator.create(requestBody.toString()).isEmptyRequest());
	}
	
	
	@Test
	void test_create_passedZeroForVehicleCapacity_isEmptyRequestreturnsTrue() {
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
		
		requestBody.add(ResponseBodyUtility.VEHICLE_CAPACITY_KEY, gson.toJsonTree(0));
		requestBody.add(ResponseBodyUtility.RATE_PER_PASSENGER_KEY, gson.toJsonTree(RATE_PER_PASSENGER));
		requestBody.add(ResponseBodyUtility.VEHICLE_RULES_KEY, gson.toJsonTree(VEHICLE_RULES));
		
		assertTrue(rideInformationCreator.create(requestBody.toString()).isEmptyRequest());
	}
	
	
	@Test
	void test_create_passedNegativeRatePerPassenger_isEmptyRequestreturnsTrue() {
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
		requestBody.add(ResponseBodyUtility.RATE_PER_PASSENGER_KEY, gson.toJsonTree(-1));
		requestBody.add(ResponseBodyUtility.VEHICLE_RULES_KEY, gson.toJsonTree(VEHICLE_RULES));
		
		assertTrue(rideInformationCreator.create(requestBody.toString()).isEmptyRequest());
	}
	
	
	@Test
	void test_create_passedInvalidVehiclePlateState_isEmptyRequestreturnsTrue() {
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
		vehicleInfo.add(ResponseBodyUtility.VEHICLE_PLATE_STATE_KEY, gson.toJsonTree("XX"));
		requestBody.add(ResponseBodyUtility.VEHICLE_INFO_HEADER_KEY, vehicleInfo);
		
		requestBody.add(ResponseBodyUtility.VEHICLE_CAPACITY_KEY, gson.toJsonTree(VEHICLE_CAPACITY));
		requestBody.add(ResponseBodyUtility.RATE_PER_PASSENGER_KEY, gson.toJsonTree(RATE_PER_PASSENGER));
		requestBody.add(ResponseBodyUtility.VEHICLE_RULES_KEY, gson.toJsonTree(VEHICLE_RULES));
		
		assertTrue(rideInformationCreator.create(requestBody.toString()).isEmptyRequest());
	}

}
