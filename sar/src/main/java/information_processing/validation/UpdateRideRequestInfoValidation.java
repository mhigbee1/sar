package information_processing.validation;

import java.util.ArrayList;
import java.util.List;

import information_processing.boundaries.ConfirmationType;
import information_processing.boundaries.InformationIDsValidation;
import information_processing.entities.UpdateRideRequestInfo;
import information_processing.validation.states.NonNullObjectValidation;
import information_processing.validation.values.TrueBooleanValidation;
import response_generation.boundaries.ResponseBodyUtility;

public class UpdateRideRequestInfoValidation implements InformationIDsValidation {
	
	private List<InformationIDsValidation> validators = new ArrayList<InformationIDsValidation>();
	
	public UpdateRideRequestInfoValidation(UpdateRideRequestInfo updateRideReqInfo) {
		String errorMsg;
		
		validators.add(new NonNullObjectValidation(
				updateRideReqInfo.getAid(),
				ResponseBodyUtility.ERR_MSG_INVALID_ACCOUNT_ID));
		
		if(updateRideReqInfo.getConfirmationType() == ConfirmationType.RIDE_CONFIRMED) {
			errorMsg = ResponseBodyUtility.ERR_MSG_INVALID_RIDE_CONFIRMATION;
			validators.add(new NonNullObjectValidation(
					updateRideReqInfo.getRequestStatus(),
					errorMsg));
		}else if(updateRideReqInfo.getConfirmationType() == ConfirmationType.PICKUP_CONFIRMED){
			errorMsg = ResponseBodyUtility.ERR_MSG_INVALID_PICKUP_CONFIRMATION;
			validators.add(new NonNullObjectValidation(
					updateRideReqInfo.getRequestStatus(),
					errorMsg));
			validators.add(new TrueBooleanValidation(
					updateRideReqInfo.getRequestStatus(),
					errorMsg));
		}
	}

	@Override
	public void validate() throws IllegalArgumentException {
		for(InformationIDsValidation validator : validators) {
			validator.validate();
		}
		
	}

}
