# project java EE

Steps to launch the project from docker:
1. maven clean package
2. mv target/company dockerfiles/glassfish/autodeploy/company
3. docker-compose up
4. open link http://192.168.99.100:8081/company/
