package com.cigi.currencyconversionservice.controller;

import com.cigi.currencyconversionservice.domain.CurrencyConversionBean;
import com.cigi.currencyconversionservice.proxy.CurrencyExchangeProxy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

@RestController
public class CurrencyConversionController {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private final CurrencyExchangeProxy proxy;

    public CurrencyConversionController(CurrencyExchangeProxy proxy) {
        this.proxy = proxy;
    }

    @GetMapping("/currency-converter-feign/from/{from}/to/{to}/quantity/{quantity}")
    public CurrencyConversionBean convertCurrencyFeign(@PathVariable String from, @PathVariable String to, @PathVariable BigDecimal quantity) {
        CurrencyConversionBean response = proxy.getExchangeValue(from, to);
        logger.info("{}", response);
        return new CurrencyConversionBean(response.getId(), from, to, response.getConversionMultiple(), quantity, quantity.multiply(response.getConversionMultiple()), response.getPort());
    }
}
