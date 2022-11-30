package org.goravski.exchangeCurrencyBelBot.util;

import jakarta.json.*;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.goravski.exchangeCurrencyBelBot.entity.CurrencyName;
import org.json.JSONArray;
import org.json.JSONObject;
import java.net.HttpURLConnection;

@Slf4j
public class RateJsonParser {

    @SneakyThrows
    public static JsonStructure getJsonStructure(HttpURLConnection connection) {
        JsonReader reader = Json.createReader(connection.getInputStream());
        return reader.read();
    }

    @SneakyThrows
    public static Double getBuyRateFromSberBamk(HttpURLConnection connection, CurrencyName currency) {
        if (currency == CurrencyName.BYN) {
            return 1.0;
        }
        JsonStructure jsonStructure = getJsonStructure(connection);
        JSONArray resp = new JSONArray(jsonStructure.toString());
        for (int i = 0; i < resp.length(); i++) {
            if (resp.getJSONObject(i).has("ratescard")) {
                JSONArray list = resp.getJSONObject(i)
                        .getJSONObject("ratescard")
                        .getJSONObject("data")
                        .getJSONObject("rates")
                        .getJSONArray("list");
                for (int j = 0; j < list.length(); j++) {
                    JSONObject rate = list.getJSONObject(j);
                    if (currency.name().equals(rate.get("iso"))) {
                        log.info("Get Buy rate {}", currency.name());
                        return rate.getDouble("buy") / currency.getScale();
                    }
                }
            }
        }
        return 0.0;
    }

    @SneakyThrows
    public static Double getSaleRateFromSberBamk(HttpURLConnection connection, CurrencyName currency) {
        if (currency == CurrencyName.BYN) {
            return 1.0;
        }
        JsonStructure jsonStructure = getJsonStructure(connection);
        JSONArray resp = new JSONArray(jsonStructure.toString());
        for (int i = 0; i < resp.length(); i++) {
            if (resp.getJSONObject(i).has("ratescard")) {
                JSONArray list = resp.getJSONObject(i)
                        .getJSONObject("ratescard")
                        .getJSONObject("data")
                        .getJSONObject("rates")
                        .getJSONArray("list");
                for (int j = 0; j < list.length(); j++) {
                    JSONObject rate = list.getJSONObject(j);
                    if (currency.name().equals(rate.get("iso"))) {
                        log.info("Get Sale rate {}", currency.name());
                        return rate.getDouble("sale") / currency.getScale();
                    }
                }
            }
        }
        return 0.0;
    }

}
