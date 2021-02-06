package information_processing.validation_test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import information_processing.boundaries.InformationIDsValidation;
import information_processing.validation.values.NonNegativeDoublesValidation;

class NonNegativeDoubleValidationTest {
	
	private InformationIDsValidation nonNegativeDoublesValidation;
	private final double POSITIVE_DOUBLE = 1.5;
	private final double ZERO = 0;
	private final double NEGATIVE_DOUBLE = -1.1;
	private final String ERR_MSG_TEXT = "TESTING: ERROR_MSG";

	@Test
	void test_validate_passedPOSITIVE_DOUBLE__throwsNoExceptions() {
		nonNegativeDoublesValidation = new NonNegativeDoublesValidation(POSITIVE_DOUBLE, ERR_MSG_TEXT);
		assertDoesNotThrow(()->{
			nonNegativeDoublesValidation.validate();
		});
	}
	
	@Test
	void test_validate_passedZERO__throwsNoExceptions() {
		nonNegativeDoublesValidation = new NonNegativeDoublesValidation(ZERO, ERR_MSG_TEXT);
		assertDoesNotThrow(()->{
			nonNegativeDoublesValidation.validate();
		});
	}
	
	@Test 
	void test_validate_passedNEGATIVE_DOUBLE__throwsIllegalArgumentException(){
		nonNegativeDoublesValidation = new NonNegativeDoublesValidation(NEGATIVE_DOUBLE,ERR_MSG_TEXT);
		assertThrows(IllegalArgumentException.class, ()->{
			nonNegativeDoublesValidation.validate();
		});
	}

}
