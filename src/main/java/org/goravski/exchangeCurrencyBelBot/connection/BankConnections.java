package org.goravski.exchangeCurrencyBelBot.connection;


import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.net.HttpURLConnection;
import java.net.URL;

@Slf4j
@Component
@RequiredArgsConstructor
public class BankConnections {

    @SneakyThrows
    public HttpURLConnection getConnection(String stringUrl) {
        URL url = new URL(stringUrl);
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestMethod("GET");
        log.info("Connection prepared");
        return con;
    }
}
