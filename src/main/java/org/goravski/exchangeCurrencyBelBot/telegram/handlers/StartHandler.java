package org.goravski.exchangeCurrencyBelBot.telegram.handlers;


import org.goravski.exchangeCurrencyBelBot.telegram.keyboard.KeyBoardFactory;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboard;

/**
 * Builds replay on command /start command.
 */
public class StartHandler extends AbstractMessageHandler {

    @Override
    public SendMessage getSendMessage(Update update) {
        ReplyKeyboard keyBoard = KeyBoardFactory.getKeyBoardFromFactory(update).getKeyBoard(update);
        return SendMessage.builder()
                .chatId(update.getMessage().getChatId())
                .text("Привет, " + update.getMessage().getFrom().getFirstName() + "!\n"
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
