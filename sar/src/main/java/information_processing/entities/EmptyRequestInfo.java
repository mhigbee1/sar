package information_processing.entities;

import information_processing.boundaries.InformationIDs;

public class EmptyRequestInfo extends InformationIDs {
	private final String errorMsg;
	
	public EmptyRequestInfo(String mErrorMsg) {
		this.errorMsg = mErrorMsg;
	}
	
	public String getErrorMsg() {
		return errorMsg;
	}
	
	@Override
	public boolean isEmptyRequest() {
		return true;
	}
}
