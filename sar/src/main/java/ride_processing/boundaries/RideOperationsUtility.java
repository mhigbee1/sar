package ride_processing.boundaries;

import java.util.Iterator;

import information_processing.boundaries.InformationIDs;
import message_processing.entities.Message;

public interface RideOperationsUtility {
	
	public int getRid();
	
	public int getAid();
	
	public String getOriginCity();
	
	public String getOriginZip();
	
	public String getDestinationCity();
	
	public String getDestinationZip();
	
	public String getRideDate();
	
	public String getRideTime();
	
	public String getVehicleMake();
	
	public String getVehicleModel();
	
	public String getVehicleColor();
	
	public String getVehiclePlateState();
	
	public String getVehiclePlateNum();
	
	public int getVehicleCapacity();
	
	public String getVehicleRules();
	
	public Double getRatePerPassenger();
	
	public int getRideRequestCreatorID(int jid);
	
	public int addRideRequest(InformationIDs rideReqUserID);
	
	public void updateRideRequest(InformationIDs rideReqUserID);
	
	public void setRideConfirmation(int jid, boolean status);
	
	public void setPickUpConfirmation(int jid);
	
	public boolean isRideDriver(int aid);
	
	public boolean isRideRider(int aid);
	
	public boolean isSubmittedRideRequest(int aid);
	
	public boolean isExistingRideRequest(int jid);
	
	public int addMessage(InformationIDs msgUserID);
	
	public Iterator<Message> getMessages();
	
	public boolean matchesFilter(String originKey, String destinationKey, String dateKey);
	
	public void updateRide(InformationIDs rideInfoID);
}
