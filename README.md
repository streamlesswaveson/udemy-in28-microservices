# Zuul 

http :8765/{service-name}/{path}
http :8765/currency-exchange-service/currency-exchange/from/USD/to/INR
proxying...
http :8000/currency-exchange/from/USD/to/INR

 http :8765/currency-conversion-service/currency-converter-feign/from/USD/to/INR/quantity/1000
