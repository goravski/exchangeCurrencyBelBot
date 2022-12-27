package org.goravski.exchangeCurrencyBelBot.entity;

import lombok.NoArgsConstructor;
import org.goravski.exchangeCurrencyBelBot.util.Emoji;

@NoArgsConstructor
public class Back implements EntityInterface{
    private final String nameBank = "НАЗАД"  + Emoji.TURN;
    private final String path = "";

    public String getNameBank() {
        return nameBank;
    }

    public String getPath() {
        return path;
    }

    @Override
    public String toString() {
        return nameBank;
    }
}
