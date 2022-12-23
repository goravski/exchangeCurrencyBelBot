package org.goravski.exchangeCurrencyBelBot.service;

import jakarta.json.JsonStructure;
import lombok.extern.slf4j.Slf4j;
import org.goravski.exchangeCurrencyBelBot.entity.BanksType;
import org.goravski.exchangeCurrencyBelBot.entity.CurrencyName;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Iterator;

@Component
@Slf4j
public class BelarusBankConversion implements CurrencyConversionService {

    private final JSONObject jsonObject;

    @Autowired
    public BelarusBankConversion(BelarusBankConnection conBank) {
        JsonStructure jsonStructure = getJsonStructure(conBank.getConnection());
        jsonObject = new JSONArray(jsonStructure.toString()).getJSONObject(0);
    }

    @Override
    public BanksType getBankType() {
        return BanksType.BELARUSBANK;
    }

    @Override
    public double getConversionRatio(CurrencyName original, CurrencyName target) {
        if (original.equals(CurrencyName.BYN) || target.equals(CurrencyName.BYN)) {
            return CurrencyConversionService.super.getConversionRatio(original, target);
        }

        return jsonObject.getDouble(keyConversionExtractor(original, target));
    }

    @Override
    public double getBuyRate(CurrencyName currency) {
        if (currency == CurrencyName.BYN) {
            return 1.0;
        }
        return jsonObject.getDouble(keyBuyExtractor(currency));
    }


    @Override
    public double getSaleRate(CurrencyName currency) {
        if (currency == CurrencyName.BYN) {
            return 1.0;
        }
        return jsonObject.getDouble(keySaleExtractor(currency));
    }

    private String keyBuyExtractor(CurrencyName currency) {
        Iterator<String> keys = jsonObject.keys();
        while (keys.hasNext()) {
            String key = keys.next();
            String[] s = key.split("_");
            if (s[0].contains(currency.name()) & s[1].equals("in")) {
                return key;
            }
        }
        return "";
    }

    private String keySaleExtractor(CurrencyName currency) {
        Iterator<String> keys = jsonObject.keys();
        while (keys.hasNext()) {
            String key = keys.next();
            String[] s = key.split("_");
            if (s[0].contains(currency.name()) & s[1].equals("out")) {
                return key;
            }
        }
        return "";
    }

    private String keyConversionExtractor(CurrencyName original, CurrencyName target) {
        Iterator<String> keys = jsonObject.keys();
        while (keys.hasNext()) {
            String key = keys.next();
            String[] s = key.split("_");
            if (s.length == 3) {
                if (s[0].contains(original.name()) & s[1].contains(target.name()) & s[2].equals("in")) {
                    return key;
                }
                if (s[0].contains(target.name()) & s[1].contains(original.name()) & s[2].equals("out")) {
                    return key;
                }
            }
        }
        return "";
    }
}
