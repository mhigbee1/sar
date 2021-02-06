package response_generation.generators;

import java.util.Iterator;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import ride_processing.boundaries.RideOperationsUtility;
import response_generation.boundaries.ResponseBodyUtility;

public class SearchRidesResponseBodyGenerator implements ResponseBodyUtility{
	
	private Iterator<RideOperationsUtility> iterator;
	
	public SearchRidesResponseBodyGenerator(Iterator<RideOperationsUtility> mIterator) {
		this.iterator = mIterator;
	}

	@Override
	public String generateResponseBody() {
		Gson gson = new Gson();
		JsonArray searchResponse = new JsonArray();
		
		while(iterator.hasNext()) {
			RideOperationsUtility rideInstance = iterator.next();
			JsonObject responseBody = new JsonObject();
			JsonObject locationInfo = new JsonObject();
			JsonObject dateTimeInfo = new JsonObject();
			responseBody.add(ResponseBodyUtility.RIDE_ID_KEY, gson.toJsonTree(rideInstance.getRid()));
			locationInfo.add(ResponseBodyUtility.ORIGIN_CITY_KEY, gson.toJsonTree(rideInstance.getOriginCity()));
			locationInfo.add(ResponseBodyUtility.ORIGIN_ZIP_KEY, gson.toJsonTree(rideInstance.getOriginZip()));
			locationInfo.add(ResponseBodyUtility.DESTINATION_CITY_KEY, gson.toJsonTree(rideInstance.getDestinationCity()));
			locationInfo.add(ResponseBodyUtility.DESTINATION_ZIP_KEY, gson.toJsonTree(rideInstance.getDestinationZip()));
			responseBody.add(ResponseBodyUtility.LOCATION_INFO_HEADER_KEY, locationInfo);
			
			dateTimeInfo.add(ResponseBodyUtility.RIDE_DATE_KEY, gson.toJsonTree(rideInstance.getRideDate()));
			dateTimeInfo.add(ResponseBodyUtility.RIDE_TIME_KEY, gson.toJsonTree(rideInstance.getRideTime()));
			responseBody.add(ResponseBodyUtility.DATE_TIME_HEADER_KEY, dateTimeInfo);
			
			searchResponse.add(responseBody);
		}
		return searchResponse.toString();
	}

}
