package com.in28minutes.microservices.currencyconversionservice;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

@RestController
public class CurrencyConversionController {
    @GetMapping("/currency-converter/from/{from}/to/{to}/quantity/{quantity}")
    public CurrencyConversion convertCurrency(@PathVariable("from") String from,
                                              @PathVariable("to") String to,
                                              @PathVariable("quantity")BigDecimal quantity) {

        return CurrencyConversion.builder()
                .id(10l)
                .from(from)
                .to(to)
                .quantity(quantity)
                .conversionMultiple(BigDecimal.ONE)
                .port(0)
                .build();
    }
}
