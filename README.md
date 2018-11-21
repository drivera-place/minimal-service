## Microservice example using undertow

This is an example of a microservice, implementing Undertow embeded server, project requires JDK 1.8+ and maven. You can read more about undertow here:

http://undertow.io/

http://undertow.io/undertow-docs/undertow-docs-2.0.0/index.html

https://github.com/undertow-io/undertow/tree/master/examples

#### Compiling the project:
```mvn clean package```

#### Running the project using the following command, by default the service uses the port 8099:
``` java -jar target/micreservice-undertow-example.jar```

#### Requesting a user from the microservice (GET method):
```curl -vL http://localhost:8099/api/v1/users```

#### Posting a user (POST method):
```curl -vL -d '{ "fullName": "Dante Rivera", "phone":"5555555" }' -X POST http://localhost:8099/api/v1/users```