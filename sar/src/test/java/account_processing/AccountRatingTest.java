package account_processing;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Iterator;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import account_operations.interactors.Account;
import information_processing.entities.RateInformation;
import information_processing.entities.UserInformation;
import rate_processing.entities.Rate;

class AccountRatingTest {
	
	private Account testAccount;
	private static final int AID = 123;
	private static final String FIRST_NAME = "John";
	private static final String LAST_NAME = "Doe";
	private static final String PHONE_NUMBER = "555-555-5555";
	private static final String USER_PIC = "http://example.com/user_pic.jpg";
	private static final boolean ACTIVE_STATUS = true;
	
	private RateInformation testRateInfo;
	private static final String RATER_FIRST_NAME = "Jane";
	private static final String DATE = "26-Dec-2020";
	private static final int RID = 1;
	private static final int SENDER_ID = 345;
	private static final int RATING = 1;
	private static final String COMMENT = "John drives like a mad man";
	
	
	@BeforeEach
	void init() {
		UserInformation userInfo = new UserInformation(FIRST_NAME, LAST_NAME, PHONE_NUMBER, USER_PIC, ACTIVE_STATUS);
		testAccount = new Account(AID, userInfo);
		testRateInfo = new RateInformation(RID,SENDER_ID, RATING, COMMENT);
	}

	@Test
	void test_addDriverRating_getDriverRatingsCounter_returnsOne() {
		testAccount.addDriverRating(testRateInfo, RATER_FIRST_NAME, DATE);
		assertEquals(1, testAccount.getDriverRatingsCounter());
	}
	
	@Test
	void test_addRiderRating_getRiderRatingsCounter_returnsOne() {
		testAccount.addRiderRating(testRateInfo, RATER_FIRST_NAME, DATE);
		assertEquals(1, testAccount.getRiderRatingsCounter());
	}
	
	@Test
	void test_getDriverAvgRating_addOneDriverRatingBeforeTest_returnsRATING() {
		testAccount.addDriverRating(testRateInfo, RATER_FIRST_NAME, DATE);
		assertEquals(RATING, testAccount.getDriverAvgRating());
	}
	
	@Test
	void test_getRiderAvgRating_addOneRiderRatingBeforeTest_returnsRATING() {
		testAccount.addRiderRating(testRateInfo, RATER_FIRST_NAME, DATE);
		assertEquals(RATING, testAccount.getRiderAvgRating());
	}
	
	private boolean verifyTestRateInfo(Rate rate) {
		return(
			RID == rate.getRid() &&
			RATER_FIRST_NAME.equals(rate.getRatedByName()) &&
			SENDER_ID == (rate.getRateByID()) &&
			RATING == (rate.getRating()) &&
			COMMENT.equals(rate.getComment())
			);
	}
	
	@Test
	void test_getDriverRatingIterator_addOneDriverRatingBeforeTest_returnsTrueForVerifyTestRateInfo() {
		testAccount.addDriverRating(testRateInfo, RATER_FIRST_NAME, DATE);
		Iterator<Rate> iterator = testAccount.getDriverRatingIterator();
		Rate rate = null;
		while(iterator.hasNext()) {
			rate = iterator.next();
		}
		assertTrue(verifyTestRateInfo(rate));
	}
	
	@Test
	void test_getRiderRatingIterator_addOneDriverRatingBeforeTest_returnsTrueForVerifyTestRateInfo() {
		testAccount.addRiderRating(testRateInfo, RATER_FIRST_NAME, DATE);
		Iterator<Rate> iterator = testAccount.getRiderRatingIterator();
		Rate rate = null;
		while(iterator.hasNext()) {
			rate = iterator.next();
		}
		assertTrue(verifyTestRateInfo(rate));
	}

}
