package information_processing.validation_test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import information_processing.boundaries.InformationIDsValidation;
import information_processing.validation.formats.RatingRangeValidation;

class RatingRangeValidationTest {
	
	private InformationIDsValidation ratingRangeValidation;
	private final String ERR_MSG_TEXT = "TESTING: ERROR MSG";

	@Test
	void test_validate_passedRatingOfONE_throwsNoExceptions() {
		ratingRangeValidation = new RatingRangeValidation(1, ERR_MSG_TEXT);
		assertDoesNotThrow(()->{
			ratingRangeValidation.validate();
		});
	}
	
	@Test
	void test_validate_passedRatingOfFIVE_throwsNoExceptions() {
		ratingRangeValidation = new RatingRangeValidation(5, ERR_MSG_TEXT);
		assertDoesNotThrow(()->{
			ratingRangeValidation.validate();
		});
	}
	
	@Test
	void test_validate_passedRatingOfZERO_throwsIllegalArgumentExceptions() {
		ratingRangeValidation = new RatingRangeValidation(0, ERR_MSG_TEXT);
		assertThrows(IllegalArgumentException.class, ()->{
			ratingRangeValidation.validate();
		});
	}
	
	@Test
	void test_validate_passedRatingOfSIX_throwsIllegalArgumentExceptions() {
		ratingRangeValidation = new RatingRangeValidation(6, ERR_MSG_TEXT);
		assertThrows(IllegalArgumentException.class, ()->{
			ratingRangeValidation.validate();
		});
	}

}
