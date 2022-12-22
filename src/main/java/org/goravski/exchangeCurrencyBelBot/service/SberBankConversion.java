package org.goravski.exchangeCurrencyBelBot.service;

import jakarta.json.JsonStructure;
import lombok.extern.slf4j.Slf4j;
import org.goravski.exchangeCurrencyBelBot.entity.BanksType;
import org.goravski.exchangeCurrencyBelBot.entity.CurrencyName;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class SberBankConversion implements CurrencyConversionService<SberBankConnection> {

    SberBankConnection conBank;
    JsonStructure jsonStructure;
    JSONArray resp;

    @Autowired
    public SberBankConversion (SberBankConnection conBank){
        this.conBank = conBank;
        jsonStructure = getJsonStructure(conBank.getConnection());
        resp = new JSONArray(jsonStructure.toString());
    }


    @Override
    public BanksType getBankType() {
        return BanksType.SBERBANK;
    }

    @Override
    public double getBuyRate(CurrencyName currency) {
        if (currency == CurrencyName.BYN) {
            return 1.0;
        }

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
    public double getSaleRate(CurrencyName currency) {
        if (currency == CurrencyName.BYN) {
            return 1.0;
        }

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
