package response_generation;

import static org.junit.jupiter.api.Assertions.*;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.TimeZone;

import org.junit.jupiter.api.Test;

import response_generation.boundaries.DateTimeFormatUtility;

class DateTimeFormatUtilityTest {

	@Test
	void test_FORMATTED_DATETIME_returnsValidDateString() {
		String createdOnDate = "23-Dec-2020, 09:30:25";
		assertDoesNotThrow(()-> {
			DateTimeFormatter formatter = DateTimeFormatUtility.FORMATTED_DATETIME;
			LocalDateTime tester = LocalDateTime.parse(createdOnDate, formatter);
			ZonedDateTime zdtTester = tester.atZone(TimeZone.getDefault().toZoneId());
			Instant timeStamp = zdtTester.toInstant();
			
			assertEquals(createdOnDate, DateTimeFormatUtility.getDefaultFormat(timeStamp));
		});
	}
	
	
	@Test
	void test_FORMATTED_TIME_returnsValidDateString() {
		String createdOnTime = "09:30";
		String createdOnDate = "23-Dec-2020, 09:30:25";
		assertDoesNotThrow(()-> {
			DateTimeFormatter formatter = DateTimeFormatUtility.FORMATTED_DATETIME;
			LocalDateTime tester = LocalDateTime.parse(createdOnDate, formatter);
			ZonedDateTime zdtTester = tester.atZone(TimeZone.getDefault().toZoneId());
			Instant timeStamp = zdtTester.toInstant();
			
			assertEquals(createdOnTime, DateTimeFormatUtility.getTimeFormatInst(timeStamp));
		});
	}
	
	@Test
	void test_FORMATTED_DATE_NEW_returnsValidDateString() {
		String createdOnDate = "23-Dec-2020";
		String createdOnTimeStamp = "23-Dec-2020, 09:30:25";
		DateTimeFormatter formatter = DateTimeFormatUtility.FORMATTED_DATETIME;
		assertDoesNotThrow(()-> {
				LocalDateTime tester = LocalDateTime.parse(createdOnTimeStamp, formatter);
				ZonedDateTime zdtTester = tester.atZone(TimeZone.getDefault().toZoneId());
				Instant timeStamp = zdtTester.toInstant();
			assertEquals(createdOnDate, DateTimeFormatUtility.getDateFormat(timeStamp));
		});
	}
	
	
	
	
	@Test
	void test_extractTimeStampFromDateFormat_returnsValidTimeStamp() {
		String createdOnDate = "23-Dec-2020";
		long expectedTimeStamp = 1608703200;
		assertDoesNotThrow(()-> {
			long timestamp = DateTimeFormatUtility.extractTimeStampFromDateFormat(createdOnDate);
			assertEquals(expectedTimeStamp, timestamp);
		});
	}
}
