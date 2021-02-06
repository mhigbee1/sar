package response_generation.generators;

import java.util.Iterator;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonNull;
import com.google.gson.JsonObject;

import account_operations.boundaries.AccountOperationsUtility;
import rate_processing.boundaries.RatingType;
import rate_processing.entities.Rate;
import response_generation.boundaries.ResponseBodyUtility;

public class ViewRatingsResponseBodyGenerator implements ResponseBodyUtility{
	
	private AccountOperationsUtility accountInstance;
	private Iterator<Rate> rateIterator;
	private int rideCount;
	private int ratingCount;
	private Double avgRating;
	
	public ViewRatingsResponseBodyGenerator(AccountOperationsUtility mAccountInstance, RatingType mType) {
		this.accountInstance = mAccountInstance;
		
		if(mType == RatingType.DRIVER) {
			rateIterator = mAccountInstance.getDriverRatingIterator();
			rideCount = mAccountInstance.getDriverRideCounter();
			ratingCount = mAccountInstance.getDriverRatingsCounter();
			avgRating = mAccountInstance.getDriverAvgRating();
		}else if(mType == RatingType.RIDER) {
			rateIterator = mAccountInstance.getRiderRatingIterator();
			rideCount = mAccountInstance.getRiderRideCounter();
			ratingCount = mAccountInstance.getRiderRatingsCounter();
			avgRating = mAccountInstance.getRiderAvgRating();
		}
	}
	
	
	@Override
	public String generateResponseBody() {
		Gson gson = new Gson();
		JsonObject responseBody = new JsonObject();
		JsonArray ratingDetail = new JsonArray();
		
		responseBody.add(ResponseBodyUtility.ACCOUNT_ID_KEY, gson.toJsonTree(accountInstance.getAid()));
		responseBody.add(ResponseBodyUtility.FIRST_NAME_KEY, gson.toJsonTree(accountInstance.getFirstName()));
		responseBody.add(ResponseBodyUtility.RIDE_COUNT_KEY, gson.toJsonTree(rideCount));
		responseBody.add(ResponseBodyUtility.RATING_COUNT_KEY, gson.toJsonTree(ratingCount));
		responseBody.add(ResponseBodyUtility.AVG_RATING_KEY, gson.toJsonTree(
				avgRating == null ? JsonNull.INSTANCE : avgRating));
		
		while(rateIterator.hasNext()) {
			Rate rateInstance = rateIterator.next();
			JsonObject detailBody = new JsonObject();
			detailBody.add(ResponseBodyUtility.RIDE_ID_KEY, gson.toJsonTree(rateInstance.getRid()));
			detailBody.add(ResponseBodyUtility.SENT_BY_ACCOUNT_KEY, gson.toJsonTree(rateInstance.getRateByID()));
			detailBody.add(ResponseBodyUtility.FIRST_NAME_KEY, gson.toJsonTree(rateInstance.getRatedByName()));
			detailBody.add(ResponseBodyUtility.RATING_DATE_KEY, gson.toJsonTree(rateInstance.getDate()));
			detailBody.add(ResponseBodyUtility.RATING_KEY, gson.toJsonTree(rateInstance.getRating()));
			detailBody.add(ResponseBodyUtility.COMMENT_KEY, gson.toJsonTree(rateInstance.getComment()));
			ratingDetail.add(detailBody);
		}
		
		responseBody.add(ResponseBodyUtility.DETAIL_KEY, ratingDetail);
		
		return responseBody.toString();
	}
	
	

}
