package org.goravski.exchangeCurrencyBelBot.service;

import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import org.goravski.exchangeCurrencyBelBot.command.BotCommandFactory;
import org.goravski.exchangeCurrencyBelBot.config.BotConfig;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;

import org.telegram.telegrambots.meta.api.objects.EntityType;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.MessageEntity;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.util.Optional;


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
        execute(handleMessage(update.getMessage()));
    }

    public SendMessage handleMessage(Message message) {
        if (message.hasText() && message.hasEntities()) {
            Optional<MessageEntity> commandEntity =
                    message.getEntities().stream().filter(e -> e.getType().equals(EntityType.BOTCOMMAND)).findFirst();
            if (commandEntity.isPresent()) {
                return BotCommandFactory.getCommandFromFactory(message, commandEntity.get()).getSendMessageByCommand(message);
            }
        }
        return SendMessage.builder().chatId(message.getChatId()).text("Команда не распознана").build();

    }

}
