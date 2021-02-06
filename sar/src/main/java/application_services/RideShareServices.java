package application_services;

import java.text.ParseException;
import java.util.Iterator;

import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.core.UriInfo;

import account_operations.boundaries.AccountOperationsBoundary;
import account_operations.boundaries.AccountOperationsUtility;
import account_operations.interactors.AccountOperations;
import information_processing.boundaries.ConfirmationType;
import information_processing.boundaries.ConfirmationUtilities;
import information_processing.boundaries.InformationIDs;
import information_processing.boundaries.InformationIDsCreator;
import information_processing.creators.RateInformationCreator;
import information_processing.creators.RideInformationCreator;
import information_processing.creators.RideRequestInfoCreator;
import information_processing.creators.UpdateRideRequestInfoCreator;
import information_processing.creators.UserInformationCreator;
import information_processing.creators.UserMessagingInfoCreator;
import rate_processing.boundaries.RatingType;
import response_generation.boundaries.ResponseBodyUtility;
import response_generation.generators.ApplicationResponseBodyGenerator;
import response_generation.generators.ErrorMessagingResponseBodyGenerator;
import response_generation.generators.PostResponseBodyGenerator;
import response_generation.generators.RideReportDetailResponseBodyGenerator;
import response_generation.generators.SearchRidesResponseBodyGenerator;
import response_generation.generators.ViewAllRideReportsResponseBodyGenerator;
import response_generation.generators.ViewRatingsResponseBodyGenerator;
import response_generation.generators.ViewRideReportDetailResponseBodyGenerator;
import ride_processing.boundaries.RideOperationsBoundary;
import ride_processing.boundaries.RideOperationsUtility;
import ride_processing.interactors.RideOperations;
import ride_report_processing.boundaries.RideReportProcess;
import ride_report_processing.interactors.RideReportBuilder;


//APPLICATION SERVICES
@Path("")
public class RideShareServices {
	
