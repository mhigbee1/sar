package response_generation.generator_tests;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.Iterator;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import information_processing.entities.RideInformation;
import response_generation.boundaries.ResponseBodyUtility;
import response_generation.generators.RideReportDetailResponseBodyGenerator;
import ride_processing.boundaries.RideOperationsUtility;
import ride_processing.interactors.Ride;
import ride_report_processing.boundaries.RideReportProcess;
import ride_report_processing.interactors.PostedRideReportsProcess;
import ride_report_processing.interactors.TakenRideReportsProcess;

public class RideReportDetailResponseBodyGeneratorTest {
	
	Gson gson = new Gson();
	JsonObject responseBody = new JsonObject();
	JsonArray reportDetailResponse = new JsonArray();
	private Iterator<RideOperationsUtility> iterator;
	private RideOperationsUtility rideInstance;
	
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
	private String startDate = "27-Jan-2021";
	
	private String generateExpectedRideReportDetail(RideOperationsUtility mRideInstance, RideReportProcess rideReportProcess) {
		
		responseBody.add(ResponseBodyUtility.REPORT_ID_KEY, gson.toJsonTree(rideReportProcess.getPid()));
		responseBody.add(ResponseBodyUtility.NAME_KEY, gson.toJsonTree(rideReportProcess.getReportNameText()));
		responseBody.add(ResponseBodyUtility.REPORT_START_DATE_KEY, gson.toJsonTree(rideReportProcess.getStartDate()));
		responseBody.add(ResponseBodyUtility.REPORT_END_DATE_KEY, gson.toJsonTree(rideReportProcess.getEndDate()));
		responseBody.add(ResponseBodyUtility.RIDE_COUNT_KEY, gson.toJsonTree(1));
		
		JsonObject detailResponseBody = new JsonObject();
		detailResponseBody.add(ResponseBodyUtility.ORIGIN_ZIP_KEY, gson.toJsonTree(mRideInstance.getOriginZip()));
		detailResponseBody.add(ResponseBodyUtility.DESTINATION_ZIP_KEY, gson.toJsonTree(mRideInstance.getDestinationZip()));
		detailResponseBody.add(ResponseBodyUtility.COUNT_KEY, gson.toJsonTree(1));
		reportDetailResponse.add(detailResponseBody);
		
		responseBody.add(ResponseBodyUtility.DETAIL_KEY, reportDetailResponse);

		return responseBody.toString();
	}
	
	@BeforeEach
	void init() {
		List<RideOperationsUtility> ridesRecord = new ArrayList<RideOperationsUtility>();
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
	void test_generateRideReportDetailResponseBody_PostedRideReportsProcess_rideReportProcessType907_returnsTrueForEqualToExpectedResponse() {
		String endDate = "";
		RideReportProcess postedRideReportsProcess = new PostedRideReportsProcess(iterator, startDate, endDate);
		String expectedResponse = generateExpectedRideReportDetail(rideInstance, postedRideReportsProcess);
		ResponseBodyUtility reportDetailResponseBody = new RideReportDetailResponseBodyGenerator(postedRideReportsProcess);
		assertEquals(expectedResponse, reportDetailResponseBody.generateResponseBody());
	}
	
	@Test
	void test_generateRideReportDetailResponseBody_TakenRideReportsProcess_rideReportProcessType911_returnsTrueForEqualToExpectedResponse() {
		String endDate = "";
		RideReportProcess takenRideReportsProcess = new TakenRideReportsProcess(iterator, startDate, endDate);
		String expectedResponse = generateExpectedRideReportDetail(rideInstance, takenRideReportsProcess);
		ResponseBodyUtility reportDetailResponseBody = new RideReportDetailResponseBodyGenerator(takenRideReportsProcess);
		assertEquals(expectedResponse, reportDetailResponseBody.generateResponseBody());
	}
	

}
