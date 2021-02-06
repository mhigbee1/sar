package information_processing.info_requests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import information_processing.entities.EmptyRequestInfo;

class EmptyRequestInfoTest {
	private EmptyRequestInfo emptyRequestInfo;
	private static final String ERROR_TEXT = "Error Encoutnered";
	
	@BeforeEach
	void init() {
		emptyRequestInfo = new EmptyRequestInfo(ERROR_TEXT);
	}

	@Test
	void test_getErrorMsg_returnsERROR_TEXT() {
		assertEquals(ERROR_TEXT, emptyRequestInfo.getErrorMsg());
	}
	
	@Test
	void test_isEmptyRequest_returnsTrue() {
		assertTrue(emptyRequestInfo.isEmptyRequest());
	}

}
