package message_processing;

import static org.junit.jupiter.api.Assertions.*;

import java.time.format.DateTimeFormatter;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import information_processing.entities.UserMessagingInfo;
import message_processing.entities.Message;
import response_generation.boundaries.DateTimeFormatUtility;

class MessageTest {
	
	private Message testMessage;
	private static final int AID = 123;
	private static final int MID = 1;
	private static final String msgText = "Meet at Starbuck on Pine";
	
	@BeforeEach
	void init() {
		UserMessagingInfo userMsgInfo = new UserMessagingInfo(AID, msgText);
		testMessage = new Message(MID, userMsgInfo);
	}
	
	@Test
	void test_getAid_returnsAID() {
		assertEquals(AID, testMessage.getAid());
	}

	@Test
	void test_getMid_returnsMID() {
		assertEquals(MID, testMessage.getMid());
	}
	
	@Test
	void test_getMsgText_returnsMsgText() {
		assertEquals(msgText, testMessage.getMsgText());
	}
	
	@Test
	void test_getMsgDate_returnsWithCorrectDateFormat() {
		DateTimeFormatter dateFormat = DateTimeFormatUtility.FORMATTED_DATE;
		assertDoesNotThrow(()-> {
			dateFormat.parse(testMessage.getMsgDate());
		});
	}

}
