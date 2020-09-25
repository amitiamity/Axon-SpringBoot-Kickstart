# Axon-SpringBoot-Kickstart

This application is based on event sourcing and have been implemented using CQRS, Axon framework, Spring Boot. 

## Prerequisites

1.Download the axon server. You can use the link :
https://axoniq.io/download#0.
If you download the axon-server as zip, run the server with below command :



```java 
java -jar {axon-server-folder-path}/axonserver.jar
```
2. Java 1.8 and above
3. Gradle build tool
## Implementation

This application exposes some api's to create a user and add address to the created user. These are created via command and user are stored are into Axon event store and addresses are stored into H2 database. User details and list of related addresses are fetched through queries (via projections). Aggregates are created using command for which event is generated to store into event store and database.

## How to Run
1. First start the axon server
2. Run below gradlew command to run the application
```gradle
./gradlew bootRun
```
