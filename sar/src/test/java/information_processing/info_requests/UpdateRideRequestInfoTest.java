package information_processing.info_requests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import information_processing.boundaries.ConfirmationType;
import information_processing.entities.UpdateRideRequestInfo;

class UpdateRideRequestInfoTest {
	private UpdateRideRequestInfo updateRideRequestInfo;
	private static final Integer AID = 123;
	private static final Boolean REQUEST_STATUS = true;
	private static final ConfirmationType CONFIRM_TYPE = ConfirmationType.PICKUP_CONFIRMED;
	
	@BeforeEach
	void init() {
		updateRideRequestInfo = new UpdateRideRequestInfo(AID, REQUEST_STATUS, CONFIRM_TYPE);
	}

	@Test
	void test_getAid_returnsAID() {
		assertEquals(AID, updateRideRequestInfo.getAid());
	}
	
	@Test
	void test_getRequestStatus_returnsREQUEST_STATUS() {
		assertEquals(REQUEST_STATUS, updateRideRequestInfo.getRequestStatus());
	}
	
	@Test
	void test_isEmptyRequest_returnsFalse() {
		assertFalse(updateRideRequestInfo.isEmptyRequest());
	}
	
	@Test
	void test_getConfirmationType_returnsCONFIRM_TYPE() {
		assertEquals(CONFIRM_TYPE, updateRideRequestInfo.getConfirmationType());
	}

}
