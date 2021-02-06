package ride_processing.interactors;

import java.util.Iterator;

import information_processing.boundaries.InformationIDs;
import information_processing.entities.RideInformation;
import information_processing.entities.RideRequestInformation;
import message_processing.entities.Message;
import message_processing.interactors.MessagingOperations;
import ride_processing.boundaries.RideOperationsUtility;
import ride_request_processing.RideRequestOperations;

public class Ride implements RideOperationsUtility{
	
	private final int rid;
	private RideInformation rideInformation;
	private RideMessageUtility rideMessageUtil;
	private RideRequestOperationsUtility rideRequestOps;
	
	public Ride(int mRid, RideInformation mRideInfo) {
		this.rid = mRid;
		this.rideInformation = mRideInfo;
		rideMessageUtil = new MessagingOperations();
		rideRequestOps = new RideRequestOperations();
	}

	@Override
	public int getRid() {
		return rid;
	}
	
	@Override
	public int getAid() {
		return rideInformation.getAid();
	}

	@Override
	public String getOriginCity() {
		return rideInformation.getOriginCity();
	}

	@Override
	public String getOriginZip() {
		return rideInformation.getOriginZip();
	}

	@Override
	public String getDestinationCity() {
		return rideInformation.getDestinationCity();
	}

	@Override
	public String getDestinationZip() {
		return rideInformation.getDestinationZip();
	}

	@Override
	public String getRideDate() {
		return rideInformation.getRideDate();
	}

	@Override
	public String getRideTime() {
		return rideInformation.getRideTime();
	}

	@Override
	public String getVehicleMake() {
		return rideInformation.getVehicleMake();
	}

	@Override
	public String getVehicleModel() {
		return rideInformation.getVehicleModel();
	}

	@Override
	public String getVehicleColor() {
		return rideInformation.getVehicleColor();
	}

	@Override
	public String getVehiclePlateState() {
		return rideInformation.getVehiclePlateState();
	}

	@Override
	public String getVehiclePlateNum() {
		return rideInformation.getVehiclePlateNum();
	}

	@Override
	public int getVehicleCapacity() {
		return rideInformation.getVehicleCapacity();
	}

	@Override
	public String getVehicleRules() {
		return rideInformation.getVehicleRules();
	}

	@Override
	public Double getRatePerPassenger() {
		return rideInformation.getRatePerPassenger();
	}
	
	@Override
	public void updateRide(InformationIDs userInfoID) {
		RideInformation updatedRideInfo = (RideInformation) userInfoID;
		this.rideInformation = updatedRideInfo;
	}

	
	
	
	
	@Override
	public int getRideRequestCreatorID(int jid) {
		return rideRequestOps.getAid(jid);
	}

	@Override
	public int addRideRequest(InformationIDs rideReqUserID) {
		return rideRequestOps.addRideRequest((RideRequestInformation)rideReqUserID);
	}

	@Override
	public void updateRideRequest(InformationIDs rideReqUserID) {
		RideInformation newRideInfo = (RideInformation)rideReqUserID;
		this.rideInformation = newRideInfo;
		
	}

	@Override
	public void setRideConfirmation(int jid, boolean status) {
		if (status) {
			rideRequestOps.approveRideRequest(jid);
		}else {
			rideRequestOps.denyRideRequest(jid);
		}
	}

	@Override
	public void setPickUpConfirmation(int jid) {
		rideRequestOps.setPickUpConfirmation(jid);
		
	}

	@Override
	public boolean isRideDriver(int aid) {
		return aid == rideInformation.getAid();
	}

	@Override
	public boolean isRideRider(int aid) {
		return rideRequestOps.isRider(aid);
	}

	@Override
	public boolean isSubmittedRideRequest(int aid) {
		return rideRequestOps.submittedRideRequest(aid);
	}

	@Override
	public boolean isExistingRideRequest(int jid) {
		return rideRequestOps.isExistingRideRequest(jid);
	}

	@Override
	public int addMessage(InformationIDs msgUserID) {
		return rideMessageUtil.addMessage(msgUserID);
	}

	@Override
	public Iterator<Message> getMessages() {
		return rideMessageUtil.getMessages();
	}
	
	@Override
	public boolean matchesFilter(String originKey, String destinationKey, String dateKey) {
		return rideInformation.matchesFilter(originKey, destinationKey, dateKey);
	}
	
	

}
