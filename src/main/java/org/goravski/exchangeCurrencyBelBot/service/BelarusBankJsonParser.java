package org.goravski.exchangeCurrencyBelBot.service;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.goravski.exchangeCurrencyBelBot.entity.CurrencyName;
import org.springframework.stereotype.Component;

import java.net.HttpURLConnection;

@Slf4j
@Component
@RequiredArgsConstructor
@Getter
public class BelarusBankJsonParser implements RateJsonParser{
    @Override
    public Double getBuyRate(HttpURLConnection connection, CurrencyName currency) {
        return null;
    }

    @Override
    public Double getSaleRate(HttpURLConnection connection, CurrencyName currency) {
        return null;
    }
}
