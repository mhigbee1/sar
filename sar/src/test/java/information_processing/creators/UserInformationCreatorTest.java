package information_processing.creators;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.google.gson.Gson;
import com.google.gson.JsonNull;
import com.google.gson.JsonObject;

import information_processing.boundaries.InformationIDsCreator;
import response_generation.boundaries.ResponseBodyUtility;

class UserInformationCreatorTest {
	
	private Gson gson = new Gson();
	private JsonObject requestBody;
	private InformationIDsCreator userInformationCreator;
	private JsonNull NULL = JsonNull.INSTANCE;
	private static String EMPTY_JSON = new JsonObject().toString();
	private static String MALFORMED_JSON = "{'key1': ':\\\\\\\\\\','key2':'value'}";
	
	private static String FIRST_NAME = "Mark";
	private static String LAST_NAME = "Gomez";
	private static String PHONE_NUM = "555-555-5555";
	private static String USER_PIC = "http://example.com/user_pic.jpg";
	private static boolean ACTIVE_STATUS = false;
	
	@BeforeEach
	void init() {
		userInformationCreator = new UserInformationCreator(ACTIVE_STATUS);
		requestBody = new JsonObject();
	}

	
	@Test
	void test_create_passedEMPTY_JSON__isEmptyRequestReturnsTrue() {
		assertTrue(userInformationCreator.create(EMPTY_JSON).isEmptyRequest());
	}
	
	
	@Test
	void test_create_passedMALFORMED_JSON_isEmptyRequestReturnTrue() {
		assertTrue(userInformationCreator.create(MALFORMED_JSON).isEmptyRequest());
	}
	
	@Test
	void test_create_passedValidUserInformationJson_isEmptyRequestReturnsFalse() {
		requestBody.add(ResponseBodyUtility.FIRST_NAME_KEY, gson.toJsonTree(FIRST_NAME));
		requestBody.add(ResponseBodyUtility.LAST_NAME_KEY, gson.toJsonTree(LAST_NAME));
		requestBody.add(ResponseBodyUtility.PHONE_NUMBER_KEY, gson.toJsonTree(PHONE_NUM));
		requestBody.add(ResponseBodyUtility.USER_PIC_KEY, gson.toJsonTree(USER_PIC));
		requestBody.add(ResponseBodyUtility.ACTIVE_STATUS_KEY, gson.toJsonTree(ACTIVE_STATUS));
		
		assertFalse(userInformationCreator.create(requestBody.toString()).isEmptyRequest());
	}
	
