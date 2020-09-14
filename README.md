# user-prev

## This API creates users and save then in memory

This API was built with Java 11, Quarkus framework, run its tests with Junit and Rest Assured, API documentation wrote with Swagger and deployed at heroku

### How to test:

Endpoints:

POST   - https://users-prev.herokuapp.com/person - creates a person

GET    - https://users-prev.herokuapp.com/person - get all persons

GET    - https://users-prev.herokuapp.com/person/{id} - get person by {id}

PUT    - https://users-prev.herokuapp.com/person - updates a person

DELETE - https://users-prev.herokuapp.com/person/{id} - delete person br {id}

Json format example to POST(create) and PUT(update): 

{ "id" : 1, "name" : "Person's name", "document" : "123456", "address" : "Person's address"} 

The documentation was wrote with swagger editor and can be find:

 - https://github.com/thiagolinoz/user-prev/blob/master/src/main/resources/META-INF/resources/swagger-user-prev1-0.yaml

> If you want run this application on your local, after the repository cloned run:

`mvn compile quarkus:dev`
