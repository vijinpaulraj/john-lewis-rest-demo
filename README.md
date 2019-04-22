# App Requirement

Create a Restful webservice to request products in category 600001506 that have a price reduction. 

Use this API to obtain a list of products:
https://jl-nonprod-syst.apigee.net/v1/categories/600001506/products?key=2ALHCAAs6ikGRBoy6eTHA58RaG097Fma

Full Requirement can be found at:
https://github.com/vijinpaulraj/john-lewis-rest-demo/wiki


## How to run the application on local machine?

## Prerequisites:

- [JDK 1.8](http://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html)
- [Maven 3](https://maven.apache.org)
- [Spring Boot](https://docs.spring.io/spring-boot/docs/2.0.4.RELEASE/reference/htmlsingle/)

### Method 1:
- Execute the `main` method from `com.johnlewis.JohnLewisRestDemo` on an IDE such as IntelliJ

### Method 2:
- Execute the below maven command from the project directory.

```shell
mvn spring-boot:run
```

Now access the application on Swagger. http://localhost:8080/swagger-ui.html#

