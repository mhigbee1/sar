package account_operations.boundaries;

import java.util.Iterator;

import information_processing.boundaries.InformationIDs;
import information_processing.entities.UserInformation;
import rate_processing.entities.Rate;

public interface AccountOperationsUtility {
	
	public int getAid();
	
	public int addDriverRating(InformationIDs userID, String ratedByName, String date);
	
	public int addRiderRating(InformationIDs userID, String ratedByName, String date);
	
	public int getDriverRideCounter();
	
	public int getRiderRideCounter();
	
	public int getDriverRatingsCounter();
	
	public int getRiderRatingsCounter();
	
	public void setDriverRideCounter();
	
	public void setRiderRideCounter();
	
	public Double getDriverAvgRating();
	
	public Double getRiderAvgRating();
	
	public Iterator<Rate> getDriverRatingIterator();
	
	public Iterator<Rate> getRiderRatingIterator();
	
	public String getFirstName();
	
	public String getLastName();
	
	public String getPhoneNumber();
	
	public String getUserPic();
	
	public String getCreatedOnDate();
	
	public boolean isActive();
	
	public void updateUserInfo(UserInformation userInfo);
	
	public boolean matchesFilter(String key);

}
