## Description

This is a basic message store appliction which is RESTful API using the following technologies:

* Java 8
* Spring Boot
* h2 database
* Hibernate
* thymeleaf

## Build the project

```
mvn clean install
```

## Running the API

Start the service by running the following command:

```
java -jar target/RestService-0.0.1-SNAPSHOT.jar
```

You can now test the service by consuming the api on port 8080. Some UI you can try in your browser:


* http://127.0.0.1:8080/

You can add new message by posting payloads like below:

```
POST 127.0.0.1:8080/messages
Content-Type: application/json
{
  	"messageDetail":"hi"
}
```
You can edit message by posting payloads like below:

```
PUT 127.0.0.1:8080/messages/hi
Content-Type: application/json
{
  	"messageDetail":"hello"
}
```

You can delete message by posting payloads like below:

```
DELETE 127.0.0.1:8080/messages/hello

```

You can get all message by posting payloads like below:

```
GET 127.0.0.1:8080/messages

```

