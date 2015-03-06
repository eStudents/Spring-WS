# About person-data-simple-more:
This is a Spring-WS contract-first web service to get person data with log4j, property place holder configurations and marshalling configurations.

Reference : 
[1] http://krams915.blogspot.com/2010/12/spring-ws-tutorial-using-latest-200-rc2.html 
[2] http://docs.spring.io/spring-ws/sites/2.0/reference/html/oxm.html

# Create the initial project structure:
mvn archetype:create -DarchetypeGroupId=org.springframework.ws -DarchetypeArtifactId=spring-ws-archetype -DarchetypeVersion=2.1.4.RELEASE -DgroupId=est.spring.jaxb -DartifactId=person-data-simple-more

# Prerequisite:
You need to have JAVA (1.7) and MAVEN (3.2.3) installed properly with JAVA_HOME and M2_HOME environment variables.

# to create eclipse project execute:
mvn eclipse:eclipse

# to clean the project:
mvn clean

# to package the project:
mvn package

# to deploy on tomcat 7:
manually: copy target\person-data-simple-more.war file to tomcat server webapp directory.

with tomcat7-maven-plugin:
1. Add user to tomcat\conf\tomcat-users.xml file and configure same user in pom.xml.
<user password="mvn" roles="manager-script" username="mvn"/>

2. execute following command
mvn tomcat7:deploy

# to check the web service definition (WSDL):
http://localhost:8080/person-data-simple-more/services/PersonDataService/personDataWsdl11Definition.wsdl





