package account_processing;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Iterator;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import account_operations.boundaries.AccountOperationsUtility;
import account_operations.interactors.AccountOperations;
import information_processing.entities.UserInformation;

class AccountOperationsTest {
	
	private UserInformation testUserInfo;
	private AccountOperations accountOperator;
	private int aid;
	private static final String FIRST_NAME = "Maria";
	private static final String LAST_NAME = "Gomez";
	private static final String PHONE_NUMBER = "555-555-2222";
	private static final String USER_PIC = "http://example.com/userPic.jpg";
	private static final boolean ACTIVE_STATUS = false;
	
	
	@BeforeEach
	void init() {
		testUserInfo = new UserInformation(FIRST_NAME, LAST_NAME, PHONE_NUMBER, USER_PIC, ACTIVE_STATUS);
		accountOperator = new AccountOperations();
		aid = accountOperator.addAccount(testUserInfo);
	}
	
	@AfterEach
	void clean() {
		accountOperator.removeAccount(aid);
	}
	
	private boolean verifyAccountContainsUserInfo(AccountOperationsUtility userAccount, UserInformation userInfo) {
		return (
				userAccount.getFirstName().equals(userInfo.getFirstName()) &&
				userAccount.getLastName().equals(userInfo.getLastName()) &&
				userAccount.getPhoneNumber().equals(userInfo.getPhoneNumber()) &&
				userAccount.getUserPic().equals(userInfo.getUserPic()) &&
				userAccount.isActive() == userInfo.isActive()
				);
	}
	

	@Test
	void test_addAccount_verifyAccountContainstUserInfo_returnsTRUE() {
		AccountOperationsUtility accountInstance = accountOperator.getAccount(aid);
		assertTrue(verifyAccountContainsUserInfo(accountInstance, testUserInfo));
	}
	
	@Test
	void test_updateAccount_activateAccount_returnsTRUE_isActiveAccount() {
		UserInformation userInfo = new UserInformation(FIRST_NAME, LAST_NAME, PHONE_NUMBER, USER_PIC, true);
		accountOperator.updateAccount(aid, userInfo);
		assertTrue(accountOperator.isActiveAccount(aid));
	}
	
	@Test
	void test_isActiveAccount_keyIsInitialActiveStatus_returnsFALSE() {
		assertFalse(accountOperator.isActiveAccount(aid));
	}
	
	@Test
	void test_getAccounts_keyIsFIRST_NAME_returnsIteratorWithAid() {
		Iterator<AccountOperationsUtility> iterator = accountOperator.getAccounts(FIRST_NAME);
		AccountOperationsUtility accountInstance = null;
		while (iterator.hasNext()) {
			accountInstance = iterator.next();
		}
		assertEquals(aid, accountInstance.getAid());
	}
	
	@Test
	void test_getAccounts_keyIsLAST_NAME_returnsIteratorWithAid() {
		Iterator<AccountOperationsUtility> iterator = accountOperator.getAccounts(LAST_NAME);
		AccountOperationsUtility accountInstance = null;
		while (iterator.hasNext()) {
			accountInstance = iterator.next();
		}
		assertEquals(aid, accountInstance.getAid());
	}
	
	@Test
	void test_getAccounts_keyIsPHONE_NUMBER_returnsIteratorWithAid() {
		Iterator<AccountOperationsUtility> iterator = accountOperator.getAccounts(PHONE_NUMBER);
		AccountOperationsUtility accountInstance = null;
		while (iterator.hasNext()) {
			accountInstance = iterator.next();
		}
		assertEquals(aid, accountInstance.getAid());
	}
	
	@Test
	void test_getAccounts_keyIsEmptyString_returnsIteratorWithAid() {
		Iterator<AccountOperationsUtility> iterator = accountOperator.getAccounts("");
		AccountOperationsUtility accountInstance = null;
		while (iterator.hasNext()) {
			accountInstance = iterator.next();
		}
		assertEquals(aid, accountInstance.getAid());
	}
	
	@Test
	void test_getAccounts_keyIsNonMatchingString_returnsEmptyIterator() {
		Iterator<AccountOperationsUtility> iterator = accountOperator.getAccounts("empty");
		assertFalse(iterator.hasNext());
	}
	
	@Test
	void test_isExistingAccount_keyIsAidFromInitalTestAccount_returnsTRUE() {
		assertTrue(accountOperator.isExistingAccount(aid));
	}
	
	@Test
	void test_isExistingAccount_keyIsNonMatchingAid_returnsFALSE() {
		assertFalse(accountOperator.isExistingAccount(333));
	}
	
	@Test
	void test_isExistingPhoneNumber_keyIsInitialTestUserInfo_returnsTRUE() {
		assertTrue(accountOperator.isExistingPhoneNumber(testUserInfo));
	}
	
	@Test
	void test_isExistingPhoneNumber_keyContainsNonMatchingPhoneNumber_returnsFALSE() {
		String nonMatchingPhoneNumber = "222-222-2222";
		UserInformation nonMatchingUserInfo = new UserInformation(FIRST_NAME, LAST_NAME, nonMatchingPhoneNumber, USER_PIC,ACTIVE_STATUS);
		assertFalse(accountOperator.isExistingPhoneNumber(nonMatchingUserInfo));
	}
	
	@Test
	void test_setDriverRideCounter_incrementsDriverRideCounterOnce_returnsOne() {
		accountOperator.setDriverRideCounter(aid);
		AccountOperationsUtility accountInstance = accountOperator.getAccount(aid);
		assertEquals(1, accountInstance.getDriverRideCounter());
	}
	
	@Test
	void test_setRiderRideCounter_incrementsDriverRideCounterOnce_returnsOne() {
		accountOperator.setRiderRideCounter(aid);
		AccountOperationsUtility accountInstance = accountOperator.getAccount(aid);
		assertEquals(1, accountInstance.getRiderRideCounter());
	}

}
