package ride_request_processing;

import information_processing.entities.RideRequestInformation;
import ride_processing.interactors.RideRequestUtility;

public class RideRequest implements RideRequestUtility{
	
	private final int jid;
	private RideRequestInformation rideRequestInfo;
	
	public RideRequest(int mJid, RideRequestInformation mRideRequestInfo) {
		this.jid = mJid;
		this.rideRequestInfo = mRideRequestInfo;
	}

	@Override
	public int getJid() {
		return jid;
	}

	@Override
	public int getAid() {
		return rideRequestInfo.getAid();
	}

	@Override
	public Boolean getRideConfirmation() {
		return rideRequestInfo.getRideConfirmation();
	}

	@Override
	public void setRideConfirmation(boolean status) {
		rideRequestInfo.setRideConfirmation(status);
		
	}

	@Override
	public void setPickUpConfirmation() {
		rideRequestInfo.setPickUpConfirmation();	
	}

}
