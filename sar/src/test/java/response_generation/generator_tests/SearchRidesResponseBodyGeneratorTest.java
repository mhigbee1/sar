package response_generation.generator_tests;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import information_processing.entities.RideInformation;
import response_generation.boundaries.ResponseBodyUtility;
import response_generation.generators.SearchRidesResponseBodyGenerator;
import ride_processing.boundaries.RideOperationsUtility;
import ride_processing.interactors.Ride;

class SearchRidesResponseBodyGeneratorTest {
	
	private Gson gson;
	private JsonArray searchResponse;
	private JsonObject responseBody;
	private JsonObject locationInfo;
	private JsonObject dateTimeInfo;
	
	private List<RideOperationsUtility> ridesRecord;
	private Iterator<RideOperationsUtility> iterator;
	private RideOperationsUtility rideInstance;
	private String expectedResponse;
	
	private final int AID = 123;
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
		searchResponse = new JsonArray();
		responseBody = new JsonObject();
		locationInfo = new JsonObject();
		dateTimeInfo = new JsonObject();
			
		ridesRecord = new ArrayList<RideOperationsUtility>();
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
		
		ridesRecord.add(rideInstance);
		iterator = ridesRecord.iterator();	
	}

	
	@Test
	void test_generateResponseBodyForSearchRide_returnsTrueForMatchingResponse() {
		
		responseBody.add(ResponseBodyUtility.RIDE_ID_KEY, gson.toJsonTree(rideInstance.getRid()));
		
		locationInfo.add(ResponseBodyUtility.ORIGIN_CITY_KEY, gson.toJsonTree(rideInstance.getOriginCity()));
		locationInfo.add(ResponseBodyUtility.ORIGIN_ZIP_KEY, gson.toJsonTree(rideInstance.getOriginZip()));
		locationInfo.add(ResponseBodyUtility.DESTINATION_CITY_KEY, gson.toJsonTree(rideInstance.getDestinationCity()));
		locationInfo.add(ResponseBodyUtility.DESTINATION_ZIP_KEY, gson.toJsonTree(rideInstance.getDestinationZip()));
		responseBody.add(ResponseBodyUtility.LOCATION_INFO_HEADER_KEY, locationInfo);
		
		dateTimeInfo.add(ResponseBodyUtility.RIDE_DATE_KEY, gson.toJsonTree(rideInstance.getRideDate()));
		dateTimeInfo.add(ResponseBodyUtility.RIDE_TIME_KEY, gson.toJsonTree(rideInstance.getRideTime()));
		responseBody.add(ResponseBodyUtility.DATE_TIME_HEADER_KEY, dateTimeInfo);
		
		searchResponse.add(responseBody);
		expectedResponse = searchResponse.toString();
		
		ResponseBodyUtility searchRidesResponseBody = new SearchRidesResponseBodyGenerator(iterator);
		assertEquals(expectedResponse, searchRidesResponseBody.generateResponseBody());
		
		
	}

}
