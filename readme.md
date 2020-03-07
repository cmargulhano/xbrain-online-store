# VirtualStore

Para rodar há duas opções via Maven ou Docker:


### Maven

``` 
docker-compose -f rabbitmq-compose.yml up -d

mvn clean install 

mvn spring-boot:run

```

### Docker

```
docker-compose -f docker-compose.yml up -d
```

### Swagger

http://localhost:8080/swagger-ui.html

### HAL Browser

http://localhost:8080/browser/index.html#/