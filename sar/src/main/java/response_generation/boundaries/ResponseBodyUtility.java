package response_generation.boundaries;

public interface ResponseBodyUtility {
	
	//Response Parameter Key/Value Pairs
	public static final String RIDE_ID_KEY = "rid";
	public static final String MESSAGE_ID_KEY = "mid";
	public static final String ACCOUNT_ID_KEY = "aid";
	public static final String REPORT_ID_KEY = "pid";
	public static final String RATING_ID_KEY = "sid";
	public static final String RIDE_REQUEST_ID_KEY = "jid";
	
	public static final String NAME_KEY = "name";
	public static final String CREATED_ON_DATE_KEY = "date_created";
	public static final String FIRST_NAME_KEY = "first_name";
	public static final String LAST_NAME_KEY = "last_name";
	public static final String PHONE_NUMBER_KEY = "phone";
	public static final String USER_PIC_KEY = "picture";
	public static final String ACTIVE_STATUS_KEY = "is_active";
	
	public static final String LOCATION_INFO_HEADER_KEY = "location_info";
	public static final String ORIGIN_CITY_KEY = "from_city";
	public static final String ORIGIN_ZIP_KEY = "from_zip";
	public static final String DESTINATION_CITY_KEY = "to_city";
	public static final String DESTINATION_ZIP_KEY = "to_zip";
	public static final String DATE_TIME_HEADER_KEY = "date_time";
	public static final String RIDE_DATE_KEY = "date";
	public static final String RIDE_TIME_KEY = "time";
	public static final String VEHICLE_INFO_HEADER_KEY = "car_info";
	public static final String VEHICLE_MAKE_KEY = "make";
	public static final String VEHICLE_MODEL_KEY = "model";
	public static final String VEHICLE_COLOR_KEY = "color";
	public static final String VEHICLE_PLATE_STATE_KEY = "plate_state";
	public static final String VEHICLE_PLATE_NUM_KEY = "plate_serial";
	public static final String VEHICLE_CAPACITY_KEY = "max_passengers";
	public static final String VEHICLE_RULES_KEY = "conditions";
	public static final String RATE_PER_PASSENGER_KEY = "amount_per_passenger";
	
	public static final String DRIVER_KEY = "driver";
	public static final String DRIVER_USER_PIC_KEY = "driver_picture";
	public static final String RIDE_COUNT_KEY = "rides";
	public static final String RATING_DATE_KEY = "date";
	public static final String RATING_COUNT_KEY = "ratings";
	public static final String RATING_KEY = "rating";
	public static final String AVG_RATING_KEY = "average_rating";
	public static final String COMMENT_KEY = "comment";
	public static final String DRIVER_COMMENTS_KEY = "comments_about_driver";
	
	public static final String PASSENGERS_KEY = "passengers";
	public static final String RIDE_CONFIRMATION_KEY = "ride_confirmed";
	public static final String PICKUP_CONFIRMATION_KEY = "pickup_confirmed";
	
	public static final String MSG_TEXT_KEY = "msg";
	public static final String MSG_BODY_KEY = "body";
	public static final String MSG_DATE_KEY = "date";
	public static final String SENT_BY_ACCOUNT_KEY = "sent_by_aid";
	public static final String SENT_BY_ID_KEY = "sent_by_id";
	
	public static final String DETAIL_KEY = "detail";
	public static final String LOCATION_KEY = "location";
	public static final String REPORT_START_DATE_KEY = "start_date";
	public static final String REPORT_END_DATE_KEY = "end_date";
	public static final String COUNT_KEY = "count";
	
	public static final String ERR_RESPONSE_TYPE_KEY = "type";
	public static final String ERR_RESPONSE_TYPE_MSG = "http://cs.iit.edu/~virgil/cs445/project/api/problems/data-validation";
	public static final String ERR_RESPONSE_TITLE_KEY = "title";
	public static final String ERR_RESPONSE_TITLE_MSG = "Your request data didn't pass validation";
	public static final String ERR_RESPONSE_DETAIL_KEY = "detail";
	public static final String ERR_RESPONSE_STATUS_KEY = "status";
	public static final String ERR_RESPONSE_INSTANCE_KEY = "instance";
		
	
	//Error Messaging Constants
	public static final String ERR_MSG_INVALID_RIDE_ID = "Invalid value for rid";
	public static final String ERR_MSG_INVALID_MESSAGE_ID = "Invalid value for mid";
	public static final String ERR_MSG_INVALID_ACCOUNT_ID = "Invalid value for aid";
	public static final String ERR_MSG_INVALID_REPORT_ID = "Invalid value for pid";
	public static final String ERR_MSG_INVALID_JOIN_RIDE_REQUEST_ID_KEY = "Invalid value for jid";
	
