package account_processing;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Iterator;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import account_operations.boundaries.AccountOperationsUtility;
import account_operations.interactors.AccountOperations;
import information_processing.entities.RateInformation;
import information_processing.entities.UserInformation;
import rate_processing.entities.Rate;

class AccountOperationsRatingTest {
	
	private UserInformation testUserInfo;
	private AccountOperations accountOperator;
	private int aid;
	private static final String FIRST_NAME = "Maria";
	private static final String LAST_NAME = "Gomez";
	private static final String PHONE_NUMBER = "555-555-2222";
	private static final String USER_PIC = "http://example.com/userPic.jpg";
	private static final boolean ACTIVE_STATUS = false;
	
	private int ratedByAid;
	private RateInformation testRateInfo;
	private static final String DATE = "26-Dec-2020";
	private static final int RID = 1;
	private static final int RATING = 4;
	private static final String COMMENT = "Maria keeps a clean car";
	
	@BeforeEach
	void init() {
		testUserInfo = new UserInformation(FIRST_NAME, LAST_NAME, PHONE_NUMBER, USER_PIC, ACTIVE_STATUS);
		accountOperator = new AccountOperations();
		aid = accountOperator.addAccount(testUserInfo);
		ratedByAid = accountOperator.addAccount(testUserInfo);
		testRateInfo = new RateInformation(RID,ratedByAid, RATING, COMMENT);
	}
	
	@AfterEach
	void clean() {
		accountOperator.removeAccount(aid);
		accountOperator.removeAccount(ratedByAid);
	}
	
	@Test
	void test_rateDriver_successfullyAddRatingToDriverAccount_returnsMatchingAID() {
		accountOperator.rateDriver(aid, testRateInfo, DATE);
		AccountOperationsUtility accountInstance = accountOperator.getAccount(aid);
		Iterator<Rate> iterator = accountInstance.getDriverRatingIterator();
		Rate rate = null;
		while(iterator.hasNext()) {
			rate = iterator.next();
		}
		assertEquals(ratedByAid, rate.getRateByID());
	}
	
	@Test
	void test_rateRider_successfullyAddRatingToRiderAccount_returnsMatchingAID() {
		accountOperator.rateRider(aid, testRateInfo, DATE);
		AccountOperationsUtility accountInstance = accountOperator.getAccount(aid);
		Iterator<Rate> iterator = accountInstance.getRiderRatingIterator();
		Rate rate = null;
		while(iterator.hasNext()) {
			rate = iterator.next();
		}
		assertEquals(ratedByAid, rate.getRateByID());
	}

}
