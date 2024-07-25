# weather-predication

The purpose of this project is to consume the OpenWeatherMap api .





## Installation

This project is java-based. So It requires Jdk 17 (or later) and Maven 3.6.3 (or later)  to run.


 cd weather-predication
 mvn clean install package
 java -jar target/weather-prediction-0.0.1-SNAPSHOT.jar 

Another way to run this  is to use docker Desktop (but first build this project with mvn clean install package)

docker build -t weather-spring-boot-app .
 docker run -p 8080:8080 weather-spring-boot-app

 



## Running tests
After changes you can run tests using Maven command:

 cd weather-api
 mvn test


## Rest API

 As required, this API has 1 endpoint :
  1. /weather/{city}




## Testing API
1. API can be tested using Postman/Soap Ui
2. Swagger is also included in api , I would suggest to use swagger then there is no need to perform extra work.

Swagger url - http://localhost:8080/swagger-ui-custom.html

This is using angular for UI .please go through angular app README file