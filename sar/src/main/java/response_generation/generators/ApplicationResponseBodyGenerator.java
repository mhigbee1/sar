package response_generation.generators;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.core.UriBuilder;
import javax.ws.rs.core.UriInfo;

import response_generation.boundaries.ResponseBodyUtility;

public class ApplicationResponseBodyGenerator {
	
	private ResponseBuilder responseBuilder;
	
	public ApplicationResponseBodyGenerator(Status status) {
		responseBuilder = Response.status(status);
	}
	
	public ApplicationResponseBodyGenerator addLocationHeader(String location, UriInfo uriInfo) {
		UriBuilder uriBuilder = uriInfo.getAbsolutePathBuilder();
		uriBuilder.path(location);
		responseBuilder.contentLocation(uriBuilder.build());
		responseBuilder.header(ResponseBodyUtility.LOCATION_KEY, uriBuilder.build().toString());
		return this;
	}
	
	public ApplicationResponseBodyGenerator addResponseBody(ResponseBodyUtility responseBody) {
		responseBuilder.entity(responseBody.generateResponseBody());
		return this;
	}
	
	public Response build() {
		return responseBuilder.build();
	}

}
