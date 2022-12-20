package org.goravski.exchangeCurrencyBelBot.telegram.handlers;


import org.goravski.exchangeCurrencyBelBot.entity.Banks;
import org.goravski.exchangeCurrencyBelBot.service.HashMapBankModeService;
import org.goravski.exchangeCurrencyBelBot.telegram.keyboard.KeyBoardFactory;
import org.goravski.exchangeCurrencyBelBot.util.Utils;
import org.goravski.exchangeCurrencyBelBot.util.Validator;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageReplyMarkup;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;

public class SetCurrencyMessageHandler extends AbstractMessageHandler {


    @Override
    public BotApiMethod<?> getSendMessage(Update update) {

        if (update.hasCallbackQuery()) {
            Long chatId = update.getCallbackQuery().getMessage().getChatId();
            Integer messageId = update.getCallbackQuery().getMessage().getMessageId();
            String param_2 = update.getCallbackQuery().getData().split(":")[1];
            if (Validator.chekBanks(param_2)) {
                HashMapBankModeService.getInstance().setBankName(chatId, Banks.valueOf(param_2));
                return SendMessage.builder()
                        .text("""
                                Выбери валюты для обмена\s
                                введи сумму в поле ввода\s
                                 ПРОДАТЬ    |    КУПИТЬ
                                """)
                        .chatId(chatId)
                        .replyMarkup(KeyBoardFactory.getKeyBoardFromFactory(update).getKeyBoard(update))
                        .build();
            } else {
                Utils.changeCurrencyInHashMap(update.getCallbackQuery());
                return EditMessageReplyMarkup.builder()
                        .chatId(chatId)
                        .messageId(messageId)
                        .replyMarkup((InlineKeyboardMarkup) KeyBoardFactory.getKeyBoardFromFactory(update).getKeyBoard(update))
                        .build();
            }
        } else {
            return SendMessage.builder()
                    .chatId(update.getCallbackQuery().getMessage().getChatId())
                    .text("something wrong")
                    .build();
        }
    }
}
