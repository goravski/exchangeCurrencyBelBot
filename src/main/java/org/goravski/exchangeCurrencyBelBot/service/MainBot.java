package org.goravski.exchangeCurrencyBelBot.service;

import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import org.goravski.exchangeCurrencyBelBot.config.BotConfig;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;

import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;



@Component
@AllArgsConstructor
public class MainBot extends TelegramLongPollingBot {

    private final BotConfig config;

    @Override
    public String getBotUsername() {
        return config.getBotName();
    }

    @Override
    public String getBotToken() {
        return config.getBotToken();
    }

    @SneakyThrows
    @Override
    public void onUpdateReceived(Update update) {
        Message message = update.getMessage();
        SendMessage from_java = SendMessage.builder()
                .chatId(message.getChatId()).text("Hi, " + message.getFrom().getFirstName())
                .build();
        this.execute(from_java);
    }
}
