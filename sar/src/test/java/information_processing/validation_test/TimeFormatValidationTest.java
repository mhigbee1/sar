package information_processing.validation_test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import information_processing.boundaries.InformationIDsValidation;
import information_processing.validation.formats.TimeFormatValidation;

class TimeFormatValidationTest {
	
	private InformationIDsValidation timeFormatValidation;
	private final String VALID_TIME = "12:34";
	private final String INVALID_TIME = "43:21";
	private final String ERR_MSG_TEXT = "TESTING: ERROR MSG";

	@Test
	void test_validate_passedVALID_TIME__throwsNoExceptions() {
		timeFormatValidation = new TimeFormatValidation(VALID_TIME, ERR_MSG_TEXT);
		assertDoesNotThrow(()->{
			timeFormatValidation.validate();
		});
	}
	
	
	@Test
	void test_validate_passedINVALID_TIME__throwsIllegalArgumentException() {
		timeFormatValidation = new TimeFormatValidation(INVALID_TIME, ERR_MSG_TEXT);
		assertThrows(IllegalArgumentException.class,()->{
			timeFormatValidation.validate();
		});
	}

}
