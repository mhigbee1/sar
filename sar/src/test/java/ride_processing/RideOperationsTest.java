package ride_processing;

import static org.junit.jupiter.api.Assertions.*;

import java.time.format.DateTimeParseException;
import java.util.Iterator;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import information_processing.boundaries.ConfirmationType;
import information_processing.entities.RideInformation;
import information_processing.entities.RideRequestInformation;
import information_processing.entities.UpdateRideRequestInfo;
import information_processing.entities.UserMessagingInfo;
import message_processing.entities.Message;
import ride_processing.boundaries.RideOperationsUtility;
import ride_processing.interactors.RideOperations;

class RideOperationsTest {
	
	private RideOperations rideOperator;
	private RideInformation rideInformation;
	private int rid;
	private static final int AID = 123;
	private static final String ORIGIN_CITY = "Chicago";
	private static final String ORIGIN_ZIP = "60618";
	private static final String DESTINATION_CITY = "Phoenix";
	private static final String DESTINATION_ZIP = "85248";
	private static final String DATE = "23-Dec-2020";
	private static final String TIME = "08:30";
	private static final String VEHICLE_MAKE = "Honda";
	private static final String VEHICLE_MODEL = "Civic";
	private static final String VEHICLE_COLOR = "Black";
	private static final String VEHICLE_PLATE_NUM = "E13084";
	private static final String VEHICLE_PLATE_STATE = "IL";
	private static final Integer VEHICLE_CAPACITY = 3;
	private static final String VEHICLE_RULES = "Small bags only";
	private static final Double RATE_PER_PASSENGER = 25.00;
	
	private boolean verifyMatchingRideInformation(RideOperationsUtility rideKey, RideInformation rideInfoKey) {
		return(
				rideKey.getAid() == rideInfoKey.getAid() &&
				rideKey.getOriginCity().equals(rideInfoKey.getOriginCity()) &&
				rideKey.getOriginZip().equals(rideInfoKey.getOriginZip()) &&
				rideKey.getDestinationCity().equals(rideInfoKey.getDestinationCity()) &&
				rideKey.getDestinationZip().equals(rideInfoKey.getDestinationZip()) &&
				rideKey.getRideDate().equals(rideInfoKey.getRideDate()) &&
				rideKey.getRideTime().equals(rideInfoKey.getRideTime()) &&
				rideKey.getVehicleMake().equals(rideInfoKey.getVehicleMake()) &&
				rideKey.getVehicleModel().equals(rideInfoKey.getVehicleModel()) &&
				rideKey.getVehicleColor().equals(rideInfoKey.getVehicleColor()) &&
				rideKey.getVehiclePlateNum().equals(rideInfoKey.getVehiclePlateNum()) &&
				rideKey.getVehiclePlateState().equals(rideInfoKey.getVehiclePlateState()) &&
				rideKey.getVehicleRules().equals(rideInfoKey.getVehicleRules()) &&
				rideKey.getRatePerPassenger().equals(rideInfoKey.getRatePerPassenger())
				);
	}
	
	@BeforeEach
	void init() {
		rideOperator = new RideOperations();
	}
	
	@Nested
	class FindRideTest{
		@BeforeEach
		void init() {
			rideInformation = new RideInformation(
					AID,
					ORIGIN_CITY,
					ORIGIN_ZIP,
					DESTINATION_CITY,
					DESTINATION_ZIP,
					DATE,
					TIME,
					VEHICLE_MAKE,
					VEHICLE_MODEL,
					VEHICLE_COLOR,
					VEHICLE_PLATE_NUM,
					VEHICLE_PLATE_STATE,
					VEHICLE_CAPACITY,
					VEHICLE_RULES,
					RATE_PER_PASSENGER);
			rid = rideOperator.addRide(rideInformation);
		}
		
		@AfterEach
		void clean() {
			rideOperator.removeRide(rid);
		}
		
		@Test
		void test_updateRide_returnsUpdatedRideInfo() {
			String newOriginCity = "Seattle";
			String newOriginZip = "98131";
			String newDestinationCity = "Chicago";
			String newDestinationZip = "60637";
			String newDate = "25-Jan-2021";
			String newTime = "13:30";
			RideInformation updatedRideInfo = new RideInformation(
					AID,
					newOriginCity,
					newOriginZip,
					newDestinationCity,
					newDestinationZip,
					newDate,
					newTime,
					VEHICLE_MAKE,
					VEHICLE_MODEL,
					VEHICLE_COLOR,
					VEHICLE_PLATE_NUM,
					VEHICLE_PLATE_STATE,
					VEHICLE_CAPACITY,
					VEHICLE_RULES,
					RATE_PER_PASSENGER);
			rideOperator.updateRide(rid, updatedRideInfo);
			assertTrue(verifyMatchingRideInformation(rideOperator.getRide(rid), updatedRideInfo));		
		}
		
