package response_generation.generators;

import java.util.Iterator;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonNull;
import com.google.gson.JsonObject;

import account_operations.boundaries.AccountOperationsUtility;
import rate_processing.entities.Rate;
import response_generation.boundaries.ResponseBodyUtility;
import ride_processing.boundaries.RideOperationsUtility;

public class ViewRideReportDetailResponseBodyGenerator implements ResponseBodyUtility {
	
	private AccountOperationsUtility accountInstance;
	private RideOperationsUtility rideInstance;
	
	
	public ViewRideReportDetailResponseBodyGenerator(
			AccountOperationsUtility mAccountInstance, 
			RideOperationsUtility mRideInstance) {
		
		this.accountInstance = mAccountInstance;
		this.rideInstance = mRideInstance;
	}

	
	@Override
	public String generateResponseBody() {
		Gson gson = new Gson();
		JsonArray reportDetailResponse = new JsonArray();
		JsonObject responseBody = new JsonObject();
		JsonObject locationInfo = new JsonObject();
		JsonObject dateTimeInfo = new JsonObject();
		JsonObject vehicleInfo = new JsonObject();
		
		responseBody.add(ResponseBodyUtility.RIDE_ID_KEY, gson.toJsonTree(rideInstance.getRid()));
		locationInfo.add(ResponseBodyUtility.ORIGIN_CITY_KEY, gson.toJsonTree(rideInstance.getOriginCity()));
		locationInfo.add(ResponseBodyUtility.ORIGIN_ZIP_KEY, gson.toJsonTree(rideInstance.getOriginZip()));
		locationInfo.add(ResponseBodyUtility.DESTINATION_CITY_KEY, gson.toJsonTree(rideInstance.getDestinationCity()));
		locationInfo.add(ResponseBodyUtility.DESTINATION_ZIP_KEY, gson.toJsonTree(rideInstance.getDestinationZip()));
		responseBody.add(ResponseBodyUtility.LOCATION_INFO_HEADER_KEY, locationInfo);
		
		dateTimeInfo.add(ResponseBodyUtility.RIDE_DATE_KEY, gson.toJsonTree(rideInstance.getRideDate()));
		dateTimeInfo.add(ResponseBodyUtility.RIDE_TIME_KEY, gson.toJsonTree(rideInstance.getRideTime()));
		responseBody.add(ResponseBodyUtility.DATE_TIME_HEADER_KEY, dateTimeInfo);
		
		vehicleInfo.add(ResponseBodyUtility.VEHICLE_MAKE_KEY, gson.toJsonTree(rideInstance.getVehicleMake()));
		vehicleInfo.add(ResponseBodyUtility.VEHICLE_MODEL_KEY,  gson.toJsonTree(rideInstance.getVehicleModel()));
		vehicleInfo.add(ResponseBodyUtility.VEHICLE_COLOR_KEY, gson.toJsonTree(rideInstance.getVehicleColor()));
		vehicleInfo.add(ResponseBodyUtility.VEHICLE_PLATE_STATE_KEY, gson.toJsonTree(rideInstance.getVehiclePlateState()));
		vehicleInfo.add(ResponseBodyUtility.VEHICLE_PLATE_NUM_KEY, gson.toJsonTree(rideInstance.getVehiclePlateNum()));
		responseBody.add(ResponseBodyUtility.VEHICLE_INFO_HEADER_KEY, vehicleInfo);
		responseBody.add(ResponseBodyUtility.DRIVER_KEY, gson.toJsonTree(accountInstance.getFirstName()));
		responseBody.add(ResponseBodyUtility.DRIVER_USER_PIC_KEY, gson.toJsonTree(accountInstance.getUserPic()));
		responseBody.add(ResponseBodyUtility.RIDE_COUNT_KEY, gson.toJsonTree(accountInstance.getDriverRideCounter()));
		responseBody.add(ResponseBodyUtility.RATING_COUNT_KEY, gson.toJsonTree(accountInstance.getDriverRatingsCounter()));
		responseBody.add(ResponseBodyUtility.AVG_RATING_KEY, gson.toJsonTree(
				accountInstance.getDriverAvgRating() == null ? JsonNull.INSTANCE : accountInstance.getDriverAvgRating()));
		responseBody.add(ResponseBodyUtility.DRIVER_COMMENTS_KEY, gatherDriverComments(gson));
		
		reportDetailResponse.add(responseBody);
		
		return responseBody.toString();
	}
	
	
	
	private JsonArray gatherDriverComments(Gson gson) {
		JsonArray driverComments = new JsonArray();
		Iterator<Rate> rateIterator = accountInstance.getDriverRatingIterator();
		
		while(rateIterator.hasNext()) {
			Rate rateInstance = rateIterator.next();
			JsonObject detailBody = new JsonObject();
			detailBody.add(ResponseBodyUtility.RIDE_ID_KEY, gson.toJsonTree(rateInstance.getRid()));
			detailBody.add(ResponseBodyUtility.RATING_DATE_KEY, gson.toJsonTree(rateInstance.getDate()));
			detailBody.add(ResponseBodyUtility.RATING_KEY, gson.toJsonTree(rateInstance.getRating()));
			detailBody.add(ResponseBodyUtility.COMMENT_KEY, gson.toJsonTree(rateInstance.getComment()));
			driverComments.add(detailBody);
		}
		return driverComments;
	}
	

}
