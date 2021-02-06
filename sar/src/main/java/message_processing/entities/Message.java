package message_processing.entities;

import java.time.Instant;
import information_processing.entities.UserMessagingInfo;
import response_generation.boundaries.DateTimeFormatUtility;

public class Message {
	private final int mid;
	private final Instant msgDate;
	private UserMessagingInfo msgInfo;
	
	public Message(Integer mMid, UserMessagingInfo mMsgInfo) {
		this.mid = mMid;
		this.msgInfo = mMsgInfo;
		msgDate = Instant.now();
		
	}
	
	public int getAid() {
		return msgInfo.getAid();
	}
	
	public int getMid() {
		return mid;
	}
	
	public String getMsgText() {
		return msgInfo.getMsgText();
	}
	
	public String getMsgDate() {
		return DateTimeFormatUtility.getDefaultFormat(msgDate);
	}

}
