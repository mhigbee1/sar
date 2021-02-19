package response_generation.boundaries;

import java.text.ParseException;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.ResolverStyle;
import java.util.TimeZone;

public abstract class DateTimeFormatUtility {
	public static final String DEFAULT_FORMAT = "dd-MMM-uuuu, HH:mm:ss";
	public static final String DATE_FORMAT = "dd-MMM-uuuu";
	public static final String TIME_FORMAT = "HH:mm";
	
	
	public static final DateTimeFormatter FORMATTED_DATETIME = DateTimeFormatter.ofPattern(DEFAULT_FORMAT).withResolverStyle(ResolverStyle.STRICT);
	public static final DateTimeFormatter FORMATTED_DATE = DateTimeFormatter.ofPattern(DATE_FORMAT).withResolverStyle(ResolverStyle.STRICT);
	public static final DateTimeFormatter FORMATTED_TIME = DateTimeFormatter.ofPattern(TIME_FORMAT);
	
	
	public static String getDefaultFormat(Instant instant) {
		ZonedDateTime zdtDateTime = ZonedDateTime.ofInstant(instant, TimeZone.getDefault().toZoneId());
		return FORMATTED_DATETIME.format(zdtDateTime);
	}
	
	
	public static String getDateFormat(Instant instant) {
		ZonedDateTime zdtDate = ZonedDateTime.ofInstant(instant, TimeZone.getDefault().toZoneId());
		return FORMATTED_DATE.format(zdtDate);
	}
	
	
	public static String getTimeFormatInst(Instant instant) {
		ZonedDateTime zdtDate = ZonedDateTime.ofInstant(instant, TimeZone.getDefault().toZoneId());
		return FORMATTED_TIME.format(zdtDate);
	}
	
	public static long extractTimeStampFromDateFormat(String date) throws ParseException {
		if(date.isEmpty()) {
			return -1;
		}
		
		DateTimeFormatter dDTF = DateTimeFormatter.ofPattern(DATE_FORMAT);
		LocalDate dateBooking = LocalDate.parse(date,dDTF);
		ZoneId defaultZone = TimeZone.getDefault().toZoneId();
		long epoch = dateBooking.atStartOfDay(defaultZone).toEpochSecond();
		
		return epoch;
	
	}
	
}