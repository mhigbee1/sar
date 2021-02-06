package information_processing.validation;

import java.util.ArrayList;
import java.util.List;

import information_processing.boundaries.InformationIDsValidation;
import information_processing.entities.UserInformation;
import information_processing.validation.formats.NameFormatValidation;
import information_processing.validation.formats.PhoneNumberFormatValidation;
import information_processing.validation.states.ActiveStatusValidation;
import information_processing.validation.states.NonEmptyStringValidation;
import information_processing.validation.states.NonNullObjectValidation;
import response_generation.boundaries.ResponseBodyUtility;

public class UserInfoValidation implements InformationIDsValidation{
	
	private List<InformationIDsValidation> validators = new ArrayList<InformationIDsValidation>();
	
	public UserInfoValidation(UserInformation userInfo, boolean systemActiveStatus) {
		validators.add(new NonNullObjectValidation(userInfo.getFirstName(),ResponseBodyUtility.ERR_MSG_INVALID_FIRST_NAME));
		validators.add(new NonEmptyStringValidation(userInfo.getFirstName(), ResponseBodyUtility.ERR_MSG_INVALID_FIRST_NAME));
		validators.add(new NameFormatValidation(userInfo.getFirstName(), ResponseBodyUtility.ERR_MSG_INVALID_FIRST_NAME));
		
		validators.add(new NonNullObjectValidation(userInfo.getLastName(),ResponseBodyUtility.ERR_MSG_INVALID_LAST_NAME));
		validators.add(new NonEmptyStringValidation(userInfo.getLastName(), ResponseBodyUtility.ERR_MSG_INVALID_LAST_NAME));
		validators.add(new NameFormatValidation(userInfo.getLastName(), ResponseBodyUtility.ERR_MSG_INVALID_LAST_NAME));
		
		validators.add(new NonNullObjectValidation(userInfo.getPhoneNumber(),ResponseBodyUtility.ERR_MSG_INVALID_PHONE_NUM));
		validators.add(new NonEmptyStringValidation(userInfo.getPhoneNumber(), ResponseBodyUtility.ERR_MSG_INVALID_PHONE_NUM));
		validators.add(new PhoneNumberFormatValidation(userInfo.getPhoneNumber(), ResponseBodyUtility.ERR_MSG_INVALID_PHONE_NUM));
		
		validators.add(new NonNullObjectValidation(userInfo.getUserPic(),ResponseBodyUtility.ERR_MSG_INVALID_USER_PIC));
		validators.add(new NonEmptyStringValidation(userInfo.getUserPic(), ResponseBodyUtility.ERR_MSG_INVALID_USER_PIC));
		
		validators.add(new NonNullObjectValidation(userInfo.isActive(),ResponseBodyUtility.ERR_MSG_INVALID_ACTIVE_STATUS));
		validators.add(new ActiveStatusValidation(systemActiveStatus, userInfo.isActive(), ResponseBodyUtility.ERR_MSG_INVALID_ACTIVE_STATUS));
	}

	@Override
	public void validate() throws IllegalArgumentException {
		for(InformationIDsValidation validator : validators) {
			validator.validate();
		}
	}
}
