package org.goravski.exchangeCurrencyBelBot;

import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.goravski.exchangeCurrencyBelBot.config.BotConfig;
import org.goravski.exchangeCurrencyBelBot.telegram.handlers.factory.MessageHandlerFactory;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.objects.*;


@Component
@AllArgsConstructor
@Slf4j
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
        execute(MessageHandlerFactory.getUpdateMethodFromFactory(update));

    }

}
