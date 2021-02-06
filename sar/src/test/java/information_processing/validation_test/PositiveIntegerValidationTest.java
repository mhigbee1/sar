package information_processing.validation_test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import information_processing.boundaries.InformationIDsValidation;
import information_processing.validation.values.PositiveIntegerValidation;

class PositiveIntegerValidationTest {
	
	private InformationIDsValidation positiveIntegerValidation;
	private final int POSITIVE_INT = 1;
	private final int ZERO = 0;
	private final int NEGATIVE_INT = -1;
	private final String ERR_MSG_TEXT = "TESTING: ERROR_MSG";

	@Test
	void test_validate_passedPOSITIVE_INT__throwsNoExceptions(){
		positiveIntegerValidation = new PositiveIntegerValidation(
				POSITIVE_INT, ERR_MSG_TEXT);
		assertDoesNotThrow(() -> {
			positiveIntegerValidation.validate();
		});
	}
	
	@Test
	void test_validate_passedZERO__throwsIllegalArgumentException(){
		positiveIntegerValidation = new PositiveIntegerValidation(
				ZERO, ERR_MSG_TEXT);
		assertThrows(IllegalArgumentException.class, () -> {
			positiveIntegerValidation.validate();
		});
	}
	
	@Test
	void test_validate_passedNEGATIVE_INT__throwsIllegalArgumentException(){
		positiveIntegerValidation = new PositiveIntegerValidation(
				NEGATIVE_INT, ERR_MSG_TEXT);
		assertThrows(IllegalArgumentException.class, () -> {
			positiveIntegerValidation.validate();
		});
	}
	
	

}
