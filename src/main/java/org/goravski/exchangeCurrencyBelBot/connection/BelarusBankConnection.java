package org.goravski.exchangeCurrencyBelBot.connection;

import lombok.Getter;
import org.springframework.stereotype.Component;

import java.net.HttpURLConnection;
/**
 * Set connection with Belarusbank
 */

@Component
public class BelarusBankConnection {
    private final static String URL_BELARUSBANK = "https://belarusbank.by/api/kurs_cards";

    @Getter
    public HttpURLConnection connection;

    public BelarusBankConnection(BankConnections conBank) {
        this.connection = conBank.getConnection(URL_BELARUSBANK);
    }
}
