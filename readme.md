# VirtualStore

Para rodar há duas opções via Maven ou Docker:


### Maven

``` 
docker-compose up &

mvn clean install 

mvn spring-boot:run


```

### Docker

```
docker-compose up &

mvn clean install

docker image build -t xbrain-online-store:1.0.0 .

docker container run --publish 8080:8080 --detach --name xbrain-online-store xbrain-online-store:1.0.0

```

### Swagger

http://localhost:8080/swagger-ui.html

### HAL Browser

http://localhost:8080/browser/index.html#/