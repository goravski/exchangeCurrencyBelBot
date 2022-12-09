package org.goravski.exchangeCurrencyBelBot.service;

import jakarta.json.Json;
import jakarta.json.JsonReader;
import jakarta.json.JsonStructure;
import lombok.SneakyThrows;
import org.goravski.exchangeCurrencyBelBot.entity.CurrencyName;

import java.net.HttpURLConnection;

public interface RateJsonParser {

    @SneakyThrows
    default JsonStructure getJsonStructure(HttpURLConnection connection) {
        JsonReader reader = Json.createReader(connection.getInputStream());
        return reader.read();
    }

    Double getBuyRate(HttpURLConnection connection, CurrencyName currency);

    Double getSaleRate(HttpURLConnection connection, CurrencyName currency);
}
