package information_processing.parsers;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.JsonSyntaxException;

import information_processing.boundaries.InformationIDs;
import information_processing.boundaries.InformationIDsParser;
import information_processing.entities.RideRequestInformation;
import response_generation.boundaries.ResponseBodyUtility;

public class RideRequestInfoParser implements InformationIDsParser {

	@Override
	public InformationIDs parse(String body) throws NullPointerException, JsonSyntaxException {
		try {
			JsonObject jsonBody = JsonParser.parseString(body).getAsJsonObject();
			Integer aid = jsonBody.get(ResponseBodyUtility.ACCOUNT_ID_KEY).isJsonNull() ? null : jsonBody.get(ResponseBodyUtility.ACCOUNT_ID_KEY).getAsInt();
			Integer passengers = jsonBody.get(ResponseBodyUtility.PASSENGERS_KEY).isJsonNull() ? null : jsonBody.get(ResponseBodyUtility.PASSENGERS_KEY).getAsInt();
			Boolean rideConfirmation = jsonBody.get(ResponseBodyUtility.RIDE_CONFIRMATION_KEY).isJsonNull() ? null : jsonBody.get(ResponseBodyUtility.RIDE_CONFIRMATION_KEY).getAsBoolean();
			Boolean pickUpConfirmation = jsonBody.get(ResponseBodyUtility.PICKUP_CONFIRMATION_KEY).isJsonNull() ? null : jsonBody.get(ResponseBodyUtility.PICKUP_CONFIRMATION_KEY).getAsBoolean();
			return new RideRequestInformation(aid, passengers, rideConfirmation, pickUpConfirmation);
		}catch(NullPointerException npe) {
			npe.printStackTrace();
			throw new NullPointerException(INCOMPLETE_REQUEST_MESSAGE);
		}catch(JsonSyntaxException jse) {
			jse.printStackTrace();
			throw new JsonSyntaxException(JSON_MESSAGE);
		}
	}
}
