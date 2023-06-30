package org.goravski.exchangeCurrencyBelBot.telegram.handlers;


import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.binary.Base64;
import org.goravski.exchangeCurrencyBelBot.entity.BanksType;
import org.goravski.exchangeCurrencyBelBot.service.HashMapBankModeService;
import org.goravski.exchangeCurrencyBelBot.telegram.keyboard.KeyBoardFactory;
import org.goravski.exchangeCurrencyBelBot.util.Images;
import org.goravski.exchangeCurrencyBelBot.util.Utils;
import org.goravski.exchangeCurrencyBelBot.util.Validator;
import org.telegram.telegrambots.meta.api.methods.send.SendPhoto;
import org.telegram.telegrambots.meta.api.objects.InputFile;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.io.ByteArrayInputStream;
import java.io.File;
/**
 * Builds message to offer currency choice
 */
@Slf4j
public class SetCurrencyMessageHandler extends AbstractMessageHandler {
    HashMapBankModeService mapBank = HashMapBankModeService.getInstance();

    @Override
    public SendPhoto getSendMessage(Update update) {

        if (update.hasCallbackQuery()) {
            Long chatId = update.getCallbackQuery().getMessage().getChatId();
            String param_2 = update.getCallbackQuery().getData().split(":")[1];
            if (Validator.chekBanks(param_2)) {
                HashMapBankModeService.getInstance().setBankEntity(chatId, BanksType.valueOf(param_2));
                log.info("SetCurrencyMessageHandler send message make choice currency keyboard");
                return SendPhoto.builder()
                        .parseMode("HTML")
//                        .photo(new InputFile(
//                                new File(mapBank.getBankEntity(chatId).getNameBankInterface().getPath())))
                        .photo( new InputFile(
                                new ByteArrayInputStream(Base64.decodeBase64(Images.NATBANK)), "natbank.png"))
                        .caption("""
                                \s
                                1. Выбери валюты для обмена\s
                                2. введи сумму в поле ввода\s
                                \s

                                """)
                        .chatId(chatId)
                        .replyMarkup(KeyBoardFactory.getKeyBoardFromFactory(update).getKeyBoard(update))
                        .build();

            } else {
                Utils.changeCurrencyInHashMap(update.getCallbackQuery());
                log.info("SetCurrencyMessageHandler send keyboard");
                return SendPhoto.builder()
                        .chatId(chatId)
//                        .photo(new InputFile(
//                                new File(mapBank.getBankEntity(chatId).getNameBankInterface().getPath())))
                        .photo( new InputFile(
                                new ByteArrayInputStream(Base64.decodeBase64(Images.NATBANK)), "natbank.png"))
                        .replyMarkup(KeyBoardFactory.getKeyBoardFromFactory(update).getKeyBoard(update))
                        .build();
            }
        } else {
            log.info("SetCurrencyMessageHandler send error message");
            return SendPhoto.builder()
                    .chatId(update.getCallbackQuery().getMessage().getChatId())
                    .photo( new InputFile(
                            new ByteArrayInputStream(Base64.decodeBase64(Images.ERROR)), "error.png"))
                    .caption("something wrong")
                    .build();
        }
    }
}
