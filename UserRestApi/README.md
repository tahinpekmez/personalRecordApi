# User Manager

User Manager is a sample tiny application that performs CRUD jobs on User Entity via Restful Webservices.

It exposes 5 endpoint as following list :

- GET /users (get a list of members)
- POST /users (create a member)
- POST /users/login (member login)
- POST /users/logUserOut (member logout)
- DELETE /users (delete members)


## How to use
```bash
mvn spring-boot:run
```
- SwaggerUI : http://localhost:8080/swagger-ui.html

# OR FOR RUN VIA DOCKER

```bash
docker-compose up
```
- SwaggerUI for Docker : http://localhost:8080/swagger-ui.html

#### Requirements:
- MySql Database Server
- Creating Database 






