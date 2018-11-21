# Config Server 
* enable 
```
management.endpoints.web.exposure.include=*
```

* request server, through actuator, to pick up latest values:
```
 curl -XPOST http://localhost:8080/actuator/refresh
```

# Eureka naming server

http://localhost:8761/

# Zipkin
* launches zipkin and rabbitmq 
docker-compose up 

http://localhost:9411/zipkin
# Zuul 

http :8765/{service-name}/{path}
http :8765/currency-exchange-service/currency-exchange/from/USD/to/INR
proxying...
http :8000/currency-exchange/from/USD/to/INR

 http :8765/currency-conversion-service/currency-converter-feign/from/USD/to/INR/quantity/1000
