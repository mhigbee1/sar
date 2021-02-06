package information_processing.validation.values;

import information_processing.boundaries.InformationIDsValidation;

public class NonNegativeDoublesValidation implements InformationIDsValidation {

	private double number;
	private String errorMsg;
	
	public NonNegativeDoublesValidation(double mNumber, String mErrorMsg) {
		this.number = mNumber;
		this.errorMsg = mErrorMsg;
	}
	
	
	@Override
	public void validate() throws IllegalArgumentException {
		if(number < 0) {
			throw new IllegalArgumentException(errorMsg);
		}
		
	}

}
