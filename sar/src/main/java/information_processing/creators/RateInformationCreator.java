package information_processing.creators;

import com.google.gson.JsonSyntaxException;

import information_processing.boundaries.InformationIDs;
import information_processing.boundaries.InformationIDsCreator;
import information_processing.boundaries.InformationIDsParser;
import information_processing.boundaries.InformationIDsValidation;
import information_processing.entities.EmptyRequestInfo;
import information_processing.entities.RateInformation;
import information_processing.parsers.RateInformationParser;
import information_processing.validation.RateInfoValidation;

public class RateInformationCreator implements InformationIDsCreator{

	@Override
	public InformationIDs create(String body) {
		try {
			InformationIDsParser rateInfoParser = new RateInformationParser();
			RateInformation rateInformation = (RateInformation) rateInfoParser.parse(body);
			InformationIDsValidation rateInfoValidator = new RateInfoValidation(rateInformation);
			rateInfoValidator.validate();
			return rateInformation;
		}catch(IllegalArgumentException iae) {
			return new EmptyRequestInfo(iae.getMessage());
		}catch(NullPointerException npe) {
			return new EmptyRequestInfo(npe.getMessage());
		}catch(JsonSyntaxException jse) {
			return new EmptyRequestInfo(jse.getMessage());
		}
	}

}
