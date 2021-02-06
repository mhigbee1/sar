package account_operations.boundaries;

import java.util.Iterator;

import information_processing.boundaries.InformationIDs;

public interface AccountOperationsBoundary {
	
	public int addAccount(InformationIDs userID);
	
	public void updateAccount(int aid, InformationIDs userID);
	
	public void removeAccount(int aid);
	
	public AccountOperationsUtility getAccount(int aid);
	
	public Iterator<AccountOperationsUtility> getAccounts(String key);
	
	public boolean isExistingAccount(int aid);
	
	public boolean isActiveAccount(int aid);
	
	public boolean isExistingPhoneNumber(InformationIDs userID);
	
	public int rateDriver(int aid, InformationIDs userID, String date);
	
	public int rateRider(int aid, InformationIDs userID, String date);
	
	public void setDriverRideCounter(int aid);
	
	public void setRiderRideCounter(int aid);
	
}
