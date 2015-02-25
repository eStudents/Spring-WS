package lk.estudents.persondata.endpoint;

import java.util.Date;
import java.util.GregorianCalendar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;
import lk.estudents.persondata.model.PersonData;
import lk.estudents.persondata.service.PersonDataService;
import lk.estudents.schemas.xsd.persondata.PersonDataRequest;
import lk.estudents.schemas.xsd.persondata.PersonDataResponse;
import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;

/**
 * Spring web service Endpoint class which is responsible for managing the
 * person data.
 * 
 * @author rangalal.g
 * 
 */
@Endpoint
public class PersonDataEndpoint {

	@Autowired
	private PersonDataService personDataService;

	@PayloadRoot(namespace = PersonDataUtil.TARGET_NAMESPACE, localPart = "PersonDataRequest")
	
	public @ResponsePayload PersonDataResponse getPersonData(@RequestPayload PersonDataRequest request) {

		PersonDataRequest personDataRequest = request;
		PersonDataResponse response = new PersonDataResponse();
		try {
			PersonData personData = personDataService.getPersonInfo(personDataRequest.getIdentificationNumber());
			
			response.setAge(personData.getAge());
			response.setDateOfBirth(this.getDate(personData.getDateOfBirth()));
			response.setFullName(personData.getFullName());
			response.setGender(personData.getGender());
			response.setPhoneNumber(personData.getPhoneNumber());
			
		} catch (Exception e) {

		}
		return response;
	}

	private XMLGregorianCalendar getDate(Date date) throws RuntimeException {
		GregorianCalendar gregorianCalendar = new GregorianCalendar();
		gregorianCalendar.setTime(date);
		DatatypeFactory datatypeFactory;
		try {
			datatypeFactory = DatatypeFactory.newInstance();
		} catch (DatatypeConfigurationException e) {
			throw new RuntimeException("Can't create the calander. "
					+ e.getMessage(), e);
		}

		XMLGregorianCalendar xmlGregorianCalendar = datatypeFactory.newXMLGregorianCalendar(gregorianCalendar);
		return xmlGregorianCalendar;
	}

}
