package ride_report_processing.interactors;

import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;

import ride_report_processing.entities.RideReportDetail;

public class RideReport {
	
	private HashMap<String, RideReportDetail> rideReportDetails = new HashMap<String, RideReportDetail>();
	
	
	public void addRideReport(String originZip, String destinationZip) {
		rideReportDetails.put(originZip + destinationZip, new RideReportDetail(originZip, destinationZip));
	}
	
	
	public Iterator<RideReportDetail> getRideReportDetails(){
		return Collections.unmodifiableCollection(rideReportDetails.values()).iterator();
	}
	
	
	public int getSize() {
		int size = 0;
		Iterator<RideReportDetail> iterator = rideReportDetails.values().iterator();
		while(iterator.hasNext()) {
			RideReportDetail rideReportInstance = iterator.next();
			size += rideReportInstance.getCount();
		}
		return size;
	}
	
	
	
	public boolean isExistingRideReport(String originZip, String destinationZip) {
		return rideReportDetails.containsKey(originZip + destinationZip);
	}
	
	
	public void incrementCounter(String originZip, String destinationZip) throws IllegalArgumentException{
		String errMsgText = "No existing token for originZip (" + originZip + ") and destinationZip (" + destinationZip +").";
		
		if(isExistingRideReport(originZip, destinationZip)) {
			throw new IllegalArgumentException(errMsgText);
		}
		
		RideReportDetail reportDetailInstance = rideReportDetails.get(originZip + destinationZip);
		reportDetailInstance.incrementCount();
	}
	
	

}
