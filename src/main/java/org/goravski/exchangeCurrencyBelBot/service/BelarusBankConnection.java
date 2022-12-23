package org.goravski.exchangeCurrencyBelBot.service;

import lombok.Getter;
import org.goravski.exchangeCurrencyBelBot.connection.BankConnections;
import org.springframework.stereotype.Component;

import java.net.HttpURLConnection;

@Component
public class BelarusBankConnection {
    private final static String URL_BELARUSBANK = "https://belarusbank.by/api/kurs_cards";

    @Getter
    public HttpURLConnection connection;

    public BelarusBankConnection(BankConnections conBank) {
        this.connection = conBank.getConnection(URL_BELARUSBANK);
    }
}
