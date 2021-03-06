package com.in28minutes.microservices.currencyexchangeservice;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class CurrencyExchangeController {

    private final int port;
    private final ExchangeValueRepository exchangeValueRepository;

    public CurrencyExchangeController(@Value("${server.port}") int port, ExchangeValueRepository exchangeValueRepository) {
        this.port = port;
        this.exchangeValueRepository = exchangeValueRepository;
    }

    @GetMapping("/currency-exchange/from/{from}/to/{to}")
    public ExchangeValue retrieveExchangeValue(@PathVariable("from") String from,
                                               @PathVariable("to") String to) {

        ExchangeValue byFromAndTo = exchangeValueRepository.findByFromAndTo(from, to);
        byFromAndTo.setPort(port);
        log.info("{}", byFromAndTo);
        return byFromAndTo;
//        return ExchangeValue.builder()
//                .id(1000l)
//                .from(from)
//                .to(to)
//                .conversionMultiple(BigDecimal.valueOf(65))
//                .port(port)
//                .build();

    }
}
