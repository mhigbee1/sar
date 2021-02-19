package response_generation.generators;

import javax.ws.rs.core.UriInfo;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import information_processing.boundaries.InformationIDs;
import information_processing.entities.EmptyRequestInfo;
import response_generation.boundaries.ResponseBodyUtility;

public class ErrorMessagingResponseBodyGenerator implements ResponseBodyUtility{
	Gson gson = new Gson();
	JsonObject responseBody;
	private UriInfo uriInfo;
	private String errorDetail;
	private int status;
	private String uriPath;
	
	
	public ErrorMessagingResponseBodyGenerator(UriInfo mUriInfo, InformationIDs emptyRequestInfo, int mStatus) {
		this.uriInfo = mUriInfo;
		this.errorDetail = ((EmptyRequestInfo) emptyRequestInfo).getErrorMsg();
		this.status = mStatus;
		this.uriPath = "/" + uriInfo.getPath();
	}
	
	public ErrorMessagingResponseBodyGenerator(UriInfo mUriInfo, String mErrorDetail, int mStatus) {
		this.uriInfo = mUriInfo;
		this.errorDetail = mErrorDetail;
		this.status = mStatus;
		this.uriPath = "/" +  uriInfo.getPath();
	}

	@Override
	public String generateResponseBody() {
		responseBody = new JsonObject();
		responseBody.add(ResponseBodyUtility.ERR_RESPONSE_TYPE_KEY, gson.toJsonTree(ResponseBodyUtility.ERR_RESPONSE_TYPE_MSG));
		responseBody.add(ResponseBodyUtility.ERR_RESPONSE_TITLE_KEY, gson.toJsonTree(ResponseBodyUtility.ERR_RESPONSE_TITLE_MSG));
		responseBody.add(ResponseBodyUtility.ERR_RESPONSE_DETAIL_KEY, gson.toJsonTree(errorDetail));
		responseBody.add(ResponseBodyUtility.ERR_RESPONSE_STATUS_KEY, gson.toJsonTree(status));
		responseBody.add(ResponseBodyUtility.ERR_RESPONSE_INSTANCE_KEY, gson.toJsonTree(uriPath));
	
		return responseBody.toString();
	}
	

}
