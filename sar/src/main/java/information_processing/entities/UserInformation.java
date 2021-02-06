package information_processing.entities;

import information_processing.boundaries.InformationIDs;

public class UserInformation extends InformationIDs{
	private final String firstName;
	private final String lastName;
	private final String phoneNumber;
	private final String userPic;
	private final Boolean activeStatus;
	
	public UserInformation(String fName, String lName, String mPhoneNum, String mUserPic, Boolean mActiveStatus) {
		this.firstName = fName;
		this.lastName = lName;
		this.phoneNumber = mPhoneNum;
		this.userPic = mUserPic;
		this.activeStatus = mActiveStatus;
	}
	
	public String getFirstName() {
		return firstName;
	}
	
	public String getLastName() {
		return lastName;
	}
	
	public String getPhoneNumber() {
		return phoneNumber;
	}
	
	public String getUserPic() {
		return userPic;
	}
	
	public Boolean isActive() {
		return activeStatus;
	}
	
	@Override
	public boolean isEmptyRequest() {
		return false;
	}

	public boolean matchesFiler(String key) {
		key = key.toUpperCase();
		String capsFirstName = firstName.toUpperCase();
		String capsLastName = lastName.toUpperCase();
		
		if(capsFirstName.contains(key) || capsLastName.contains(key) || phoneNumber.contains(key)) {
			return true;
		}
		return false;
	}
}
