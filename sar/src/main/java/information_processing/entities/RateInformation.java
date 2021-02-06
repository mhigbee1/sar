package information_processing.entities;

import information_processing.boundaries.InformationIDs;

public class RateInformation extends InformationIDs {
	private final Integer rid;
	private final Integer senderID;
	private final Integer rating;
	private String comment;
	
	public RateInformation(Integer mRid, Integer mSenderID, Integer mRating, String mComment) {
		this.rid = mRid;
		this.senderID = mSenderID;
		this.rating = mRating;
		this.comment = mComment;
	}
	
	@Override
	public Integer getRid() {
		return rid;
	}
	
	@Override
	public Integer getAid() {
		return senderID;
	}
	
	public Integer getRating() {
		return rating;
	}
	
	public String getComment() {
		return comment;
	}
	
	@Override
	public boolean isEmptyRequest() {
		return false;
	}
	
	
}
