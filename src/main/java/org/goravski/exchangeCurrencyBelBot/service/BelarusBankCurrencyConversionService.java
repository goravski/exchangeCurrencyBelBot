package org.goravski.exchangeCurrencyBelBot.service;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.goravski.exchangeCurrencyBelBot.connection.BankConnections;
import org.goravski.exchangeCurrencyBelBot.entity.CurrencyName;
import org.springframework.stereotype.Component;

import java.net.HttpURLConnection;

@Component
@Slf4j
@AllArgsConstructor
@Getter
public class BelarusBankCurrencyConversionService implements CurrencyConversionService{
    private final static String URL_BELARUSBANK = "https://belarusbank.by/api/kurs_cards";
    private final BankConnections conBank;
    private final BelarusBankJsonParser jsonParser;

    @Override
    @SneakyThrows
    public double getBuyRate(CurrencyName currency) {
        HttpURLConnection connection = conBank.getConnection(URL_BELARUSBANK);
        log.info("Connection SBERBANK for buy {} started", currency.name());
        return jsonParser.getBuyRate(connection, currency);
    }

    @Override
    @SneakyThrows
    public double getSaleRate(CurrencyName currency) {
        HttpURLConnection connection = conBank.getConnection(URL_BELARUSBANK);
        log.info("Connection SBERBANK for sale {} started", currency.name());
        return jsonParser.getSaleRate(connection, currency);
    }
}
