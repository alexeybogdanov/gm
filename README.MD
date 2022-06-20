### Install and run
$ mvnw spring-boot:run (from project directory)

### Start test
$ mwnw test

### BASE URL
http://localhost:8080/api/csv"

### REST API
* POST `items`  load items
* DELETE `items`  delete items
* GET  `items` return list of items
* GET `items/{code}` get item by code

### HTTP Status codes
* HTTP 200 in case of success operations
* HTTP 201 in case of successful resource creation
* HTTP 204 no content
* HTTP 404 in case of resource not found
* HTTP 500 internal server error response

### Error Details
Application provides detailed error object in next format
```json
{
 "url": "http://localhost:8080/api/csv/items/271636001",
    "details": "Item with code '271636001' not found.",
    "timestamp": "2022-06-20T22:10:46.591"
 }
```

### H2 DB access
http://localhost:8080/h2

JDBC URL: jdbc:h2:mem:itemdb

* username: sa
* without password