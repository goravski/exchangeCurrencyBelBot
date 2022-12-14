package org.goravski.exchangeCurrencyBelBot.connection;

import lombok.Getter;
import org.goravski.exchangeCurrencyBelBot.connection.BankConnections;
import org.springframework.stereotype.Component;

import java.net.HttpURLConnection;
/**
 * Set connection with Sberbank
 */
@Component
public class SberBankConnection {

    private final static String URL_SBERBANK = "https://www.sber-bank.by/rates/rates.json";

    @Getter
    public HttpURLConnection connection;

    private SberBankConnection(BankConnections conBank) {
        this.connection = conBank.getConnection(URL_SBERBANK);
    }

}
