package information_processing.info_requests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import information_processing.entities.UserMessagingInfo;

class UserMessagingInfoTest {
	private UserMessagingInfo userMsgInfo;
	private static final Integer AID = 123;
	private static final String MESSAGE_TEXT = "Call when you arrive";
	
	@BeforeEach
	void init(){
		userMsgInfo = new UserMessagingInfo(AID, MESSAGE_TEXT);
	}

	@Test
	void test_getAid_returnsAID() {
		assertEquals(AID, userMsgInfo.getAid());
	}
	
	@Test
	void test_getMsgText_returnMESSAGE_TEXT() {
		assertEquals(MESSAGE_TEXT, userMsgInfo.getMsgText());
	}
	
	@Test
	void test_isEmptyRequest_returnsFalse() {
		assertFalse(userMsgInfo.isEmptyRequest());
	}

}
