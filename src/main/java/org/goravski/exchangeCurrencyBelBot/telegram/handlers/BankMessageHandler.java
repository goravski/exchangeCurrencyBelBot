package org.goravski.exchangeCurrencyBelBot.telegram.handlers;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.binary.Base64;
import org.goravski.exchangeCurrencyBelBot.telegram.keyboard.KeyBoardFactory;
import org.goravski.exchangeCurrencyBelBot.util.Images;
import org.telegram.telegrambots.meta.api.methods.send.SendPhoto;
import org.telegram.telegrambots.meta.api.objects.InputFile;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboard;

import java.io.ByteArrayInputStream;
import java.io.File;
/**
 * Builds message to offer bank choice
 */
@Slf4j
public class BankMessageHandler extends AbstractMessageHandler {

    @Override
    public SendPhoto getSendMessage(Update update) {
        ReplyKeyboard keyBoard = KeyBoardFactory.getKeyBoardFromFactory(update).getKeyBoard(update);
        log.info("BankMessageHandler sendMessage");
        SendPhoto sendPhoto = SendPhoto.builder()
                .chatId(update.getCallbackQuery().getMessage().getChatId())
                .photo( new InputFile(
                        new ByteArrayInputStream(Base64.decodeBase64(Images.NATBANK)), "natbank.png"))
                .parseMode("HTML")
                .caption("<strong>Выбери банк</strong>")
                .replyMarkup(keyBoard)
                .build();
        return sendPhoto;
    }
}
