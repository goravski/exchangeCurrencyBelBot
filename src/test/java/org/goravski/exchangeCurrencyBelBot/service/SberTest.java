package org.goravski.exchangeCurrencyBelBot.service;

import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.goravski.exchangeCurrencyBelBot.connection.BankConnections;
import org.goravski.exchangeCurrencyBelBot.entity.CurrencyName;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.net.HttpURLConnection;

@SpringBootTest
@RequiredArgsConstructor
public class SberTest {

//    @Autowired
//    protected SberBankCurrencyConversionService service;
//    @Autowired
//    private SberRateJsonParser jsonParser;
    @Autowired
    private BankConnections connections;


    @Test
    @SneakyThrows
    void getByuRateTest() {
        CurrencyName currency = CurrencyName.RUB;
        HttpURLConnection connection = connections.getConnection("https://www.sber-bank.by/rates/rates.json");
//        Double expect = jsonParser.getBuyRate(connection, currency);
//        Double result = service.getBuyRate(CurrencyName.RUB);
//        System.out.println("Buy: " + expect + " = " + result);
//        Assertions.assertEquals(expect, result);

    }

    @Test
    @SneakyThrows
    void getSaleRateTest() {
        CurrencyName currency = CurrencyName.USD;
        HttpURLConnection connection = connections.getConnection("https://www.sber-bank.by/rates/rates.json");
//        Double expect = jsonParser.getSaleRate(connection, currency);
//        Double result = service.getSaleRate(CurrencyName.USD);
//        System.out.println("Sale: " + expect + " = " + result);
//        Assertions.assertEquals(expect, result);

    }
}
