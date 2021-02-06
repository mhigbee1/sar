package ride_report_processing.interactors;

import java.util.Iterator;

import ride_processing.boundaries.RideOperationsUtility;
import ride_report_processing.boundaries.RideReportProcess;

public class TakenRideReportsProcess extends RideReportProcess{
	
	public static final int PID = 911;
	public static final String NAME_TEXT = "Rides taken between two dates";

	public TakenRideReportsProcess(Iterator<RideOperationsUtility> mRidesRecord, String mStartDate, String mEndDate) {
		super(mRidesRecord, mStartDate, mEndDate);
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
