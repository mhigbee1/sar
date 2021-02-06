package information_processing.parsers;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.JsonSyntaxException;

import information_processing.boundaries.InformationIDs;
import information_processing.boundaries.InformationIDsParser;
import information_processing.entities.UserInformation;
import response_generation.boundaries.ResponseBodyUtility;

public class UserInformationParser implements InformationIDsParser{

	@Override
	public InformationIDs parse(String body) throws NullPointerException, JsonSyntaxException {
		try {
			JsonObject jsonBody = JsonParser.parseString(body).getAsJsonObject();
			String firstName = jsonBody.get(ResponseBodyUtility.FIRST_NAME_KEY).isJsonNull() ? null : jsonBody.get(ResponseBodyUtility.FIRST_NAME_KEY).getAsString();
			String lastName = jsonBody.get(ResponseBodyUtility.LAST_NAME_KEY).isJsonNull() ? null : jsonBody.get(ResponseBodyUtility.LAST_NAME_KEY).getAsString();
			String phoneNumber = jsonBody.get(ResponseBodyUtility.PHONE_NUMBER_KEY).isJsonNull() ? null : jsonBody.get(ResponseBodyUtility.PHONE_NUMBER_KEY).getAsString();
			String userPic = jsonBody.get(ResponseBodyUtility.USER_PIC_KEY).isJsonNull() ? null : jsonBody.get(ResponseBodyUtility.USER_PIC_KEY).getAsString();
			Boolean activeStatus = jsonBody.get(ResponseBodyUtility.ACTIVE_STATUS_KEY).isJsonNull() ? null : jsonBody.get(ResponseBodyUtility.ACTIVE_STATUS_KEY).getAsBoolean();
			return new UserInformation(firstName, lastName, phoneNumber, userPic, activeStatus);
		}catch(NullPointerException npe) {
			npe.printStackTrace();
			throw new NullPointerException(INCOMPLETE_REQUEST_MESSAGE);
		}catch(JsonSyntaxException jse) {
			jse.printStackTrace();
			throw new JsonSyntaxException(JSON_MESSAGE);
		}
	}

}
