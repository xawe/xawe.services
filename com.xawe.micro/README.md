# com.xawe.micro.estabeleciomentocomercial
Spring laboratory using HTTP and RabbitMQ to microservices comunication.

Go to resources and run #docker-compose up to deploy a RabbitMq Container.
Administration area on http://localhost:15672 , user guest , pwd guest


##Docker
To build a service docker image run :
	docker build --tag=alpine-java:base --rm=true .

To create a volume container
	docker volume create --name=spring-cloud-config-repo
To start the built container
	docker run -it -p 9000:8000 --name docker_boot_app -d javatechy/dockboot	
	
OBS:: Docker files not fully implemented.

#References 
	
	https://github.com/javatechy/docker_boot/blob/master/src/main/docker/Dockerfile
	
	https://dzone.com/articles/dockerizing-spring-boot-application-1
	
	https://www.baeldung.com/dockerizing-spring-boot-application