	AccountOperationsBoundary accountOperator = new AccountOperations();
	RideOperationsBoundary rideOperator = new RideOperations();
	private final int BAD_REQUEST_CODE = Status.BAD_REQUEST.getStatusCode();
	
	
	@Path("accounts")
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response addAccount(String responseBody, @Context UriInfo uri) {
		InformationIDsCreator userInfoCreator = UserInformationCreator.NEW_ACCOUNT();
		InformationIDs userInfo = userInfoCreator.create(responseBody);
		int aid = accountOperator.addAccount(userInfo);
		String errMsgDetail;
		
		
		if(userInfo.isEmptyRequest()) {
			return emptyRequestResponseHelper(uri, userInfo).build();
		}
		
		if(accountOperator.isExistingPhoneNumber(userInfo)) {
			errMsgDetail = ResponseBodyUtility.ERR_MSG_INVALID_PHONE_NUM;
			return accountBadRequestResponseHelper(uri, errMsgDetail).build();
		}
	
		return new ApplicationResponseBodyGenerator(Status.CREATED)
				.addLocationHeader(Integer.toString(aid), uri)
				.addResponseBody(new PostResponseBodyGenerator(
						ResponseBodyUtility.ACCOUNT_ID_KEY, aid))
				.build();
		
	}
	
	
	@Path("accounts/{aid}/status")
	@PUT
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response activateAccount(@PathParam("aid") int aid, String responseBody, @Context UriInfo uri) {
		String errMsgDetail;
		
		if(!accountOperator.isExistingAccount(aid)) {
			errMsgDetail = ResponseBodyUtility.invalidAccountErrMessage(aid);
			return accountBadRequestResponseHelper(uri, errMsgDetail).build();
		}
		
		if(accountOperator.isActiveAccount(aid)) {
			errMsgDetail = ResponseBodyUtility.ERR_MSG_ACCOUNT_ALREADY_ACTIVE;
			return accountBadRequestResponseHelper(uri, errMsgDetail).build();
		}
		
		InformationIDsCreator userInfoCreator = UserInformationCreator.ACTIVE_ACCOUNT();
		InformationIDs userInfo = userInfoCreator.create(responseBody);
		
		if(userInfo.isEmptyRequest()) {
			return emptyRequestResponseHelper(uri, userInfo).build();
		}
		
		accountOperator.updateAccount(aid, userInfo);
		return new ApplicationResponseBodyGenerator(Status.NO_CONTENT).build();
	}
	
	
	@Path("accounts/{aid}")
	@PUT
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response updateAccount(@PathParam("aid") int aid, String resonseBody, @Context UriInfo uri) {
		String errMsgDetail;
		
		if(!accountOperator.isExistingAccount(aid)) {
			errMsgDetail = ResponseBodyUtility.invalidAccountErrMessage(aid);
			return accountBadRequestResponseHelper(uri,errMsgDetail).build();
		}
		
		InformationIDsCreator userInfoCreator;
		
		if(accountOperator.isActiveAccount(aid)) {
			userInfoCreator = new UserInformationCreator(true);
		}else {
			userInfoCreator = new UserInformationCreator(false);
		}
		
		InformationIDs userInfo = userInfoCreator.create(resonseBody);
		
		if(userInfo.isEmptyRequest()) {
			return emptyRequestResponseHelper(uri, userInfo).build();
		}
		
		accountOperator.updateAccount(aid, userInfo);
		return new ApplicationResponseBodyGenerator(Status.NO_CONTENT).build();
	}
	
	
	@Path("accounts/{aid}/ratings")
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response rateAccount(@PathParam("aid") int aid, String responseBody, @Context UriInfo uri) {
		
		InformationIDsCreator rateInfoCreator = new RateInformationCreator();
		InformationIDs rateInfo = rateInfoCreator.create(responseBody);
		String errMsgDetail;
		
		if(rateInfo.isEmptyRequest()) {
			return emptyRequestResponseHelper(uri, rateInfo).build();
		}
		
		int ratedByAid = rateInfo.getAid();
		if(aid==ratedByAid) {
			errMsgDetail = ResponseBodyUtility.ERR_MSG_ACCOUNT_RATE_SELF;
			return accountBadRequestResponseHelper(uri, errMsgDetail).build();
		}
		
		if(!accountOperator.isExistingAccount(aid)) {
			return new ApplicationResponseBodyGenerator(Status.NOT_FOUND).build();
		}
		
		int rid = rateInfo.getRid();
		if(!rideOperator.isExistingRide(rid)){
			errMsgDetail = ResponseBodyUtility.nonExistingRideErrMessage(rid);
			return accountBadRequestResponseHelper(uri, errMsgDetail).build();
		}
		
		if(!rideOperator.isAccountRideDriver(rid, aid) && !rideOperator.isAccountRideRider(rid, ratedByAid)) {
			errMsgDetail = ResponseBodyUtility.invalidRatingErrMessageNoSuchRider(rid, aid);
			return accountBadRequestResponseHelper(uri, errMsgDetail).build();
		}
		
		String rideDate = rideOperator.getRideDate(rid);
		int sid;
		
		if(rideOperator.isAccountRideDriver(rid, aid)) {
			sid = accountOperator.rateDriver(aid, rateInfo, rideDate);
		}else {
			sid = accountOperator.rateRider(aid, rateInfo, rideDate);
			accountOperator.setRiderRideCounter(aid);
		}
		
		return new ApplicationResponseBodyGenerator(Status.CREATED).addLocationHeader(Integer.toString(sid), uri)
				.addResponseBody(new PostResponseBodyGenerator(
						ResponseBodyUtility.RATING_ID_KEY, sid)).build();
	}
	
	
	
	@Path("accounts/{aid}/driver")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response viewDriverRatings(@PathParam("aid") int aid){
		
		if(!accountOperator.isExistingAccount(aid)) {
			return new ApplicationResponseBodyGenerator(Status.NOT_FOUND).build();
		}
		
		return new ApplicationResponseBodyGenerator(Status.OK)
				.addResponseBody(new ViewRatingsResponseBodyGenerator(
						accountOperator.getAccount(aid),
						RatingType.DRIVER)).build();
	}
	
	
	
	@Path("accounts/{aid}/rider")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response viewRiderRatings(@PathParam("aid") int aid){
		
		if(!accountOperator.isExistingAccount(aid)) {
			return new ApplicationResponseBodyGenerator(Status.NOT_FOUND).build();
		}
		
		return new ApplicationResponseBodyGenerator(Status.OK)
				.addResponseBody(new ViewRatingsResponseBodyGenerator(
						accountOperator.getAccount(aid),
						RatingType.RIDER)).build();
	}
	
	
	
