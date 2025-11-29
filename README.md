# GlassGo Platform

### Reference Documentation

For further reference, please consider the following sections:

* [Official Apache Maven documentation](https://maven.apache.org/guides/index.html)
* [Spring Boot Maven Plugin Reference Guide](https://docs.spring.io/spring-boot/3.5.7/maven-plugin)
* [Create an OCI image](https://docs.spring.io/spring-boot/3.5.7/maven-plugin/build-image.html)
* [Spring Data JPA](https://docs.spring.io/spring-boot/3.5.7/reference/data/sql.html#data.sql.jpa-and-spring-data)
* [Spring Boot DevTools](https://docs.spring.io/spring-boot/3.5.7/reference/using/devtools.html)
* [Spring Web](https://docs.spring.io/spring-boot/3.5.7/reference/web/servlet.html)

### Guides

The following guides illustrate how to use some features concretely:

* [Accessing Data with JPA](https://spring.io/guides/gs/accessing-data-jpa/)
* [Accessing data with MySQL](https://spring.io/guides/gs/accessing-data-mysql/)
* [Building a RESTful Web Service](https://spring.io/guides/gs/rest-service/)
* [Serving Web Content with Spring MVC](https://spring.io/guides/gs/serving-web-content/)
* [Building REST services with Spring](https://spring.io/guides/tutorials/rest/)

### Maven Parent overrides

Due to Maven's design, elements are inherited from the parent POM to the project POM.
While most of the inheritance is fine, it also inherits unwanted elements like `<license>` and `<developers>` from the
parent.
To prevent this, the project POM contains empty overrides for these elements.
If you manually switch to a different parent and actually want the inheritance, you need to remove those overrides.

## Running the Application ( Important )

### Instructions to run the application:

Run in src/main/java/com/glassgo/platform/GlassgoPlatformApplication.java 

The public class of GlassgoPlatformApplication

### Link de Swagger

http://localhost:8080/swagger-ui/index.html

## Project Structure

```
glassgo-backend/
├── docs/
│   ├── class-diagram.puml
│   └── technical-stories.md
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── com/
│   │   │       └── glassgo/
│   │   │           └── platform/
│   │   │               ├── GlassgoPlatformApplication.java
│   │   │               ├── analytics/                                          # Bounded Context
│   │   │               │   ├── application/
│   │   │               │   │   └── internal/
│   │   │               │   │       ├── commandservices/
│   │   │               │   │       │   └── ReportCommandServiceimpl.java
│   │   │               │   │       └── queryservices/
│   │   │               │   │           └── ReportQueryServiceImpl.java
│   │   │               │   ├── domain/
│   │   │               │   │   ├── model/
│   │   │               │   │   │   ├── aggregates/
│   │   │               │   │   │   │   └── Report.java                      # "Report" is the aggregate root
│   │   │               │   │   │   ├── commands/
│   │   │               │   │   │   │   └── CreateReportCommand.java
│   │   │               │   │   │   └── queries/
│   │   │               │   │   │       ├── GetReportByIdQuery.java
│   │   │               │   │   │       └── GetReportBySourceIdQuery.java
│   │   │               │   │   └── services/
│   │   │               │   │       ├── ReportCommandService.java
│   │   │               │   │       └── ReportQueryService.java
│   │   │               │   ├── infrastructure/
│   │   │               │   │   └── persistence/
│   │   │               │   │       └── jpa/
│   │   │               │   │           └── ReportRepository.java
│   │   │               │   └── interfaces/
│   │   │               │       └── rest/
│   │   │               │           ├── ReportController.java
│   │   │               │           ├── resources/
│   │   │               │           │   ├── CreateReportResource.java
│   │   │               │           │   └── ReportResource.java
│   │   │               │           └── transform/
│   │   │               │               ├── CreateReportCommandFromResourceAssembler.java
│   │   │               │               └── ReportFromEntityAssembler.java
│   │   │               └── shared/                                                     # Cross-cutting shared layer (following DDD principles)
│   │   │                   ├── domain/
│   │   │                   │   └── model/
│   │   │                   │       ├── aggregates/
│   │   │                   │       │   └── AuditableAbstractAggregateRoot.java
│   │   │                   │       └── entities/
│   │   │                   │           └── AuditableModel.java
│   │   │                   ├── infrastructure/
│   │   │                   │   ├── documentation/
│   │   │                   │   │   └── openapi/
│   │   │                   │   │       └── configuration/
│   │   │                   │   │           └── OpenApiConfiguration.java
│   │   │                   │   └── persistence/
│   │   │                   │       └── jpa/
│   │   │                   │           └── configuration/
│   │   │                   │               └── strategy/
│   │   │                   │                   └── SnakeCaseWithPluralizedTablePhysicalNamingStrategy.java
│   │   │                   └── interfaces/
│   │   │                       └── rest/
│   │   │                           ├── GlobalExceptionHandler.java
│   │   │                           └── resources/
│   │   │                               └── MessageResource.java
│   │   └── resources/
│   │       └── application.properties
│   └── test/
│       └── java/
│           └── com/
│               └── glassgo/
│                   └── platform/
│                       └── GlassgoPlatformApplicationTests.java
├── .gitignore
├── LICENSE.md
├── pom.xml
└── README.md
```