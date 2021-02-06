package account_operations.interactors;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import account_operations.boundaries.AccountOperationsBoundary;
import account_operations.boundaries.AccountOperationsUtility;
import information_processing.boundaries.InformationIDs;
import information_processing.entities.RateInformation;
import information_processing.entities.UserInformation;

public class AccountOperations implements AccountOperationsBoundary {
	
	private static HashMap<Integer, AccountOperationsUtility> accountRecords = new HashMap<>();
	private static int index = 1;

	@Override
	public int addAccount(InformationIDs userID) {
		AccountOperationsUtility accountInstance = new Account(index,(UserInformation) userID);
		accountRecords.put(index, accountInstance);
		index++;
		return accountInstance.getAid();
	}

	@Override
	public void updateAccount(int aid, InformationIDs userID) {
		AccountOperationsUtility accountInstance = accountRecords.get(aid);
		accountInstance.updateUserInfo((UserInformation) userID);
	}

	@Override
	public void removeAccount(int aid) {
		accountRecords.remove(aid);
	}

	@Override
	public AccountOperationsUtility getAccount(int aid) {
		return accountRecords.get(aid);
	}

	@Override
	public Iterator<AccountOperationsUtility> getAccounts (String key) {
		if(key.isEmpty()) {
			return Collections.unmodifiableCollection(accountRecords.values()).iterator();
		}
		return buildAccountCollectionMatchingKey(key);
	}
	
	private Iterator<AccountOperationsUtility> buildAccountCollectionMatchingKey(String key){
		List<AccountOperationsUtility> extractedAccounts = new ArrayList<AccountOperationsUtility>();
		Iterator<AccountOperationsUtility> iterator = accountRecords.values().iterator();
		while(iterator.hasNext()) {
			AccountOperationsUtility accountInstance = iterator.next();
			if(accountInstance.matchesFilter(key)) {
				extractedAccounts.add(accountInstance);
			}
		}
		return Collections.unmodifiableList(extractedAccounts).iterator();
	}
	

	@Override
	public boolean isExistingAccount(int aid) {
		return accountRecords.containsKey(aid);
	}

	@Override
	public boolean isActiveAccount(int aid) {
		if(isExistingAccount(aid)) {
			AccountOperationsUtility accountInstance = accountRecords.get(aid);
			return accountInstance.isActive();
		}
		return false;
	}

	@Override
	public boolean isExistingPhoneNumber(InformationIDs userID) {
		Iterator<AccountOperationsUtility> iterator = accountRecords.values().iterator();
		while(iterator.hasNext()) {
			AccountOperationsUtility accountInstance = iterator.next();
			String userPhoneNumber = ((UserInformation)userID).getPhoneNumber();
			if(accountInstance.getPhoneNumber().equals(userPhoneNumber)){
				return true;
			}
		}
		return false;
	}

	@Override
	public int rateDriver(int aid, InformationIDs userID, String date) {
		AccountOperationsUtility ratedBy = accountRecords.get(userID.getAid());
		AccountOperationsUtility accountToRate = accountRecords.get(aid);
		return accountToRate.addDriverRating((RateInformation) userID, ratedBy.getFirstName(), date);
	}

	@Override
	public int rateRider(int aid, InformationIDs userID, String date) {
		AccountOperationsUtility ratedBy = accountRecords.get(userID.getAid());
		AccountOperationsUtility accountToRate = accountRecords.get(aid);
		return accountToRate.addRiderRating((RateInformation)userID, ratedBy.getFirstName(), date);
	}

	@Override
	public void setDriverRideCounter(int aid) {
		AccountOperationsUtility accountInstance = accountRecords.get(aid);
		accountInstance.setDriverRideCounter();
	}

	@Override
	public void setRiderRideCounter(int aid) {
		AccountOperationsUtility accountInstance = accountRecords.get(aid);
		accountInstance.setRiderRideCounter();
	}

}
