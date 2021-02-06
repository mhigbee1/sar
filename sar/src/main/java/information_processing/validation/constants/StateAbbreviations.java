package information_processing.validation.constants;

public enum StateAbbreviations {
	ALABAMA("AL"),
	ALASKA("AK"),
	ARKANSAS("AR"),
	AMERICAN_SAMOA("AS"),
	ARIZONA("AZ"),
	CALIFORNIA("CA"),
	COLORADO("CO"),
	CONNECTICUT("CT"),
	DELAWARE("DE"),
	FLORIDA("FL"),
	GEORGIA("GA"),
	GUAM("GU"),
	HAWAII("HI"),
	IOWA("IA"),
	IDAHO("ID"),
	ILLINOIS("IL"),
	INDIANA("IN"),
	KANSAS("KS"),
	KENTUCKY("KY"),
	LOUISIANA("LA"),
	MASSACHUSETTS("MA"),
	MARYLAND("MD"),
	MAINE("ME"),
	MICHIGAN("MI"),
	MINNESOTA("MN"),
	MISSOURI("MO"),
	MISSISSIPPI("MS"),
	MONTANA("MT"),
	NORTH_CAROLINA("NC"),
	NORTH_DAKOTA("ND"),
	NEBRASKA("NE"),
	NEW_HAMPSHIRE("NH"),
	NEW_JERSEY("NJ"),
	NEW_MEXICO("NM"),
	NEVADA("NV"),
	NEW_YORK("NY"),
	OHIO("OH"),
	OKLAHOMA("OK"),
	OREGON("OR"),
	PENNSYLVANIA("PA"),
	RHODE_ISLAND("RI"),
	SOUTH_CAROLINA("SC"),
	SOUTH_DAKOTA("SD"),
	TENNESSEE("TN"),
	TEXAS("TX"),
	UTAH("UT"),
	VIRGINIA("VA"),
	VERMONT("VT"),
	WASHINGTON("WA"),
	WISCONSIN("WI"),
	WEST_VIRGINIA("WV"),
	WYOMING("WY");
	
	private String state;
	
	private StateAbbreviations(String mState) {
		this.state = mState;
	}
	
	public String getState() {
		return state;
	}

}
