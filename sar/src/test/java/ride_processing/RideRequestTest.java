package ride_processing;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import information_processing.entities.RideInformation;
import information_processing.entities.RideRequestInformation;
import ride_processing.interactors.Ride;

class RideRequestTest {
	
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
	
	private RideRequestInformation rideRequestInfo;
	private int jid;
	private final int rAID = 456;
	private static final int PASSENGER_COUNT = 3;
	private static Boolean PICKUP_CONFIRMATION = null;
	private static Boolean RIDE_CONFIRMATION = null;
	
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
		
		rideRequestInfo = new RideRequestInformation(rAID, PASSENGER_COUNT, RIDE_CONFIRMATION, PICKUP_CONFIRMATION);
		jid = testRide.addRideRequest(rideRequestInfo);
	}

	@Test
	void test_isExistingRideRequest_addRideRequestWithJid_returnsTrue() {
		assertTrue(testRide.isExistingRideRequest(jid));
	}
	
	@Test
	void test_isSubmittedRideRequest_usesRideRequestCreatorAid_returnsTrue() {
		assertTrue(testRide.isSubmittedRideRequest(rAID));
	}
	
	@Test
	void test_isRideRider_defaultReturnsFalse() {
		assertFalse(testRide.isRideRider(rAID));
	}
	
	@Test
	void test_setRideConfirmation_statusTrue_returnsTrueForRiderAid() {
		testRide.setRideConfirmation(jid, true);
		assertTrue(testRide.isRideRider(rAID));
	}
	
	@Test
	void test_setRideConfirmation_statusFalse_returnsFlaseforRiderAid() {
		testRide.setRideConfirmation(jid, false);
		assertFalse(testRide.isRideRider(rAID));
	}
	
	@Test
	void test_getPickUpConfirmation_defaultReturnNull() {
		assertNull(rideRequestInfo.getPickUpConfirmation());
	}
	
	@Test
	void test_setPickUpConfirmation_returnsTrue() {
		testRide.setPickUpConfirmation(jid);
		assertTrue(rideRequestInfo.getPickUpConfirmation());
	}

}
