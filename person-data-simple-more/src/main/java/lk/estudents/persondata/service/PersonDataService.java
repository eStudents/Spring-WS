package lk.estudents.persondata.service;

import lk.estudents.persondata.model.PersonData;

/**
 * Person data service interface class
 * @author rangalal.g
 *
 */

public interface PersonDataService {
	
	/**
	 * @param identificationNumber
	 * @return PersonData
	 */
	public PersonData getPersonInfo(String identificationNumber);

}
