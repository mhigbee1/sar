package information_processing.validation;

import java.util.ArrayList;
import java.util.List;

import information_processing.boundaries.InformationIDsValidation;
import information_processing.entities.UserMessagingInfo;
import information_processing.validation.states.NonNullObjectValidation;
import response_generation.boundaries.ResponseBodyUtility;

public class UserMessagingInfoValidation implements InformationIDsValidation {
	

	private List<InformationIDsValidation> validators = new ArrayList<InformationIDsValidation>();
	
	public UserMessagingInfoValidation(UserMessagingInfo userMessagingInfo) {
		validators.add(new NonNullObjectValidation(
				userMessagingInfo.getAid(),
				ResponseBodyUtility.ERR_MSG_INVALID_ACCOUNT_ID));
		validators.add(new NonNullObjectValidation(
				userMessagingInfo.getMsgText(),
				ResponseBodyUtility.ERR_MSG_INVALID_MSG_TEXT));
	}	
		
	@Override
	public void validate() throws IllegalArgumentException {
		for(InformationIDsValidation validator : validators) {
			validator.validate();
		}
		
	}

}
