package information_processing.entities;

import information_processing.boundaries.InformationIDs;

public class RideRequestInformation extends InformationIDs{
	private final Integer aid;
	private final Integer passengerCount;
	private Boolean rideConfirmation;
	private Boolean pickUpConfirmation;
	
	public RideRequestInformation(
			Integer mAid, 
			Integer mPassengerCount,
			Boolean mRideConfirmation,
			Boolean mPickUpConfirmation) {
		this.aid = mAid;
		this.passengerCount = mPassengerCount;
		this.rideConfirmation = mRideConfirmation;
		this.pickUpConfirmation = mPickUpConfirmation;
	}
	
	@Override
	public Integer getAid() {
		return aid;
	}
	
	public Integer getPassengerCount() {
		return passengerCount;
	}
	
	public Boolean getRideConfirmation() {
		return rideConfirmation;
	}
	
	public void setRideConfirmation(Boolean status) {
		this.rideConfirmation = status;
	}
	
	public Boolean getPickUpConfirmation() {
		return pickUpConfirmation;
	}
	
	public void setPickUpConfirmation() {
		this.pickUpConfirmation = true;
	}
	
	@Override
	public boolean isEmptyRequest() {
		return false;
	}
}
