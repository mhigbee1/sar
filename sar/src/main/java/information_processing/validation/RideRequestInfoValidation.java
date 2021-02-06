package information_processing.validation;

import java.util.ArrayList;
import java.util.List;

import information_processing.boundaries.InformationIDsValidation;
import information_processing.entities.RideRequestInformation;
import information_processing.validation.states.NonNullObjectValidation;
import information_processing.validation.states.NullReferenceValidation;
import information_processing.validation.values.PositiveIntegerValidation;
import response_generation.boundaries.ResponseBodyUtility;

public class RideRequestInfoValidation implements InformationIDsValidation{

	private List<InformationIDsValidation> validators = new ArrayList<InformationIDsValidation>();
	
	public RideRequestInfoValidation(RideRequestInformation rideReqInfo) {
		validators.add(new NonNullObjectValidation(
				rideReqInfo.getAid(),
				ResponseBodyUtility.ERR_MSG_INVALID_ACCOUNT_ID));
		validators.add(new NonNullObjectValidation(
				rideReqInfo.getPassengerCount(), 
				ResponseBodyUtility.ERR_MSG_INVALID_PASSENGERS));
		validators.add(new PositiveIntegerValidation(
				rideReqInfo.getPassengerCount(), 
				ResponseBodyUtility.ERR_MSG_INVALID_PASSENGERS));
		validators.add(new NullReferenceValidation<Boolean>(
				rideReqInfo.getRideConfirmation(),
				ResponseBodyUtility.ERR_MSG_INVALID_ACTIVE_STATUS));
		validators.add(new NullReferenceValidation<Boolean>(
				rideReqInfo.getPickUpConfirmation(), 
				ResponseBodyUtility.ERR_MSG_INVALID_ACTIVE_STATUS));
	}

	@Override
	public void validate() throws IllegalArgumentException {
		for(InformationIDsValidation validator : validators) {
			validator.validate();
		}
		
	}

}
