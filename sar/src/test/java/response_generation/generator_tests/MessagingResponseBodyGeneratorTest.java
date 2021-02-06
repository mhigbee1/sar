package response_generation.generator_tests;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import information_processing.entities.UserMessagingInfo;
import message_processing.entities.Message;
import response_generation.boundaries.ResponseBodyUtility;
import response_generation.generators.MessagingResponseBodyGenerator;

class MessagingResponseBodyGeneratorTest {
	private Gson gson;
	private JsonArray jsonArray;
	private JsonObject responseBody;
	
	private List<Message> messageRecord;
	private Message messageInstance;
	private final int AID = 123;
	private final int MID = 45;
	private final String MESSAGE_TEXT = "Sample message";
	private String expectedResponse;
	
	@BeforeEach
	void init() {
		gson = new Gson();
		jsonArray = new JsonArray();
		responseBody = new JsonObject();
		
		messageRecord = new ArrayList<Message>();
		messageInstance = new Message(MID, new UserMessagingInfo(AID, MESSAGE_TEXT));
		
		responseBody.add(ResponseBodyUtility.MESSAGE_ID_KEY, gson.toJsonTree(messageInstance.getMid()));
		responseBody.add(ResponseBodyUtility.SENT_BY_ACCOUNT_KEY, gson.toJsonTree(messageInstance.getAid()));
		responseBody.add(ResponseBodyUtility.MSG_DATE_KEY, gson.toJsonTree(messageInstance.getMsgDate()));
		responseBody.add(ResponseBodyUtility.MSG_BODY_KEY, gson.toJsonTree(messageInstance.getMsgText()));
		
		jsonArray.add(responseBody);
		expectedResponse = jsonArray.toString();
	}
	

	@Test
	void test_generateReponseBody__returnsExpectedResponse() {
		messageRecord.add(messageInstance);
		Iterator<Message> iterator = messageRecord.iterator();
		ResponseBodyUtility messagingResponseBody = new MessagingResponseBodyGenerator(iterator);
		assertEquals(expectedResponse, messagingResponseBody.generateResponseBody());
	}

}
