package org.goravski.exchangeCurrencyBelBot.service;

import lombok.SneakyThrows;
import org.goravski.exchangeCurrencyBelBot.entity.CurrencyName;
import java.net.HttpURLConnection;

import static org.goravski.exchangeCurrencyBelBot.connection.BankConnections.*;
import static org.goravski.exchangeCurrencyBelBot.util.RateJsonParser.*;


public class SberBankCurrencyConversionService implements CurrencyConversionService {
    @Override
    public double getConversionRatio(CurrencyName original, CurrencyName target) {
        double originalRate = getRate(original);
        double targetRate = getRate(target);
        return originalRate / targetRate;
    }

    @SneakyThrows
    public double getRate(CurrencyName currency) {
        HttpURLConnection connection = getConnection("https://www.sber-bank.by/rates/rates.json");
        return getBuyRateFromSberBamk(connection, currency);
    }
}
