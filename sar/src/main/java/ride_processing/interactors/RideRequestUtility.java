package ride_processing.interactors;

public interface RideRequestUtility {
	
	public int getJid();
	
	public int getAid();
	
	public Boolean getRideConfirmation();
	
	public void setRideConfirmation(boolean status);
	
	public void setPickUpConfirmation();

}
