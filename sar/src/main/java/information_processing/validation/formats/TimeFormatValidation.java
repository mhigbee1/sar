package information_processing.validation.formats;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import information_processing.boundaries.InformationIDsValidation;
import response_generation.boundaries.DateTimeFormatUtility;

public class TimeFormatValidation implements InformationIDsValidation{
	
	private String time;
	private String errorMsg;
	
	public TimeFormatValidation(String mTime, String mErrorMsg) {
		this.time = mTime;
		this.errorMsg = mErrorMsg;
	}
	
	@Override
	public void validate() throws IllegalArgumentException {
		DateFormat dateFormat = new SimpleDateFormat(DateTimeFormatUtility.TIME_FORMAT);
		dateFormat.setLenient(false);
		try {
			dateFormat.parse(time);
		}catch(ParseException pe) {
			throw new IllegalArgumentException(errorMsg);
		}
	}

}
