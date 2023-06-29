package org.goravski.exchangeCurrencyBelBot.telegram.handlers;


import lombok.extern.slf4j.Slf4j;
import org.goravski.exchangeCurrencyBelBot.telegram.keyboard.KeyBoardFactory;
import org.goravski.exchangeCurrencyBelBot.util.Validator;
import org.telegram.telegrambots.meta.api.methods.send.SendPhoto;
import org.telegram.telegrambots.meta.api.objects.InputFile;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboard;

import java.io.File;

/**
 * Builds replay on command /start command.
 */
@Slf4j
public class StartHandler extends AbstractMessageHandler {

    @Override
    public SendPhoto getSendMessage(Update update) {
        ReplyKeyboard keyBoard = KeyBoardFactory.getKeyBoardFromFactory(update).getKeyBoard(update);
        log.info("StartHandler send message + keyboard");
        Long chatId;
        String name;
        if (Validator.chekCallBackQuery(update)){
            chatId = update.getCallbackQuery().getMessage().getChatId();
            name = update.getCallbackQuery().getMessage().getFrom().getFirstName();
        } else {
            chatId = update.getMessage().getChatId();
            name = update.getMessage().getFrom().getFirstName();
        }
        return SendPhoto.builder()
                .chatId(chatId)
                .photo(new InputFile(
                        new File("src/main/resources/assets/100-banner2_C2.png")))
                .caption("Привет, " + name + "!\n"
                        + "Здесь можно:\n"
                        + "1. Посчитать результат обмена валют для выбранного банка.\n"
                        + "2. Посмотреть аналитику по валютам.\n")
                .replyMarkup(keyBoard)
                .build();
    }
}
