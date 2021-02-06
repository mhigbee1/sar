package information_processing.parsers;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.JsonSyntaxException;

import information_processing.boundaries.InformationIDs;
import information_processing.boundaries.InformationIDsParser;
import information_processing.entities.UserMessagingInfo;
import response_generation.boundaries.ResponseBodyUtility;

public class UserMessagingInfoParser implements InformationIDsParser {
	

	@Override
	public InformationIDs parse(String body) throws NullPointerException, JsonSyntaxException {
		try {
			JsonObject jsonBody = JsonParser.parseString(body).getAsJsonObject();
			Integer aid = jsonBody.get(ResponseBodyUtility.ACCOUNT_ID_KEY).isJsonNull() ? null : jsonBody.get(ResponseBodyUtility.ACCOUNT_ID_KEY).getAsInt();
			String message = jsonBody.get(ResponseBodyUtility.MSG_TEXT_KEY).isJsonNull() ? null : jsonBody.get(ResponseBodyUtility.MSG_TEXT_KEY).getAsString();
			
			return new UserMessagingInfo(aid, message);
			
		}catch(NullPointerException npe) {
			npe.printStackTrace();
			throw new NullPointerException(INCOMPLETE_REQUEST_MESSAGE);
		}catch(JsonSyntaxException jse) {
			jse.printStackTrace();
			throw new JsonSyntaxException(JSON_MESSAGE);
		}
	}
}
