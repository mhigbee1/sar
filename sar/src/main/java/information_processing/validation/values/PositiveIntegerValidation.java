package information_processing.validation.values;

import information_processing.boundaries.InformationIDsValidation;

public class PositiveIntegerValidation implements InformationIDsValidation {
	private Integer value;
	private String errorMsg;
	
	public PositiveIntegerValidation(Integer mValue, String mErrorMsg) {
		this.value = mValue;
		this.errorMsg = mErrorMsg;
	}
	
	@Override
	public void validate() throws IllegalArgumentException {
		if(value <= 0) {
			throw new IllegalArgumentException(errorMsg);
		}
	}
}
