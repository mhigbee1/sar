package information_processing.validation.states;

import information_processing.boundaries.InformationIDsValidation;

public class NonEmptyStringValidation implements InformationIDsValidation{
	
	public String input;
	public String errorMsg;
	
	public NonEmptyStringValidation(String mInput, String mErrorMsg) {
		this.input = mInput;
		this.errorMsg = mErrorMsg;
	}

	@Override
	public void validate() throws IllegalArgumentException {
		if(input.isEmpty()) {
			throw new IllegalArgumentException(errorMsg);
		}
		
	}

}
