package org.goravski.exchangeCurrencyBelBot.telegram.handlers;

import lombok.extern.slf4j.Slf4j;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;

@Slf4j
public class ErrorEnterMessageHandler extends AbstractMessageHandler {
    @Override
    public BotApiMethod<?> getSendMessage(Update update) {
        log.info("ErrorEnterMessageHandler send Message");
        return SendMessage.builder()
                .chatId(update.getMessage().getChatId())
                .text("Введите корректную сумму")
                .build();
    }
}
