# VirtualStore

## Para rodar o microservice há duas opções via Maven ou Docker:


### Maven

``` 
docker-compose -f rabbitmq-compose.yml up -d

mvn clean install 

mvn spring-boot:run

```

### Docker

```
mvn clean install

docker-compose build

docker-compose -f docker-compose.yml up -d
```

## Executar testes

```
mvn test
```

## Swagger

http://localhost:8080/swagger-ui.html

## HAL Browser

http://localhost:8080/browser/index.html#/