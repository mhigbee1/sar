package ride_processing.interactors;

import java.text.ParseException;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.TimeZone;

import information_processing.entities.RideInformation;
import information_processing.entities.UpdateRideRequestInfo;
import information_processing.boundaries.InformationIDs;
import message_processing.entities.Message;
import response_generation.boundaries.DateTimeFormatUtility;
import ride_processing.boundaries.RideOperationsBoundary;
import ride_processing.boundaries.RideOperationsUtility;

public class RideOperations implements RideOperationsBoundary {
	
	private static HashMap<Integer, RideOperationsUtility> rideRecords = new HashMap<>();
	private static int counter = 1;

	@Override
	public int addRide(InformationIDs userID) {
		Ride newRideInstance = new Ride(counter, (RideInformation) userID);
		rideRecords.put(counter, newRideInstance);
		counter++;
		return newRideInstance.getRid();
	}

	@Override
	public void updateRide(int rid, InformationIDs userID) {
		RideOperationsUtility ride = rideRecords.get(rid);
		ride.updateRideRequest((RideInformation) userID);
	}

	@Override
	public void removeRide(int rid) {
		rideRecords.remove(rid);
	}

	@Override
	public RideOperationsUtility getRide(int rid) {
		return rideRecords.get(rid);
	}

	@Override
	public Iterator<RideOperationsUtility> findRide(String origin, String destination, String date) {
		if (origin.isEmpty() && destination.isEmpty() && date.isEmpty()) {
			return Collections.unmodifiableCollection(rideRecords.values()).iterator();
		}
		return buildRideCollectionForFilter(origin, destination, date);
	}
	
	private Iterator<RideOperationsUtility>buildRideCollectionForFilter(String originKey, String destinationKey, String dateKey){
		List<RideOperationsUtility>extractedRides = new ArrayList<RideOperationsUtility>();
		Iterator<RideOperationsUtility> iterator = rideRecords.values().iterator();
		while (iterator.hasNext()) {
			RideOperationsUtility rideInstance = iterator.next();
			if(rideInstance.matchesFilter(originKey, destinationKey, dateKey)) {
				extractedRides.add(rideInstance);
			}
		}
		return Collections.unmodifiableList(extractedRides).iterator();
	}
	
	
	
	@Override
	public String getRideDate(int rid) {
		RideOperationsUtility rideInstance = rideRecords.get(rid);
		return rideInstance.getRideDate();
	}
	
	

	@Override
	public Iterator<RideOperationsUtility> findRidesInDateRange(String startDate, String endDate)
			throws ParseException {
		if(startDate.isEmpty() && endDate.isEmpty()) {
			return Collections.unmodifiableCollection(rideRecords.values()).iterator();
		}
		
		long startDateTimeStamp = DateTimeFormatUtility.extractTimeStampFromDateFormat(startDate);
		long endDateTimeStamp = DateTimeFormatUtility.extractTimeStampFromDateFormat(endDate);
		
		if (startDateTimeStamp == -1) {
			startDateTimeStamp = Long.MIN_VALUE;
		}
		
		if (endDateTimeStamp == -1) {
			endDateTimeStamp = Long.MAX_VALUE;
		}
		
		return buildRideCollectionForDateRange(startDateTimeStamp, endDateTimeStamp);
	}
	
	private Iterator<RideOperationsUtility> buildRideCollectionForDateRange(long startTimeStamp, long endTimeStamp){
		
		List<RideOperationsUtility> extractedRides = new ArrayList<RideOperationsUtility>();
		Iterator<RideOperationsUtility> iterator = rideRecords.values().iterator();
		
		while(iterator.hasNext()) {
			RideOperationsUtility rideInstance = iterator.next();
			String tmpDate = rideInstance.getRideDate();
			
			try {		
				DateTimeFormatter dDTF = DateTimeFormatter.ofPattern(DateTimeFormatUtility.DATE_FORMAT);
				LocalDate parsedDate = LocalDate.parse(tmpDate, dDTF);
				ZoneId defaultZone = TimeZone.getDefault().toZoneId();
				long rideTimeStamp = parsedDate.atStartOfDay(defaultZone).toEpochSecond();
				
				if(startTimeStamp <= rideTimeStamp && rideTimeStamp <= endTimeStamp) {
					extractedRides.add(rideInstance);
				}
			}catch (DateTimeParseException pe) {
				pe.printStackTrace();
			}
		}
		return Collections.unmodifiableList(extractedRides).iterator();
		
	}

	@Override
	public int addRideRequest(int rid, InformationIDs userID) {
		RideOperationsUtility rideInstance = rideRecords.get(rid);
		return rideInstance.addRideRequest(userID);
	}

	@Override
	public void setRideConfirmation(int rid, int jid, InformationIDs userID) {
		RideOperationsUtility rideInstance = rideRecords.get(rid);
		boolean status = ((UpdateRideRequestInfo) userID).getRequestStatus();
		rideInstance.setRideConfirmation(jid, status);
	}

	@Override
	public void setPickUpConfirmation(int rid, int jid) {
		RideOperationsUtility rideInstance = rideRecords.get(rid);
		rideInstance.setPickUpConfirmation(jid);
	}

	@Override
	public int addMessage(int rid, InformationIDs userInfo) {
		RideOperationsUtility rideInstance = rideRecords.get(rid);
		return rideInstance.addMessage(userInfo);
	}

	@Override
	public Iterator<Message> getMessages(int rid) {
		RideOperationsUtility rideInstance = rideRecords.get(rid);
		return rideInstance.getMessages();
	}

	@Override
	public int getRideRequestCreatorID(int rid, int jid) {
		RideOperationsUtility rideInstance = rideRecords.get(rid);
		return rideInstance.getRideRequestCreatorID(jid);
	}
	

	@Override
	public boolean isExistingRide(int rid) {
		return rideRecords.containsKey(rid);
	}

	@Override
	public boolean isExistingRideRequest(int rid, int jid) {
		RideOperationsUtility rideInstance = rideRecords.get(rid);
		return rideInstance.isExistingRideRequest(jid);
	}

	@Override
	public boolean submittedRideRequest(int rid, int aid) {
		RideOperationsUtility rideInstance = rideRecords.get(rid);
		return rideInstance.isSubmittedRideRequest(aid);
	}

	@Override
	public boolean isAccountRideDriver(int rid, int aid) {
		RideOperationsUtility rideInstance = rideRecords.get(rid);
		return rideInstance.isRideDriver(aid);
	}

	@Override
	public boolean isAccountRideRider(int rid, int aid) {
		RideOperationsUtility rideInstance = rideRecords.get(rid);
		return rideInstance.isRideRider(aid);
	}

}
