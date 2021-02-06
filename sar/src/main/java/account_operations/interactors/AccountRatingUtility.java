package account_operations.interactors;

import java.util.Iterator;

import information_processing.boundaries.InformationIDs;
import rate_processing.entities.Rate;

public interface AccountRatingUtility {
	
	public int addRating(InformationIDs rateInfo, String ratedByName, String date);
	
	public Double getAvgRating();
	
	public void advanceRideCounter();
	
	public int getRideCounter();
	
	public int getRatingsCounter();
	
	public Iterator<Rate> getRatingIterator();
}
