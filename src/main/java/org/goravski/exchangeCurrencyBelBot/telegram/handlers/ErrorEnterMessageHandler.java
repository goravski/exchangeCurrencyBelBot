package org.goravski.exchangeCurrencyBelBot.telegram.handlers;

import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;

public class ErrorEnterMessageHandler extends AbstractMessageHandler {
    @Override
    public BotApiMethod<?> getSendMessage(Update update) {

        return SendMessage.builder()
                .chatId(update.getMessage().getChatId())
                .text("Введите корректную сумму")
                .build();
    }
}
