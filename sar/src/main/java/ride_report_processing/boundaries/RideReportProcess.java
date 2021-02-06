package ride_report_processing.boundaries;

import java.util.Iterator;

import ride_processing.boundaries.RideOperationsUtility;
import ride_report_processing.interactors.RideReport;
import ride_report_processing.interactors.RideReportProcessTypes;

public abstract class RideReportProcess {
	
	private Iterator<RideOperationsUtility> ridesRecord;
	private String startDate;
	private String endDate;
	
	public static boolean isExistingProcess(int pid) {
		for(RideReportProcessTypes type : RideReportProcessTypes.values()) {
			if(type.getPid() == pid) {
				return true;
			}
		}
		return false;
	}
	
	public RideReportProcess(Iterator<RideOperationsUtility> mRidesRecord, String mStartDate, String mEndDate) {
		this.ridesRecord = mRidesRecord;
		this.startDate = mStartDate;
		this.endDate = mEndDate;
	}
	
	protected Iterator<RideOperationsUtility> getRidesRecord(){
		return this.ridesRecord;
	}
	
	public String getStartDate() {
		return startDate;
	}
	
	public String getEndDate() {
		return endDate;
	}
	
	public RideReport generateRideReport() {
		
		RideReport reportInstance = new RideReport();
		while(ridesRecord.hasNext()) {
			RideOperationsUtility rideInstance = ridesRecord.next();
			String originZip = rideInstance.getOriginZip();
			String destinationZip = rideInstance.getDestinationZip();
			
			if(reportInstance.isExistingRideReport(originZip, destinationZip)) {
				reportInstance.incrementCounter(originZip, destinationZip);
			}else {
				reportInstance.addRideReport(originZip, destinationZip);
			}
		}
		return reportInstance;
		
	}
	
	public abstract int getPid();
	public abstract String getReportNameText();

}
