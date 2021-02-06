package information_processing.creators;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.google.gson.Gson;
import com.google.gson.JsonNull;
import com.google.gson.JsonObject;

import information_processing.boundaries.InformationIDsCreator;
import response_generation.boundaries.ResponseBodyUtility;

class UpdateRideRequestInfoCreatorTest {
	
	
	private Gson gson = new Gson();
	private InformationIDsCreator updateRideRequestInfoCreator;
	private JsonObject updatedRideConfirmation;
	private JsonObject updatedPickUpConfirmation;
	private static final int AID = 123;
	private JsonNull NULL = JsonNull.INSTANCE;
	private static String EMPTY_JSON = new JsonObject().toString();
	private static String MALFORMED_JSON = "{'key1': ':\\\\\\\\\\','key2':'value'}";

	@BeforeEach
	void init() {
		updateRideRequestInfoCreator = new UpdateRideRequestInfoCreator();
	}
	
	@Test
	void test_create_passedEMPTY_JSON__isEmptyRequestReturnsTrue() {
		assertTrue(updateRideRequestInfoCreator.create(EMPTY_JSON).isEmptyRequest());
	}
	
	@Test
	void test_create_passedMALFORMED_JSON_isEmptyRequestReturnTrue() {
		assertTrue(updateRideRequestInfoCreator.create(MALFORMED_JSON).isEmptyRequest());
	}
	
	@Test
	void test_create_passedValidJsonRideRequestInfo_isEmptyRequestreturnsFalse() {
		updatedRideConfirmation = new JsonObject();
		updatedRideConfirmation.add(ResponseBodyUtility.ACCOUNT_ID_KEY, gson.toJsonTree(AID));
		updatedRideConfirmation.add(ResponseBodyUtility.RIDE_CONFIRMATION_KEY, gson.toJsonTree(true));
		
		updatedPickUpConfirmation = new JsonObject();
		updatedPickUpConfirmation.add(ResponseBodyUtility.ACCOUNT_ID_KEY, gson.toJsonTree(AID));
		updatedPickUpConfirmation.add(ResponseBodyUtility.PICKUP_CONFIRMATION_KEY, gson.toJsonTree(true));
		
		assertFalse(updateRideRequestInfoCreator.create(updatedRideConfirmation.toString()).isEmptyRequest());
		assertFalse(updateRideRequestInfoCreator.create(updatedPickUpConfirmation.toString()).isEmptyRequest());
	}
	
	@Test
	void test_create_passedNullAID_isEmptyRequestreturnsTrue() {
		updatedRideConfirmation = new JsonObject();
		updatedRideConfirmation.add(ResponseBodyUtility.ACCOUNT_ID_KEY, NULL);
		updatedRideConfirmation.add(ResponseBodyUtility.RIDE_CONFIRMATION_KEY, gson.toJsonTree(true));
		
		assertTrue(updateRideRequestInfoCreator.create(updatedRideConfirmation.toString()).isEmptyRequest());
	}
	
	@Test
	void test_create_passedNullRideConfirmation_isEmptyRequestreturnsTrue() {
		updatedRideConfirmation = new JsonObject();
		updatedRideConfirmation.add(ResponseBodyUtility.ACCOUNT_ID_KEY, gson.toJsonTree(AID));
		updatedRideConfirmation.add(ResponseBodyUtility.RIDE_CONFIRMATION_KEY, NULL);
		
		assertTrue(updateRideRequestInfoCreator.create(updatedRideConfirmation.toString()).isEmptyRequest());
	}
	
	
	@Test
	void test_create_passedNullPickUpConfirmation_isEmptyRequestreturnsTrue() {
		updatedPickUpConfirmation = new JsonObject();
		updatedPickUpConfirmation.add(ResponseBodyUtility.ACCOUNT_ID_KEY, gson.toJsonTree(AID));
		updatedPickUpConfirmation.add(ResponseBodyUtility.PICKUP_CONFIRMATION_KEY, NULL);
		
		assertTrue(updateRideRequestInfoCreator.create(updatedPickUpConfirmation.toString()).isEmptyRequest());
	}
	
	@Test
	void test_create_passedFalsePickUpConfirmation_isEmptyRequestreturnsTrue() {
		updatedPickUpConfirmation = new JsonObject();
		updatedPickUpConfirmation.add(ResponseBodyUtility.ACCOUNT_ID_KEY, gson.toJsonTree(AID));
		updatedPickUpConfirmation.add(ResponseBodyUtility.PICKUP_CONFIRMATION_KEY, gson.toJsonTree(false));
		
		assertTrue(updateRideRequestInfoCreator.create(updatedPickUpConfirmation.toString()).isEmptyRequest());
	}
	
	
	

}
