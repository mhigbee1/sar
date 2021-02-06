package information_processing.entities;

import information_processing.boundaries.InformationIDs;

public class UserMessagingInfo extends InformationIDs {
	private final Integer aid;
	private final String msgText;
	
	public UserMessagingInfo(Integer mAid, String mMsgText) {
		this.aid = mAid;
		this.msgText = mMsgText;
	}
	
	@Override
	public Integer getAid() {
		return aid;
	}
	
	public String getMsgText() {
		return msgText;
	}
	
	@Override
	public boolean isEmptyRequest() {
		return false;
	}
}
