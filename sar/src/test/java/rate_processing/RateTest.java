package rate_processing;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import information_processing.entities.RateInformation;
import rate_processing.entities.Rate;

class RateTest {
	
	private Rate rate;
	private static final int SID = 123;
	private static final int RID = 456;
	private static final int SENDER_ID = 789;
	private static final String RATED_BY_NAME = "Jane";
	private static final int RATING = 4;
	private static final String COMMENT = "Kind and helpful";
	private static final String DATE = "23-Dec-2020";
	
	@BeforeEach
	void init() {
		RateInformation rateInfo = new RateInformation(RID, SENDER_ID, RATING, COMMENT);
		rate = new Rate(SID, rateInfo, RATED_BY_NAME, DATE);
	}

	@Test
	void test_getSid_returnsSID() {
		assertEquals(SID, rate.getSid());
	}
	
	@Test
	void test_getRid_returnsRID() {
		assertEquals(RID, rate.getRid());
	}
	
	@Test
	void test_getRatedByID_returnsSENDER_ID() {
		assertEquals(SENDER_ID, rate.getRateByID());
	}
	
	@Test
	void test_getRating_returnsRATING() {
		assertEquals(RATING, rate.getRating());
	}
	
	@Test
	void test_getComment_returnsCOMMENT() {
		assertEquals(COMMENT, rate.getComment());
	}
	
	@Test
	void test_getRatedByName_returnsRATED_BY_NAME() {
		assertEquals(RATED_BY_NAME, rate.getRatedByName());
	}
	
	@Test
	void test_getDate_returnsDATE() {
		assertEquals(DATE, rate.getDate());
	}

}
