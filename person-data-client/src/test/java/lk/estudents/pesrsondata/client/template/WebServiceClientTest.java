package lk.estudents.pesrsondata.client.template;

import lk.estudents.persondata.client.gateway.BaseTest;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class WebServiceClientTest extends BaseTest {

	private String idnumber = "1234567890v";
	private String message = "<per:PersonDataRequest xmlns:per='http://schemas.estudents.lk/xsd/persondata'>"
			+ "<per:identificationNumber>"
			+ idnumber
			+ "</per:identificationNumber>" + "</per:PersonDataRequest>";

	@Autowired
	private WebServiceClient webServiceClient;

	@Test
	public void testSimpleSendAndReceive() {
		webServiceClient.simpleSendAndReceive(message);
	}

	@Test
	public void testCustomSendAndReceive() {
		String URI = "http://localhost:8080/person-data-simple/services/PersonDataService/PersonDataServiceDefinition.wsdl";
		webServiceClient.customSendAndReceive(URI, message);
	}

}
