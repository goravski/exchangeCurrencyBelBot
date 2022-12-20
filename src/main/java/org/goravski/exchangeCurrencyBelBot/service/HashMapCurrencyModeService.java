package org.goravski.exchangeCurrencyBelBot.service;

import lombok.extern.slf4j.Slf4j;
import org.goravski.exchangeCurrencyBelBot.entity.CurrencyName;

import java.util.HashMap;
import java.util.Map;

/**
 * Implementation CurrencyModeService for saving currency choice
 */

@Slf4j
public class HashMapCurrencyModeService {
    private final Map<Long, CurrencyName> originalCurrency = new HashMap<>();
    private final Map<Long, CurrencyName> targetCurrency = new HashMap<>();

    private static HashMapCurrencyModeService INSTANCE;

    private HashMapCurrencyModeService() {
        System.out.println("HASHMAP for Currency is created");

    }

    public static synchronized HashMapCurrencyModeService getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new HashMapCurrencyModeService();
        }
        return INSTANCE;
    }


    public CurrencyName getOriginalCurrency(long chatId) {
        log.info("get original currency {}", chatId);
        return originalCurrency.getOrDefault(chatId, CurrencyName.USD);
    }


    public CurrencyName getTargetCurrency(long chatId) {
        log.info("get target currency {}", chatId);
        return targetCurrency.getOrDefault(chatId, CurrencyName.USD);
    }


    public void setOriginalCurrency(long chatId, CurrencyName currency) {
        originalCurrency.put(chatId, currency);
    }


    public void setTargetCurrency(long chatId, CurrencyName currency) {
        targetCurrency.put(chatId, currency);
    }
}
