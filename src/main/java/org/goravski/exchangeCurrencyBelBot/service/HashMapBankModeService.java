package org.goravski.exchangeCurrencyBelBot.service;

import lombok.extern.slf4j.Slf4j;
import org.goravski.exchangeCurrencyBelBot.entity.BanksType;

import java.util.HashMap;
import java.util.Map;

/**
 * Saving Bank choice
 */


@Slf4j
public class HashMapBankModeService {
    private static  HashMapBankModeService INSTANCE;

    private final Map<Long, BanksType> choiceBank = new HashMap<>();

    private HashMapBankModeService (){
        System.out.println("HASHMAP for Bank is created");
    }

    public static synchronized HashMapBankModeService getInstance(){
        if (INSTANCE == null){
            INSTANCE = new HashMapBankModeService();
        }
        return INSTANCE;
    }
     public BanksType getBankName(Long chatId) {
        log.info("get bank {} by {}", choiceBank.get(chatId), chatId);
        return choiceBank.get(chatId);
    }

    public void setBankName(Long chatId, BanksType banks) {
        log.info("set bank choice {} by {}", choiceBank.get(chatId), chatId);
        choiceBank.put(chatId, banks);
    }
}
