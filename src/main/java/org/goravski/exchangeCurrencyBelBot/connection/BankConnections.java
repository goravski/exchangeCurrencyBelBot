package org.goravski.exchangeCurrencyBelBot.connection;


import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import java.net.HttpURLConnection;
import java.net.URL;

@Slf4j
public class BankConnections {

    @SneakyThrows
    public static HttpURLConnection getConnection(String stringUrl) {
        URL url = new URL(stringUrl);
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestMethod("GET");
        log.info("Connection prepared");
        return con;
    }
}