	@Path("rides")
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response createRide(String responseBody, @Context UriInfo uri) {
		InformationIDsCreator rideInfoCreator = new RideInformationCreator();
		InformationIDs rideInfo = rideInfoCreator.create(responseBody);
		String errMsgDetail;
		
		if(rideInfo.isEmptyRequest()) {
			return emptyRequestResponseHelper(uri, rideInfo).build();
		}
		
		
		int aid = rideInfo.getAid();
		if(!accountOperator.isActiveAccount(aid)){
			errMsgDetail = ResponseBodyUtility.inactiveAccountErrMessageAddRide(aid);
			return accountBadRequestResponseHelper(uri, errMsgDetail).build();
		}
		
		int rid = rideOperator.addRide(rideInfo);
		return new ApplicationResponseBodyGenerator(Status.CREATED)
				.addLocationHeader(Integer.toString(rid), uri)
				.addResponseBody(new PostResponseBodyGenerator(
						ResponseBodyUtility.RIDE_ID_KEY, rid)).build();
	}
	
	
	
	
	@Path("rides")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response searchRides(
			@DefaultValue("") @QueryParam("from") String origin,
			@DefaultValue("") @QueryParam("to") String destination,
			@DefaultValue("") @QueryParam("date") String date) {
		
		Iterator<RideOperationsUtility> iterator = rideOperator.findRide(origin, destination, date);
		return new ApplicationResponseBodyGenerator(Status.OK).addResponseBody(
				new SearchRidesResponseBodyGenerator(iterator)).build();
	}
	
	
	@Path("rides/{rid}")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response viewRideReportDetail(@PathParam("rid") int rid) {
		
		if(!rideOperator.isExistingRide(rid)) {
			return new ApplicationResponseBodyGenerator(Status.NOT_FOUND).build();
		}
		
		RideOperationsUtility rideInstance = rideOperator.getRide(rid);
		int aid = rideInstance.getAid();
		AccountOperationsUtility accountInstance = accountOperator.getAccount(aid);
		
		return new ApplicationResponseBodyGenerator(Status.OK)
				.addResponseBody(new ViewRideReportDetailResponseBodyGenerator(
						accountInstance, rideInstance)).build();
	}
	
	
	@Path("rides/{rid}/join_requests")
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response createRideRequest(@PathParam("rid") int rid, String responseBody, @Context UriInfo uri) {
		
		InformationIDsCreator rideRequestInfoCreator = new RideRequestInfoCreator();
		InformationIDs rideRequestInfo = rideRequestInfoCreator.create(responseBody);
		String errMsgDetail;
		
		if(rideRequestInfo.isEmptyRequest()) {
			return emptyRequestResponseHelper(uri, rideRequestInfo).build();
		}
		
		
		int aid = rideRequestInfo.getAid();
		if(!accountOperator.isActiveAccount(aid)) {
			errMsgDetail = ResponseBodyUtility.inactiveAccountErrMessageAddRideRequest(aid);
			return accountBadRequestResponseHelper(uri, errMsgDetail).build();
		}
		
		
		if(!rideOperator.isExistingRide(rid)) {
			return new ApplicationResponseBodyGenerator(Status.NOT_FOUND).build();
		}
		
		
		if(rideOperator.submittedRideRequest(rid, aid)) {
			errMsgDetail = ResponseBodyUtility.existingRequestErrMessageJoinRequest(rid, aid);
			return accountBadRequestResponseHelper(uri, errMsgDetail).build();		
		}
		
		
		if(rideOperator.isAccountRideDriver(rid, aid)) {
			errMsgDetail = ResponseBodyUtility.ERR_MSG_ACCOUNT_CREATOR_JOIN_REQUEST;
			return accountBadRequestResponseHelper(uri, errMsgDetail).build();
		}
		
		
		int jid = rideOperator.addRideRequest(rid, rideRequestInfo);
		
		return new ApplicationResponseBodyGenerator(Status.CREATED)
				.addLocationHeader(Integer.toString(jid), uri)
				.addResponseBody(new PostResponseBodyGenerator(
						ResponseBodyUtility.RIDE_REQUEST_ID_KEY,
						jid)).build();
	}
	
	
	
	@Path("Rides/{jid}/join_requests/{jid}")
	@PATCH
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response updateRideRequest(@PathParam("rid") int rid, @PathParam("jid") int jid, String responseBody, UriInfo uri) {
	
			InformationIDsCreator updateRideRequestInfoCreator = new UpdateRideRequestInfoCreator();
			InformationIDs updateRideRequestInfo = updateRideRequestInfoCreator.create(responseBody);
			String errMsgDetail;
			
			if(updateRideRequestInfo.isEmptyRequest()) {
				return emptyRequestResponseHelper(uri, updateRideRequestInfo).build();
			}
			
			
			if(!rideOperator.isExistingRide(rid) || !rideOperator.isExistingRideRequest(rid, jid)) {
				return new ApplicationResponseBodyGenerator(Status.NOT_FOUND).build();
			}
			
			
			ConfirmationUtilities updateRideRequestConfirmation = (ConfirmationUtilities) updateRideRequestInfo;
			int aid = updateRideRequestInfo.getAid();
			
			if(updateRideRequestConfirmation.getConfirmationType() == ConfirmationType.RIDE_CONFIRMED) {
				if(rideOperator.isAccountRideDriver(rid, aid)) {
					accountOperator.setDriverRideCounter(aid);
					int rideID = rideOperator.getRideRequestCreatorID(rid, jid);
					accountOperator.setDriverRideCounter(rideID);
					rideOperator.setRideConfirmation(rid, jid, updateRideRequestInfo);
					return new ApplicationResponseBodyGenerator(Status.OK).build();
				}
				
				errMsgDetail = ResponseBodyUtility.invalidAccountErrMessageRideConfirmation(rid, aid);
				return accountBadRequestResponseHelper(uri, errMsgDetail).build();
				
				
			}else if(updateRideRequestConfirmation.getConfirmationType() == ConfirmationType.PICKUP_CONFIRMED) {
				if(rideOperator.isAccountRideRider(rid, aid)) {
					rideOperator.setPickUpConfirmation(rid, jid);
					return new ApplicationResponseBodyGenerator(Status.OK).build();
				}
				
				errMsgDetail = ResponseBodyUtility.invalidAccountErrMessagePickUpConfirmation(rid, aid);
				return accountBadRequestResponseHelper(uri, errMsgDetail).build();
			}
			
			return new ApplicationResponseBodyGenerator(Status.INTERNAL_SERVER_ERROR).build();
			
	}
	
	
	
