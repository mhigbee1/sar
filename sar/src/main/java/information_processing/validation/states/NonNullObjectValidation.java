package information_processing.validation.states;

import information_processing.boundaries.InformationIDsValidation;

public class NonNullObjectValidation implements InformationIDsValidation{
	
	private Object object;
	private String errorMsg;
	
	public NonNullObjectValidation(Object mObject, String mErrorMsg) {
		this.object = mObject;
		this.errorMsg = mErrorMsg;
	}

	@Override
	public void validate() throws IllegalArgumentException {
		if(object == null) {
			throw new IllegalArgumentException(errorMsg);
		}
	}
}
