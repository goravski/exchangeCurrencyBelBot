package org.goravski.exchangeCurrencyBelBot.service;

import org.goravski.exchangeCurrencyBelBot.entity.BanksType;

public interface Conversioner<T> {
    BanksType getBankType();

    Double conversion(T bank);
}
