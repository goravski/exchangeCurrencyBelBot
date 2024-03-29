package org.goravski.exchangeCurrencyBelBot.entity;


import lombok.NoArgsConstructor;

/**
 * Describe Belarusbank properties
 */
@NoArgsConstructor
public class BelarusBank implements EntityInterface{
    private final String nameBank = "БЕЛАРУСБАНК";
    private final String path = "src/main/resources/assets/belarusbank-logo.jpg";

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
