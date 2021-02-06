package rate_processing.entities;

import information_processing.entities.RateInformation;

public class Rate {
	private final int sid;
	private final RateInformation rateInfo;
	private final String ratedByName;
	private final String date;
	
	public Rate(int mSid, RateInformation mRateInfo, String mRatedByName, String mDate) {
		this.sid = mSid;
		this.rateInfo = mRateInfo;
		this.ratedByName = mRatedByName;
		this.date = mDate;
	}
	
	public int getSid() {
		return sid;
	}
	
	public int getRid() {
		return rateInfo.getRid();
	}
	
	public int getRateByID() {
		return rateInfo.getAid();
	}
	
	public int getRating() {
		return rateInfo.getRating();
	}
	
	public String getComment() {
		return rateInfo.getComment();
	}
	
	public String getRatedByName() {
		return ratedByName;
	}
	
	public String getDate() {
		return date;
	}
	
}
