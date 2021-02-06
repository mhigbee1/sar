package response_generation.generators;

import java.util.Iterator;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import account_operations.boundaries.AccountOperationsUtility;
import response_generation.boundaries.ResponseBodyUtility;

public class SearchAccountsResponseBodyGenerator implements ResponseBodyUtility{
	
	private Iterator<AccountOperationsUtility> iterator;
	
	public SearchAccountsResponseBodyGenerator(Iterator<AccountOperationsUtility> mIterator) {
		this.iterator = mIterator;
	}

	@Override
	public String generateResponseBody() {
		Gson gson = new Gson();
		JsonArray searchResponse = new JsonArray();
		
		while(iterator.hasNext()) {
			AccountOperationsUtility accountInstance = iterator.next();
			String fullName = accountInstance.getFirstName() + " " + accountInstance.getLastName();
			JsonObject responseBody = new JsonObject();
			responseBody.add(ResponseBodyUtility.ACCOUNT_ID_KEY, gson.toJsonTree(accountInstance.getAid()));
			responseBody.add(ResponseBodyUtility.NAME_KEY, gson.toJsonTree(fullName));
			responseBody.add(ResponseBodyUtility.CREATED_ON_DATE_KEY, gson.toJsonTree(accountInstance.getCreatedOnDate()));
			responseBody.add(ResponseBodyUtility.ACTIVE_STATUS_KEY, gson.toJsonTree(accountInstance.isActive()));
			searchResponse.add(responseBody);
			
		}
		return searchResponse.toString();
	}
	
}
