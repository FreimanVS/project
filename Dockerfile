FROM tomcat:8.5.28-alpine

ENV JAVA_OPTS -Dfile.encoding=UTF8 -Duser.language=ru -Duser.region=RU -Djavax.xml.parsers.DocumentBuilderFactory=com.sun.org.apache.xerces.internal.jaxp.DocumentBuilderFactoryImpl -Djavax.xml.parsers.SAXParserFactory=com.sun.org.apache.xerces.internal.jaxp.SAXParserFactoryImpl
ENV SLEEP_TIME_JAVA 3000
ENV MARKER_NAME testJava

COPY ./target/company /usr/local/tomcat/webapps/company
COPY ./dockerfiles/tomcat /usr/local/tomcat

CMD ["catalina.sh", "run"]

#command to run: docker-compose up