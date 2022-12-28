package org.goravski.exchangeCurrencyBelBot.util;

import org.goravski.exchangeCurrencyBelBot.entity.BanksType;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.util.stream.Stream;
/**
 * Validation message params
 */
public class Validator {
    /**
     * Validate is CallbackQuery not empty
     */
    public static boolean chekCallBackQuery (Update update){
        return update.hasCallbackQuery();
    }
    /**
     * Validate is MessageEntity not empty
     */
    public static boolean chekMessageEntity(Message message) {
        return message.hasEntities();
    }
    /**
     * Validate MessageEntity type
     */
    public static boolean chekEntityType(Message message, String entityType) {
       if (chekMessageEntity(message)){
           return message.getEntities().stream()
                   .anyMatch(e -> e.getType().equals(entityType));
       }
        return false;
    }

    /**
     * Validate bank name
     */
    public static boolean chekBanks (String bank){
        return Stream.of(BanksType.values()).anyMatch(banks -> bank.equals(banks.toString()));
    }
}
