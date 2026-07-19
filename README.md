# Springboot application with docker


## Specs:
- Java 21
- Spring
- mysql DB
- Maven
- Thymeleaf
- Docker

## Setting up locally:
Create Users table:

```
CREATE TABLE `users` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(45) DEFAULT NULL,
  `lastname` varchar(45) DEFAULT NULL,
  `email` varchar(45) DEFAULT NULL,
  `username` varchar(45) DEFAULT NULL,
  `password` varchar(45) DEFAULT NULL,
  `created_at` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `email_UNIQUE` (`email`),
  UNIQUE KEY `username_UNIQUE` (`username`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
```
COnection through `application.properties`
 ```
spring.application.name=1-springboot-mvc

spring.datasource.url=jdbc:mysql://localhost:3306/db_springboot
spring.datasource.username=root
spring.datasource.password=123456
```

If you're using docker, setting the next properties also:
 ```
 spring.application.name=1-springboot-mvc

spring.datasource.url=jdbc:mysql://host.docker.internal:3306/db_springboot
spring.datasource.username=root
spring.datasource.password=123456
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver
spring.jpa.show-sql=true
```

