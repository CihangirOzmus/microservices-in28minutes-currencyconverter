package com.cigi.currencyexchangeservice.controller;

import com.cigi.currencyexchangeservice.domain.ExchangeValue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.Objects;

@RestController
@RequestMapping("/currency-exchange")
public class CurrencyExchangeController {

    @Autowired
    private Environment environment;

    @GetMapping("/from/{from}/to/{to}")
    public ExchangeValue getExchangeValue(@PathVariable String from, @PathVariable String to){
        return new ExchangeValue(1000L, from, to, BigDecimal.valueOf(65), Integer.parseInt(Objects.requireNonNull(environment.getProperty("local.server.port"))));
    }
}
