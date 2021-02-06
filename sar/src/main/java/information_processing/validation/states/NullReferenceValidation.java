package information_processing.validation.states;

import information_processing.boundaries.InformationIDsValidation;

public class NullReferenceValidation<T> implements InformationIDsValidation{

	private T value;
	private String errorMsg;
	
	public NullReferenceValidation(T mValue, String mErrorMsg) {
		this.value = mValue;
		this.errorMsg = mErrorMsg;
	}
	
	@Override
	public void validate() throws IllegalArgumentException {
		if(value != null) {
			throw new IllegalArgumentException(errorMsg);
		}
		
	}

}
