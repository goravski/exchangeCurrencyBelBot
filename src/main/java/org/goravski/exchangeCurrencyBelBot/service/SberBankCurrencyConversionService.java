package org.goravski.exchangeCurrencyBelBot.service;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.goravski.exchangeCurrencyBelBot.connection.BankConnections;
import org.goravski.exchangeCurrencyBelBot.entity.CurrencyName;
import org.springframework.stereotype.Component;

import java.net.HttpURLConnection;

/**
 * Implementation CurrencyConversionService interface for Sberbank Belarus
 */

@Component
@Slf4j
@AllArgsConstructor
@Getter
public class SberBankCurrencyConversionService implements CurrencyConversionService {
    private final static String URL_SBERBANK = "https://www.sber-bank.by/rates/rates.json";

    private final BankConnections conBank;

    private final SberRateJsonParser jsonParser;

    @SneakyThrows
    @Override
    public double getBuyRate(CurrencyName currency) {
        HttpURLConnection connection = conBank.getConnection(URL_SBERBANK);
        log.info("Connection for buy {} started", currency.name());
        return jsonParser.getBuyRate(connection, currency);
    }

    @SneakyThrows
    @Override
    public double getSaleRate(CurrencyName currency) {
        HttpURLConnection connection = conBank.getConnection(URL_SBERBANK);
        log.info("Connection for sale {} started", currency.name());
        return jsonParser.getSaleRate(connection, currency);
    }
}
