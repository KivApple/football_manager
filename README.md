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
   I store enumerate values as `VARCHAR` and not a number to
   simplify manual database queries and to have possibility to extend enumeration
   in future software updates. In case if we would expect frequent changes of
   football rules (and don't want for user to wait a software update), we could
   use an extra table for player positions instead of enumeration.
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
