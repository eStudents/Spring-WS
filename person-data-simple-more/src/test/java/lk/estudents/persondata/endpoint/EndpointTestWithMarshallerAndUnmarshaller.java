package lk.estudents.persondata.endpoint;

import static org.springframework.ws.test.server.RequestCreators.withPayload;
import java.io.IOException;
import java.io.StringWriter;

import javax.xml.transform.stream.StreamResult;
import lk.estudents.schemas.xsd.persondata.PersonDataRequest;
import lk.estudents.schemas.xsd.persondata.PersonDataResponse;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.oxm.Marshaller;
import org.springframework.oxm.Unmarshaller;
import org.springframework.oxm.XmlMappingException;
import org.springframework.ws.WebServiceMessage;
import org.springframework.ws.test.server.MockWebServiceClient;
import org.springframework.ws.test.server.ResponseMatcher;
import org.springframework.xml.transform.StringSource;

/**
 * @author rangalal.g
 *
 */
public class EndpointTestWithMarshallerAndUnmarshaller extends BaseTest {

	@Autowired
	protected Marshaller marshaller;

	@Autowired
	protected Unmarshaller unmarshaller;

	private MockWebServiceClient mockClient;
	private String idnumber = "123";

	@Before
	public void createClient() {
		mockClient = MockWebServiceClient.createClient(applicationContext);
	}

	@Test
	public void getPersonDataServerSideTest() throws Exception, IOException {
		PersonDataRequest personDataRequest = PersonDataUtil.getObjectFactory().createPersonDataRequest();
		personDataRequest.setIdentificationNumber(idnumber);

		sendRequestToServerEndpoint(personDataRequest);
	}

	private void sendRequestToServerEndpoint(PersonDataRequest request) throws IOException, XmlMappingException {
		
		StringWriter output = PersonDataUtil.getStringWriter();

		marshaller.marshal(request, new StreamResult(output));

		ResponseMatcher acceptanceCriteria = new ResponseMatcher() {

			public void match(WebServiceMessage request, WebServiceMessage response) throws IOException, AssertionError {

				PersonDataRequest personDataRequest = (PersonDataRequest) unmarshaller.unmarshal(request.getPayloadSource());
				PersonDataResponse personDataResponse = (PersonDataResponse) unmarshaller.unmarshal(response.getPayloadSource());

				Assert.assertNotNull("Id number can't be NULL", personDataRequest.getIdentificationNumber());
				Assert.assertNotNull("Response can't be NULL", personDataResponse);
				Assert.assertEquals("Gender should be Male", "Male", personDataResponse.getGender());
				Assert.assertEquals("Phone number should matched", "0123456789", personDataResponse.getPhoneNumber());

			}
		};

		mockClient.sendRequest(withPayload(new StringSource(output.toString()))).andExpect(acceptanceCriteria);
	}
}
