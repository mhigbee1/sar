package information_processing.parsers;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.JsonSyntaxException;

import information_processing.boundaries.InformationIDs;
import information_processing.boundaries.InformationIDsParser;
import information_processing.entities.RideInformation;
import response_generation.boundaries.ResponseBodyUtility;

public class RideInformationParser implements InformationIDsParser{
	
	private String originCity;
	private String originZip;
	private String destinationCity;
	private String destinationZip;
	private String rideDate;
	private String rideTime;
	private String vehicleMake;
	private String vehicleModel;
	private String vehicleColor;
	private String vehiclePlateNum;
	private String vehiclePlateState;
	
	
	@Override
	public InformationIDs parse(String body) throws NullPointerException, JsonSyntaxException {
		try {
			JsonObject jsonBody = JsonParser.parseString(body).getAsJsonObject();
			parseLocationBody(jsonBody.get(ResponseBodyUtility.LOCATION_INFO_HEADER_KEY).getAsJsonObject());
			parseDateTimeBody(jsonBody.get(ResponseBodyUtility.DATE_TIME_HEADER_KEY).getAsJsonObject());
			parseVehicleBody(jsonBody.get(ResponseBodyUtility.VEHICLE_INFO_HEADER_KEY).getAsJsonObject());
			
			Integer aid = jsonBody.get(ResponseBodyUtility.ACCOUNT_ID_KEY).isJsonNull() ? null : jsonBody.get(ResponseBodyUtility.ACCOUNT_ID_KEY).getAsInt();
			Integer vehicleCapacity = jsonBody.get(ResponseBodyUtility.VEHICLE_CAPACITY_KEY).isJsonNull() ? null : jsonBody.get(ResponseBodyUtility.VEHICLE_CAPACITY_KEY).getAsInt();
			Double ratePerPassenger = jsonBody.get(ResponseBodyUtility.RATE_PER_PASSENGER_KEY).isJsonNull() ? null : checkRatePerPassengerForZero(jsonBody.get(ResponseBodyUtility.RATE_PER_PASSENGER_KEY).getAsDouble());
			String vehicleRules = jsonBody.get(ResponseBodyUtility.VEHICLE_RULES_KEY).isJsonNull() ? null : jsonBody.get(ResponseBodyUtility.VEHICLE_RULES_KEY).getAsString();
			
			return new RideInformation(
					aid,
					originCity,
					originZip,
					destinationCity,
					destinationZip,
					rideDate,
					rideTime,
					vehicleMake,
					vehicleModel,
					vehicleColor,
					vehiclePlateNum,
					vehiclePlateState,
					vehicleCapacity,
					vehicleRules,
					ratePerPassenger);	
		}catch(NullPointerException npe) {
			npe.printStackTrace();
			throw new NullPointerException(INCOMPLETE_REQUEST_MESSAGE);
		}catch(JsonSyntaxException jse) {
			jse.printStackTrace();
			throw new JsonSyntaxException(JSON_MESSAGE);
		}
	}
	
	
	private void parseLocationBody(JsonObject locationBody) {
		originCity = locationBody.get(ResponseBodyUtility.ORIGIN_CITY_KEY).isJsonNull() ? null : locationBody.get(ResponseBodyUtility.ORIGIN_CITY_KEY).getAsString();
		originZip = locationBody.has(ResponseBodyUtility.ORIGIN_ZIP_KEY) ? locationBody.get(ResponseBodyUtility.ORIGIN_ZIP_KEY).getAsString() : "";
		destinationCity = locationBody.get(ResponseBodyUtility.DESTINATION_CITY_KEY).isJsonNull() ? null : locationBody.get(ResponseBodyUtility.DESTINATION_CITY_KEY).getAsString();
		destinationZip = locationBody.has(ResponseBodyUtility.DESTINATION_ZIP_KEY) ? locationBody.get(ResponseBodyUtility.DESTINATION_ZIP_KEY).getAsString() : "";
	}
	
	private void parseDateTimeBody(JsonObject dateTimeBody) {
		rideDate = dateTimeBody.get(ResponseBodyUtility.RIDE_DATE_KEY).isJsonNull() ? null : dateTimeBody.get(ResponseBodyUtility.RIDE_DATE_KEY).getAsString();
		rideTime = dateTimeBody.get(ResponseBodyUtility.RIDE_TIME_KEY).isJsonNull() ? null : dateTimeBody.get(ResponseBodyUtility.RIDE_TIME_KEY).getAsString();
	}
	
	private void parseVehicleBody(JsonObject vehicleInfoBody) {
		vehicleMake = vehicleInfoBody.get(ResponseBodyUtility.VEHICLE_MAKE_KEY).isJsonNull() ? null : vehicleInfoBody.get(ResponseBodyUtility.VEHICLE_MAKE_KEY).getAsString();
		vehicleModel = vehicleInfoBody.get(ResponseBodyUtility.VEHICLE_MODEL_KEY).isJsonNull() ? null : vehicleInfoBody.get(ResponseBodyUtility.VEHICLE_MODEL_KEY).getAsString();
		vehicleColor = vehicleInfoBody.get(ResponseBodyUtility.VEHICLE_COLOR_KEY).isJsonNull() ? null : vehicleInfoBody.get(ResponseBodyUtility.VEHICLE_COLOR_KEY).getAsString();
		vehiclePlateNum = vehicleInfoBody.get(ResponseBodyUtility.VEHICLE_PLATE_NUM_KEY).isJsonNull() ? null : vehicleInfoBody.get(ResponseBodyUtility.VEHICLE_PLATE_NUM_KEY).getAsString();
		vehiclePlateState = vehicleInfoBody.get(ResponseBodyUtility.VEHICLE_PLATE_STATE_KEY).isJsonNull() ? null : vehicleInfoBody.get(ResponseBodyUtility.VEHICLE_PLATE_STATE_KEY).getAsString();
	}
	
	private Double checkRatePerPassengerForZero(Double rate) {
		if(rate == 0.0) {
			return null;
		}else {
			return rate;
		}
	}

}
