package lk.estudents.persondata.client;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;
@Configuration
public class ClientAppConfig {
	@Bean
	public Jaxb2Marshaller marshaller() {
		Jaxb2Marshaller marshaller = new Jaxb2Marshaller();
		marshaller.setContextPath("lk.estudents.schemas.xsd.persondata");
		return marshaller;
	}
	@Bean
	public PersonDataClient studentClient(Jaxb2Marshaller marshaller) {
		PersonDataClient client = new PersonDataClient();
		client.setDefaultUri("http://localhost:8080/person-data-simple/services/PersonDataService/PersonDataServiceDefinition.wsdl");
		client.setMarshaller(marshaller);
		client.setUnmarshaller(marshaller);
		return client;
	}
} 