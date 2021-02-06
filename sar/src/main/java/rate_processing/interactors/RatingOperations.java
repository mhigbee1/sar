package rate_processing.interactors;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import account_operations.interactors.AccountRatingUtility;
import information_processing.boundaries.InformationIDs;
import information_processing.entities.RateInformation;
import rate_processing.entities.Rate;

public class RatingOperations implements AccountRatingUtility{
	private List<Rate> ratingRecord = new ArrayList<Rate>();
	private static int index = 0;
	private int rideCounter = 0;
	private Double avgRating = null;
	
	@Override
	public int addRating(InformationIDs rateInfo, String ratedByName, String date) {
		Rate newRating = new Rate(index, (RateInformation) rateInfo, ratedByName, date);
		ratingRecord.add(newRating);
		calculateAvgRating(newRating.getRating());
		index++;
		return newRating.getSid();
	}
	
	private void calculateAvgRating(int rating) {
		if(avgRating == null) {
			avgRating = (double) rating;
		}else {
			avgRating += rating;
			avgRating = avgRating/ratingRecord.size();
		}
	}
	
	@Override
	public Double getAvgRating() {
		return avgRating;
	}
	
	@Override
	public void advanceRideCounter() {
		rideCounter++;
	}
	
	@Override
	public int getRideCounter() {
		return rideCounter;
	}
	
	@Override
	public int getRatingsCounter() {
		return ratingRecord.size();
	}
	
	@Override
	public Iterator<Rate> getRatingIterator(){
		return Collections.unmodifiableList(ratingRecord).iterator();
	}
	
}
