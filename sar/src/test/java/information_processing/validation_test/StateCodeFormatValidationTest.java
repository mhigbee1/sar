package information_processing.validation_test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import information_processing.boundaries.InformationIDsValidation;
import information_processing.validation.formats.StateCodeFormatValidation;

class StateCodeFormatValidationTest {
	
	private InformationIDsValidation stateCodeFormatValidation;
	private final String VALID_STATE_CODE = "AZ";
	private final String INVALID_STATE_CODE = "XX";
	private final String INVALID_FORMAT = "ARM";
	private final String ERR_MSG_TEXT = "TESTING: ERROR_MSG";

	@Test
	void test_validate_passedVALID_STATE_CODE__throwsNoExceptions() {
		stateCodeFormatValidation = new StateCodeFormatValidation(VALID_STATE_CODE, ERR_MSG_TEXT);
		assertDoesNotThrow(()->{
			stateCodeFormatValidation.validate();
		});
	}
	
	@Test
	void test_validate_passedINVALID_STATE_CODE__throwsIllegalArgumentException() {
		stateCodeFormatValidation = new StateCodeFormatValidation(INVALID_STATE_CODE, ERR_MSG_TEXT);
		assertThrows(IllegalArgumentException.class, ()->{
			stateCodeFormatValidation.validate();
		});
	}
	
	@Test
	void test_validate_passedINVALID_FORMAT__throwsIllegalArgumentException() {
		stateCodeFormatValidation = new StateCodeFormatValidation(INVALID_FORMAT, ERR_MSG_TEXT);
		assertThrows(IllegalArgumentException.class, ()->{
			stateCodeFormatValidation.validate();
		});
	}

}
