package ride_report_processing.interactors;

import java.util.Iterator;

import ride_processing.boundaries.RideOperationsUtility;
import ride_report_processing.boundaries.RideReportProcess;

public class PostedRideReportsProcess extends RideReportProcess{
	
	public static final int PID = 907;
	public static final String NAME_TEXT = "Rides posted between two dates";
	
	public PostedRideReportsProcess(Iterator<RideOperationsUtility> ridesRecord, String startDate, String endDate) {
		super(ridesRecord, startDate, endDate);
	}
	
	@Override
	public int getPid() {
		return PID;
	}
	
	@Override
	public String getReportNameText() {
		return NAME_TEXT;
	}

}