		@Test
		void test_getRide_keyIsRid_returnsTrueForMatch() {
			RideOperationsUtility rideOpUtil = rideOperator.getRide(rid);
			assertEquals(rid, rideOpUtil.getRid());
		}
		
		@Test
		void test_findRide_originKeyIsOriginCity_returnsRID() {
			Iterator<RideOperationsUtility> iterator = rideOperator.findRide(ORIGIN_CITY, "", "");
			RideOperationsUtility rideOperationUtil = null;
			while(iterator.hasNext()) {
				rideOperationUtil = iterator.next();
			}
			assertEquals(rid, rideOperationUtil.getRid());
		}
		
		@Test
		void test_findRide_originKeyIsOriginZip_returnsRID() {
			Iterator<RideOperationsUtility> iterator = rideOperator.findRide(ORIGIN_ZIP, "", "");
			RideOperationsUtility rideOperationUtil = null;
			while(iterator.hasNext()) {
				rideOperationUtil = iterator.next();
			}
			assertEquals(rid, rideOperationUtil.getRid());
		}
		
		@Test
		void test_findRide_originKeyIsUnmatchedString_returnsEmptyIterator() {
			Iterator<RideOperationsUtility> iterator = rideOperator.findRide("unmatched", "", "");
			assertFalse(iterator.hasNext());
		}
		
		@Test
		void test_findRide_destinationKeyIsDestinationCity_returnsRID() {
			Iterator<RideOperationsUtility> iterator = rideOperator.findRide("", DESTINATION_CITY, "");
			RideOperationsUtility rideOperationUtil = null;
			while(iterator.hasNext()) {
				rideOperationUtil = iterator.next();
			}
			assertEquals(rid, rideOperationUtil.getRid());
		}
		
		@Test
		void test_findRide_destinationKeyIsDestinationZip_returnsRID() {
			Iterator<RideOperationsUtility> iterator = rideOperator.findRide("", DESTINATION_ZIP, "");
			RideOperationsUtility rideOperationUtil = null;
			while(iterator.hasNext()) {
				rideOperationUtil = iterator.next();
			}
			assertEquals(rid, rideOperationUtil.getRid());
		}
		
		@Test
		void test_findRide_destinationKeyIsUnmatchedString_returnsEmptyIterator() {
			Iterator<RideOperationsUtility> iterator = rideOperator.findRide("", "unmatched", "");
			assertFalse(iterator.hasNext());
		}
		
		@Test
		void test_findRide_dateKeyIsDATE_returnsRID() {
			Iterator<RideOperationsUtility> iterator = rideOperator.findRide("", "", DATE);
			RideOperationsUtility rideOperationUtil = null;
			while(iterator.hasNext()) {
				rideOperationUtil = iterator.next();
			}
			assertEquals(rid, rideOperationUtil.getRid());
		}
		
		@Test
		void test_findRide_dateKeyIsTIME_returnsRID() {
			Iterator<RideOperationsUtility> iterator = rideOperator.findRide("", "", TIME);
			RideOperationsUtility rideOperationUtil = null;
			while(iterator.hasNext()) {
				rideOperationUtil = iterator.next();
			}
			assertEquals(rid, rideOperationUtil.getRid());
		}
		
		@Test
		void test_findRide_dateKeyIsUnmatchedString_returnsEmptyIterator() {
			Iterator<RideOperationsUtility> iterator = rideOperator.findRide("", "", "unmatched");
			assertFalse(iterator.hasNext());
		}
		
		@Test
		void test_isExistingRide_ridReturnsTrue() {
			assertTrue(rideOperator.isExistingRide(rid));
		}
		
		@Test
		void test_isExistingRide_unmatchedRidReturnsFalse() {
			assertFalse(rideOperator.isExistingRide(999));
		}
		
		@Test
		void test_addRideRequest_returnsTrueIfConnectedToRide() {
			int aid = 234;
			int PASSENGER_COUNT = 3;
			RideRequestInformation rideReqInfo = new RideRequestInformation(aid, PASSENGER_COUNT, null, null);
			int jid = rideOperator.addRideRequest(rid, rideReqInfo);
			assertTrue(rideOperator.isExistingRideRequest(rid, jid));
		}
		
