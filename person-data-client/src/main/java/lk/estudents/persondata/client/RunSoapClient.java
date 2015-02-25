package lk.estudents.persondata.client;

import lk.estudents.schemas.xsd.persondata.PersonDataResponse;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class RunSoapClient {
	public static void main(String[] args) {
		AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext();
		ctx.register(ClientAppConfig.class);
		ctx.refresh();
		PersonDataClient personDataClient = ctx.getBean(PersonDataClient.class);
		System.out.println("For idnumber : 1234567890v");
		PersonDataResponse response = personDataClient.getPersonData("1234567890v");
		System.out.println("Name:" + response.getFullName());
		System.out.println("Age:" + response.getAge());
	}
}

/*reference

[1] http://www.concretepage.com/spring-4/spring-4-soap-web-service-producer-consumer-example-with-tomcat

[2] http://myshittycode.com/category/spring/spring-web-services/

*/