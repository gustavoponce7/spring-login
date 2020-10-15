# Spring Security Login Tutorial

### Tutorial / Full Information

https://medium.com/@gustavo.ponce.ch/spring-boot-spring-mvc-spring-security-mysql-a5d8545d837d

1. mvn clean install
2. java -jar target/login-tutorial.jar
3. java -jar -Dserver.port=9090 target/login-tutorial.jar

- http://localhost:9090/registration
- http://localhost:9090/login

### Docker
- mvn clean install
- docker build --tag login-tutorial .
- docker run --net=host login-tutorial 