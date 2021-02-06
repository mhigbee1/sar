package information_processing.creators;

import com.google.gson.JsonSyntaxException;

import information_processing.boundaries.InformationIDsCreator;
import information_processing.boundaries.InformationIDs;
import information_processing.boundaries.InformationIDsParser;
import information_processing.boundaries.InformationIDsValidation;
import information_processing.entities.EmptyRequestInfo;
import information_processing.entities.UserInformation;
import information_processing.parsers.UserInformationParser;
import information_processing.validation.UserInfoValidation;

public class UserInformationCreator implements InformationIDsCreator{
	
	private final boolean systemActiveStatus;
	
	public static UserInformationCreator NEW_ACCOUNT() {
		return new UserInformationCreator(false);
	}
	
	public static UserInformationCreator ACTIVE_ACCOUNT() {
		return new UserInformationCreator(true);
	}
	
	public UserInformationCreator(boolean systemActiveStatus) {
		this.systemActiveStatus = systemActiveStatus;
	}
	
	@Override
	public InformationIDs create(String body) {
		try {
			InformationIDsParser userInfoParser = new UserInformationParser();
			UserInformation userInformation = (UserInformation) userInfoParser.parse(body);
			InformationIDsValidation userInfoValidator = new UserInfoValidation(userInformation, systemActiveStatus);
			userInfoValidator.validate();
			return userInformation;
		}catch(IllegalArgumentException iae){
			return new EmptyRequestInfo(iae.getMessage());
		}catch(NullPointerException npe) {
			return new EmptyRequestInfo(npe.getMessage());
		}catch(JsonSyntaxException jse) {
			return new EmptyRequestInfo(jse.getMessage());
		}
	}
}
