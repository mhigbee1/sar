package information_processing.parsers;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.JsonSyntaxException;

import information_processing.boundaries.InformationIDs;
import information_processing.boundaries.InformationIDsParser;
import information_processing.entities.RateInformation;
import response_generation.boundaries.ResponseBodyUtility;

public class RateInformationParser implements InformationIDsParser {

	@Override
	public InformationIDs parse(String body) throws NullPointerException, JsonSyntaxException {
		try {
			JsonObject jsonBody = JsonParser.parseString(body).getAsJsonObject();
			Integer rid = jsonBody.get(ResponseBodyUtility.RIDE_ID_KEY).isJsonNull() ? null : jsonBody.get(ResponseBodyUtility.RIDE_ID_KEY).getAsInt();
			
			Integer senderID = jsonBody.get(ResponseBodyUtility.SENT_BY_ID_KEY).isJsonNull() ? null : jsonBody.get(ResponseBodyUtility.SENT_BY_ID_KEY).getAsInt();
			
			Integer rating = jsonBody.get(ResponseBodyUtility.RATING_KEY).isJsonNull() ? null : jsonBody.get(ResponseBodyUtility.RATING_KEY).getAsInt();
			
			String comment = jsonBody.get(ResponseBodyUtility.COMMENT_KEY).isJsonNull() ? null : jsonBody.get(ResponseBodyUtility.COMMENT_KEY).getAsString();
			
			return new RateInformation(rid, senderID, rating, comment);
		}catch(NullPointerException npe) {
			npe.printStackTrace();
			throw new NullPointerException(INCOMPLETE_REQUEST_MESSAGE);
		}catch (JsonSyntaxException jse) {
			jse.printStackTrace();
			throw new JsonSyntaxException(JSON_MESSAGE);
		}
	}

}
