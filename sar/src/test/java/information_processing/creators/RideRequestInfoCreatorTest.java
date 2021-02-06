package information_processing.creators;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.google.gson.Gson;
import com.google.gson.JsonNull;
import com.google.gson.JsonObject;

import information_processing.boundaries.InformationIDsCreator;
import response_generation.boundaries.ResponseBodyUtility;

class RideRequestInfoCreatorTest {
	
	private Gson gson = new Gson();
	private InformationIDsCreator rideRequestInfoCreator;
	private JsonObject requestBody;
	private static final int AID = 123;
	private static final int PASSENGER_COUNT = 3;
	private JsonNull NULL = JsonNull.INSTANCE;
	private static String EMPTY_JSON = new JsonObject().toString();
	private static String MALFORMED_JSON = "{'key1': ':\\\\\\\\\\','key2':'value'}";

	@BeforeEach
	void init() {
		rideRequestInfoCreator = new RideRequestInfoCreator();
		requestBody = new JsonObject();
	}
	
	@Test
	void test_create_passedEMPTY_JSON__isEmptyRequestReturnsTrue() {
		assertTrue(rideRequestInfoCreator.create(EMPTY_JSON).isEmptyRequest());
	}
	
	@Test
	void test_create_passedMALFORMED_JSON_isEmptyRequestReturnTrue() {
		assertTrue(rideRequestInfoCreator.create(MALFORMED_JSON).isEmptyRequest());
	}
	
	@Test
	void test_create_passedValidJsonRideRequestInfo_isEmptyRequestreturnsFalse() {
		requestBody.add(ResponseBodyUtility.ACCOUNT_ID_KEY, gson.toJsonTree(AID));
		requestBody.add(ResponseBodyUtility.PASSENGERS_KEY, gson.toJsonTree(PASSENGER_COUNT));
		requestBody.add(ResponseBodyUtility.RIDE_CONFIRMATION_KEY, JsonNull.INSTANCE);
		requestBody.add(ResponseBodyUtility.PICKUP_CONFIRMATION_KEY, JsonNull.INSTANCE);
		
		assertFalse(rideRequestInfoCreator.create(requestBody.toString()).isEmptyRequest());
	}
	
	@Test
	void test_create_passedNullAID_isEmptyRequestreturnsTrue() {
		requestBody.add(ResponseBodyUtility.ACCOUNT_ID_KEY, NULL);
		requestBody.add(ResponseBodyUtility.PASSENGERS_KEY, gson.toJsonTree(PASSENGER_COUNT));
		requestBody.add(ResponseBodyUtility.RIDE_CONFIRMATION_KEY, JsonNull.INSTANCE);
		requestBody.add(ResponseBodyUtility.PICKUP_CONFIRMATION_KEY, JsonNull.INSTANCE);
		
		assertTrue(rideRequestInfoCreator.create(requestBody.toString()).isEmptyRequest());
	}
	
	@Test
	void test_create_passedNullPassengerCount_isEmptyRequestreturnsTrue() {
		requestBody.add(ResponseBodyUtility.ACCOUNT_ID_KEY, gson.toJsonTree(AID));
		requestBody.add(ResponseBodyUtility.PASSENGERS_KEY, NULL);
		requestBody.add(ResponseBodyUtility.RIDE_CONFIRMATION_KEY, JsonNull.INSTANCE);
		requestBody.add(ResponseBodyUtility.PICKUP_CONFIRMATION_KEY, JsonNull.INSTANCE);
		
		assertTrue(rideRequestInfoCreator.create(requestBody.toString()).isEmptyRequest());
	}
	
	@Test
	void test_create_passedZEROPassengerCount_isEmptyRequestreturnsTrue() {
		requestBody.add(ResponseBodyUtility.ACCOUNT_ID_KEY, gson.toJsonTree(AID));
		requestBody.add(ResponseBodyUtility.PASSENGERS_KEY, gson.toJsonTree(0));
		requestBody.add(ResponseBodyUtility.RIDE_CONFIRMATION_KEY, JsonNull.INSTANCE);
		requestBody.add(ResponseBodyUtility.PICKUP_CONFIRMATION_KEY, JsonNull.INSTANCE);
		
		assertTrue(rideRequestInfoCreator.create(requestBody.toString()).isEmptyRequest());
	}
	
	@Test
	void test_create_passedTrueRideConfirmation_isEmptyRequestreturnsTrue() {
		requestBody.add(ResponseBodyUtility.ACCOUNT_ID_KEY, gson.toJsonTree(AID));
		requestBody.add(ResponseBodyUtility.PASSENGERS_KEY, gson.toJsonTree(0));
		requestBody.add(ResponseBodyUtility.RIDE_CONFIRMATION_KEY, gson.toJsonTree(true));
		requestBody.add(ResponseBodyUtility.PICKUP_CONFIRMATION_KEY, JsonNull.INSTANCE);
		
		assertTrue(rideRequestInfoCreator.create(requestBody.toString()).isEmptyRequest());
	}

}
