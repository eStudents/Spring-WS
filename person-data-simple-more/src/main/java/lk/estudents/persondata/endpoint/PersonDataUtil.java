package lk.estudents.persondata.endpoint;

import java.io.StringWriter;

import lk.estudents.schemas.xsd.persondata.ObjectFactory;

/**
 * Utility class for person data.
 * 
 * @author rangalal.g
 * 
 */
public class PersonDataUtil {

	public static final String TARGET_NAMESPACE = "http://schemas.estudents.lk/xsd/persondata";

	private static ObjectFactory objFactory = new ObjectFactory();

	public static ObjectFactory getObjectFactory() {
		return objFactory;
	}

	public static StringWriter getStringWriter() {
		StringWriter output = new StringWriter(); 
		return output;
	}

}
