package org.goravski.exchangeCurrencyBelBot.entity;


import lombok.AllArgsConstructor;
import lombok.Getter;


@Getter
@AllArgsConstructor
public enum CurrencyName {
    USD(1),
    EUR(1),
    RUB (100),
    BYN(1);

    private int scale;

}