	@Test
	void test_create_passedNullFirstName_isEmptyRequestReturnsTrue() {
		requestBody.add(ResponseBodyUtility.FIRST_NAME_KEY, NULL);
		requestBody.add(ResponseBodyUtility.LAST_NAME_KEY, gson.toJsonTree(LAST_NAME));
		requestBody.add(ResponseBodyUtility.PHONE_NUMBER_KEY, gson.toJsonTree(PHONE_NUM));
		requestBody.add(ResponseBodyUtility.USER_PIC_KEY, gson.toJsonTree(USER_PIC));
		requestBody.add(ResponseBodyUtility.ACTIVE_STATUS_KEY, gson.toJsonTree(ACTIVE_STATUS));
		
		assertTrue(userInformationCreator.create(requestBody.toString()).isEmptyRequest());
	}
	
	
	@Test
	void test_create_passedNullLastName_isEmptyRequestReturnsTrue() {
		requestBody.add(ResponseBodyUtility.FIRST_NAME_KEY, gson.toJsonTree(FIRST_NAME));
		requestBody.add(ResponseBodyUtility.LAST_NAME_KEY, NULL);
		requestBody.add(ResponseBodyUtility.PHONE_NUMBER_KEY, gson.toJsonTree(PHONE_NUM));
		requestBody.add(ResponseBodyUtility.USER_PIC_KEY, gson.toJsonTree(USER_PIC));
		requestBody.add(ResponseBodyUtility.ACTIVE_STATUS_KEY, gson.toJsonTree(ACTIVE_STATUS));
		
		assertTrue(userInformationCreator.create(requestBody.toString()).isEmptyRequest());
	}
	
	
	@Test
	void test_create_passedNullPhoneNumber_isEmptyRequestReturnsTrue() {
		requestBody.add(ResponseBodyUtility.FIRST_NAME_KEY, gson.toJsonTree(FIRST_NAME));
		requestBody.add(ResponseBodyUtility.LAST_NAME_KEY, gson.toJsonTree(LAST_NAME));
		requestBody.add(ResponseBodyUtility.PHONE_NUMBER_KEY, NULL);
		requestBody.add(ResponseBodyUtility.USER_PIC_KEY, gson.toJsonTree(USER_PIC));
		requestBody.add(ResponseBodyUtility.ACTIVE_STATUS_KEY, gson.toJsonTree(ACTIVE_STATUS));
		
		assertTrue(userInformationCreator.create(requestBody.toString()).isEmptyRequest());
	}
	
	
	@Test
	void test_create_passedNullUserPic_isEmptyRequestReturnsTrue() {
		requestBody.add(ResponseBodyUtility.FIRST_NAME_KEY, gson.toJsonTree(FIRST_NAME));
		requestBody.add(ResponseBodyUtility.LAST_NAME_KEY, gson.toJsonTree(LAST_NAME));
		requestBody.add(ResponseBodyUtility.PHONE_NUMBER_KEY, gson.toJsonTree(PHONE_NUM));
		requestBody.add(ResponseBodyUtility.USER_PIC_KEY, NULL);
		requestBody.add(ResponseBodyUtility.ACTIVE_STATUS_KEY, gson.toJsonTree(ACTIVE_STATUS));
		
		assertTrue(userInformationCreator.create(requestBody.toString()).isEmptyRequest());
	}
	
	
	@Test
	void test_create_passedNullActiveStatus_isEmptyRequestReturnsTrue() {
		requestBody.add(ResponseBodyUtility.FIRST_NAME_KEY, gson.toJsonTree(FIRST_NAME));
		requestBody.add(ResponseBodyUtility.LAST_NAME_KEY, gson.toJsonTree(LAST_NAME));
		requestBody.add(ResponseBodyUtility.PHONE_NUMBER_KEY, gson.toJsonTree(PHONE_NUM));
		requestBody.add(ResponseBodyUtility.USER_PIC_KEY, gson.toJsonTree(USER_PIC));
		requestBody.add(ResponseBodyUtility.ACTIVE_STATUS_KEY, NULL);
		
		assertTrue(userInformationCreator.create(requestBody.toString()).isEmptyRequest());
	}
	
	@Test
	void test_create_passedInvalidFirstName_isEmptyRequestReturnsTrue() {
		requestBody.add(ResponseBodyUtility.FIRST_NAME_KEY, gson.toJsonTree("999"));
		requestBody.add(ResponseBodyUtility.LAST_NAME_KEY, gson.toJsonTree(LAST_NAME));
		requestBody.add(ResponseBodyUtility.PHONE_NUMBER_KEY, gson.toJsonTree(PHONE_NUM));
		requestBody.add(ResponseBodyUtility.USER_PIC_KEY, gson.toJsonTree(USER_PIC));
		requestBody.add(ResponseBodyUtility.ACTIVE_STATUS_KEY, gson.toJsonTree(ACTIVE_STATUS));
		
		assertTrue(userInformationCreator.create(requestBody.toString()).isEmptyRequest());
	}
	
