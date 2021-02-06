package information_processing.parsers;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.JsonSyntaxException;

import information_processing.boundaries.ConfirmationType;
import information_processing.boundaries.InformationIDs;
import information_processing.boundaries.InformationIDsParser;
import information_processing.entities.UpdateRideRequestInfo;
import response_generation.boundaries.ResponseBodyUtility;

public class UpdateRideRequestInfoParser implements InformationIDsParser {
	
	private Boolean confirmationStatus;
	private ConfirmationType confirmationType;

	@Override
	public InformationIDs parse(String body) throws NullPointerException, JsonSyntaxException {
		try {
			JsonObject jsonBody = JsonParser.parseString(body).getAsJsonObject();
			Integer aid = jsonBody.get(ResponseBodyUtility.ACCOUNT_ID_KEY).isJsonNull() ? null : jsonBody.get(ResponseBodyUtility.ACCOUNT_ID_KEY).getAsInt();
			parseConfirmationTypeStatus(jsonBody);
			return new UpdateRideRequestInfo(aid, confirmationStatus, confirmationType);
		}catch(NullPointerException npe) {
			npe.printStackTrace();
			throw new NullPointerException(INCOMPLETE_REQUEST_MESSAGE);
		}catch(JsonSyntaxException jse) {
			jse.printStackTrace();
			throw new JsonSyntaxException(JSON_MESSAGE);
		}
	}
	
	private void parseConfirmationTypeStatus(JsonObject jsonBody) {
		if(jsonBody.has(ResponseBodyUtility.RIDE_CONFIRMATION_KEY)) {
			confirmationStatus = jsonBody.get(ResponseBodyUtility.RIDE_CONFIRMATION_KEY).isJsonNull() ? null : jsonBody.get(ResponseBodyUtility.RIDE_CONFIRMATION_KEY).getAsBoolean();
			confirmationType = ConfirmationType.RIDE_CONFIRMED; 
		}
		
		if(jsonBody.has(ResponseBodyUtility.PICKUP_CONFIRMATION_KEY)) {
			confirmationStatus = jsonBody.get(ResponseBodyUtility.PICKUP_CONFIRMATION_KEY).isJsonNull() ? null : jsonBody.get(ResponseBodyUtility.PICKUP_CONFIRMATION_KEY).getAsBoolean();
			confirmationType = ConfirmationType.PICKUP_CONFIRMED; 
		}
	}
}
