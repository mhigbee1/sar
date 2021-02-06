package response_generation.generator_tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import response_generation.boundaries.ResponseBodyUtility;
import response_generation.generators.ViewAllRideReportsResponseBodyGenerator;
import ride_report_processing.interactors.PostedRideReportsProcess;
import ride_report_processing.interactors.TakenRideReportsProcess;

class ViewAllRideReportsResponseBodyGeneratorTest {
	
	ResponseBodyUtility viewAllRideReportsResponseBodyGenerator;
	private final String POSTED_RIDE_REPORT_TOKEN = "{\"pid\":907,\"name\":\"" + PostedRideReportsProcess.NAME_TEXT + "\"},";
	private final String TAKEN_RIDE_REPORT_TOKEN = "{\"pid\":911,\"name\":\"" + TakenRideReportsProcess.NAME_TEXT + "\"}";
	
	
	@Test
	void test_generateResponseBody_returnsTrueForMatchingExpectedResponse() {
		String expectedResponse = "["+ POSTED_RIDE_REPORT_TOKEN + TAKEN_RIDE_REPORT_TOKEN + "]";
		viewAllRideReportsResponseBodyGenerator = new ViewAllRideReportsResponseBodyGenerator();
		
		assertEquals(expectedResponse, viewAllRideReportsResponseBodyGenerator.generateResponseBody());
	}

}
