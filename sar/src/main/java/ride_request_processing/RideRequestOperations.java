package ride_request_processing;

import java.util.HashMap;
import java.util.Iterator;

import information_processing.entities.RideRequestInformation;
import ride_processing.interactors.RideRequestOperationsUtility;
import ride_processing.interactors.RideRequestUtility;

public class RideRequestOperations implements RideRequestOperationsUtility {
	private HashMap<Integer, RideRequestUtility> rideRequestRecord = new HashMap<Integer, RideRequestUtility>();
	private static int counter = 1;

	@Override
	public int addRideRequest(RideRequestInformation rideRequestInfo) {
		RideRequestUtility rideRequest = new RideRequest(counter, rideRequestInfo);
		rideRequestRecord.put(counter, rideRequest);
		counter++;
		return rideRequest.getJid();
	}

	@Override
	public int getAid(int jid) {
		RideRequestUtility rideRequest = rideRequestRecord.get(jid);
		return rideRequest.getAid();
	}

	@Override
	public void approveRideRequest(int jid) {
		RideRequestUtility rideRequest = rideRequestRecord.get(jid);
		rideRequest.setRideConfirmation(true);
	}

	@Override
	public void denyRideRequest(int jid) {
		RideRequestUtility rideRequest = rideRequestRecord.get(jid);
		rideRequest.setRideConfirmation(false);
	}

	@Override
	public void setPickUpConfirmation(int jid) {
		RideRequestUtility rideRequest = rideRequestRecord.get(jid);
		rideRequest.setPickUpConfirmation();
	}

	@Override
	public boolean isExistingRideRequest(int jid) {
		return rideRequestRecord.containsKey(jid);
	}

	@Override
	public boolean submittedRideRequest(int aid) {
		Iterator<RideRequestUtility> iterator = rideRequestRecord.values().iterator();
		while(iterator.hasNext()) {
			RideRequestUtility rideRequest = iterator.next();
			if(rideRequest.getAid() == aid) {
				return true;
			}
		}
		return false;
	}

	@Override
	public boolean isRider(int aid) {
		Iterator<RideRequestUtility> iterator = rideRequestRecord.values().iterator();
		while(iterator.hasNext()) {
			RideRequestUtility rideRequest = iterator.next();
			if(rideRequest.getAid() == aid) {
				if(rideRequest.getRideConfirmation() == null) {
					return false;
				} else if (rideRequest.getRideConfirmation()) {
					return true;
				}
			}
		} 
		return false;
	}
}
