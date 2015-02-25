package lk.estudents.persondata.client.gateway;

import javax.xml.transform.Source;

import lk.estudents.persondata.client.PersonDataClient;
import lk.estudents.schemas.xsd.persondata.PersonDataResponse;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.test.client.MockWebServiceServer;
import org.springframework.xml.transform.StringSource;

import static org.springframework.ws.test.client.RequestMatchers.*;
import static org.springframework.ws.test.client.ResponseCreators.*;

public class PersonDataClientIntegrationTest extends BaseTest {

	private String idnumber = "123";

	@Autowired
	private PersonDataClient personDataClient;

	private MockWebServiceServer mockServer;

	@Before
	public void createServer() throws Exception {
		mockServer = MockWebServiceServer.createServer(personDataClient);
	}

	@Test
	public void personDatarClient() throws Exception {
		Source requestPayload = new StringSource(
				"<per:PersonDataRequest xmlns:per='http://schemas.estudents.lk/xsd/persondata'>"
						+ "<per:identificationNumber>"
						+ idnumber
						+ "</per:identificationNumber>"
						+ "</per:PersonDataRequest>");

		Source expectedResponsePayload = new StringSource(
				"<ns2:PersonDataResponse xmlns:ns2='http://schemas.estudents.lk/xsd/persondata'>"
						+ "<ns2:fullName>person-data-mock: Rangalal Gamage</ns2:fullName>"
						+ "<ns2:dateOfBirth>2014-12-29+05:30</ns2:dateOfBirth>"
						+ "<ns2:phoneNumber>0123456789</ns2:phoneNumber>"
						+ "<ns2:age>35</ns2:age>"
						+ "<ns2:gender>Male</ns2:gender>"
						+ "</ns2:PersonDataResponse>");

		mockServer.expect(payload(requestPayload)).andRespond(
				withPayload(expectedResponsePayload));

		PersonDataResponse personDataResponse = personDataClient
				.getPersonData(idnumber);

		Assert.assertEquals("Gender should be Male", "Male",
				personDataResponse.getGender());
		Assert.assertEquals("Name","person-data-mock: Rangalal Gamage", personDataResponse.getFullName());
		System.out.println("Age:" + personDataResponse.getAge());
		mockServer.verify();
		
		
	}

}
