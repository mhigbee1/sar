package ride_processing.boundaries;

import java.text.ParseException;
import java.util.Iterator;

import information_processing.boundaries.InformationIDs;
import message_processing.entities.Message;

public interface RideOperationsBoundary {
	
	public int addRide(InformationIDs userID);
	
	public void updateRide(int rid, InformationIDs userID);
	
	public void removeRide(int rid);
	
	public RideOperationsUtility getRide(int rid);
	
	public Iterator<RideOperationsUtility> findRide(String origin, String destination, String date);
	
	public Iterator<RideOperationsUtility> findRidesInDateRange(String startDate, String endDate) throws ParseException;
	
	public int addRideRequest(int rid, InformationIDs userID);
	
	public void setRideConfirmation(int rid, int jid, InformationIDs userID);
	
	public void setPickUpConfirmation(int rid, int jid);
	
	public int addMessage(int rid, InformationIDs userInfo);
	
	public Iterator<Message> getMessages(int rid);
	
	public int getRideRequestCreatorID(int rid, int jid);
	
	public String getRideDate(int rid);
	
	public boolean isExistingRide(int rid);
	
	public boolean isExistingRideRequest(int rid, int jid);
	
	public boolean submittedRideRequest(int rid, int aid);
	
	public boolean isAccountRideDriver(int rid, int aid);
	
	public boolean isAccountRideRider(int rid, int aid);

}
