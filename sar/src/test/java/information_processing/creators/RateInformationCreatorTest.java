package information_processing.creators;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.google.gson.Gson;
import com.google.gson.JsonNull;
import com.google.gson.JsonObject;

import information_processing.boundaries.InformationIDsCreator;
import response_generation.boundaries.ResponseBodyUtility;

class RateInformationCreatorTest {
	
	private Gson gson = new Gson();
	private JsonObject requestBody;
	private InformationIDsCreator rateInformationCreator;
	private static String EMPTY_JSON = new JsonObject().toString();
	private static String MALFORMED_JSON = "{'key1': ':\\\\\\\\\\','key2':'value'}";
	private static final int RID = 12;
	private static final int SENDER_ID = 34;
	private static final int RATING = 3;
	private static final String COMMENT = "Good driver";
	private JsonNull NULL = JsonNull.INSTANCE;
	
	@BeforeEach
	void init() {
		rateInformationCreator = new RateInformationCreator();
		requestBody = new JsonObject();
	}
	
	@Test
	void test_create_passedEMPTY_JSON__isEmptyRequestReturnsTrue() {
		assertTrue(rateInformationCreator.create(EMPTY_JSON).isEmptyRequest());
	}
	
	
	@Test
	void test_create_passedMALFORMED_JSON_isEmptyRequestReturnsTrue() {
		assertTrue(rateInformationCreator.create(MALFORMED_JSON).isEmptyRequest());
	}

	@Test
	void test_create_passedValidRateInformationJson_isEmtpyRequestReturnsFalse() {
		requestBody.add(ResponseBodyUtility.RIDE_ID_KEY, gson.toJsonTree(RID));
		requestBody.add(ResponseBodyUtility.SENT_BY_ID_KEY, gson.toJsonTree(SENDER_ID));
		requestBody.add(ResponseBodyUtility.RATING_KEY, gson.toJsonTree(RATING));
		requestBody.add(ResponseBodyUtility.COMMENT_KEY, gson.toJsonTree(COMMENT));
	
		assertFalse(rateInformationCreator.create(requestBody.toString()).isEmptyRequest());
	}
	
	
	@Test
	void test_create_passedNullRID_isEmtpyRequestReturnsTrue() {
		requestBody.add(ResponseBodyUtility.RIDE_ID_KEY, NULL);
		requestBody.add(ResponseBodyUtility.SENT_BY_ID_KEY, gson.toJsonTree(SENDER_ID));
		requestBody.add(ResponseBodyUtility.RATING_ID_KEY, gson.toJsonTree(RATING));
		requestBody.add(ResponseBodyUtility.COMMENT_KEY, gson.toJsonTree(COMMENT));
		
		assertTrue(rateInformationCreator.create(requestBody.toString()).isEmptyRequest());
	}
	
	
	@Test
	void test_create_passedNullSENDER_ID_isEmtpyRequestReturnsTrue() {
		requestBody.add(ResponseBodyUtility.RIDE_ID_KEY, gson.toJsonTree(RID));
		requestBody.add(ResponseBodyUtility.SENT_BY_ID_KEY, NULL);
		requestBody.add(ResponseBodyUtility.RATING_ID_KEY, gson.toJsonTree(RATING));
		requestBody.add(ResponseBodyUtility.COMMENT_KEY, gson.toJsonTree(COMMENT));
		
		assertTrue(rateInformationCreator.create(requestBody.toString()).isEmptyRequest());
	}
	
	@Test
	void test_create_passedNullRating_isEmtpyRequestReturnsTrue() {
		requestBody.add(ResponseBodyUtility.RIDE_ID_KEY, gson.toJsonTree(RID));
		requestBody.add(ResponseBodyUtility.SENT_BY_ID_KEY, gson.toJsonTree(SENDER_ID));
		requestBody.add(ResponseBodyUtility.RATING_ID_KEY, NULL);
		requestBody.add(ResponseBodyUtility.COMMENT_KEY, gson.toJsonTree(COMMENT));
		
		assertTrue(rateInformationCreator.create(requestBody.toString()).isEmptyRequest());
	}
	
	@Test
	void test_create_passedNullCOMMENT_isEmtpyRequestReturnsTrue() {
		requestBody.add(ResponseBodyUtility.RIDE_ID_KEY, gson.toJsonTree(RID));
		requestBody.add(ResponseBodyUtility.SENT_BY_ID_KEY, gson.toJsonTree(SENDER_ID));
		requestBody.add(ResponseBodyUtility.RATING_ID_KEY, gson.toJsonTree(RATING));
		requestBody.add(ResponseBodyUtility.COMMENT_KEY, NULL);
		
		assertTrue(rateInformationCreator.create(requestBody.toString()).isEmptyRequest());
	}
	
	@Test
	void test_create_passedRatingLessThanOne_isEmtpyRequestReturnsTrue() {
		requestBody.add(ResponseBodyUtility.RIDE_ID_KEY, gson.toJsonTree(RID));
		requestBody.add(ResponseBodyUtility.SENT_BY_ID_KEY, gson.toJsonTree(SENDER_ID));
		requestBody.add(ResponseBodyUtility.RATING_ID_KEY, gson.toJsonTree(0));
		requestBody.add(ResponseBodyUtility.COMMENT_KEY, gson.toJsonTree(COMMENT));
		
		assertTrue(rateInformationCreator.create(requestBody.toString()).isEmptyRequest());
	}
	
	
	@Test
	void test_create_passedRatingGreaterThanFive_isEmtpyRequestReturnsTrue() {
		requestBody.add(ResponseBodyUtility.RIDE_ID_KEY, gson.toJsonTree(RID));
		requestBody.add(ResponseBodyUtility.SENT_BY_ID_KEY, gson.toJsonTree(SENDER_ID));
		requestBody.add(ResponseBodyUtility.RATING_ID_KEY, gson.toJsonTree(6));
		requestBody.add(ResponseBodyUtility.COMMENT_KEY, gson.toJsonTree(COMMENT));
		
		assertTrue(rateInformationCreator.create(requestBody.toString()).isEmptyRequest());
	}

}
