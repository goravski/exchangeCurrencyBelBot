package org.goravski.exchangeCurrencyBelBot.service;

import jakarta.json.JsonStructure;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.goravski.exchangeCurrencyBelBot.entity.CurrencyName;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Component;

import java.net.HttpURLConnection;

@Slf4j
@Component
@RequiredArgsConstructor
@Getter
public class SberRateJsonParser implements RateJsonParser {

    @Override
    public Double getBuyRate(HttpURLConnection connection, CurrencyName currency) {
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

    @Override
    public Double getSaleRate(HttpURLConnection connection, CurrencyName currency) {
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