	@Test
	void test_create_passedInvalidLastName_isEmptyRequestReturnsTrue() {
		requestBody.add(ResponseBodyUtility.FIRST_NAME_KEY, gson.toJsonTree(FIRST_NAME));
		requestBody.add(ResponseBodyUtility.LAST_NAME_KEY, gson.toJsonTree("999"));
		requestBody.add(ResponseBodyUtility.PHONE_NUMBER_KEY, gson.toJsonTree(PHONE_NUM));
		requestBody.add(ResponseBodyUtility.USER_PIC_KEY, gson.toJsonTree(USER_PIC));
		requestBody.add(ResponseBodyUtility.ACTIVE_STATUS_KEY, gson.toJsonTree(ACTIVE_STATUS));
		
		assertTrue(userInformationCreator.create(requestBody.toString()).isEmptyRequest());
	}
	
	
	@Test
	void test_create_passedInvalidPhoneNumber_isEmptyRequestReturnsTrue() {
		requestBody.add(ResponseBodyUtility.FIRST_NAME_KEY, gson.toJsonTree(FIRST_NAME));
		requestBody.add(ResponseBodyUtility.LAST_NAME_KEY, gson.toJsonTree(LAST_NAME));
		requestBody.add(ResponseBodyUtility.PHONE_NUMBER_KEY, gson.toJsonTree("5555555555"));
		requestBody.add(ResponseBodyUtility.USER_PIC_KEY, gson.toJsonTree(USER_PIC));
		requestBody.add(ResponseBodyUtility.ACTIVE_STATUS_KEY, gson.toJsonTree(ACTIVE_STATUS));
		
		assertTrue(userInformationCreator.create(requestBody.toString()).isEmptyRequest());
	}
	
	
	@Test
	void test_create_passedInvalidUserPic_isEmptyRequestReturnsTrue() {
		requestBody.add(ResponseBodyUtility.FIRST_NAME_KEY, gson.toJsonTree(FIRST_NAME));
		requestBody.add(ResponseBodyUtility.LAST_NAME_KEY, gson.toJsonTree(LAST_NAME));
		requestBody.add(ResponseBodyUtility.PHONE_NUMBER_KEY, gson.toJsonTree(PHONE_NUM));
		requestBody.add(ResponseBodyUtility.USER_PIC_KEY, gson.toJsonTree(""));
		requestBody.add(ResponseBodyUtility.ACTIVE_STATUS_KEY, gson.toJsonTree(ACTIVE_STATUS));
		
		assertTrue(userInformationCreator.create(requestBody.toString()).isEmptyRequest());
	}
	
	@Test
	void test_create_passedTrueActiveStatus_isEmptyRequestReturnsTrue() {
		requestBody.add(ResponseBodyUtility.FIRST_NAME_KEY, gson.toJsonTree(FIRST_NAME));
		requestBody.add(ResponseBodyUtility.LAST_NAME_KEY, gson.toJsonTree(LAST_NAME));
		requestBody.add(ResponseBodyUtility.PHONE_NUMBER_KEY, gson.toJsonTree(PHONE_NUM));
		requestBody.add(ResponseBodyUtility.USER_PIC_KEY, gson.toJsonTree(USER_PIC));
		requestBody.add(ResponseBodyUtility.ACTIVE_STATUS_KEY, gson.toJsonTree(true));
		
		assertTrue(userInformationCreator.create(requestBody.toString()).isEmptyRequest());
	}
	
	
	@Test
	void test_NEW_ACCOUNT_passedValidUserInformationJson_isEmptyRequestReturnsFalse() {
		UserInformationCreator newCreator = UserInformationCreator.NEW_ACCOUNT();
		
		requestBody.add(ResponseBodyUtility.FIRST_NAME_KEY, gson.toJsonTree(FIRST_NAME));
		requestBody.add(ResponseBodyUtility.LAST_NAME_KEY, gson.toJsonTree(LAST_NAME));
		requestBody.add(ResponseBodyUtility.PHONE_NUMBER_KEY, gson.toJsonTree(PHONE_NUM));
		requestBody.add(ResponseBodyUtility.USER_PIC_KEY, gson.toJsonTree(USER_PIC));
		requestBody.add(ResponseBodyUtility.ACTIVE_STATUS_KEY, gson.toJsonTree(ACTIVE_STATUS));
		
		assertFalse(newCreator.create(requestBody.toString()).isEmptyRequest());
	}
	
	@Test
	void test_ACTIVE_ACCOUNT_passedValidUserInformationJson_isEmptyRequestReturnsFalse() {
		UserInformationCreator newCreator = UserInformationCreator.ACTIVE_ACCOUNT();
		
		requestBody.add(ResponseBodyUtility.FIRST_NAME_KEY, gson.toJsonTree(FIRST_NAME));
		requestBody.add(ResponseBodyUtility.LAST_NAME_KEY, gson.toJsonTree(LAST_NAME));
		requestBody.add(ResponseBodyUtility.PHONE_NUMBER_KEY, gson.toJsonTree(PHONE_NUM));
		requestBody.add(ResponseBodyUtility.USER_PIC_KEY, gson.toJsonTree(USER_PIC));
		requestBody.add(ResponseBodyUtility.ACTIVE_STATUS_KEY, gson.toJsonTree(true));
		
		assertFalse(newCreator.create(requestBody.toString()).isEmptyRequest());
	}
	
	
}
