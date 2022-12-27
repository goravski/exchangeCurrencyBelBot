package org.goravski.exchangeCurrencyBelBot.entity;

import java.util.function.Supplier;


public enum BanksType {
    SBERBANK (Sberbank::new),
    BELARUSBANK(BelarusBank::new),
    BACK(Back::new);

    BanksType(Supplier<EntityInterface> consumer) {
        this.nameBank = consumer.get();
    }

    private final EntityInterface nameBank;

    public EntityInterface getNameBankInterface() {
        return nameBank;
    }
}
