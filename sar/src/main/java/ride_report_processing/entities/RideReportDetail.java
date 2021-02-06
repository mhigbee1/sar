package ride_report_processing.entities;

public class RideReportDetail {
	
	private String originZip;
	private String destinationZip;
	private int count;
	
	
	public RideReportDetail(String mOriginZip, String mDestinationZip) {
		this.originZip = mOriginZip;
		this.destinationZip = mDestinationZip;
		this.count = 1;
	}
	
	public String getOriginZip() {
		return originZip;
	}
	
	public String getDestinationZip() {
		return destinationZip;
	}
	
	public int getCount() {
		return count;
	}
	
	public void incrementCount() {
		count++;
	}

}
