# PALO Orders Application

Simple application done with Angular 11 and Spring Boot 2.4. Allows user to upload a specific CSV and display the content in a paginated table.

## Installation

Running using Dockerfile: 

```bash
docker build -t palo-order .
docker run -t -p 8080:8080 palo-order
```

If you have no docker, you will need maven, node, and angular CLI installed in your local, you can run:

```bash
mvn clean install spring-boot:run
```

The URL to access will be: http://localhost:8080

## Support

This application uses H2 Spring In Memory Database due to time constraint. This might cause slight performance issues and memory usage issues when adding a large number of records, therefore I have limited the file upload size to be 100MB.

If you have GC Overhead Limit exceeded, please increase the JVM memory of your container to support this.
