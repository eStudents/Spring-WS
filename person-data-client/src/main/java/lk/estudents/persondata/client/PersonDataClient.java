package lk.estudents.persondata.client;

import lk.estudents.schemas.xsd.persondata.PersonDataRequest;
import lk.estudents.schemas.xsd.persondata.PersonDataResponse;
import org.springframework.ws.soap.client.core.SoapActionCallback;
import org.springframework.ws.client.core.support.WebServiceGatewaySupport;

public class PersonDataClient extends WebServiceGatewaySupport {

	public PersonDataResponse getPersonData(String idnumber) {
		PersonDataRequest request = new PersonDataRequest();
		request.setIdentificationNumber(idnumber);

		System.out.println();
		System.out.println("Requesting person dat for " + idnumber);

		PersonDataResponse response = (PersonDataResponse) getWebServiceTemplate()
				.marshalSendAndReceive(
						request,
						new SoapActionCallback(
								"http://localhost:8080/person-data-simple/services/PersonDataService/PersonDataResponse"));
		return response;
	}

}
