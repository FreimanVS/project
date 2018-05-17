#FROM tomcat:8.5.28-alpine
#FROM oracle/glassfish:nightly
#FROM oracle/glassfish:latest
FROM hihoall/debianmavenjavatomcatglassfish

MAINTAINER VIACHESLAV

ENV JAVA_OPTS -Dfile.encoding=UTF8 -Duser.language=ru -Duser.region=RU -Djavax.xml.parsers.DocumentBuilderFactory=com.sun.org.apache.xerces.internal.jaxp.DocumentBuilderFactoryImpl -Djavax.xml.parsers.SAXParserFactory=com.sun.org.apache.xerces.internal.jaxp.SAXParserFactoryImpl
ENV SLEEP_TIME_JAVA 3000
ENV MARKER_NAME testJava
ENV JAVA_BASE_URI http://192.168.99.100
ENV JAVA_PORT 8081
ENV JAVA_CONTEXT_PATH /company

#COPY ./target/company /usr/local/tomcat/webapps/company
#COPY ./dockerfiles/tomcat /usr/local/tomcat

#COPY ./target/company /glassfish5/glassfish/domains/domain1/autodeploy/company
COPY ./autodeploy /glassfish5/glassfish/domains/domain1/autodeploy
COPY ./glassfish /glassfish5/glassfish

#COPY ./glassfish5 /glassfish5
CMD ["/glassfish5/glassfish/bin/asadmin", "start-domain", "-v"]

#CMD ["catalina.sh", "run"]

#command to run: docker-compose up