	public static final String ERR_MSG_INVALID_FIRST_NAME = "The first name appears to be invalid.";
	public static final String ERR_MSG_INVALID_LAST_NAME = "The last name appears to be invalid";
	public static final String ERR_MSG_INVALID_PHONE_NUM = "Invalid phone number";
	public static final String ERR_MSG_INVALID_USER_PIC = "Invalid profile picture";
	public static final String ERR_MSG_INVALID_ACTIVE_STATUS = "Invalid value for is_active";
	
	public static final String ERR_MSG_INVALID_ORIGIN_CITY = "Invalid value for from_city";
	public static final String ERR_MSG_INVALID_ORIGIN_ZIP = "Invalid value for from_zip";
	public static final String ERR_MSG_INVALID_DESTINATION_CITY = "Invalid value for to_city";
	public static final String ERR_MSG_INVALID_DESTINATION_ZIP = "Invalid value for to_zip";
	public static final String ERR_MSG_INVALID_RIDE_DATE = "Invalid date";
	public static final String ERR_MSG_INVALID_RIDE_TIME = "Invalid time";
	public static final String ERR_MSG_INVALID_VEHICLE_MAKE = "Invalid value for make";
	public static final String ERR_MSG_INVALID_VEHICLE_MODEL = "Invalid value for model";
	public static final String ERR_MSG_INVALID_VEHICLE_COLOR = "Invalid value for color";
	public static final String ERR_MSG_INVALID_VEHICLE_PLATE_STATE = "Invalid value for plate_state";
	public static final String ERR_MSG_INVALID_VEHICLE_PLATE_NUM = "Invalid value for plate_serial";
	public static final String ERR_MSG_INVALID_VEHICLE_CAPACITY = "Invalid value for max_passengers";
	public static final String ERR_MSG_INVALID_VEHICLE_RULES = "Invalid value for conditions";
	public static final String ERR_MSG_INVALID_RATE_PER_PASSENGER = "Invalid value for amount_per_passenger";
	public static final String ERR_MSG_INVALID_RATING = "Invalid value for rating";
	public static final String ERR_MSG_INVALID_COMMENT = "Invalid value for comment";
	public static final String ERR_MSG_INVALID_PASSENGERS = "Invalid value for passengers";
	public static final String ERR_MSG_INVALID_RIDE_CONFIRMATION = "Invalid value for ride_confirmed";
	public static final String ERR_MSG_INVALID_PICKUP_CONFIRMATION = "Invalid value for pickup_confirmed";
	public static final String ERR_MSG_INVALID_MSG_TEXT = "Invalid value for msg";
	public static final String ERR_MSG_INVALID_SENT_BY_ID = "Invalid value for sent_by_id";
	public static final String ERR_MSG_INVALID_ACCOUNT_CREATOR = "Only the creator of the ride may change it";
	public static final String ERR_MSG_ACCOUNT_CREATOR_JOIN_REQUEST = "The creator of the ride may not also become a rider";
	public static final String ERR_MSG_ACCOUNT_ALREADY_ACTIVE = "Account is already active";
	public static final String ERR_MSG_ACCOUNT_NOT_ACTIVE = "Account is not active";
	public static final String ERR_MSG_ACCOUNT_RATE_SELF = "Accounts cannot rate themselves";
	
	//CustomizedErrorMessaging
	public static String invalidAccountErrMessage(int aid) {
		return "Account identified by (" + aid + ") does not exist or is not active.";
	}
	
	public static String invalidAccountErrMessageRideConfirmation(int rid, int aid) {
		return "Account identified by (" + aid + ") didn't create the ride ("+ rid + ")";
	}
	
	public static String invalidAccountErrMessagePickUpConfirmation(int rid, int aid) {
		return "Account identified by (" + aid + ") has not requested to join this ride ("+ rid +")";
	}
	
	public static String nonExistingRideErrMessage(int rid) {
		return "Ride ("+rid+") does not exist";
	}
	
	public static String inactiveAccountErrMessageAddRide(int aid) {
		return "Account identified by ("+aid+") is not active and may not create rides";
	}
	
	public static String inactiveAccountErrMessageAddRideRequest(int aid) {
		return "Account identified by ("+aid+") is not active and may not request to join ride";
	}
	
	public static String existingRequestErrMessageJoinRequest(int rid, int aid) {
		return "Account identified by("+aid+") has already sent a join request for ride ("+rid+")";
	}
	
	public static String invalidRatingErrMessageNoSuchRider(int rid, int aid) {
		return "Account identified by ("+aid+") must be creator of ride ("+rid+") or a rider to provide rating";
	}
	
	
	public String generateResponseBody();
	
	
}
