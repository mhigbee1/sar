package information_processing.validation_test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import information_processing.boundaries.InformationIDsValidation;
import information_processing.validation.states.ActiveStatusValidation;

class ActiveStatusValidationTest {
	
	private InformationIDsValidation activeStatusValidation;
	private static final String ERR_MSG_TEXT = "TESTING: ERROR MESSAGE";
	
	@Test
	void test_validate_matchingStatuses_returnsNoExceptions() {
		activeStatusValidation = new ActiveStatusValidation(true, true, ERR_MSG_TEXT);
		assertDoesNotThrow(()-> {
			activeStatusValidation.validate();
		});
	}

	@Test
	void test_validate_unmatchedStatuses_returnsIllegalArgumentException() {
		activeStatusValidation = new ActiveStatusValidation(true, false, ERR_MSG_TEXT);
		assertThrows(IllegalArgumentException.class, () -> {
			activeStatusValidation.validate();
		});
	}

}
