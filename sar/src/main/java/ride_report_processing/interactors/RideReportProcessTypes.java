package ride_report_processing.interactors;

public enum RideReportProcessTypes {	
	POSTED_RIDE_REPORT_PROCESS(907),
	TAKEN_RIDE_REPORT_PROCESS(911);
	
	private final int pid;
	
	private RideReportProcessTypes(int mPid) {
		this.pid = mPid;
	}
	
	public int getPid() {
		return pid;
	}
	


}
