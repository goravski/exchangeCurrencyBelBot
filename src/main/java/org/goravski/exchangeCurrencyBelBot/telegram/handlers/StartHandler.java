package org.goravski.exchangeCurrencyBelBot.telegram.handlers;


import lombok.extern.slf4j.Slf4j;
import org.goravski.exchangeCurrencyBelBot.telegram.keyboard.KeyBoardFactory;
import org.goravski.exchangeCurrencyBelBot.util.Validator;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboard;

/**
 * Builds replay on command /start command.
 */
@Slf4j
public class StartHandler extends AbstractMessageHandler {

    @Override
    public SendMessage getSendMessage(Update update) {
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
        return SendMessage.builder()
                .chatId(chatId)
                .text("Привет, " + name + "!\n"
                        + "Чтобы узнать результат обмена валют:\n"
                        + "1. Выбери банк.\n"
                        + "2. Выбери валюты обмена.\n"
                        + "3. Введи сумму в поле ввода.\n"
                        + "4. Нажмите синий треугольник справа.\n"
                        + "Сумму можешь вводить многократно, выбор валют сохраняется.")
                .replyMarkup(keyBoard)
                .build();
    }
}
