package org.goravski.exchangeCurrencyBelBot.util;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.goravski.exchangeCurrencyBelBot.entity.CurrencyName;
import org.goravski.exchangeCurrencyBelBot.service.HashMapCurrencyModeService;
import org.telegram.telegrambots.meta.api.objects.CallbackQuery;
import org.telegram.telegrambots.meta.api.objects.Message;

/**
 * Set utility methods
 */
@Slf4j
public class Utils {
    /**
     * Rewrite currency keyboard according users choice
     */
    @SneakyThrows
    public static void changeCurrencyInHashMap(CallbackQuery callbackQuery) {
        Message message = callbackQuery.getMessage();
        String[] params = callbackQuery.getData().split(":");
        String action = params[1];
        CurrencyName newCurrencyName = CurrencyName.valueOf(params[2]);
        log.info("change currency to {}", newCurrencyName);
        HashMapCurrencyModeService hashMapCurrency = HashMapCurrencyModeService.getInstance();
        switch (action) {
            case "Продажа" -> {
                hashMapCurrency.setOriginalCurrency(message.getChatId(), newCurrencyName);
                log.info("set original currency {} {}", message.getFrom().getFirstName(), newCurrencyName);
            }
            case "Покупка" -> {
                hashMapCurrency.setTargetCurrency(message.getChatId(), newCurrencyName);
                log.info("set target currency {} {}", message.getFrom().getFirstName(), newCurrencyName);
            }
        }
    }
}
