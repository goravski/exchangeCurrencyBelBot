package org.goravski.exchangeCurrencyBelBot.command;

import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;

public class SetCurrencyCommand implements BotCommandInterface{
    @Override
    public SendMessage getSendMessageByCommand(Message message) {
                return SendMessage.builder()
                        .text("Выбери валюты для обмена")
                        .chatId(message.getChatId().toString())
                        .build();
    }
}
