package lk.estudents.persondata.service;

import java.util.Date;

import org.springframework.stereotype.Service;

import lk.estudents.persondata.model.PersonData;

/**
 * @author rangalal.g
 *
 */
@Service
public class PersonDataServiceImpl implements PersonDataService {

	/* (non-Javadoc)
	 * @see lk.estudents.persondata.service.PersonDataService#getPersonInfo(java.lang.String)
	 */
	public PersonData getPersonInfo(String identificationNumber) {
		
		PersonData personData= new PersonData();
		
		personData.setAge(34);
		personData.setDateOfBirth(new Date());
		personData.setFullName("person-data-simple: Rangalal Gamage");
		personData.setGender("Male");
		personData.setIdentificationNumber(identificationNumber);
		personData.setPhoneNumber("0123456789");
		
		return personData;
	}

}
