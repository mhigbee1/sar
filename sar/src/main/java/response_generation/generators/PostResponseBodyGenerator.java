package response_generation.generators;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import response_generation.boundaries.ResponseBodyUtility;

public class PostResponseBodyGenerator implements ResponseBodyUtility{
	
	private String parameterKey;
	private int	value;
	
	public PostResponseBodyGenerator(String mParameterKey, int mValue) {
		this.parameterKey = mParameterKey;
		this.value = mValue;
	}

	@Override
	public String generateResponseBody() {
		Gson gson = new Gson();
		JsonObject responseBody = new JsonObject();
		responseBody.add(parameterKey, gson.toJsonTree(value));
		
		return responseBody.toString();
	}

}
