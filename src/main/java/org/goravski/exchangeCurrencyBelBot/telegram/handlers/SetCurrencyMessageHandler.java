package org.goravski.exchangeCurrencyBelBot.telegram.handlers;


import lombok.extern.slf4j.Slf4j;
import org.goravski.exchangeCurrencyBelBot.entity.BanksType;
import org.goravski.exchangeCurrencyBelBot.service.HashMapBankModeService;
import org.goravski.exchangeCurrencyBelBot.telegram.keyboard.KeyBoardFactory;
import org.goravski.exchangeCurrencyBelBot.util.Utils;
import org.goravski.exchangeCurrencyBelBot.util.Validator;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageReplyMarkup;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;

@Slf4j
public class SetCurrencyMessageHandler extends AbstractMessageHandler {
    HashMapBankModeService mapBank = HashMapBankModeService.getInstance();

    @Override
    public BotApiMethod<?> getSendMessage(Update update) {

        if (update.hasCallbackQuery()) {
            Long chatId = update.getCallbackQuery().getMessage().getChatId();
            Integer messageId = update.getCallbackQuery().getMessage().getMessageId();
            String param_2 = update.getCallbackQuery().getData().split(":")[1];
            if (Validator.chekBanks(param_2)) {
                HashMapBankModeService.getInstance().setBankName(chatId, BanksType.valueOf(param_2));
                log.info("SetCurrencyMessageHandler send message make choice currency keyboard");
                return SendMessage.builder()
                        .text(String.format( """
                                Выбран %s
                                \s
                                Выбери валюты для обмена\s
                                и\s
                                введи сумму в поле ввода\s
                                \s
                                 ПРОДАТЬ    |    КУПИТЬ
                                """, mapBank.getBankName(chatId)))
                        .chatId(chatId)
                        .replyMarkup(KeyBoardFactory.getKeyBoardFromFactory(update).getKeyBoard(update))
                        .build();
            } else {
                Utils.changeCurrencyInHashMap(update.getCallbackQuery());
                log.info("SetCurrencyMessageHandler send keyboard");
                return EditMessageReplyMarkup.builder()
                        .chatId(chatId)
                        .messageId(messageId)
                        .replyMarkup((InlineKeyboardMarkup) KeyBoardFactory.getKeyBoardFromFactory(update).getKeyBoard(update))
                        .build();
            }
        } else {
            log.info("SetCurrencyMessageHandler send error message");
            return SendMessage.builder()
                    .chatId(update.getCallbackQuery().getMessage().getChatId())
                    .text("something wrong")
                    .build();
        }
    }
}
