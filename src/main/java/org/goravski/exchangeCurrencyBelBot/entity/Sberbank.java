package org.goravski.exchangeCurrencyBelBot.entity;

import lombok.NoArgsConstructor;

/**
 * Describe Sberbank properties
 */
@NoArgsConstructor
public class Sberbank implements EntityInterface{
    private final String nameBank = "СБЕРБАНК";
    private final String path = "src/main/resources/assets/sberbank.png";

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
