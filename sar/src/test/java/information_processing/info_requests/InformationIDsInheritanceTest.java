package information_processing.info_requests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import information_processing.boundaries.ConfirmationType;
import information_processing.boundaries.InformationIDs;
import information_processing.entities.EmptyRequestInfo;
import information_processing.entities.RateInformation;
import information_processing.entities.RideInformation;
import information_processing.entities.RideRequestInformation;
import information_processing.entities.UpdateRideRequestInfo;
import information_processing.entities.UserInformation;

class InformationIDsInheritanceTest {
	private static final Integer AID = 123;
	private static final String FIRST_NAME = "John";
	private static final String LAST_NAME = "Doe";
	private static final String PHONE_NUM = "555-555-5555";
	private static final String USER_PIC = "Temp Photo";
	private static final boolean INACTIVE = false;
	
	private static final String ORIGIN_CITY = "Chicago";
	private static final String ORIGIN_ZIP = "60618";
	private static final String DESTINATION_CITY = "Phoenix";
	private static final String DESTINATION_ZIP = "85248";
	private static final String DATE = "23-Dec-2020";
	private static final String TIME = "08:30";
	private static final String VEHICLE_MAKE = "Honda";
	private static final String VEHICLE_MODEL = "Civic";
	private static final String VEHICLE_COLOR = "Black";
	private static final String VEHICLE_PLATE_NUM = "E13084";
	private static final String VEHICLE_PLATE_STATE = "IL";
	private static final Integer VEHICLE_CAPACITY = 3;
	private static final String VEHICLE_RULES = "Small bags only";
	private static final Double RATE_PER_PASSENGER = 25.00;
	
	private static final Integer RID = 123;
	private static final Integer SENDER_ID = 345;
	private static final Integer RATING = 2;
	private static final String COMMENT = "Good ride partner";
	
	private static final Boolean REQUEST_STATUS = true;
	private static final ConfirmationType CONFIRM_TYPE = ConfirmationType.PICKUP_CONFIRMED;
	
	
	@Nested
	public class UserInformationInheritanceTest{
		private InformationIDs userInformation;
		
		@BeforeEach
		void init() {
			userInformation = new UserInformation(
					FIRST_NAME, 
					LAST_NAME, 
					PHONE_NUM, 
					USER_PIC, 
					INACTIVE);
		}
		
		@Test
		void test_getAid_returnsNull() {
			assertNull(userInformation.getAid());
		}
		
		@Test
		void test_getRid_returnsNull() {
			assertNull(userInformation.getRid());
		}
		
		@Test
		void test_isEmptyReqeust_returnFalse() {
			assertFalse(userInformation.isEmptyRequest());
		}
	}
	
	@Nested
	public class RateInformationInheritanceTest{
		private InformationIDs rateInformation;
		
		@BeforeEach
		void init() {
			rateInformation = new RateInformation(
					RID, 
					SENDER_ID, 
					RATING, 
					COMMENT);
		}
		
		@Test
		void test_getAid_returnsNull() {
			assertNull(rateInformation.getAid());
		}
		
		@Test
		void test_getRid_returnsNull() {
			assertNull(rateInformation.getRid());
		}
		
		@Test
		void test_isEmptyReqeust_returnFalse() {
			assertFalse(rateInformation.isEmptyRequest());
		}
	}
	
	@Nested
	public class RideInformationInheritanceTest{
		private InformationIDs rideInformation;
		
		@BeforeEach
		void init() {
			rideInformation = new RideInformation(
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
					RATE_PER_PASSENGER);
		}
		
		@Test
		void test_getAid_returnsNull() {
			assertNull(rideInformation.getAid());
		}
		
		@Test
		void test_getRid_returnsNull() {
			assertNull(rideInformation.getRid());
		}
		
		@Test
		void test_isEmptyReqeust_returnFalse() {
			assertFalse(rideInformation.isEmptyRequest());
		}
	}
	
	@Nested
	public class RideRequestInformationInheritanceTest{
		private InformationIDs rideRequestInformation;
		
		@BeforeEach
		void init() {
			rideRequestInformation = new RideRequestInformation(
					AID, null, null, null);
		}
	
		@Test
		void test_getAid_returnsTRUEforMatchedAid() {
			assertEquals(AID, rideRequestInformation.getAid());
		}
		
		@Test
		void test_getRid_returnsNull() {
			assertNull(rideRequestInformation.getRid());
		}
		
		@Test
		void test_isEmptyRequest_returnsFalse() {
			assertFalse(rideRequestInformation.isEmptyRequest());
		}
	}
	
	@Nested
	public class UpdateRideRequestInfoInheritanceTest{
		private InformationIDs updateRideRequestInfo;
		
		@BeforeEach
		void init() {
			updateRideRequestInfo = new UpdateRideRequestInfo(
					AID, REQUEST_STATUS, CONFIRM_TYPE);
		}
	
		@Test
		void test_getAid_returnsTRUEforMatchedAid() {
			assertEquals(AID, updateRideRequestInfo.getAid());
		}
		
		@Test
		void test_getRid_returnsNull() {
			assertNull(updateRideRequestInfo.getRid());
		}
		
		@Test
		void test_isEmptyRequest_returnsFalse() {
			assertFalse(updateRideRequestInfo.isEmptyRequest());
		}
	}
	
	
	@Nested
	public class EmptyRequestInfoInheritanceTest{
		private InformationIDs emptyRequestInfo;
		
		@BeforeEach
		void init() {
			emptyRequestInfo = new EmptyRequestInfo("TESTING: ERROR MSG");
		}
	
		@Test
		void test_getAid_returnsTRUEforMatchedAid() {
			assertEquals(AID, emptyRequestInfo.getAid());
		}
		
		@Test
		void test_getRid_returnsNull() {
			assertNull(emptyRequestInfo.getRid());
		}
		
		@Test
		void test_isEmptyRequest_returnsFalse() {
			assertFalse(emptyRequestInfo.isEmptyRequest());
		}
	}
	
	
	
	
	
	

	}
