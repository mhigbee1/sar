package information_processing.info_requests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import information_processing.entities.UserInformation;

public class UserInformationTest {
	
	private UserInformation userInfo;
	private static final String FIRST_NAME = "John";
	private static final String LAST_NAME = "Doe";
	private static final String PHONE_NUM = "555-555-5555";
	private static final String USER_PIC = "Temp Photo";
	private static final boolean INACTIVE = false;

	
	@BeforeEach
	void init() {
		userInfo = new UserInformation(FIRST_NAME, LAST_NAME, PHONE_NUM, USER_PIC, INACTIVE);			
	}
	
	
	@Test
	void test_getFirstName_returnsFIRST_NAME() {
		assertEquals(FIRST_NAME, userInfo.getFirstName());
	}
	
	@Test
	void test_getLastName_returnsLAST_NAME() {
		assertEquals(LAST_NAME, userInfo.getLastName());
	}
	
	@Test
	void test_getPhoneNumber_returnsPHONE_NUM() {
		assertEquals(PHONE_NUM, userInfo.getPhoneNumber());
	}
	
	@Test
	void test_getUserPic_returnsUSER_PIC() {
		assertEquals(USER_PIC, userInfo.getUserPic());
	}
	
	@Test
	void test_isActive_returnsINACTIVE() {
		assertFalse(userInfo.isActive());
	}
	
	@Test
	void test_isEmptyRequest_returnsFALSE() {
		assertFalse(userInfo.isEmptyRequest());
	}
	
	@Test
	void test_matchesFilter_keyIsFIRST_NAME_returnsTrue() {
		assertTrue(userInfo.matchesFiler(FIRST_NAME));
	}
	
	@Test
	void test_matchesFilter_keyIsLAST_NAME_returnsTrue() {
		assertTrue(userInfo.matchesFiler(LAST_NAME));
	}
	
	@Test
	void test_matchesFilter_keyIsPHONE_NUMBER_returnsTrue() {
		assertTrue(userInfo.matchesFiler(PHONE_NUM));
	}
	
	@Test
	void test_matchesFilter_keyIsEmptyString_returnsTrue() {
		assertTrue(userInfo.matchesFiler(""));
	}
	
	@Test
	void test_matchesFilter_keyIsUnmatchedString_returnsFalse() {
		assertFalse(userInfo.matchesFiler("unmatched"));
	}

}
