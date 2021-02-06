package response_generation.generators;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import response_generation.boundaries.ResponseBodyUtility;
import ride_report_processing.interactors.RideReportBuilder;
import ride_report_processing.interactors.RideReportProcessTypes;

public class ViewAllRideReportsResponseBodyGenerator implements ResponseBodyUtility{
	

	@Override
	public String generateResponseBody() {
		Gson gson = new Gson();
		JsonArray viewAllReportingResponse = new JsonArray();
		for(RideReportProcessTypes processType: RideReportProcessTypes.values()) {
			JsonObject responseBody = new JsonObject();
			responseBody.add(ResponseBodyUtility.REPORT_ID_KEY, gson.toJsonTree(processType.getPid()));
			responseBody.add(ResponseBodyUtility.NAME_KEY, gson.toJsonTree(RideReportBuilder.getProcessNameText(processType)));
			viewAllReportingResponse.add(responseBody);
		}
		
		return viewAllReportingResponse.toString();
	}
	
}
