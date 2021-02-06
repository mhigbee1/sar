package information_processing.creators;

import com.google.gson.JsonSyntaxException;

import information_processing.boundaries.InformationIDs;
import information_processing.boundaries.InformationIDsCreator;
import information_processing.boundaries.InformationIDsParser;
import information_processing.boundaries.InformationIDsValidation;
import information_processing.entities.EmptyRequestInfo;
import information_processing.entities.UserMessagingInfo;
import information_processing.parsers.UserMessagingInfoParser;
import information_processing.validation.UserMessagingInfoValidation;

public class UserMessagingInfoCreator implements InformationIDsCreator{

	@Override
	public InformationIDs create(String body) {
		try {
			InformationIDsParser userMessagingInfoParser = new UserMessagingInfoParser();
			UserMessagingInfo userMessagingInfo = (UserMessagingInfo) userMessagingInfoParser.parse(body);
			InformationIDsValidation userMessagingInfoValidator = new UserMessagingInfoValidation(userMessagingInfo);
			userMessagingInfoValidator.validate();
			return userMessagingInfo;
		}catch(IllegalArgumentException iae){
			return new EmptyRequestInfo(iae.getMessage());
		}catch(NullPointerException npe) {
			return new EmptyRequestInfo(npe.getMessage());
		}catch(JsonSyntaxException jse) {
			return new EmptyRequestInfo(jse.getMessage());
		}
	}

}
