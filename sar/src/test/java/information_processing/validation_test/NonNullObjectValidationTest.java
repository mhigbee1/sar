package information_processing.validation_test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import information_processing.boundaries.InformationIDsValidation;
import information_processing.validation.states.NonNullObjectValidation;


class NonNullObjectValidationTest {
	
	private InformationIDsValidation nonNullObjectValidation;
	private final Object testObject = new Object();
	private final Object nullObject = null;
	private final String ERR_MSG_TEXT = "TESTING: ERROR MESSAGE";
	
	@Test
	void test_validate_passedObject__throwsNoExceptions() {
		nonNullObjectValidation = new NonNullObjectValidation(testObject, ERR_MSG_TEXT);
		assertDoesNotThrow(()-> {
			nonNullObjectValidation.validate();
		});
	}
	
	@Test
	void test_validate_passedNull__throwsIllegalArgumentException() {
		nonNullObjectValidation = new NonNullObjectValidation(nullObject, ERR_MSG_TEXT);
		assertThrows(IllegalArgumentException.class, () -> {
			nonNullObjectValidation.validate();
		});
	}

}
