package org.goravski.exchangeCurrencyBelBot.service;


import org.goravski.exchangeCurrencyBelBot.entity.CurrencyName;

public interface CurrencyModeService {

    static CurrencyModeService getInstance() {
        return new HashMapCurrencyModeService();
    }

    CurrencyName getOriginalCurrency(long chatId);

    CurrencyName getTargetCurrency(long chatId);

    void setOriginalCurrency(long chatId, CurrencyName currency);

    void setTargetCurrency(long chatId, CurrencyName currency);
}