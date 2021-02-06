package information_processing.info_requests;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import information_processing.entities.RateInformation;

class RateInformationTest {
	private RateInformation rateInfo;
	private static final Integer RID = 123;
	private static final Integer SENDER_ID = 345;
	private static final Integer RATING = 2;
	private static final String COMMENT = "Good ride partner";
	
	@BeforeEach
	void init() {
		rateInfo = new RateInformation(RID, SENDER_ID, RATING, COMMENT);
	}

	@Test
	void test_getRid_returnsRID() {
		assertEquals(RID, rateInfo.getRid());
	}
	
	@Test
	void test_getAid_returnsAID() {
		assertEquals(SENDER_ID, rateInfo.getAid());
	}
	
	@Test
	void test_getRating_returnsRATING() {
		assertEquals(RATING, rateInfo.getRating());
	}
	
	@Test
	void test_getComment_returnsCOMMENT() {
		assertEquals(COMMENT, rateInfo.getComment());
	}
	
	@Test
	void test_isEmptyRequest_returnsFalse() {
		assertFalse(rateInfo.isEmptyRequest());
	}

}
