package information_processing.validation;

import java.util.ArrayList;
import java.util.List;

import information_processing.boundaries.InformationIDsValidation;
import information_processing.entities.RideInformation;
import information_processing.validation.formats.DateFormatValidation;
import information_processing.validation.formats.StateCodeFormatValidation;
import information_processing.validation.formats.TimeFormatValidation;
import information_processing.validation.states.NonEmptyStringValidation;
import information_processing.validation.states.NonNullObjectValidation;
import information_processing.validation.values.NonNegativeDoublesValidation;
import information_processing.validation.values.PositiveIntegerValidation;
import response_generation.boundaries.ResponseBodyUtility;

public class RideInfoValidation implements InformationIDsValidation{
	
	private List<InformationIDsValidation> validators = new ArrayList<InformationIDsValidation>();
	
	public RideInfoValidation(RideInformation rideInformation) {
		validators.add(new NonNullObjectValidation(
				rideInformation.getAid(),
				ResponseBodyUtility.ERR_MSG_INVALID_ACCOUNT_ID));
		validators.add(new NonNullObjectValidation(
				rideInformation.getOriginCity(),
				ResponseBodyUtility.ERR_MSG_INVALID_ORIGIN_CITY));
		validators.add(new NonEmptyStringValidation(
				rideInformation.getOriginCity(),
				ResponseBodyUtility.ERR_MSG_INVALID_ORIGIN_CITY));
		validators.add(new NonNullObjectValidation(
				rideInformation.getDestinationCity(),
				ResponseBodyUtility.ERR_MSG_INVALID_DESTINATION_CITY));
		validators.add(new NonEmptyStringValidation(
				rideInformation.getDestinationCity(),
				ResponseBodyUtility.ERR_MSG_INVALID_DESTINATION_CITY));
		validators.add(new NonNullObjectValidation(
				rideInformation.getVehicleMake(),
				ResponseBodyUtility.ERR_MSG_INVALID_VEHICLE_MAKE));
		validators.add(new NonEmptyStringValidation(
				rideInformation.getVehicleMake(),
				ResponseBodyUtility.ERR_MSG_INVALID_VEHICLE_MAKE));
		validators.add(new NonNullObjectValidation(
				rideInformation.getVehicleModel(),
				ResponseBodyUtility.ERR_MSG_INVALID_VEHICLE_MODEL));
		validators.add(new NonEmptyStringValidation(
				rideInformation.getVehicleModel(),
				ResponseBodyUtility.ERR_MSG_INVALID_VEHICLE_MODEL));
		validators.add(new NonNullObjectValidation(
				rideInformation.getVehicleColor(),
				ResponseBodyUtility.ERR_MSG_INVALID_VEHICLE_COLOR));
		validators.add(new NonEmptyStringValidation(
				rideInformation.getVehicleColor(),
				ResponseBodyUtility.ERR_MSG_INVALID_VEHICLE_COLOR));
		validators.add(new NonNullObjectValidation(
				rideInformation.getVehiclePlateNum(),
				ResponseBodyUtility.ERR_MSG_INVALID_VEHICLE_PLATE_NUM));
		validators.add(new NonEmptyStringValidation(
				rideInformation.getVehiclePlateNum(),
				ResponseBodyUtility.ERR_MSG_INVALID_VEHICLE_PLATE_NUM));
		validators.add(new NonNullObjectValidation(
				rideInformation.getVehiclePlateState(),
				ResponseBodyUtility.ERR_MSG_INVALID_VEHICLE_PLATE_STATE));
		validators.add(new StateCodeFormatValidation(
				rideInformation.getVehiclePlateState(),
				ResponseBodyUtility.ERR_MSG_INVALID_VEHICLE_PLATE_STATE));
		validators.add(new NonNullObjectValidation(
				rideInformation.getVehicleCapacity(),
				ResponseBodyUtility.ERR_MSG_INVALID_VEHICLE_CAPACITY));
		validators.add(new PositiveIntegerValidation(
				rideInformation.getVehicleCapacity(),
				ResponseBodyUtility.ERR_MSG_INVALID_VEHICLE_CAPACITY));
		validators.add(new NonNullObjectValidation(
				rideInformation.getVehicleRules(),
				ResponseBodyUtility.ERR_MSG_INVALID_VEHICLE_RULES));
		if(rideInformation.getRatePerPassenger() !=null) {
			validators.add(new NonNegativeDoublesValidation(
				rideInformation.getRatePerPassenger(),
				ResponseBodyUtility.ERR_MSG_INVALID_RATE_PER_PASSENGER));
		}
		validators.add(new NonNullObjectValidation(
				rideInformation.getRideDate(),
				ResponseBodyUtility.ERR_MSG_INVALID_RIDE_DATE));
		validators.add(new DateFormatValidation(
				rideInformation.getRideDate(),
				ResponseBodyUtility.ERR_MSG_INVALID_RIDE_DATE));
		validators.add(new NonNullObjectValidation(
				rideInformation.getRideTime(),
				ResponseBodyUtility.ERR_MSG_INVALID_RIDE_TIME));
		validators.add(new TimeFormatValidation(
				rideInformation.getRideTime(),
				ResponseBodyUtility.ERR_MSG_INVALID_RIDE_TIME));
		
	}

	@Override
	public void validate() throws IllegalArgumentException {
		for(InformationIDsValidation validator : validators) {
			validator.validate();
		}
		
	}

}
