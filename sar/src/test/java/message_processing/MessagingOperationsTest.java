package message_processing;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Iterator;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import information_processing.entities.UserMessagingInfo;
import message_processing.entities.Message;
import message_processing.interactors.MessagingOperations;

class MessagingOperationsTest {
	
	private MessagingOperations messagingOperator;
	private int AID = 1;
	private String msgText = "Pick me up at North Gate";
	
	@BeforeEach
	void init() {
		messagingOperator = new MessagingOperations();
	}
	
	@Test
	void test_getMessages_returnsDefaultEmptyIterator() {
		assertFalse(messagingOperator.getMessages().hasNext());
	}

	@Test
	void test_addMessage_newMessagePostsToAccount_returnsMatchingAID() {
		UserMessagingInfo userMsgInfo = new UserMessagingInfo(AID, msgText);
		messagingOperator.addMessage(userMsgInfo);
		Message messageInstance = null;
		Iterator<Message> iterator = messagingOperator.getMessages();
		while(iterator.hasNext()) {
			messageInstance = iterator.next();
		}
		assertEquals(AID, messageInstance.getAid());
	}

}
