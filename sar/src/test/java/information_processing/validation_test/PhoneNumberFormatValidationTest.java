package information_processing.validation_test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import information_processing.boundaries.InformationIDsValidation;
import information_processing.validation.formats.PhoneNumberFormatValidation;

class PhoneNumberFormatValidationTest {
	
	private InformationIDsValidation phoneNumberFormatValidation;
	private final String VALID_PHONE_NUM = "555-555-5555";
	private final String INVALID_PHONE_NUM_NO_DASHES = "5555555555";
	private final String INVALID_PHONE_NUM_EXTRA_DIGITS = "555-555-55555";
	private final String INVALID_PHONE_NUM_MISPLACED_DASHES = "55-5555-555";
	private final String INVALID_PHONE_NUM_MISSING_DIGITS = "555-555-555";
	private final String INVALID_PHONE_NUM_HAS_ALPHAS = "555-5a5-5555";
	private final String ERR_MSG_TEXT = "TESTING: ERROR MSG";

	@Test
	void test_validate_passedVALID_PHONE_NUM__throwsNoExceptions() {
		phoneNumberFormatValidation = new PhoneNumberFormatValidation(VALID_PHONE_NUM, ERR_MSG_TEXT);
		assertDoesNotThrow(()->{
			phoneNumberFormatValidation.validate();
		});
	}
	
	@Test
	void test_validate_passedINVALID_PHONE_NUM_NO_DASHES__throwsIllegalArgumentException() {
		phoneNumberFormatValidation = new PhoneNumberFormatValidation(INVALID_PHONE_NUM_NO_DASHES, ERR_MSG_TEXT);
		assertThrows(IllegalArgumentException.class, ()->{
			phoneNumberFormatValidation.validate();
		});
	}
	
	@Test
	void test_validate_passedINVALID_PHONE_NUM_EXTRA_DIGITS__throwsIllegalArgumentException() {
		phoneNumberFormatValidation = new PhoneNumberFormatValidation(INVALID_PHONE_NUM_EXTRA_DIGITS, ERR_MSG_TEXT);
		assertThrows(IllegalArgumentException.class, ()->{
			phoneNumberFormatValidation.validate();
		});
	}
	
	@Test
	void test_validate_passedINVALID_PHONE_NUM_MISPLACED_DASHES__throwsIllegalArgumentException() {
		phoneNumberFormatValidation = new PhoneNumberFormatValidation(INVALID_PHONE_NUM_MISPLACED_DASHES, ERR_MSG_TEXT);
		assertThrows(IllegalArgumentException.class, ()->{
			phoneNumberFormatValidation.validate();
		});
	}
	
	@Test
	void test_validate_passedINVALID_PHONE_NUM_MISSING_DIGITS__throwsIllegalArgumentException() {
		phoneNumberFormatValidation = new PhoneNumberFormatValidation(INVALID_PHONE_NUM_MISSING_DIGITS, ERR_MSG_TEXT);
		assertThrows(IllegalArgumentException.class, ()->{
			phoneNumberFormatValidation.validate();
		});
	}
	
	@Test
	void test_validate_passedINVALID_PHONE_NUM_HAS_ALPHAS__throwsIllegalArgumentException() {
		phoneNumberFormatValidation = new PhoneNumberFormatValidation(INVALID_PHONE_NUM_HAS_ALPHAS, ERR_MSG_TEXT);
		assertThrows(IllegalArgumentException.class, ()->{
			phoneNumberFormatValidation.validate();
		});
	}
	
	

}
