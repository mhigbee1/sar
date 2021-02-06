package ride_processing.interactors;

import java.util.Iterator;

import information_processing.boundaries.InformationIDs;
import message_processing.entities.Message;

public interface RideMessageUtility {
	
	public int addMessage(InformationIDs msgUserID);
	
	public Iterator<Message> getMessages();

}
