package com.in28minutes.microservices.currencyconversionservice;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@RestController
public class CurrencyConversionController {

    private final CurrencyExchangeProxy currencyExchangeProxy;

    public CurrencyConversionController(CurrencyExchangeProxy currencyExchangeProxy) {
        this.currencyExchangeProxy = currencyExchangeProxy;
    }


    @GetMapping("/currency-converter-feign/from/{from}/to/{to}/quantity/{quantity}")
    public CurrencyConversion convertCurrencyFeign(@PathVariable("from") String from,
                                              @PathVariable("to") String to,
                                              @PathVariable("quantity") BigDecimal quantity) {

        log.info("Using feign...");
        CurrencyConversion lookup = currencyExchangeProxy.retrieveExchangeValue(from,to);

        return lookup.toBuilder()
                .quantity(quantity)
                .totalCalculatedAmount(quantity.multiply(lookup.getConversionMultiple()))
                .build();

    }

    @GetMapping("/currency-converter/from/{from}/to/{to}/quantity/{quantity}")
    public CurrencyConversion convertCurrency(@PathVariable("from") String from,
                                              @PathVariable("to") String to,
                                              @PathVariable("quantity") BigDecimal quantity) {

        CurrencyConversion lookup = new LookupWithRestTemplate().lookup(from, to);

        return lookup.toBuilder()
                .quantity(quantity)
                .totalCalculatedAmount(quantity.multiply(lookup.getConversionMultiple()))
                .build();

    }

    static class LookupStatic {
        CurrencyConversion lookup(String from, String to) {
            return CurrencyConversion.builder()
                    .id(10l)
                    .from(from)
                    .to(to)
                    .conversionMultiple(BigDecimal.ONE)
                    .port(0)
                    .build();
        }
    }

    static class LookupWithRestTemplate {
        private String endpoint = "http://localhost:8000/currency-exchange/from/{from}/to/{to}";

        CurrencyConversion lookup(String from, String to) {
            Map<String, String> myvars = new HashMap<>();
            myvars.put("from", from);
            myvars.put("to", to);
            ResponseEntity<CurrencyConversion> entity = new RestTemplate().getForEntity(endpoint, CurrencyConversion.class, myvars);
            return entity.getBody();
        }
    }
}
