package information_processing.creators;

import com.google.gson.JsonSyntaxException;

import information_processing.boundaries.InformationIDs;
import information_processing.boundaries.InformationIDsCreator;
import information_processing.boundaries.InformationIDsParser;
import information_processing.boundaries.InformationIDsValidation;
import information_processing.entities.EmptyRequestInfo;
import information_processing.entities.RideRequestInformation;
import information_processing.parsers.RideRequestInfoParser;
import information_processing.validation.RideRequestInfoValidation;

public class RideRequestInfoCreator implements InformationIDsCreator{

	@Override
	public InformationIDs create(String body) {
		try {
			InformationIDsParser rideReqInfoParser = new RideRequestInfoParser();
			RideRequestInformation rideRequestInfo = (RideRequestInformation) rideReqInfoParser.parse(body);
			InformationIDsValidation rideRequestInfoValidator = new RideRequestInfoValidation(rideRequestInfo);
			rideRequestInfoValidator.validate();
			return rideRequestInfo;
		}catch(IllegalArgumentException iae) {
			return new EmptyRequestInfo(iae.getMessage());
		}catch(NullPointerException npe) {
			return new EmptyRequestInfo(npe.getMessage());
		}catch(JsonSyntaxException jse) {
			return new EmptyRequestInfo(jse.getMessage());
		}
	}

}
