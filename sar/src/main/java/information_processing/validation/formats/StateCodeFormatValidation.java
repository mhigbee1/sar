package information_processing.validation.formats;

import information_processing.boundaries.InformationIDsValidation;
import information_processing.validation.constants.StateAbbreviations;

public class StateCodeFormatValidation implements InformationIDsValidation{
	
	private String state;
	private String errorMsg;
	
	public StateCodeFormatValidation(String mState, String mErrorMsg) {
		this.state = mState;
		this.errorMsg = mErrorMsg;
	}
	
	private boolean isValidState() {
		for(StateAbbreviations stateCode : StateAbbreviations.values()) {
			if(stateCode.getState().equals(state)) {
				return true;
			}
		}
		return false;
	}

	@Override
	public void validate() throws IllegalArgumentException {
		if(!isValidState()) {
			throw new IllegalArgumentException(errorMsg);
		}
	}

}
