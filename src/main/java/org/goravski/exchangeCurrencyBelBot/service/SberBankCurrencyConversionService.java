package org.goravski.exchangeCurrencyBelBot.service;

import lombok.SneakyThrows;
import org.goravski.exchangeCurrencyBelBot.entity.CurrencyName;
import org.springframework.stereotype.Component;

import java.net.HttpURLConnection;

import static org.goravski.exchangeCurrencyBelBot.connection.BankConnections.*;
import static org.goravski.exchangeCurrencyBelBot.util.RateJsonParser.*;

@Component
public class SberBankCurrencyConversionService implements CurrencyConversionService {
    private final static String URL_SBERBANK = "https://www.sber-bank.by/rates/rates.json";

    @Override
    public double getConversionRatio(CurrencyName original, CurrencyName target) {
        double originalRate = getBuyRate(original);
        double targetRate = getSaleRate(target);
        return originalRate / targetRate;
    }

    @SneakyThrows
    public double getBuyRate(CurrencyName currency) {
        HttpURLConnection connection = getConnection(URL_SBERBANK);
        return getBuyRateFromSberBamk(connection, currency);
    }

    @SneakyThrows
    public double getSaleRate(CurrencyName currency) {
        HttpURLConnection connection = getConnection(URL_SBERBANK);
        return getSaleRateFromSberBamk(connection, currency);
    }
}
