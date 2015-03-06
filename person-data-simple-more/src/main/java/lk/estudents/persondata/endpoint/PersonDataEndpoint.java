package lk.estudents.persondata.endpoint;

import java.util.Date;
import java.util.GregorianCalendar;

import org.apache.log4j.Logger;
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

	private static final Logger logger = Logger.getLogger(PersonDataEndpoint.class);

	@PayloadRoot(namespace = PersonDataUtil.TARGET_NAMESPACE, localPart = "PersonDataRequest")
	public @ResponsePayload	PersonDataResponse getPersonData(@RequestPayload PersonDataRequest request) {

		if (logger.isDebugEnabled()) {
			logger.debug("PersonDataEndpoint request received with IdentificationNumber : " + request.getIdentificationNumber());
		}
		
		PersonDataRequest personDataRequest = request;
		PersonDataResponse response = PersonDataUtil.getObjectFactory().createPersonDataResponse();
		try {
			PersonData personData = personDataService.getPersonInfo(personDataRequest.getIdentificationNumber());

			response.setAge(personData.getAge());
			response.setDateOfBirth(this.getDate(personData.getDateOfBirth()));
			response.setFullName(personData.getFullName());
			response.setGender(personData.getGender());
			response.setPhoneNumber(personData.getPhoneNumber());
			
			if (logger.isDebugEnabled()) {
				logger.debug("PersonDataEndpoint response received with, full name : " + personData.getFullName());
			}


		} catch (Exception e) {

			if (logger.isTraceEnabled()) {
				logger.trace(e);
			} else if (logger.isInfoEnabled()) {
				logger.info("Exception occure : " + e.getMessage());
			}
			
			logger.error(e.getMessage(), e);

		}
		return response;
	}

	private XMLGregorianCalendar getDate(Date date) throws RuntimeException {
		GregorianCalendar gc = new GregorianCalendar();
		gc.setTime(date);
		DatatypeFactory df;
		try {
			df = DatatypeFactory.newInstance();
		} catch (DatatypeConfigurationException e) {
			throw new RuntimeException("Calendar Setup Failed. "
					+ e.getMessage(), e);
		}

		XMLGregorianCalendar xmlGregorianCalendar = df
				.newXMLGregorianCalendar(gc);
		return xmlGregorianCalendar;
	}

}
