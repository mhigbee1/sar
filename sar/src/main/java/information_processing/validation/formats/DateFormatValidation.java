package information_processing.validation.formats;

import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import information_processing.boundaries.InformationIDsValidation;
import response_generation.boundaries.DateTimeFormatUtility;

public class DateFormatValidation implements InformationIDsValidation{
	private String date;
	private String errorMsg;
	
	public DateFormatValidation(String mDate, String mErrorMsg) {
		this.date = mDate;
		this.errorMsg = mErrorMsg;
	}
	
	@Override
	public void validate() throws IllegalArgumentException{
		DateTimeFormatter dateFormat = DateTimeFormatUtility.FORMATTED_DATE;
		try {
			dateFormat.parse(date);
		}catch (DateTimeParseException pe){
			pe.printStackTrace();
			throw new IllegalArgumentException(errorMsg);
		}
	}
}
