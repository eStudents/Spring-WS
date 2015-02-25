package lk.estudents.pesrsondata.client.template;

import lk.estudents.persondata.client.gateway.BaseTest;
import lk.estudents.schemas.xsd.persondata.ObjectFactory;
import lk.estudents.schemas.xsd.persondata.PersonDataRequest;
import lk.estudents.schemas.xsd.persondata.PersonDataResponse;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.client.core.WebServiceTemplate;

public class ClientTest extends BaseTest {

	 @Autowired
	 private WebServiceTemplate webServiceTemplate;
	 
	 @Test
	    public void testAccountBalanceService() {
	 
		 	PersonDataRequest request = new ObjectFactory().createPersonDataRequest();
	        request.setIdentificationNumber("1234567890v");
	        PersonDataResponse response = (PersonDataResponse) webServiceTemplate.marshalSendAndReceive(request);
	 
	        Assert.assertEquals("Gender should be Male", "Male", response.getGender());
	        System.out.println("Name:" + response.getFullName());
			System.out.println("Age:" + response.getAge());
	    }
}
