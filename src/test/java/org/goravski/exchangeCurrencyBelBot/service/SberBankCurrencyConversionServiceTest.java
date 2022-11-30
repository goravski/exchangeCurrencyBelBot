package org.goravski.exchangeCurrencyBelBot.service;

import lombok.SneakyThrows;
import org.goravski.exchangeCurrencyBelBot.entity.CurrencyName;
import org.goravski.exchangeCurrencyBelBot.util.RateJsonParser;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.net.HttpURLConnection;

import static org.goravski.exchangeCurrencyBelBot.connection.BankConnections.getConnection;

@SpringBootTest
class SberBankCurrencyConversionServiceTest {

    @Autowired
    protected SberBankCurrencyConversionService service;


    @Test
    @SneakyThrows
    void getByuRateTest() {
        CurrencyName currency = CurrencyName.RUB;
        HttpURLConnection connection = getConnection("https://www.sber-bank.by/rates/rates.json");
        Double expect = RateJsonParser.getBuyRateFromSberBamk(connection, currency);
        Double result = service.getBuyRate(CurrencyName.RUB);
        System.out.println("Buy: " + expect + " = " + result);
        Assertions.assertEquals(expect, result);

    }

    @Test
    @SneakyThrows
    void getSaleRateTest() {
        CurrencyName currency = CurrencyName.USD;
        HttpURLConnection connection = getConnection("https://www.sber-bank.by/rates/rates.json");
        Double expect = RateJsonParser.getSaleRateFromSberBamk(connection, currency);
        Double result = service.getSaleRate(CurrencyName.USD);
        System.out.println("Sale: " + expect + " = " + result);
        Assertions.assertEquals(expect, result);

    }

}