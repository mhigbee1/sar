package response_generation.generator_tests;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonNull;
import com.google.gson.JsonObject;

import account_operations.boundaries.AccountOperationsUtility;
import account_operations.interactors.Account;
import information_processing.entities.UserInformation;
import rate_processing.boundaries.RatingType;
import response_generation.boundaries.ResponseBodyUtility;
import response_generation.generators.ViewRatingsResponseBodyGenerator;

class ViewRatingsResponseBodyGeneratorTest {
	
	private Gson gson;
	private JsonObject responseBody;
	private JsonArray ratingDetail;
	private String expectedResponse;
	
	private AccountOperationsUtility accountInstance;
	private final int AID = 123;
	private final String TEST_FIRST_NAME = "Mark";
	private final String TEST_LAST_NAME = "GOMEZ";
	private final String TEST_PHONE_NUM = "312-555-1234";
	private final String TEST_USER_PIC = "http://example.com/user_pic.jpg";
	private final boolean TEST_ACTIVE_STATUS = false;
	
	@BeforeEach
	void init() {
		gson = new Gson();
		responseBody = new JsonObject();
		ratingDetail = new JsonArray();
		List<AccountOperationsUtility> accountRecords = new ArrayList<AccountOperationsUtility>();
		UserInformation userInfo = new UserInformation(
				TEST_FIRST_NAME,
				TEST_LAST_NAME,
				TEST_PHONE_NUM,
				TEST_USER_PIC,
				TEST_ACTIVE_STATUS);
		accountInstance = new Account(AID, userInfo);
		accountRecords.add(accountInstance);
		
	}

	@Test
	void test_generaterResponseBody_viewRatingsForDriver_returnsTrueForMatchingExpectedResult() {
		ResponseBodyUtility viewRatingsResponseBodyGenerator;
		
		responseBody.add(ResponseBodyUtility.ACCOUNT_ID_KEY, gson.toJsonTree(accountInstance.getAid()));
		responseBody.add(ResponseBodyUtility.FIRST_NAME_KEY, gson.toJsonTree(accountInstance.getFirstName()));
		responseBody.add(ResponseBodyUtility.RIDE_COUNT_KEY, gson.toJsonTree(accountInstance.getDriverRideCounter()));
		responseBody.add(ResponseBodyUtility.RATING_COUNT_KEY, gson.toJsonTree(accountInstance.getDriverRatingsCounter()));
		responseBody.add(ResponseBodyUtility.AVG_RATING_KEY, gson.toJsonTree(
				accountInstance.getDriverAvgRating() == null ? JsonNull.INSTANCE : accountInstance.getDriverAvgRating()));
		
		responseBody.add(ResponseBodyUtility.DETAIL_KEY, ratingDetail);
		expectedResponse = responseBody.toString();
		viewRatingsResponseBodyGenerator = new ViewRatingsResponseBodyGenerator(accountInstance, RatingType.DRIVER);
		
		assertEquals(expectedResponse, viewRatingsResponseBodyGenerator.generateResponseBody());
	}
	
	@Test
	void test_generaterResponseBody_viewRatingsForRider_returnsTrueForMatchingExpectedResult() {
		ResponseBodyUtility viewRatingsResponseBodyGenerator;
		
		responseBody.add(ResponseBodyUtility.ACCOUNT_ID_KEY, gson.toJsonTree(accountInstance.getAid()));
		responseBody.add(ResponseBodyUtility.FIRST_NAME_KEY, gson.toJsonTree(accountInstance.getFirstName()));
		responseBody.add(ResponseBodyUtility.RIDE_COUNT_KEY, gson.toJsonTree(accountInstance.getRiderRideCounter()));
		responseBody.add(ResponseBodyUtility.RATING_COUNT_KEY, gson.toJsonTree(accountInstance.getRiderRatingsCounter()));
		responseBody.add(ResponseBodyUtility.AVG_RATING_KEY, gson.toJsonTree(
				accountInstance.getDriverAvgRating() == null ? JsonNull.INSTANCE : accountInstance.getRiderAvgRating()));
		
		responseBody.add(ResponseBodyUtility.DETAIL_KEY, ratingDetail);
		expectedResponse = responseBody.toString();
		viewRatingsResponseBodyGenerator = new ViewRatingsResponseBodyGenerator(accountInstance, RatingType.RIDER);
		
		assertEquals(expectedResponse, viewRatingsResponseBodyGenerator.generateResponseBody());
	}

}
