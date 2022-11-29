package org.goravski.exchangeCurrencyBelBot.service;

import org.goravski.exchangeCurrencyBelBot.entity.CurrencyName;

public interface CurrencyConversionService {
    static CurrencyConversionService getInstance() {
        return new SberBankCurrencyConversionService();
    }

    double getConversionRatio(CurrencyName original, CurrencyName target);

}
