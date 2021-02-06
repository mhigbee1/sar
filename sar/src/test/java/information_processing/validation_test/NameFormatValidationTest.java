package information_processing.validation_test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import information_processing.boundaries.InformationIDsValidation;
import information_processing.validation.formats.NameFormatValidation;

class NameFormatValidationTest {
	
	private InformationIDsValidation nameFormatValidation;
	private final String VALID_NAME_FORMAT_ALPHAS = "Michael";
	private final String VALID_NAME_FORMAT_HYPHEN = "Gomez-Hughes";
	private final String INVALID_NAME_FORMAT_NUM = "123";
	private final String ERR_MSG_TEXT = "TESTING: ERROR_MSG";

	@Test
	void test_validate_passed_VALID_NAME_FORMAT_ALPHAS__throwsNoExceptions(){
			nameFormatValidation = new NameFormatValidation(VALID_NAME_FORMAT_ALPHAS, ERR_MSG_TEXT);
			assertDoesNotThrow(()-> {
				nameFormatValidation.validate();
			});
	}
	
	@Test
	void test_validate_passed_VALID_NAME_FORMAT_HYPHEN__throwsNoExceptions(){
			nameFormatValidation = new NameFormatValidation(VALID_NAME_FORMAT_HYPHEN, ERR_MSG_TEXT);
			assertDoesNotThrow(()-> {
				nameFormatValidation.validate();
			});
	}
	
	@Test
	void test_validate_passed_INVALID_NAME_FORMAT_NUM__throwsIllegalArgumentException(){
			nameFormatValidation = new NameFormatValidation(INVALID_NAME_FORMAT_NUM, ERR_MSG_TEXT);
			assertThrows(IllegalArgumentException.class, ()-> {
				nameFormatValidation.validate();
			});
	}
	
}
