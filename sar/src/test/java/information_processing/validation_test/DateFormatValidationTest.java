package information_processing.validation_test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import information_processing.boundaries.InformationIDsValidation;
import information_processing.validation.formats.DateFormatValidation;

class DateFormatValidationTest {
	
	private InformationIDsValidation dateFormatValidation;
	private final String VALID_DATE = "26-Dec-2020";
	private final String INVALID_DATE = "31-Sep-2020";
	private final String INVALID_DATE_FORMAT = "12-26-2020";
	private final String ERR_MSG_TEXT = "TESTING: ERROR_MSG";


	@Test
	void test_validate_passedVALID_DATE_returnsNoExceptions() {
		dateFormatValidation = new DateFormatValidation(VALID_DATE, ERR_MSG_TEXT);
		assertDoesNotThrow(()-> {
			dateFormatValidation.validate();
		});
	}

	@Test
	void test_validate_passedINVALID_DATE_returnsIllegalArgumentException() {
		dateFormatValidation = new DateFormatValidation(INVALID_DATE, ERR_MSG_TEXT);
		assertThrows(IllegalArgumentException.class, ()->{
			dateFormatValidation.validate();
		});
	}
	
	@Test
	void test_validate_passedINVALID_DATE_FORMAT_returnsIllegalArgumentException() {
		dateFormatValidation = new DateFormatValidation(INVALID_DATE_FORMAT, ERR_MSG_TEXT);
		assertThrows(IllegalArgumentException.class, () -> {
			dateFormatValidation.validate();
		});
	}
	
}
