package org.goravski.exchangeCurrencyBelBot.service;

import lombok.extern.slf4j.Slf4j;
import org.goravski.exchangeCurrencyBelBot.entity.CurrencyName;

import java.util.HashMap;
import java.util.Map;

/**
 * Implementation CurrencyModeService
 */
@Slf4j
public class HashMapCurrencyModeService implements CurrencyModeService {
    private final Map<Long, CurrencyName> originalCurrency = new HashMap<>();
    private final Map<Long, CurrencyName> targetCurrency = new HashMap<>();

    public HashMapCurrencyModeService() {
        System.out.println("HASHMAP MODE is created");
    }

    @Override
    public CurrencyName getOriginalCurrency(long chatId) {
        log.info("get original currency {}", chatId);
        return originalCurrency.getOrDefault(chatId, CurrencyName.USD);
    }

    @Override
    public CurrencyName getTargetCurrency(long chatId) {
        log.info("get target currency {}", chatId);
        return targetCurrency.getOrDefault(chatId, CurrencyName.USD);
    }

    @Override
    public void setOriginalCurrency(long chatId, CurrencyName currency) {
        originalCurrency.put(chatId, currency);
    }

    @Override
    public void setTargetCurrency(long chatId, CurrencyName currency) {
        targetCurrency.put(chatId, currency);
    }
}
