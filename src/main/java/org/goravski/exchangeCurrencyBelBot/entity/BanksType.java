package org.goravski.exchangeCurrencyBelBot.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.goravski.exchangeCurrencyBelBot.util.Emoji;

@Getter
@AllArgsConstructor
public enum BanksType {
    SBERBANK ("СБЕРБАНК"),
    BELARUSBANK("БЕЛАРУСБАНК"),
    BACK("НАЗАД  " + Emoji.TURN);

    private String nameBank;

}
