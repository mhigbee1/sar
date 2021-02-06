package ride_report_processing.interactors;

import java.util.Iterator;

import ride_processing.boundaries.RideOperationsUtility;
import ride_report_processing.boundaries.RideReportProcess;

public class RideReportBuilder {
	
	private Iterator<RideOperationsUtility> ridesRecord;
	private String startDate;
	private String endDate;
	
	public RideReportBuilder(Iterator<RideOperationsUtility> mRidesRecord, String mStartDate, String mEndDate) {
		this.ridesRecord = mRidesRecord;
		this.startDate = mStartDate;
		this.endDate = mEndDate;
	}
	
	
	public RideReportProcess buildRideReportsProcess(int pid) {
		RideReportProcess processInstance;
		switch(pid) {
		case PostedRideReportsProcess.PID:
			processInstance = new PostedRideReportsProcess(ridesRecord, startDate, endDate);
			break;
		case TakenRideReportsProcess.PID:
			processInstance = new TakenRideReportsProcess(ridesRecord, startDate, endDate);
			break;
		default:
			throw new IllegalArgumentException("Invalid PID received");
		}
		return processInstance;
	}
	
	public static String getProcessNameText(RideReportProcessTypes processType) {
		
		String nameText = null;
		switch(processType) {
		case POSTED_RIDE_REPORT_PROCESS:
			nameText = PostedRideReportsProcess.NAME_TEXT;
			break;
		case TAKEN_RIDE_REPORT_PROCESS:
			nameText = TakenRideReportsProcess.NAME_TEXT;
			break;
		}
		return nameText;
		
	}

}
