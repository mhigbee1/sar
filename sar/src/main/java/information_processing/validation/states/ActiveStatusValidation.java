package information_processing.validation.states;

import information_processing.boundaries.InformationIDsValidation;

public class ActiveStatusValidation implements InformationIDsValidation{
	private Boolean systemActivationStatus;
	private Boolean requestActivationStatus;
	private String errorMsg;
	
	public ActiveStatusValidation (
			Boolean mSystemActivationStatus, 
			Boolean mRequestActivationStatus, 
			String mErrorMsg) {
		this.systemActivationStatus = mSystemActivationStatus;
		this.requestActivationStatus = mRequestActivationStatus;
		this.errorMsg = mErrorMsg;
	}
	
	@Override
	public void validate() throws IllegalArgumentException{
		if(systemActivationStatus != requestActivationStatus) {
			throw new IllegalArgumentException(errorMsg);
		}
	}

}
