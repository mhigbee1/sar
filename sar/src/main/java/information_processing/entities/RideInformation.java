package information_processing.entities;

import information_processing.boundaries.InformationIDs;

public class RideInformation extends InformationIDs{
	private final Integer aid;
	
	private final String originCity;
	private final String originZip;
	private final String destinationCity;
	private final String destinationZip;
	private final String rideDate;
	private final String rideTime;
	
	private final String vehicleMake;
	private final String vehicleModel;
	private final String vehicleColor;
	private final String vehiclePlateNum;
	private final String vehiclePlateState;
	private final Integer vehicleCapacity;
	private final String vehicleRules;
	
	private final Double ratePerPassenger;
	
	public RideInformation (
			Integer mAid,
			String mOriginCity,
			String mOriginZip,
			String mDestinationCity,
			String mDestinationZip,
			String mRideDate,
			String mRideTime,
			String mVehicleMake,
			String mVehicleModel,
			String mVehicleColor,
			String mVehiclePlateNum,
			String mVehiclePlateState,
			Integer mVehicleCapacity,
			String mVehicleRules,
			Double mRatePerPassenger) {
		this.aid = mAid;
		this.originCity = mOriginCity;
		this.originZip = mOriginZip;
		this.destinationCity = mDestinationCity;
		this.destinationZip = mDestinationZip;
		this.rideDate = mRideDate;
		this.rideTime = mRideTime;
		this.vehicleMake = mVehicleMake;
		this.vehicleModel = mVehicleModel;
		this.vehicleColor = mVehicleColor;
		this.vehiclePlateNum = mVehiclePlateNum;
		this.vehiclePlateState = mVehiclePlateState;
		this.vehicleCapacity = mVehicleCapacity;
		this.vehicleRules = mVehicleRules;
		this.ratePerPassenger = mRatePerPassenger;
	}
	
	@Override
	public Integer getAid() {
		return aid;
	}
	
	public String getOriginCity() {
		return originCity;
	}
	
	public String getOriginZip() {
		return originZip;
	}
	
	public String getDestinationCity() {
		return destinationCity;
	}
	
	public String getDestinationZip() {
		return destinationZip;
	}
	
	public String getRideDate() {
		return rideDate;
	}
	
	public String getRideTime() {
		return rideTime;
	}
	
	public String getVehicleMake() {
		return vehicleMake;
	}
	
	public String getVehicleModel() {
		return vehicleModel;
	}
	
	public String getVehicleColor() {
		return vehicleColor;
	}
	
	public String getVehiclePlateNum() {
		return vehiclePlateNum;
	}
	
	public String getVehiclePlateState() {
		return vehiclePlateState;
	}
	
	public Integer getVehicleCapacity() {
		return vehicleCapacity;
	}
	
	public String getVehicleRules() {
		return vehicleRules;
	}
	
	public Double getRatePerPassenger() {
		return ratePerPassenger;
	}
	
	@Override
	public boolean isEmptyRequest() {
		return false;
	}
	
	private String normalizeTerm(String term) {
		return term.toLowerCase();
	}
	
	public boolean matchesFilter(String originKey, String destinationKey, String dateKey) {
		
		return 
				verifyOriginMatchesKey(normalizeTerm(originKey)) && 
				verifyDestinationMatchesKey(normalizeTerm(destinationKey)) &&
				verifyDateMatchesKey(normalizeTerm(dateKey));
	}
	
	
	private boolean verifyOriginMatchesKey(String originKey) {
		if(originKey.isEmpty()) {
			return true;
		}
		return normalizeTerm(originCity).contains(originKey) || normalizeTerm(originZip).contains(originKey);
	}
	
	
	private boolean verifyDestinationMatchesKey(String destinationKey) {
		if(destinationKey.isEmpty()) {
			return true;
		}
		return normalizeTerm(destinationCity).contains(destinationKey) || normalizeTerm(destinationZip).contains(destinationKey);
	}
	
	private boolean verifyDateMatchesKey(String dateKey) {
		if(dateKey.isEmpty()) {
			return true;
		}
		return normalizeTerm(rideDate).contains(dateKey) || normalizeTerm(rideTime).contains(dateKey);
	}
	
}