		@Test
		void test_setRideConfirmation_addRideRequestWithActiveStatusTrue_returnsTrueForRideRider() {
			int aid = 345;
			int PASSENGER_COUNT = 3;
			RideRequestInformation rideReqInfo = new RideRequestInformation(aid, PASSENGER_COUNT, null, null);
			int jid = rideOperator.addRideRequest(rid, rideReqInfo);
			UpdateRideRequestInfo updateRideReqInfo = new UpdateRideRequestInfo(aid, true, ConfirmationType.RIDE_CONFIRMED);
			rideOperator.setRideConfirmation(rid, jid, updateRideReqInfo);
			assertTrue(rideOperator.isAccountRideRider(rid, aid));
		}
		
		@Test
		void test_setPickUpConfirmation_returnsTrue() {
			int aid = 345;
			int PASSENGER_COUNT = 3;
			RideRequestInformation rideReqInfo = new RideRequestInformation(aid, PASSENGER_COUNT, null, null);
			int jid = rideOperator.addRideRequest(rid, rideReqInfo);
			rideOperator.setPickUpConfirmation(rid, jid);
			assertTrue(rideReqInfo.getPickUpConfirmation());
		}
		
		@Test
		void test_isAccountRideRider_unconfirmed_returnsFalse() {
			int aid = 456;
			int PASSENGER_COUNT = 3;
			RideRequestInformation rideReqInfo = new RideRequestInformation(aid, PASSENGER_COUNT, null, null);
			rideOperator.addRideRequest(rid, rideReqInfo);
			assertFalse(rideOperator.isAccountRideRider(rid, aid));
		}
		
		@Test
		void test_isAccountRideDriver_returnTrueForAID() {
			assertTrue(rideOperator.isAccountRideDriver(rid, AID));
		}
		
		@Test
		void test_isAccountRideDriver_unmatchedAid_returnsFalse() {
			assertFalse(rideOperator.isAccountRideDriver(rid, 999));
		}
		
		@Test
		void test_getMessages_defaultReturnsEmtpyIterator() {
			Iterator<Message> iterator = rideOperator.getMessages(rid);
			assertFalse(iterator.hasNext());
		}
		
		@Test
		void test_addMessage_matchedMID_returnsTrue() {
			int aid = 111;
			String msgText = "Take me home";
			int MID = rideOperator.addMessage(rid, new UserMessagingInfo(aid, msgText));
			Iterator<Message> iterator = rideOperator.getMessages(rid);
			Message messageInstance = null;
			while(iterator.hasNext()) {
				messageInstance = iterator.next();
			}
			assertEquals(MID, messageInstance.getMid());
		}
		
		@Test
		void test_getRideDate_returnsDateFromRid() {
			assertEquals(DATE, rideOperator.getRideDate(rid));
		}
		
		@Test
		void test_findRidesInDateRange_startDateKeyisDATE_returnsIteratorWithRid() {
			assertDoesNotThrow(()->{
				Iterator<RideOperationsUtility> iterator = rideOperator.findRidesInDateRange(DATE, "");
				RideOperationsUtility rideInstance = null;
				while(iterator.hasNext()) {
					rideInstance = iterator.next();
				}
				assertEquals(rid, rideInstance.getRid());
			});
		}
		
		@Test
		void test_findRidesInDateRange_keysAreEmptyStrings_returnsIteratorWithRid() {
			assertDoesNotThrow(()->{
				Iterator<RideOperationsUtility> iterator = rideOperator.findRidesInDateRange("", "");
				RideOperationsUtility rideInstance = null;
				while(iterator.hasNext()) {
					rideInstance = iterator.next();
				}
				assertEquals(rid, rideInstance.getRid());
			});
		}
		
		@Test
		void test_findRidesInDateRange_endDateKeyisBeforeDATE_returnsEmptyIterator() {
			assertDoesNotThrow(()->{
				Iterator<RideOperationsUtility> iterator = rideOperator.findRidesInDateRange("", "05-Dec-2020");
				assertFalse(iterator.hasNext());
			});
		}
		
		@Test
		void test_findRidesInDateRange_keyIsImproperFormatting_throwsParseException() {
			assertThrows(DateTimeParseException.class,()->{
				rideOperator.findRidesInDateRange("unmatched", "unmatched");
			});
		}
	}
	
	@Test
	void test_findRide_keyIsEmptyStrings_returnsEmptyIterator() {
		Iterator<RideOperationsUtility>iterator = rideOperator.findRide("", "", "");
		assertFalse(iterator.hasNext());
	}
	
	@Test
	void test_findRidesInDateRange_keyIsEmptyStrings_returnsEmptyIterator() {
		assertDoesNotThrow(()-> {
			Iterator<RideOperationsUtility>iterator = rideOperator.findRidesInDateRange("", "");
			assertFalse(iterator.hasNext());
		});
	}
	
}
