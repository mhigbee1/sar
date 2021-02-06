package information_processing.creators;

import com.google.gson.JsonSyntaxException;

import information_processing.boundaries.InformationIDs;
import information_processing.boundaries.InformationIDsCreator;
import information_processing.boundaries.InformationIDsParser;
import information_processing.boundaries.InformationIDsValidation;
import information_processing.entities.EmptyRequestInfo;
import information_processing.entities.UpdateRideRequestInfo;
import information_processing.parsers.UpdateRideRequestInfoParser;
import information_processing.validation.UpdateRideRequestInfoValidation;

public class UpdateRideRequestInfoCreator implements InformationIDsCreator{

	@Override
	public InformationIDs create(String body) {
		try {
			InformationIDsParser updateRideReqInfoParser = new UpdateRideRequestInfoParser();
			UpdateRideRequestInfo updateRideRequestInfo = (UpdateRideRequestInfo) updateRideReqInfoParser.parse(body);
			InformationIDsValidation updateRideRequestInfoValidator = new UpdateRideRequestInfoValidation(updateRideRequestInfo);
			updateRideRequestInfoValidator.validate();
			return updateRideRequestInfo;
		}catch(IllegalArgumentException iae) {
			return new EmptyRequestInfo(iae.getMessage());
		}catch(NullPointerException npe) {
			return new EmptyRequestInfo(npe.getMessage());
		}catch(JsonSyntaxException jse) {
			return new EmptyRequestInfo(jse.getMessage());
		}
	}

}
