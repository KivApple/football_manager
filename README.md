# football_manager

## Project structure

- `com.eternal_search.football_manager` - Root app package
- `.controller` - Controllers (endpoints implementation)
- `.service` - Services (business-logic layer)
- `.model` - Data model classes
- `.model.enumeration` - All enumerations used by DTO and JPA entities
- `.model.entity` - JPA entities
- `.model.dto` - DTO classes
- `.model.mapper` - Entity-to-DTO and DTO-to-entity mappers
- `.model.repository` - Database access layer
- `.exception` - Custom exception classes
- `src/test` - Unit and integration tests

## Endpoints

- `GET /team/list` - List all teams (sorting and pagination available)
- `POST /team` - Create a new team
- `DELETE /team/{id}` - Delete a team (BONUS)

Please access http://localhost:8080/swagger-ui/index.html to have more info
(expected parameters etc.) or look into `TeamController` class.

## How to run a project

First you need Java 17 to run the app.

Second you need to create an empty Postgres database for the app.
Default database URL and credentials are defined in `src/main/resources/application.properties`.
If you want to change them you can either change them in this file or by passing environment variables
(e.g. to override `spring.datasource.url` you can use `SPRING_DATASOURCE_URL` environment variable).
Database needed both for normal app operation and for running tests.

Project uses Gradle build system. Some useful commands:

- `./gradlew bootRun` - Run an app
- `./gradlew bootJar` - Builds JAR of the app located in `build/libs`. 
  This JAR will contain all dependencies and can be used without any other project files.
- `./gradlew test` - Run all tests

On Windows system `./gradlew` should be replaced with `gradlew.bat`. Also, you can install Gradle globally
and just use `gradle` command. Finally you can open the project in IntelliJ IDEA and run it using
IDE.

## Technical decisions

1. I've chosen PostgreSQL as a relational database system, because on one hand
   it is a mature scalable DB, on other hand I have the most experience with it.
   The second choice could be Oracle.
2. I've chosen Flyway migrations. The second choice could be Liquibase. I prefer
   Flyway, because in my opinion it is more natural to write migrations in pure SQL
   rather than in XML/JSON/YAML. Also, anyway most of the projects use some 
   database vendor-specific stuff to gain more performance,
   and it is easier with SQL migrations. On other hand
   database engine change is not very probable during project lifetime.
3. I'm using Lombok to reduce boilerplate code and speed up the development.
4. I'm using `BIGINT` as a column type for team budget. I've found that the richest
   football club in the world in 2023 is "Manchester City" and it has a budget of
   approximately 707 M€. It is just 4 times less than `INTEGER` maximum value, so
   I'd prefer to use `BIGINT` to be safe. The second point is that our app is not
   bank or accounting software and I don't think that football manager needs
   cents to compare teams. In case if we need more precision we could use 
   `DECIMAL(13,2)` to handle up to 9.999 trillion € with a precision up to 1 cent.
   And we shouldn't use `REAL` to store money because of variable precision.
5. I'm using enumerated value for player position column type, because it is
   quite unlikely that football rules would change adding/removing positions.
   I store enumerate values as `VARCHAR` and not as a number to
   simplify manual database queries and to have simple possibility to extend enumeration
   in future software updates (I also could use Postgres custom enumerated type
   e.g. `CREATE TYPE player_position AS ENUM(...)`, but native DB enumerated type isn't 
   supported by Hibernate out of the box without extra libraries and annotations). 
   In case if we would expect frequent changes of football rules (and don't want 
   for user to wait a software update), we could use an extra table for player 
   positions instead of enumeration.
6. I created an index on `team_id` field of players table, because we need to
   be able to query players by teams and also because of cascading deletion
   of teams to players.
7. I've populated tables with 16 football teams and some players for Nice and 
   Toulouse football clubs for demonstrative purposes using `V2__initData.sql` 
   migration. Due to limited time I haven't found what does acronym mean for
   football team, so I use the closest airport IATA code to home city of a club.
8. I use MapStruct for generate mappers Entity-to-DTO and DTO-to-Entity to speed up
   the development.
9. I use `EntityGraph` to avoid "N + 1 problem" when fetching linked players while 
   querying teams. Property `spring.jpa.show-sql` is used to verify that we
   don't have N + 1 problem. Should be turned off in production, because of
   too much logging.
10. I use Swagger to document API and easily test endpoints from browser:
    http://localhost:8080/swagger-ui/index.html
11. I use Hibernate Validation to check all request parameters for validity.
12. I write JavaDocs for all functions with body, but I don't write JavaDocs
    for DTO and entity fields, because on one hand I have very limited
    domain knowledge according to the task and on other hand they are quite
    self-explanatory. Finally, I have quite limited time budget for the project.
13. Because there is no business-logic, I've written unit tests of validation 
    for `PageRequestDTO`.
14. For integration tests I'm testing a use-case of creating a team with players and then deleting it.

## Future work or points of improvement

If it was a real project with more time budget, there are few things which could be done:

1. It is good to add teardown step for our insert-and-delete integration test which will cleanup database in case of test failure.
   But on other hand we could assume that tests are executed in temporary environment and database will be deleted anyway.
2. Add full CRUD for teams and players: endpoints for update teams
   (now you can only create and delete them) and separate players inside the team
3. Add an endpoint for transfer player from one team to another
4. Add an endpoint for query teams without players (for better performance when we don't need team details)
5. Add an endpoint for query one team with players by its id
6. Add integration tests for all endpoints
7. Use Java 14 record type in place of Lombok @Data
8. Handle UNIQUE constraint errors while creating a team and translate it to 409 Conflict HTTP error code
9. Add an authentication (authenticated method to be defined)
10. Write a Dockerfile for Docker deployment of the app
11. It is possible to create a Docker Compose configuration or HELM chart to simplify deployment of both app and DB
12. If we need more performance we could use `spring-boot-starter-webflux` to create asynchronous endpoints
