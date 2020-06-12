package com.cigi.currencyexchangeservice.controller;

import com.cigi.currencyexchangeservice.domain.ExchangeValue;
import com.cigi.currencyexchangeservice.repository.ExchangeValueRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CurrencyExchangeController {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private final ExchangeValueRepository exchangeValueRepository;
    private final Environment environment;

    public CurrencyExchangeController(ExchangeValueRepository exchangeValueRepository, Environment environment) {
        this.exchangeValueRepository = exchangeValueRepository;
        this.environment = environment;
    }

    @GetMapping("/currency-exchange/from/{from}/to/{to}")
    public ExchangeValue getExchangeValue(@PathVariable String from, @PathVariable String to) {
        ExchangeValue exchangeValue = exchangeValueRepository.findByFromAndTo(from, to);
        exchangeValue.setPort(environment.getProperty("local.server.port"));
        logger.info("{}", exchangeValue);
        return exchangeValue;
    }
}
