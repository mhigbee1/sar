package response_generation.generators;

import java.util.Iterator;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import message_processing.entities.Message;
import response_generation.boundaries.ResponseBodyUtility;

public class MessagingResponseBodyGenerator implements ResponseBodyUtility{
	
	private Iterator<Message> iterator;
	
	public MessagingResponseBodyGenerator(Iterator<Message> mIterator) {
		this.iterator = mIterator;
	}

	@Override
	public String generateResponseBody() {
		Gson gson = new Gson();
		JsonArray messageResponse = new JsonArray();
		
		while(iterator.hasNext()) {
			Message messageInstance = iterator.next();
			JsonObject responseBody = new JsonObject();
			responseBody.add(ResponseBodyUtility.MESSAGE_ID_KEY, gson.toJsonTree(messageInstance.getMid()));
			responseBody.add(ResponseBodyUtility.SENT_BY_ACCOUNT_KEY, gson.toJsonTree(messageInstance.getAid()));
			responseBody.add(ResponseBodyUtility.MSG_DATE_KEY, gson.toJsonTree(messageInstance.getMsgDate()));
			responseBody.add(ResponseBodyUtility.MSG_BODY_KEY, gson.toJsonTree(messageInstance.getMsgText()));
			
			messageResponse.add(responseBody);
		}
		
		return messageResponse.toString();
	}

}
