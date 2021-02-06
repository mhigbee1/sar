package information_processing.validation.formats;

import information_processing.boundaries.InformationIDsValidation;

public class RatingRangeValidation implements InformationIDsValidation{
	
	private Integer ratingValue;
	private String errorMsg;
	private static final int RANGE_MAX = 5;
	private static final int RANGE_MIN = 1;
	
	public RatingRangeValidation(Integer mRatingValue, String mErrorMsg) {
		this.ratingValue = mRatingValue;
		this.errorMsg = mErrorMsg;
	}

	@Override
	public void validate() throws IllegalArgumentException {
		if(ratingValue < RANGE_MIN || ratingValue > RANGE_MAX) {
			throw new IllegalArgumentException(errorMsg);
		}
		
	}

}
