package information_processing.info_requests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import information_processing.entities.RideInformation;

class RideInformationTest {
	private RideInformation rideInfo;
	private static final Integer AID = 123;
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
	
	
	@BeforeEach
	void init() {
		rideInfo = new RideInformation(
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
	void test_getAid_returnsAID() {
		assertEquals(AID, rideInfo.getAid());
	}
	
	@Test
	void test_getOriginCity_returnsORIGIN_CITY() {
		assertEquals(ORIGIN_CITY, rideInfo.getOriginCity());
	}
	
	@Test
	void test_getOriginZip_returnsORIGIN_ZIP() {
		assertEquals(ORIGIN_ZIP, rideInfo.getOriginZip());
	}
	
	@Test
	void test_getDestinationCity_returnsDESTINATION_CITY() {
		assertEquals(DESTINATION_CITY, rideInfo.getDestinationCity());
	}
	
	@Test
	void test_getDestinationZip_returnsDESTINATION_ZIP() {
		assertEquals(DESTINATION_ZIP, rideInfo.getDestinationZip());
	}
	
	@Test
	void test_getRideDate_returnsDATE() {
		assertEquals(DATE, rideInfo.getRideDate());
	}
	
	@Test
	void test_getRideTime_returnsTime() {
		assertEquals(TIME, rideInfo.getRideTime());
	}
	
	@Test
	void test_getVehicleMake_returnsVEHICLE_MAKE() {
		assertEquals(VEHICLE_MAKE, rideInfo.getVehicleMake());
	}
	
	@Test
	void test_getVehicleModel_returnsVEHICLE_MODEL() {
		assertEquals(VEHICLE_MODEL, rideInfo.getVehicleModel());
	}
	
	@Test
	void test_getVehicleColor_returnsVEHICLE_COLOR() {
		assertEquals(VEHICLE_COLOR, rideInfo.getVehicleColor());
	}
	
	@Test
	void test_getVehiclePlateNum_returnsVEHICLE_PLATE_NUM() {
		assertEquals(VEHICLE_PLATE_NUM, rideInfo.getVehiclePlateNum());
	}
	
	@Test
	void test_getVehiclePlateState_returnsVEHICLE_PLATE_STATE() {
		assertEquals(VEHICLE_PLATE_STATE, rideInfo.getVehiclePlateState());
	}
	
	@Test
	void test_getVehicleCapacity_returnsVEHICLE_CAPACITY() {
		assertEquals(VEHICLE_CAPACITY, rideInfo.getVehicleCapacity());
	}
	
	@Test
	void test_getVehicleRules_returnsVEHICLE_RULES() {
		assertEquals(VEHICLE_RULES, rideInfo.getVehicleRules());
	}
	
	@Test
	void test_getRatePerPassenger_returnsRATE_PER_PASSENGER() {
		assertEquals(RATE_PER_PASSENGER, rideInfo.getRatePerPassenger());
	}
	
	@Test
	void test_isEmptyRequest_returnsFalse() {
		assertFalse(rideInfo.isEmptyRequest());
	}
	
	@Test
	void test_matchingFilter_originKeyIsORIGIN_CITY_returnTrue() {
		assertTrue(rideInfo.matchesFilter(ORIGIN_CITY, "", ""));
	}
	
	@Test
	void test_matchingFilter_originKeyIsORIGIN_ZIP_returnTrue() {
		assertTrue(rideInfo.matchesFilter(ORIGIN_ZIP, "", ""));
	}
	
	@Test
	void test_matchingFilter_originKeyIsUnmatchedString_returnFalse() {
		assertFalse(rideInfo.matchesFilter("unmatched", "", ""));
	}
	
	@Test
	void test_matchingFilter_destinationKeyIsDESTINATION_CITY_returnTrue() {
		assertTrue(rideInfo.matchesFilter("",DESTINATION_CITY, ""));
	}
	
	@Test
	void test_matchingFilter_destinationKeyIsDESTINATION_ZIP_returnTrue() {
		assertTrue(rideInfo.matchesFilter("",DESTINATION_ZIP, ""));
	}
	
	@Test
	void test_matchingFilter_destinationKeyIsUnmatchedString_returnFalse() {
		assertFalse(rideInfo.matchesFilter("", "unmatched", ""));
	}
	
	@Test
	void test_matchingFilter_dateKeyIsDATE_returnTrue() {
		assertTrue(rideInfo.matchesFilter("","", DATE));
	}
	
	@Test
	void test_matchingFilter_dateKeyIsTIME_returnTrue() {
		assertTrue(rideInfo.matchesFilter("","", TIME));
	}
	
	@Test
	void test_matchingFilter_dateKeyIsUnmatchedString_returnFalse() {
		assertFalse(rideInfo.matchesFilter("", "", "unmatched"));
	}
	
	@Test
	void test_matchingFilter_KeyIsAllEmptyStrings_returnTrue() {
		assertTrue(rideInfo.matchesFilter("", "", ""));
	}

}
