package information_processing.validation.values;

import information_processing.boundaries.InformationIDsValidation;

public class TrueBooleanValidation implements InformationIDsValidation{

	private boolean value;
	private String errorMsg;
	
	public TrueBooleanValidation(boolean mValue, String mErrorMsg) {
		this.value = mValue;
		this.errorMsg = mErrorMsg;
	}
	
	@Override
	public void validate() throws IllegalArgumentException {
		if(!value) {
			throw new IllegalArgumentException(errorMsg);
		}
		
	}

}