	@Path("rides/{rid}/messages")
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response createUserMessage(@PathParam("rid") int rid, String responseBody, @Context UriInfo uri) {
		
		InformationIDsCreator userMsgInfoCreator = new UserMessagingInfoCreator();
		InformationIDs userMessagingInfo = userMsgInfoCreator.create(responseBody);
		String errMsgDetail;
		
		
		if(userMessagingInfo.isEmptyRequest()) {
			return emptyRequestResponseHelper(uri, userMessagingInfo).build();
		}
		
		
		if(!rideOperator.isExistingRide(rid)) {
			return new ApplicationResponseBodyGenerator(Status.NOT_FOUND).build();
		}
		
		int aid = userMessagingInfo.getAid();
		if(!accountOperator.isExistingAccount(aid)) {
			errMsgDetail = ResponseBodyUtility.invalidAccountErrMessage(aid);
			return accountBadRequestResponseHelper(uri, errMsgDetail).build();
		}
		
		if(!accountOperator.isActiveAccount(aid)) {
			errMsgDetail = ResponseBodyUtility.ERR_MSG_ACCOUNT_NOT_ACTIVE;
			return accountBadRequestResponseHelper(uri, errMsgDetail).build();
		}
		
		int mid = rideOperator.addMessage(rid, userMessagingInfo);
		return new ApplicationResponseBodyGenerator(Status.CREATED)
				.addLocationHeader(Integer.toString(mid), uri)
				.addResponseBody(new PostResponseBodyGenerator(
						ResponseBodyUtility.MESSAGE_ID_KEY, mid)).build();
	
	}
	
	
	
	@Path("reports")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response viewAllRideReports() {
		return new ApplicationResponseBodyGenerator(Status.OK)
				.addResponseBody(new ViewAllRideReportsResponseBodyGenerator()).build();
	}
		
	
	
	@Path("reports/{pid}")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getRideReportDetail(@PathParam("pid") int pid, 
			@DefaultValue("") @QueryParam("start_date") String startDate, 
			@DefaultValue("") @QueryParam("end_date") String endDate) {
		
		if(!RideReportProcess.isExistingProcess(pid)) {
			return new ApplicationResponseBodyGenerator(Status.NOT_FOUND).build();
		}
		
		Iterator<RideOperationsUtility> rideRecords = null;
		try {
			rideRecords = rideOperator.findRidesInDateRange(startDate, endDate);
		}catch(ParseException pe){
			pe.printStackTrace();
			return new ApplicationResponseBodyGenerator(Status.INTERNAL_SERVER_ERROR).build();
		}
		
		RideReportBuilder rideReportBuilder = new RideReportBuilder(rideRecords, startDate, endDate);
		RideReportProcess rideReportProcess = rideReportBuilder.buildRideReportsProcess(pid);
		return new ApplicationResponseBodyGenerator(Status.OK).addResponseBody(
				new RideReportDetailResponseBodyGenerator(rideReportProcess)).build();
		
	}
	
	
	
	//SERVICE RESPONSE HELPER FUNCTIONS
	private ApplicationResponseBodyGenerator accountBadRequestResponseHelper(UriInfo uri, String errorMsg) {
		
		return new ApplicationResponseBodyGenerator(Status.BAD_REQUEST)
				.addResponseBody(new ErrorMessagingResponseBodyGenerator(uri, errorMsg, BAD_REQUEST_CODE));
	}
	
	
	
	private ApplicationResponseBodyGenerator emptyRequestResponseHelper(UriInfo uri, InformationIDs userInfo) {
		return new ApplicationResponseBodyGenerator(Status.BAD_REQUEST)
				.addResponseBody(new ErrorMessagingResponseBodyGenerator(uri, userInfo, BAD_REQUEST_CODE));
	}
	
	
}
