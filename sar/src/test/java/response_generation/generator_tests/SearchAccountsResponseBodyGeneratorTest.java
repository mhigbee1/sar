package response_generation.generator_tests;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import account_operations.boundaries.AccountOperationsUtility;
import account_operations.interactors.Account;
import information_processing.entities.UserInformation;
import response_generation.boundaries.ResponseBodyUtility;
import response_generation.generators.SearchAccountsResponseBodyGenerator;

class SearchAccountsResponseBodyGeneratorTest {
	
	private Gson gson;
	private JsonArray searchResponse;
	private JsonObject responseBody;
	
	private List<AccountOperationsUtility> accountRecord;
	private Iterator<AccountOperationsUtility> iterator;
	private AccountOperationsUtility accountInstance;
	private String expectedResponse;
	
	private final int AID = 123;
	private final String TEST_FIRST_NAME = "Dan";
	private final String TEST_LAST_NAME = "Dean";
	private final String TEST_PHONE_NUM = "312-555-1234";
	private final String TEST_USER_PIC = "http://example.com/user_pic.jpg";
	private boolean ACTIVE_STATUS = false;
	
	@BeforeEach
	void init() {
		gson = new Gson();
		searchResponse = new JsonArray();
		responseBody = new JsonObject();
		
		UserInformation userInfo = new UserInformation(
				TEST_FIRST_NAME,
				TEST_LAST_NAME,
				TEST_PHONE_NUM,
				TEST_USER_PIC,
				ACTIVE_STATUS);
		accountInstance = new Account(AID, userInfo);
		accountRecord = new ArrayList<AccountOperationsUtility>();
		accountRecord.add(accountInstance);
		iterator = accountRecord.iterator();
	}
		

	@Test
	void test_generateResponseBody_passedValidJsonForAccount_returnsExpectedSearchResponse() {
		responseBody.add(ResponseBodyUtility.ACCOUNT_ID_KEY, gson.toJsonTree(accountInstance.getAid()));
		responseBody.add(ResponseBodyUtility.NAME_KEY, gson.toJsonTree(accountInstance.getFirstName() + " " + accountInstance.getLastName()));
		responseBody.add(ResponseBodyUtility.CREATED_ON_DATE_KEY, gson.toJsonTree(accountInstance.getCreatedOnDate()));
		responseBody.add(ResponseBodyUtility.ACTIVE_STATUS_KEY, gson.toJsonTree(accountInstance.isActive()));
		searchResponse.add(responseBody);
		expectedResponse = searchResponse.toString();
		
		ResponseBodyUtility searchAccountsResponseBodyGenerator = new SearchAccountsResponseBodyGenerator(iterator);
		assertEquals(expectedResponse, searchAccountsResponseBodyGenerator.generateResponseBody());
	}

}
