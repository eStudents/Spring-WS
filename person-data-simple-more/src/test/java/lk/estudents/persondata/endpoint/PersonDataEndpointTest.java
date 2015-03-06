package lk.estudents.persondata.endpoint;

import org.springframework.ws.test.server.MockWebServiceClient;
import org.springframework.ws.test.server.RequestCreator;
import org.springframework.ws.test.server.RequestCreators;
import org.springframework.ws.test.server.ResponseMatcher;
import org.springframework.ws.test.server.ResponseMatchers;
import org.springframework.xml.transform.StringSource;
import org.junit.Before;
import org.junit.Test;
import javax.xml.transform.Source;

/**
 * @author rangalal.g
 *
 */
public class PersonDataEndpointTest extends BaseTest {

	private MockWebServiceClient mockClient;
	private String idnumber = "123";

	@Before
	public void createClient() {
		mockClient = MockWebServiceClient.createClient(applicationContext);
	}
	
	/**
	 * This method test server side code against expected response.
	 * @throws Exception
	 */
	@Test
	public void getPersonDataServerSideTest() throws Exception {

		/* note that we haven't validate the identificationNumber */
		Source requestPayload = new StringSource("<per:PersonDataRequest xmlns:per='http://schemas.estudents.lk/xsd/persondata'>"
						+ "<per:identificationNumber>" + idnumber + "</per:identificationNumber>"
						+ "</per:PersonDataRequest>");

		Source expectedResponsePayload = new StringSource("<ns2:PersonDataResponse xmlns:ns2='http://schemas.estudents.lk/xsd/persondata'>"
						+ "<ns2:fullName>person-simple-more: Rangalal Gamage</ns2:fullName>"
						+ "<ns2:dateOfBirth>2014-12-29+05:30</ns2:dateOfBirth>"
						+ "<ns2:phoneNumber>0123456789</ns2:phoneNumber>"
						+ "<ns2:age>34</ns2:age>"
						+ "<ns2:gender>Male</ns2:gender>"
						+ "</ns2:PersonDataResponse>");

		RequestCreator creator = RequestCreators.withPayload(requestPayload);
		ResponseMatcher responseMatcher = ResponseMatchers.payload(expectedResponsePayload);
		mockClient.sendRequest(creator).andExpect(responseMatcher);
		
		/* in single line with following two imports instead of above three lines.
		 * import static org.springframework.ws.test.server.RequestCreators.withPayload;
		 * import static org.springframework.ws.test.server.ResponseMatchers.payload;
		 * 
		 * mockClient.sendRequest(withPayload(requestPayload)).andExpect(payload(responsePayload)); 
		 */
			
	}

}
