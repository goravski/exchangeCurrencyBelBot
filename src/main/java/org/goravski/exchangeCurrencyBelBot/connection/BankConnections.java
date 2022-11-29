package org.goravski.exchangeCurrencyBelBot.connection;


import lombok.SneakyThrows;
import java.net.HttpURLConnection;
import java.net.URL;


public class BankConnections {

    @SneakyThrows
    public static HttpURLConnection getConnection(String stringUrl) {
        URL url = new URL(stringUrl);
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestMethod("GET");
        return con;
    }
}
