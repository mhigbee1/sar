package response_generation.generator_tests;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Iterator;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonNull;
import com.google.gson.JsonObject;

import account_operations.boundaries.AccountOperationsUtility;
import account_operations.interactors.Account;
import information_processing.entities.RideInformation;
import information_processing.entities.UserInformation;
import rate_processing.entities.Rate;
import response_generation.boundaries.ResponseBodyUtility;
import response_generation.generators.ViewRideReportDetailResponseBodyGenerator;
import ride_processing.boundaries.RideOperationsUtility;
import ride_processing.interactors.Ride;

class ViewRideReportDetailResponseBodyGeneratorTest {
	private Gson gson;
	private JsonObject responseBody;
	private JsonObject locationInfo;
	private JsonObject dateTimeInfo;
	private JsonObject vehicleInfo;
	private String expectedResponse;
	
	private AccountOperationsUtility accountInstance;
	private final int AID = 123;
	private final String TEST_FIRST_NAME = "Mark";
	private final String TEST_LAST_NAME = "GOMEZ";
	private final String TEST_PHONE_NUM = "312-555-1234";
	private final String TEST_USER_PIC = "http://example.com/user_pic.jpg";
	private final boolean TEST_ACTIVE_STATUS = false;
	
	private RideOperationsUtility rideInstance;
	private final int RID = 345;
	private final String ORIGIN_CITY = "Chicago";
	private final String ORIGIN_ZIP = "60618";
	private final String DESTINATION_CITY = "Phoenix";
	private final String DESTINATION_ZIP = "85248";
	private final String DATE = "23-Dec-2020";
	private final String TIME = "8:30";
	private final String VEHICLE_MAKE = "Honda";
	private final String VEHICLE_MODEL = "Civic";
	private final String VEHICLE_COLOR = "Black";
	private final String VEHICLE_PLATE_NUM = "E13084";
	private final String VEHICLE_PLATE_STATE = "IL";
	private final Integer VEHICLE_CAPACITY = 3;
	private final String VEHICLE_RULES = "Small bags only";
	private final Double RATE_PER_PASSENGER = 25.00;
	
	
	@BeforeEach
	void init() {
		gson = new Gson();
		responseBody = new JsonObject();
		locationInfo = new JsonObject();
		dateTimeInfo = new JsonObject();
		vehicleInfo = new JsonObject();
		
		UserInformation userInfo = new UserInformation(
				TEST_FIRST_NAME,
				TEST_LAST_NAME,
				TEST_PHONE_NUM,
				TEST_USER_PIC,
				TEST_ACTIVE_STATUS);
		accountInstance = new Account(AID, userInfo);
		
		rideInstance = new Ride(RID, new RideInformation(
				AID,
				ORIGIN_CITY,
				ORIGIN_ZIP,
				DESTINATION_CITY,
				DESTINATION_ZIP,
				DATE,
				TIME,
				VEHICLE_MAKE,
				VEHICLE_MODEL,
				VEHICLE_COLOR,
				VEHICLE_PLATE_NUM,
				VEHICLE_PLATE_STATE,
				VEHICLE_CAPACITY,
				VEHICLE_RULES,
				RATE_PER_PASSENGER));	
	}

