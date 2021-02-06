package information_processing.validation_test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import information_processing.boundaries.InformationIDsValidation;
import information_processing.validation.values.TrueBooleanValidation;

class TrueBooleanValidationTest {
	
	private InformationIDsValidation trueBooleanValidation;
	private final boolean TRUE_VALUE = true;
	private final boolean FALSE_VALUE = false;
	private final String ERR_MSG_TEXT = "TESTING: ERROR MSG";

	@Test
	void test_validate_passedTRUE_VALUE_throwsNoExceptions() {
		trueBooleanValidation = new TrueBooleanValidation(TRUE_VALUE, ERR_MSG_TEXT);
		assertDoesNotThrow(()->{
			trueBooleanValidation.validate();
		});
	}
	
	@Test
	void test_validate_passedFALSE_VALUE__throwsIllegalArgumentException() {
		trueBooleanValidation = new TrueBooleanValidation(FALSE_VALUE, ERR_MSG_TEXT);
		assertThrows(IllegalArgumentException.class, ()->{
			trueBooleanValidation.validate();
		});
	}

}
