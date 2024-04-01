##FROM openjdk:8
##EXPOSE 8080
##ADD target/jenkins-docker-integration.jar jenkins-docker-integration.jar
##ENTRYPOINT ["java","jar","/jenkins-docker-integration.jar "]
#FROM openjdk:8
#
## Expose port 8080
#EXPOSE 8080
#
## Add the JAR file from the target directory to the Docker image
#ADD target/jenkins-docker-integration.jar jenkins-docker-integration.jar
#
## Define the entry point for running the application
#ENTRYPOINT ["java", "-jar", "/jenkins-docker-integration.jar"]

FROM tomcat:7.0
EXPOSE 8080
ADD  ./target/jenkins-docker-integration.jar /usr/local/tomcat/webapps/
CMD ["catalina.sh", "run"]
