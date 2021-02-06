package ride_processing;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import information_processing.entities.RideInformation;
import ride_processing.interactors.Ride;

class RideTest {
	
	private Ride testRide;
	private static final int AID = 123;
	private static final int RID = 45;
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
		testRide = new Ride(RID, new RideInformation(
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
	void test_getAid_returnsAID(){
		assertEquals(AID, testRide.getAid());
	}

	@Test
	void test_getRid_returnsRID() {
		assertEquals(RID, testRide.getRid());
	}
	
	@Test
	void test_getOriginCity_returnsORIGIN_CITY() {
		assertEquals(ORIGIN_CITY, testRide.getOriginCity());
	}
	
	@Test
	void test_getOriginZip_returnsORIGIN_ZIP() {
		assertEquals(ORIGIN_ZIP, testRide.getOriginZip());
	}
	
	@Test
	void test_getDestinationCity_returnsDESTINATION_CITY() {
		assertEquals(DESTINATION_CITY, testRide.getDestinationCity());
	}
	
	@Test
	void test_getDestinationZip_returnsDESTINATION_ZIP() {
		assertEquals(DESTINATION_ZIP, testRide.getDestinationZip());
	}
	
	@Test
	void test_getRideDate_returnsDATE() {
		assertEquals(DATE, testRide.getRideDate());
	}
	
	@Test
	void test_getRideTime_returnsTime() {
		assertEquals(TIME, testRide.getRideTime());
	}
	
	@Test
	void test_getVehicleMake_returnsVEHICLE_MAKE() {
		assertEquals(VEHICLE_MAKE, testRide.getVehicleMake());
	}
	
	@Test
	void test_getVehicleModel_returnsVEHICLE_MODEL() {
		assertEquals(VEHICLE_MODEL, testRide.getVehicleModel());
	}
	
	@Test
	void test_getVehicleColor_returnsVEHICLE_COLOR() {
		assertEquals(VEHICLE_COLOR, testRide.getVehicleColor());
	}
	
	@Test
	void test_getVehiclePlateNum_returnsVEHICLE_PLATE_NUM() {
		assertEquals(VEHICLE_PLATE_NUM, testRide.getVehiclePlateNum());
	}
	
	@Test
	void test_getVehiclePlateState_returnsVEHICLE_PLATE_STATE() {
		assertEquals(VEHICLE_PLATE_STATE, testRide.getVehiclePlateState());
	}
	
	@Test
	void test_getVehicleCapacity_returnsVEHICLE_CAPACITY() {
		assertEquals(VEHICLE_CAPACITY, testRide.getVehicleCapacity());
	}
	
	@Test
	void test_getVehicleRules_returnsVEHICLE_RULES() {
		assertEquals(VEHICLE_RULES, testRide.getVehicleRules());
	}
	
	@Test
	void test_getRatePerPassenger_returnsRATE_PER_PASSENGER() {
		assertEquals(RATE_PER_PASSENGER, testRide.getRatePerPassenger());
	}
	
	
	
	@Test
	void test_updateRide_returnsUpdatedRideInfo() {
		String newOriginCity = "Seattle";
		String newOriginZip = "98131";
		String newDestinationCity = "Chicago";
		String newDestinationZip = "60637";
		String newDate = "25-Jan-2021";
		String newTime = "13:30";
		RideInformation updatedRideInfo = new RideInformation(
				AID,
				newOriginCity,
				newOriginZip,
				newDestinationCity,
				newDestinationZip,
				newDate,
				newTime,
				VEHICLE_MAKE,
				VEHICLE_MODEL,
				VEHICLE_COLOR,
				VEHICLE_PLATE_NUM,
				VEHICLE_PLATE_STATE,
				VEHICLE_CAPACITY,
				VEHICLE_RULES,
				RATE_PER_PASSENGER);
		testRide.updateRide(updatedRideInfo);
		assertTrue(verifyRideInformationMatches(testRide, updatedRideInfo));
				
	}
	
	private boolean verifyRideInformationMatches(Ride rideKey, RideInformation rideInfoKey) {
		return(
				rideKey.getAid() == rideInfoKey.getAid() &&
				rideKey.getOriginCity().equals(rideInfoKey.getOriginCity()) &&
				rideKey.getOriginZip().equals(rideInfoKey.getOriginZip()) &&
				rideKey.getDestinationCity().equals(rideInfoKey.getDestinationCity()) &&
				rideKey.getDestinationZip().equals(rideInfoKey.getDestinationZip()) &&
				rideKey.getRideDate().equals(rideInfoKey.getRideDate()) &&
				rideKey.getRideTime().equals(rideInfoKey.getRideTime()) &&
				rideKey.getVehicleMake().equals(rideInfoKey.getVehicleMake()) &&
				rideKey.getVehicleModel().equals(rideInfoKey.getVehicleModel()) &&
				rideKey.getVehicleColor().equals(rideInfoKey.getVehicleColor()) &&
				rideKey.getVehiclePlateNum().equals(rideInfoKey.getVehiclePlateNum()) &&
				rideKey.getVehiclePlateState().equals(rideInfoKey.getVehiclePlateState()) &&
				rideKey.getVehicleRules().equals(rideInfoKey.getVehicleRules()) &&
				rideKey.getRatePerPassenger().equals(rideInfoKey.getRatePerPassenger())
				);
	}
	
	
	@Test
	void test_isRideDriver_keyIsDriverAid_returnsTrue() {
		assertTrue(testRide.isRideDriver(AID));
	}
	
	@Test
	void test_isRideDriver_keyIsUnmatchedAID_returnsFalse() {
		assertFalse(testRide.isRideDriver(999));
	}
	

	@Test
	void test_isSubmittedRideRequest_keyIsUnmatchedAid_returnsFalse() {
		assertFalse(testRide.isSubmittedRideRequest(999));
	}
	
	@Test
	void test_isExistingRideRequest_keyIsUnmatchedJid_returnsFalse() {
		assertFalse(testRide.isExistingRideRequest(999));
	}
	
	
	
	@Test
	void test_getMessages_returnsDefaultEmptyIterator() {
		assertFalse(testRide.getMessages().hasNext());
	}
	
	@Test
	void test_matchesFilter_originkeyIsOriginCity_returnsTrue() {
		assertTrue(testRide.matchesFilter(ORIGIN_CITY,"",""));
	}
	
	@Test
	void test_matchesFilter_originKeyIsOriginZip_returnsTrue() {
		assertTrue(testRide.matchesFilter(ORIGIN_ZIP, "", ""));
	}
	
	@Test
	void test_matchesFilter_originKeyIsUnMatchedString_returnsFalse() {
		assertFalse(testRide.matchesFilter("unmatched", "", ""));
	}
	@Test
	void test_matchesFilter_destinationKeyIsDestinationCity_returnsTrue() {
		assertTrue(testRide.matchesFilter("",DESTINATION_CITY,""));
	}
	
	@Test
	void test_matchesFilter_destinationKeyIsDestinationZip_returnsTrue() {
		assertTrue(testRide.matchesFilter("",DESTINATION_ZIP,""));
	}
	
	@Test
	void test_matchesFilter_destinationKeyIsUnMatchedString_returnsFalse() {
		assertFalse(testRide.matchesFilter("", "unmatched", ""));
	}
	
	@Test
	void test_matchesFilter_dateKeyIsDATE_returnsTrue() {
		assertTrue(testRide.matchesFilter("", "", DATE));
	}
	
	@Test
	void test_matchesFilter_dateKeyIsTIME_returnsTrue() {
		assertTrue(testRide.matchesFilter("", "", TIME));
	}
	
	@Test
	void test_matchesFilter_dateKeyIsUnMatchedString_returnsFalse() {
		assertFalse(testRide.matchesFilter("", "", "unmatched"));
	}

	

}
