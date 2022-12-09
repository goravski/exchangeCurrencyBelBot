package org.goravski.exchangeCurrencyBelBot.service;

import org.goravski.exchangeCurrencyBelBot.entity.CurrencyName;

public interface CurrencyConversionService {

    double getConversionRatio(CurrencyName original, CurrencyName target);

    double getBuyRate(CurrencyName currency);

    double getSaleRate(CurrencyName currency);

}
