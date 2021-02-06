package information_processing.validation;

import java.util.ArrayList;
import java.util.List;

import information_processing.boundaries.InformationIDsValidation;
import information_processing.entities.RateInformation;
import information_processing.validation.formats.RatingRangeValidation;
import information_processing.validation.states.NonNullObjectValidation;
import response_generation.boundaries.ResponseBodyUtility;

public class RateInfoValidation implements InformationIDsValidation {
	
	private List<InformationIDsValidation> validators = new ArrayList<InformationIDsValidation>();
	
	public RateInfoValidation(RateInformation rateInformation) {
		validators.add(new NonNullObjectValidation(
				rateInformation.getAid(),
				ResponseBodyUtility.ERR_MSG_INVALID_ACCOUNT_ID));
		validators.add(new NonNullObjectValidation(
				rateInformation.getRid(),
				ResponseBodyUtility.ERR_MSG_INVALID_RIDE_ID));
		validators.add(new NonNullObjectValidation(
				rateInformation.getRating(),
				ResponseBodyUtility.ERR_MSG_INVALID_RATING));
		validators.add(new RatingRangeValidation(
				rateInformation.getRating(),
				ResponseBodyUtility.ERR_MSG_INVALID_RATING));
		validators.add(new NonNullObjectValidation(
				rateInformation.getComment(),
				ResponseBodyUtility.ERR_MSG_INVALID_COMMENT));
	}

	@Override
	public void validate() throws IllegalArgumentException {
		for(InformationIDsValidation validator : validators) {
			validator.validate();
		}
		
	}

}