	@Test
	void test_generateResponseBody_returnsTrueForMatchingExpectedResponse() {
		ResponseBodyUtility viewRideReportDetailResponseBodyGenerator;
		Iterator<Rate> rateIterator = accountInstance.getDriverRatingIterator();
		
		responseBody.add(ResponseBodyUtility.RIDE_ID_KEY, gson.toJsonTree(rideInstance.getRid()));
		locationInfo.add(ResponseBodyUtility.ORIGIN_CITY_KEY, gson.toJsonTree(rideInstance.getOriginCity()));
		locationInfo.add(ResponseBodyUtility.ORIGIN_ZIP_KEY, gson.toJsonTree(rideInstance.getOriginZip()));
		locationInfo.add(ResponseBodyUtility.DESTINATION_CITY_KEY, gson.toJsonTree(rideInstance.getDestinationCity()));
		locationInfo.add(ResponseBodyUtility.DESTINATION_ZIP_KEY, gson.toJsonTree(rideInstance.getDestinationZip()));
		responseBody.add(ResponseBodyUtility.LOCATION_INFO_HEADER_KEY, locationInfo);
		
		dateTimeInfo.add(ResponseBodyUtility.RIDE_DATE_KEY, gson.toJsonTree(rideInstance.getRideDate()));
		dateTimeInfo.add(ResponseBodyUtility.RIDE_TIME_KEY, gson.toJsonTree(rideInstance.getRideTime()));
		responseBody.add(ResponseBodyUtility.DATE_TIME_HEADER_KEY, dateTimeInfo);
		
		vehicleInfo.add(ResponseBodyUtility.VEHICLE_MAKE_KEY, gson.toJsonTree(rideInstance.getVehicleMake()));
		vehicleInfo.add(ResponseBodyUtility.VEHICLE_MODEL_KEY,  gson.toJsonTree(rideInstance.getVehicleModel()));
		vehicleInfo.add(ResponseBodyUtility.VEHICLE_COLOR_KEY, gson.toJsonTree(rideInstance.getVehicleColor()));
		vehicleInfo.add(ResponseBodyUtility.VEHICLE_PLATE_STATE_KEY, gson.toJsonTree(rideInstance.getVehiclePlateState()));
		vehicleInfo.add(ResponseBodyUtility.VEHICLE_PLATE_NUM_KEY, gson.toJsonTree(rideInstance.getVehiclePlateNum()));
		responseBody.add(ResponseBodyUtility.VEHICLE_INFO_HEADER_KEY, vehicleInfo);
		responseBody.add(ResponseBodyUtility.DRIVER_KEY, gson.toJsonTree(accountInstance.getFirstName()));
		responseBody.add(ResponseBodyUtility.DRIVER_USER_PIC_KEY, gson.toJsonTree(accountInstance.getUserPic()));
		responseBody.add(ResponseBodyUtility.RIDE_COUNT_KEY, gson.toJsonTree(accountInstance.getDriverRideCounter()));
		responseBody.add(ResponseBodyUtility.RATING_COUNT_KEY, gson.toJsonTree(accountInstance.getDriverRatingsCounter()));
		responseBody.add(ResponseBodyUtility.AVG_RATING_KEY, gson.toJsonTree(
				accountInstance.getDriverAvgRating() == null ? JsonNull.INSTANCE : accountInstance.getDriverAvgRating()));
		responseBody.add(ResponseBodyUtility.DRIVER_COMMENTS_KEY, gatherDriverComments(gson, rateIterator));
		
		expectedResponse = responseBody.toString();
		viewRideReportDetailResponseBodyGenerator = new ViewRideReportDetailResponseBodyGenerator(accountInstance, rideInstance);
		
		
		assertEquals(expectedResponse, viewRideReportDetailResponseBodyGenerator.generateResponseBody());
	}
	
	
		
	private JsonArray gatherDriverComments(Gson gson, Iterator<Rate> rIterator) {
		JsonArray driverComments = new JsonArray();
		
		while(rIterator.hasNext()) {
			Rate rateInstance = rIterator.next();
			JsonObject detailBody = new JsonObject();
			detailBody.add(ResponseBodyUtility.RIDE_ID_KEY, gson.toJsonTree(rateInstance.getRid()));
			detailBody.add(ResponseBodyUtility.RATING_DATE_KEY, gson.toJsonTree(rateInstance.getDate()));
			detailBody.add(ResponseBodyUtility.RATING_KEY, gson.toJsonTree(rateInstance.getRating()));
			detailBody.add(ResponseBodyUtility.COMMENT_KEY, gson.toJsonTree(rateInstance.getComment()));
			driverComments.add(detailBody);
		}
		return driverComments;
	}

}
