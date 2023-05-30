package org.goravski.exchangeCurrencyBelBot.telegram.handlers;

import lombok.extern.slf4j.Slf4j;
import org.goravski.exchangeCurrencyBelBot.telegram.keyboard.KeyBoardFactory;
import org.telegram.telegrambots.meta.api.methods.send.SendPhoto;
import org.telegram.telegrambots.meta.api.objects.InputFile;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboard;

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
                .photo(new InputFile(
                        new File("assests/nbrb.jpg")))
                .parseMode("HTML")
                .caption("<strong>Выбери банк</strong>")
                .replyMarkup(keyBoard)
                .build();
        return sendPhoto;
    }
}
