Api gateway 
-----------

API - api gateway serves a single entry point 

1) encapsulate the internal system architechture

   ADDING NEW MICROSERVICE 
   EDITING 
   
2) SECURITY, LOAD BALANCING, rate limiting, and analytics

3) Can authenticate incoming request and pass only valid request

4) can aggregate response from different microservice


It is used to route request to the right service

Load balancing

rate limiting

authentication and authorisation



STEPS
------

create a new project for gateway we use the 
CONFIG SERVER 

zipkin

eureka discovery client





add this application.properties 
--------------------------------

spring.application.name=gateway



server.port=8891




#eureka

#Url mentioning
eureka.client.serviceUrl.defaultZone=http://localhost:8761/eureka/


# this project is a eureka server so it need not register itself
eureka.client.register-with-eureka=true

# this project is already present need not fetch from anywhere
eureka.client.fetch-registry=true



# Zipkin
# this command 100% of the requests will be traced and sent to Zipkin. 0.1 means 10%
management.tracing.sampling.probability=1.0



#Gateway
spring.cloud.gateway.routes[0].id=companyms
spring.cloud.gateway.routes[0].uri=http://localhost:8897
spring.cloud.gateway.routes[0].predicates[0]=Path=/companyms/**

spring.cloud.gateway.routes[1].id=jobms
spring.cloud.gateway.routes[1].uri=http://localhost:8898
spring.cloud.gateway.routes[1].predicates[0]=Path=/jobms/**

spring.cloud.gateway.routes[2].id=reviewms
spring.cloud.gateway.routes[2].uri=http://localhost:8896
spring.cloud.gateway.routes[2].predicates[0]=Path=/reviewms/**



logging.level.root=info
logging.level.org.springframework.cloud.gateway.route.RouteDefinitionLocator=INFO
logging.level.org.springframework.cloud.gateway=TRACE




Here’s your **API Gateway overview and setup rewritten neatly** and clearly:

---

## 🚪 API Gateway Overview

### 📌 What is API Gateway?

An **API Gateway** serves as a **single entry point** for all client requests in a microservices architecture.
It helps encapsulate internal services and exposes a unified external interface.

---

### ✅ Key Features

1. **Encapsulation of Architecture**

   * Hides internal microservices from clients
   * Easier to **add**, **edit**, or **remove** microservices without affecting clients

2. **Routing & Load Balancing**

   * Routes requests to the correct microservice
   * Performs built-in load balancing

3. **Security**

   * Handles authentication & authorization
   * Accepts only valid requests

4. **Traffic Control**

   * Supports **rate limiting**, **circuit breakers**, and **throttling**

5. **Response Aggregation**

   * Combines responses from multiple microservices into one

6. **Monitoring**

   * Integrates with tools like **Zipkin** for tracing
   * Collects analytics

---

## 🛠️ Setting Up API Gateway

### 🔧 Step-by-Step Setup

1. **Create a new Spring Boot project**

   * Add the required dependencies:

     * Spring Cloud Gateway
     * Eureka Discovery Client
     * Zipkin/Tracing (optional)
     * Config Client (optional if using centralized config)

2. **Register with Eureka Server**

   * Enable service discovery

3. **Define Routing Rules**

   * Set up routes to each microservice

---

### 🗂️ Sample `application.properties` (for Gateway)

```properties
spring.application.name=gateway
server.port=8891

# Eureka Client Configuration
eureka.client.serviceUrl.defaultZone=http://localhost:8761/eureka/
eureka.client.register-with-eureka=true
eureka.client.fetch-registry=true

# Zipkin (Distributed Tracing)
management.tracing.sampling.probability=1.0

# Gateway Routes
spring.cloud.gateway.routes[0].id=companyms
spring.cloud.gateway.routes[0].uri=http://localhost:8897
spring.cloud.gateway.routes[0].predicates[0]=Path=/companyms/**

spring.cloud.gateway.routes[1].id=jobms
spring.cloud.gateway.routes[1].uri=http://localhost:8898
spring.cloud.gateway.routes[1].predicates[0]=Path=/jobms/**

spring.cloud.gateway.routes[2].id=reviewms
spring.cloud.gateway.routes[2].uri=http://localhost:8896
spring.cloud.gateway.routes[2].predicates[0]=Path=/reviewms/**

# Logging
logging.level.root=info
logging.level.org.springframework.cloud.gateway.route.RouteDefinitionLocator=INFO
logging.level.org.springframework.cloud.gateway=TRACE
```

---

### 🧪 Example API Calls

* `http://localhost:8891/companyms/api/companies`
* `http://localhost:8891/jobms/api/jobs`
* `http://localhost:8891/reviewms/api/reviews`

---

Let me know if you'd like a diagram or code snippet to go along with this!

