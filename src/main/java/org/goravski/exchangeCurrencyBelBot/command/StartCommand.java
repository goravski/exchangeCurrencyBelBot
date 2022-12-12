package org.goravski.exchangeCurrencyBelBot.command;

import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;

/**
 * Builds replay on command /start command.
 */
public class StartCommand implements BotCommandInterface {
    @Override
    public SendMessage getSendMessageByCommand(Message message) {
        return SendMessage.builder()
                .chatId(message.getChatId())
                .text("Привет, " + message.getFrom().getFirstName() + "!\n"
                        + "Здесь можно посчитать результат обмена валют по картам БПС-Сбербанк.\n"
                        + "1. Выбери команду в Menu слева.\n"
                        + "2. Выбери валюты обмена.\n"
                        + "3. Введи сумму в поле ввода.\n"
                        + "4. Нажмите синий треугольник справа.\n"
                        + "Сумму можешь вводить многократно, выбор валют сохраняется.")
                .build();
    }
}
