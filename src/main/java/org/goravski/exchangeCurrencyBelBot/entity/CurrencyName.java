package org.goravski.exchangeCurrencyBelBot.entity;


import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum CurrencyName {
    USD,
    EUR,
    RUB,
    BYN;

    private String code;

}
