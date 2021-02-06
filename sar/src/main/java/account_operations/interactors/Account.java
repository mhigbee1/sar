package account_operations.interactors;

import java.time.Instant;
import java.util.Iterator;

import account_operations.boundaries.AccountOperationsUtility;
import information_processing.boundaries.InformationIDs;
import information_processing.entities.UserInformation;
import rate_processing.entities.Rate;
import rate_processing.interactors.RatingOperations;
import response_generation.boundaries.DateTimeFormatUtility;

public class Account implements AccountOperationsUtility{
	private final int aid;
	private UserInformation userInfo;
	private AccountRatingUtility driverRatingOperations;
	private AccountRatingUtility riderRatingOperations;
	private final Instant createdOnDate;
	
	
	public Account(int mAid, UserInformation mUserInfo) {
		this.aid = mAid;
		this.userInfo = mUserInfo;
		createdOnDate = Instant.now();
		driverRatingOperations = new RatingOperations();
		riderRatingOperations = new RatingOperations();
	}
	
	@Override
	public int getAid() {
		return aid;
	}
	
	@Override
	public String getFirstName() {
		return userInfo.getFirstName();
	}
	
	
	@Override
	public String getLastName() {
		return userInfo.getLastName();
	}
	
	
	@Override
	public String getPhoneNumber() {
		return userInfo.getPhoneNumber();
	}
	
	
	@Override
	public String getUserPic() {
		return userInfo.getUserPic();
	}
	
	
	@Override
	public String getCreatedOnDate() {
		return DateTimeFormatUtility.getDefaultFormat(createdOnDate);
	}
	
	
	@Override
	public boolean isActive() {
		return userInfo.isActive();
	}
	
	
	@Override
	public int addDriverRating(InformationIDs userID, String ratedByName, String date) {
		return driverRatingOperations.addRating(userID, ratedByName, date);
	}
	
	
	@Override
	public int addRiderRating(InformationIDs userID, String ratedByName, String date) {
		return riderRatingOperations.addRating(userID, ratedByName, date);
	}
	
	
	@Override
	public int getDriverRideCounter() {
		return driverRatingOperations.getRideCounter();
	}
	
	
	@Override
	public int getRiderRideCounter() {
		return riderRatingOperations.getRideCounter();
	}
	
	
	@Override
	public int getDriverRatingsCounter() {
		return driverRatingOperations.getRatingsCounter();
	}
	
	
	@Override
	public int getRiderRatingsCounter() {
		return riderRatingOperations.getRatingsCounter();
	}
	
	
	@Override
	public void setDriverRideCounter() {
		driverRatingOperations.advanceRideCounter();
	}
	
	
	@Override
	public void setRiderRideCounter() {
		riderRatingOperations.advanceRideCounter();
	}
	
	
	@Override
	public Double getDriverAvgRating() {
		return driverRatingOperations.getAvgRating();
	}
	
	
	@Override
	public Double getRiderAvgRating() {
		return riderRatingOperations.getAvgRating();
	}
	
	
	@Override
	public Iterator<Rate> getDriverRatingIterator() {
		return driverRatingOperations.getRatingIterator();
	}
	
	
	@Override
	public Iterator<Rate> getRiderRatingIterator() {
		return riderRatingOperations.getRatingIterator();
	}
	
	
	@Override
	public boolean matchesFilter(String key) {
		return userInfo.matchesFiler(key);
	}
	
	
	@Override
	public void updateUserInfo(UserInformation mUserInfo) {
		this.userInfo = mUserInfo;
	}
	
	

}
