package information_processing.info_requests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import information_processing.entities.RideRequestInformation;

public class RideRequestInformationTest {
	private RideRequestInformation rideRequestInfo;
	private static final Integer AID = 123;
	private static final Integer PASSENGER_COUNT = 1;
	private Boolean RIDE_CONFIRM = null;
	private Boolean PICKUP_CONFIRM = null;
	
	@BeforeEach
	void init() {
		rideRequestInfo = new RideRequestInformation(AID, PASSENGER_COUNT, RIDE_CONFIRM, PICKUP_CONFIRM);
	}

	@Test
	void test_getAid_returnsAID() {
		assertEquals(AID, rideRequestInfo.getAid());
	}
	
	@Test
	void test_getPassengerCount_returnsPASSENGER_COUNT() {
		assertEquals(PASSENGER_COUNT, rideRequestInfo.getPassengerCount());
	}
	
	@Test
	void test_getRideConfirmation_returnsNULL() {
		assertNull(rideRequestInfo.getRideConfirmation());
	}
	
	@Test
	void test_setRideConfirmation_setToTRUE_returnsTRUE() {
		rideRequestInfo.setRideConfirmation(true);
		assertTrue(rideRequestInfo.getRideConfirmation());
	}
	
	@Test 
	void test_setRideConfirmation_setToFalse_returnsFALSE(){
		rideRequestInfo.setRideConfirmation(false);
		assertFalse(rideRequestInfo.getRideConfirmation());
	}
	
	@Test
	void test_getPickUpConfirmation_returnsNULL() {
		assertNull(rideRequestInfo.getPickUpConfirmation());
	}
	
	@Test
	void test_setPickUpConfirmation_returnsTRUE() {
		rideRequestInfo.setPickUpConfirmation();
		assertTrue(rideRequestInfo.getPickUpConfirmation());
	}
	
	@Test
	void test_isEmptyRequest_returnsFALSE() {
		assertFalse(rideRequestInfo.isEmptyRequest());
	}
	
	

}
