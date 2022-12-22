package org.goravski.exchangeCurrencyBelBot.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum BanksType {
    SBERBANK ("СБЕРБАНК"),
    BELARUSBANK("БЕЛАРУСБАНК");

    private String nameBank;

}
