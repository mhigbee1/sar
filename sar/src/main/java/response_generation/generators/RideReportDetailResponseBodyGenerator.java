package response_generation.generators;

import java.util.Iterator;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import response_generation.boundaries.ResponseBodyUtility;
import ride_report_processing.boundaries.RideReportProcess;
import ride_report_processing.entities.RideReportDetail;
import ride_report_processing.interactors.RideReport;

public class RideReportDetailResponseBodyGenerator implements ResponseBodyUtility {

		private RideReportProcess rideReportProcess;
		
		public RideReportDetailResponseBodyGenerator(RideReportProcess mRideReportProcess) {
			this.rideReportProcess = mRideReportProcess;
		}
		
		

		@Override
		public String generateResponseBody() {
			Gson gson = new Gson();
			JsonObject responseBody = new JsonObject();
			JsonArray reportDetailResponse = new JsonArray();
			RideReport rideReportInstance = rideReportProcess.generateRideReport();
			Iterator<RideReportDetail> iterator = rideReportInstance.getRideReportDetails();
			
			responseBody.add(ResponseBodyUtility.REPORT_ID_KEY, gson.toJsonTree(rideReportProcess.getPid()));
			responseBody.add(ResponseBodyUtility.NAME_KEY, gson.toJsonTree(rideReportProcess.getReportNameText()));
			responseBody.add(ResponseBodyUtility.REPORT_START_DATE_KEY, gson.toJsonTree(rideReportProcess.getStartDate()));
			responseBody.add(ResponseBodyUtility.REPORT_END_DATE_KEY, gson.toJsonTree(rideReportProcess.getEndDate()));
			responseBody.add(ResponseBodyUtility.RIDE_COUNT_KEY, gson.toJsonTree(rideReportInstance.getSize()));
			
			while(iterator.hasNext()) {
				RideReportDetail rideReportElement = iterator.next();
				JsonObject detailResponseBody = new JsonObject();
				detailResponseBody.add(ResponseBodyUtility.ORIGIN_ZIP_KEY, gson.toJsonTree(rideReportElement.getOriginZip()));
				detailResponseBody.add(ResponseBodyUtility.DESTINATION_ZIP_KEY, gson.toJsonTree(rideReportElement.getDestinationZip()));
				detailResponseBody.add(ResponseBodyUtility.COUNT_KEY, gson.toJsonTree(rideReportElement.getCount()));
				reportDetailResponse.add(detailResponseBody);
			}
			responseBody.add(ResponseBodyUtility.DETAIL_KEY, reportDetailResponse);
			
			return responseBody.toString();
		}
		
}
