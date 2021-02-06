package information_processing.creators;

import com.google.gson.JsonSyntaxException;

import information_processing.boundaries.InformationIDs;
import information_processing.boundaries.InformationIDsCreator;
import information_processing.boundaries.InformationIDsParser;
import information_processing.boundaries.InformationIDsValidation;
import information_processing.entities.EmptyRequestInfo;
import information_processing.entities.RideInformation;
import information_processing.parsers.RideInformationParser;
import information_processing.validation.RideInfoValidation;

public class RideInformationCreator implements InformationIDsCreator{

	@Override
	public InformationIDs create(String body) {
		try {
			InformationIDsParser rideInfoParser = new RideInformationParser();
			RideInformation rideInformation = (RideInformation) rideInfoParser.parse(body);
			InformationIDsValidation rideInfoValidator = new RideInfoValidation(rideInformation);
			rideInfoValidator.validate();
			return rideInformation;
		}catch(IllegalArgumentException iae) {
			return new EmptyRequestInfo(iae.getMessage());
		}catch(NullPointerException npe) {
			return new EmptyRequestInfo(npe.getMessage());
		}catch(JsonSyntaxException jse) {
			return new EmptyRequestInfo(jse.getMessage());
		}
	}

}
