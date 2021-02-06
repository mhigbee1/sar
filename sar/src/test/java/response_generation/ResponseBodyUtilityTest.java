package response_generation;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import response_generation.boundaries.ResponseBodyUtility;

class ResponseBodyUtilityTest {

	@Test
	void test_invalidAccountErrMessage_returnsFormattedString() {
		int aid = 123;
		String formattedString = "Account identified by (" + aid + ") does not exist or is not active.";
		assertEquals(formattedString, ResponseBodyUtility.invalidAccountErrMessage(aid));
	}
	
	@Test
	void test_invalidAccountErrMessageRideConfirmation_returnsFormattedString() {
		int aid = 123;
		int rid = 33;
		String formattedString = "Account identified by (" + aid + ") didn't create the ride ("+ rid + ")";
		assertEquals(formattedString, ResponseBodyUtility.invalidAccountErrMessageRideConfirmation(rid, aid));
	}
	
	@Test
	void test_invalidAccountErrMessagePickUpConfirmation_returnsFormattedString() {
		int aid = 123;
		int rid = 33;
		String formattedString = "Account identified by (" + aid + ") has not requested to join this ride ("+ rid +")";
		assertEquals(formattedString, ResponseBodyUtility.invalidAccountErrMessagePickUpConfirmation(rid, aid));
	}
	
	@Test
	void test_nonExistingRideErrMessage_returnsFormattedString() {
		int rid = 33;
		String formattedString = "Ride ("+rid+") does not exist";
		assertEquals(formattedString, ResponseBodyUtility.nonExistingRideErrMessage(rid));
	}
	
	@Test
	void test_inactiveAccountErrMessageAddRide_returnsFormattedString() {
		int aid = 123;
		String formattedString = "Account identified by ("+aid+") is not active and may not create rides";
		assertEquals(formattedString, ResponseBodyUtility.inactiveAccountErrMessageAddRide(aid));
	}
	
	@Test
	void test_inactiveAccountErrMessageAddRideRequest_returnsFormattedString() {
		int aid = 123;
		String formattedString = "Account identified by ("+aid+") is not active and may not request to join ride";
		assertEquals(formattedString, ResponseBodyUtility.inactiveAccountErrMessageAddRideRequest(aid));
	}
	
	@Test
	void test_existingRequestErrMessageJoinRequest_returnsFormattedString() {
		int aid = 123;
		int rid = 33;
		String formattedString = "Account identified by("+aid+") has already sent a join request for ride ("+rid+")";
		assertEquals(formattedString, ResponseBodyUtility.existingRequestErrMessageJoinRequest(rid, aid));
	}
	
	@Test
	void test_invalidRatingErrMessageNoSuchRider_returnsFormattedString() {
		int aid = 123;
		int rid = 33;
		String formattedString = "Account identified by ("+aid+") must be creator of ride ("+rid+") or a rider to provide rating";
		assertEquals(formattedString, ResponseBodyUtility.invalidRatingErrMessageNoSuchRider(rid, aid));
	}
	
}
