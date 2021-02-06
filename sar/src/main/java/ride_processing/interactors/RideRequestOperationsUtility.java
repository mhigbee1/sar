package ride_processing.interactors;

import information_processing.entities.RideRequestInformation;

public interface RideRequestOperationsUtility {
	
	public int addRideRequest(RideRequestInformation rideRequestInfo);
	
	public int getAid(int jid);
	
	public void approveRideRequest(int jid);
	
	public void denyRideRequest(int jid);
	
	public void setPickUpConfirmation(int jid);
	
	public boolean isExistingRideRequest(int jid);
	
	public boolean submittedRideRequest(int aid);
	
	public boolean isRider(int aid);

}
