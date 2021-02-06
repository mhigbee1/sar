package information_processing.validation_test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import information_processing.boundaries.InformationIDsValidation;
import information_processing.validation.states.NullReferenceValidation;

class NullReferenceValidationTest {
	
	private InformationIDsValidation nullReferenceValidation;
	private final Object testObject = new Object();
	private final Object nullObject = null;
	private final String ERR_MSG_TEXT = "TESTING: ERROR MESSAGE";
	
	@Test
	void test_validate_passedNull__throwsNoExceptions() {
		nullReferenceValidation = new NullReferenceValidation<Object>(nullObject, ERR_MSG_TEXT);
		assertDoesNotThrow(()-> {
			nullReferenceValidation.validate();
		});
	}
	
	@Test
	void test_validate_passedEmptyObject__throwsIllegalArgumentException() {
		nullReferenceValidation = new NullReferenceValidation<Object>(testObject, ERR_MSG_TEXT);
		assertThrows(IllegalArgumentException.class, () -> {
			nullReferenceValidation.validate();
		});
	}

}
