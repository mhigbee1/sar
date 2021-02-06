package information_processing.entities;

import information_processing.boundaries.ConfirmationType;
import information_processing.boundaries.ConfirmationUtilities;
import information_processing.boundaries.InformationIDs;

public class UpdateRideRequestInfo extends InformationIDs implements ConfirmationUtilities{
	private Integer aid;
	private Boolean requestStatus;
	private ConfirmationType confirmType;
	
	public UpdateRideRequestInfo(
			Integer mAid, 
			Boolean mRequestStatus, 
			ConfirmationType mConfirmType) {
		
		this.aid = mAid;
		this.requestStatus = mRequestStatus;
		this.confirmType = mConfirmType;
	}
	
	@Override
	public Integer getAid() {
		return aid;
	}
	
	public Boolean getRequestStatus() {
		return requestStatus;
	}
	
	@Override
	public boolean isEmptyRequest() {
		return false;
	}
	
	@Override
	public ConfirmationType getConfirmationType() {
		return confirmType;
	}
	
}

