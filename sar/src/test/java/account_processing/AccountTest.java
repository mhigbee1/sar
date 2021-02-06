package account_processing;

import static org.junit.jupiter.api.Assertions.*;

import java.time.format.DateTimeFormatter;
import java.util.Iterator;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import account_operations.interactors.Account;
import information_processing.entities.UserInformation;
import rate_processing.entities.Rate;
import response_generation.boundaries.DateTimeFormatUtility;

class AccountTest {
	private Account testAccount;
	private static final int AID = 123;
	private static final String FIRST_NAME = "Jane";
	private static final String LAST_NAME = "Doe";
	private static final String PHONE_NUMBER = "555-555-5555";
	private static final String USER_PIC = "http://example.com/userPic.jpg";
	private static final boolean ACTIVE_STATUS = false;
	
	@BeforeEach
	void init() {
		UserInformation userInfo = new UserInformation(FIRST_NAME, LAST_NAME, PHONE_NUMBER, USER_PIC, ACTIVE_STATUS);
		testAccount = new Account(AID, userInfo);
	}

	@Test
	void test_getAid_returnsAID() {
		assertEquals(AID, testAccount.getAid());
	}
	
	@Test
	void test_getFirstName_returnsFIRST_NAME() {
		assertEquals(FIRST_NAME, testAccount.getFirstName());
	}
	
	@Test
	void test_getLastName_returnsLAST_NAME() {
		assertEquals(LAST_NAME, testAccount.getLastName());
	}
	
	@Test
	void test_getPhoneNumber_returnsPHONE_NUMBER() {
		assertEquals(PHONE_NUMBER, testAccount.getPhoneNumber());
	}
	
	@Test
	void test_getUserPic_returnsUSER_PIC() {
		assertEquals(USER_PIC, testAccount.getUserPic());
	}
	
	@Test
	void test_getCreatedOnDate_returnsWithCorrectDateFormat() {
		DateTimeFormatter dateFormat = DateTimeFormatUtility.FORMATTED_DATETIME;
		assertDoesNotThrow(()->{
			dateFormat.parse(testAccount.getCreatedOnDate());
		});
	}
	
	@Test
	void test_isActive_returnsACTIVE_STATUS() {
		assertFalse(testAccount.isActive());
	}
	
	@Test
	void test_getDriverRideCounter_default_returnsZero() {
		assertEquals(0, testAccount.getDriverRideCounter());
	}
	
	@Test
	void test_getRiderRideCounter_default_returnsZero() {
		assertEquals(0, testAccount.getRiderRideCounter());
	}
	
	@Test
	void test_getDriverAvgRating_default_returnsNULL() {
		assertNull(testAccount.getDriverAvgRating());
	}
	
	@Test
	void test_getRiderAvgRating_default_returnsNULL() {
		assertNull(testAccount.getRiderAvgRating());
	}
	
	@Test
	void test_getDriverRatingIterator_defaultEmpty_returnsFALSEforHasNext() {
		Iterator<Rate> iterator = testAccount.getDriverRatingIterator();
		assertFalse(iterator.hasNext());
	}
	
	@Test
	void test_getRiderRatingIterator_defaultEmpty_returnsFALSEForHasNext() {
		Iterator<Rate> iterator = testAccount.getRiderRatingIterator();
		assertFalse(iterator.hasNext());
	}
	
	@Test
	void test_getDriverRatingsCounter_default_returnsZero() {
		assertEquals(0, testAccount.getDriverRatingsCounter());
	}
	
	
	@Test
	void test_setDriverRideCounter_incrementsDriverRideCounterOnce_returns1() {
		testAccount.setDriverRideCounter();
		assertEquals(1, testAccount.getDriverRideCounter());
	}
	
	@Test
	void test_setRiderRideCounter_incrementRiderRideCounterOnce_return1() {
		testAccount.setRiderRideCounter();
		assertEquals(1, testAccount.getRiderRideCounter());
	}
	
	@Test
	void test_updateUserInfo_activateAccount_returnsIsActiveTRUE() {
		UserInformation newUserInfo = new UserInformation(FIRST_NAME, LAST_NAME,PHONE_NUMBER, USER_PIC, true);
		testAccount.updateUserInfo(newUserInfo);
		assertTrue(testAccount.isActive());
	}
	
	@Test
	void test_matchesFilter_keyIsFIRST_NAME_returnsTrue() {
		assertTrue(testAccount.matchesFilter(FIRST_NAME));
	}
	
	@Test
	void test_matchesFilter_keyIsLAST_NAME_returnsTrue() {
		assertTrue(testAccount.matchesFilter(LAST_NAME));
	}
	
	@Test
	void test_matchesFilter_keyIsPHONE_NUMBER_returnsTrue() {
		assertTrue(testAccount.matchesFilter(PHONE_NUMBER));
	}
	
	@Test
	void test_matchesFilter_keyDoesNotMatch_returnsFALSE() {
		String testStr = "NOMATCH";
		assertFalse(testAccount.matchesFilter(testStr));
	}

}
