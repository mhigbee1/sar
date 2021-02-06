package information_processing.validation_test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import information_processing.boundaries.InformationIDsValidation;
import information_processing.validation.states.NonEmptyStringValidation;

class NonEmptyStringValidationTest {
	
	private InformationIDsValidation nonEmptyStringValidation;
	private final String VALID_STRING = "anything";
	private final String INVALID_STRING = "";
	private final String ERR_MSG_TEXT = "TESTING: ERROR MSG";

	@Test
	void test_validate_passedVALID_STRING__throwsNoExceptions() {
		nonEmptyStringValidation = new NonEmptyStringValidation(VALID_STRING, ERR_MSG_TEXT);
		assertDoesNotThrow(()->{
			nonEmptyStringValidation.validate();
		});
	}
	
	
	@Test
	void test_validate_passedINVALID_STRING__throwsIllegalArgumentException() {
		nonEmptyStringValidation = new NonEmptyStringValidation(INVALID_STRING, ERR_MSG_TEXT);
		assertThrows(IllegalArgumentException.class, ()->{
			nonEmptyStringValidation.validate();
		});
	}

}
