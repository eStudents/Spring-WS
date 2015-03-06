package lk.estudents.persondata.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
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
		
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		Date date = null;
		try {
			date = dateFormat.parse("2014-12-29");
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
				
		personData.setAge(34);
		personData.setDateOfBirth(date);
		personData.setFullName("person-simple-more: Rangalal Gamage");
		personData.setGender("Male");
		personData.setIdentificationNumber(identificationNumber);
		personData.setPhoneNumber("0123456789");
		
		return personData;
	}

}
