package org.goravski.exchangeCurrencyBelBot.service;

import com.vdurmont.emoji.EmojiParser;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.goravski.exchangeCurrencyBelBot.command.BotCommandFactory;
import org.goravski.exchangeCurrencyBelBot.config.BotConfig;
import org.goravski.exchangeCurrencyBelBot.entity.CurrencyName;
import org.goravski.exchangeCurrencyBelBot.util.Utils;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;

import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageReplyMarkup;
import org.telegram.telegrambots.meta.api.objects.*;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;


@Component
@AllArgsConstructor
@Slf4j
public class MainBot extends TelegramLongPollingBot {

    private final BotConfig config;

    private final CurrencyModeService currencyModeService = CurrencyModeService.getInstance();


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
        if (update.hasCallbackQuery()) {
            execute(handleCallBack(update.getCallbackQuery()));
        } else {
            execute(handleMessage(update));
        }

    }


    public SendMessage handleMessage(Update update) {
        Message message = update.getMessage();
        if (message.hasText() && message.hasEntities()) {
            Optional<MessageEntity> commandEntity =
                    message.getEntities().stream().filter(e -> e.getType().equals(EntityType.BOTCOMMAND)).findFirst();
            if (commandEntity.isPresent()) {
                return BotCommandFactory.getCommandFromFactory(message, commandEntity.get())
                        .getSendMessageByCommand(message);
            }
        }
        return SendMessage.builder().chatId(message.getChatId()).text("Команда не распознана").build();
    }

    @SneakyThrows
    private EditMessageReplyMarkup handleCallBack(CallbackQuery callbackQuery) {
        log.info("handleCallBac () currencyModeService = {}", currencyModeService);
        Message message = callbackQuery.getMessage();
        String[] params = callbackQuery.getData().split(":");
        String action = params[0];
        CurrencyName newCurrencyName = CurrencyName.valueOf(params[1]);
        switch (action) {
            case "Продажа" -> currencyModeService.setOriginalCurrency(message.getChatId(), newCurrencyName);
            case "Покупка" -> currencyModeService.setTargetCurrency(message.getChatId(), newCurrencyName);
        }
        List<List<InlineKeyboardButton>> buttons = Utils.buttonsConstruct(currencyModeService, message);
        return EditMessageReplyMarkup.builder()
                .chatId(message.getChatId())
                .messageId(message.getMessageId())
                .replyMarkup(InlineKeyboardMarkup.builder().keyboard(buttons).build())
                .build();
    }
}
