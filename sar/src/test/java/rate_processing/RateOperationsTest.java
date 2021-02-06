package rate_processing;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Iterator;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import information_processing.entities.RateInformation;
import rate_processing.entities.Rate;
import rate_processing.interactors.RatingOperations;

class RateOperationsTest {
	
	private RatingOperations ratingOperator;
	private RateInformation testRateInfo;
	private static final String RATER_FIRST_NAME = "Mary";
	private static final String DATE = "27-Dec-2020";
	private static final int RID = 1;
	private static final int SENDER_ID = 345;
	private static final int RATING = 3;
	private static final String COMMENT = "Mary has great playlists";
	
	@BeforeEach
	void init() {
		ratingOperator = new RatingOperations();
	}
	

	@Test
	void test_addRating_returnsIteratorWithSID() {
		testRateInfo = new RateInformation(RID,SENDER_ID, RATING, COMMENT);
		int sid = ratingOperator.addRating(testRateInfo, RATER_FIRST_NAME, DATE);
		Iterator<Rate> iterator = ratingOperator.getRatingIterator();
		Rate rateInstance = null;
		while(iterator.hasNext()) {
			rateInstance = iterator.next();
		}
		assertEquals(sid, rateInstance.getSid());
	}
	
	@Test
	void test_getAvgRating_returnsDefaultNull() {
		assertNull(ratingOperator.getAvgRating());
	}
	
	@Test
	void test_getAvgRating_addOneRatingBeforeTest_returnsRATING() {
		testRateInfo = new RateInformation(RID, SENDER_ID, RATING, COMMENT);
		ratingOperator.addRating(testRateInfo, RATER_FIRST_NAME, DATE);
		assertEquals(RATING, ratingOperator.getAvgRating());
	}
	
	@Test
	void test_getAvgRating_addTwoRatingBeforeTest_returnsMatchingEXPECTED_AVG() {
		int SECOND_RATING = 4;
		double EXPECTED_AVG = (RATING + SECOND_RATING)/2.0;
		
		testRateInfo = new RateInformation(RID, SENDER_ID, RATING, COMMENT);
		RateInformation testRateInfoTWO = new RateInformation(RID, SENDER_ID, SECOND_RATING, COMMENT);
		ratingOperator.addRating(testRateInfo, RATER_FIRST_NAME, DATE);
		ratingOperator.addRating(testRateInfoTWO, RATER_FIRST_NAME, DATE);
		assertEquals(EXPECTED_AVG, ratingOperator.getAvgRating());
	}
	
	@Test
	void test_advanceRideCounter_returnsOne() {
		ratingOperator.advanceRideCounter();
		assertEquals(1, ratingOperator.getRideCounter());
	}
	
	@Test
	void test_getRideCounter_returnsDefaultOfZero() {
		assertEquals(0, ratingOperator.getRideCounter());
	}
	
	@Test
	void test_getRatingsCounter_returnsDefaultOfZero() {
		assertEquals(0, ratingOperator.getRatingsCounter());
	}
	
	@Test
	void test_getRatingIterator_returnsDefaultEmptyIterator() {
		assertFalse(ratingOperator.getRatingIterator().hasNext());
	}
	
	@Test
	void test_getRatingIterator_addOneDriverRatingBeforeTest_returnsTrueForVerifyTestRateInfo() {
		testRateInfo = new RateInformation(RID, SENDER_ID, RATING, COMMENT);
		ratingOperator.addRating(testRateInfo, RATER_FIRST_NAME, DATE);
		Iterator<Rate> iterator = ratingOperator.getRatingIterator();
		Rate rate = null;
		while(iterator.hasNext()) {
			rate = iterator.next();
		}
		assertTrue(verifyTestRateInfo(rate));
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
	

}
