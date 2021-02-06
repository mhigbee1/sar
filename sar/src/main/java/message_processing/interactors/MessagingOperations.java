package message_processing.interactors;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import information_processing.boundaries.InformationIDs;
import information_processing.entities.UserMessagingInfo;
import message_processing.entities.Message;
import ride_processing.interactors.RideMessageUtility;

public class MessagingOperations implements RideMessageUtility {
	private List<Message> msgRecord = new ArrayList<Message>();
	private static int counter = 1;
	
	@Override
	public int addMessage(InformationIDs msgUserID) {
		Message message = new Message(counter, (UserMessagingInfo) msgUserID);
		msgRecord.add(message);
		counter++;
		return message.getMid();
	}
	
	
	@Override
	public Iterator<Message> getMessages() {
		return Collections.unmodifiableList(msgRecord).iterator();
	}
	
	
}
