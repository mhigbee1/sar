package information_processing.boundaries;

import com.google.gson.JsonSyntaxException;

public interface InformationIDsParser {
	public static final String JSON_MESSAGE = "Invalid format for information request";
	public static final String INCOMPLETE_REQUEST_MESSAGE = "Request missing elements";
	public InformationIDs parse(String body) throws NullPointerException, JsonSyntaxException;
}
