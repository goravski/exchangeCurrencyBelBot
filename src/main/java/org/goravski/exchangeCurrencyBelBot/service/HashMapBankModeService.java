package org.goravski.exchangeCurrencyBelBot.service;

import lombok.extern.slf4j.Slf4j;
import org.goravski.exchangeCurrencyBelBot.entity.Banks;

import java.util.HashMap;
import java.util.Map;

/**
 * Saving Bank choice
 */


@Slf4j
public class HashMapBankModeService {
    private static  HashMapBankModeService INSTANCE;

    private final Map<Long, Banks> choiceBank = new HashMap<>();

    private HashMapBankModeService (){
        System.out.println("HASHMAP for Bank is created");
    }

    public static synchronized HashMapBankModeService getInstance(){
        if (INSTANCE == null){
            INSTANCE = new HashMapBankModeService();
        }
        return INSTANCE;
    }
     public Banks getBankName(Long chatId) {
        log.debug("get name {} by {}", choiceBank.get(chatId), chatId);
        return choiceBank.get(chatId);
    }

    public void setBankName(Long chatId, Banks banks) {
        log.debug("set bank choice {} by {}", choiceBank.get(chatId), chatId);
        choiceBank.put(chatId, banks);
    }
}
