package information_processing.validation.formats;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import information_processing.boundaries.InformationIDsValidation;

public class PhoneNumberFormatValidation implements InformationIDsValidation{
	
	private String phoneNumber;
	private String errorMsg;
	private static final String PHONE_NUMBER_REGEX = "^\\d{3}[-]\\d{3}[-]\\d{4}$";
	
	public PhoneNumberFormatValidation(String mPhoneNumber, String mErrorMsg) {
		this.phoneNumber = mPhoneNumber;
		this.errorMsg = mErrorMsg;
	}
	
	private void checkForNullPhoneNumber() throws IllegalArgumentException{
		if(phoneNumber == null) {
			throw new IllegalArgumentException(errorMsg);
		}
	}
	
	private void validatePhoneNumberFormat() throws IllegalArgumentException {
		try {
			Pattern pattern = Pattern.compile(PHONE_NUMBER_REGEX);
			Matcher matcher = pattern.matcher(phoneNumber);
				if(!matcher.matches()) {
					throw new IllegalArgumentException(errorMsg);
				}
			}catch (IllegalArgumentException iae){
				iae.printStackTrace();
				throw new IllegalArgumentException(errorMsg);
			}
	}
	
	@Override
	public void validate() throws IllegalArgumentException{
		checkForNullPhoneNumber();
		validatePhoneNumberFormat();
	}

}
