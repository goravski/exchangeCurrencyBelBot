package org.goravski.exchangeCurrencyBelBot.command;

import org.goravski.exchangeCurrencyBelBot.service.CurrencyModeService;
import org.goravski.exchangeCurrencyBelBot.util.Utils;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

import java.util.List;

/**
 * Builds replay on command /start command.
 */

public class SetCurrencyCommand implements BotCommandInterface {
    private final CurrencyModeService currencyModeService = CurrencyModeService.getInstance();

    @Override
    public SendMessage getSendMessageByCommand(Message message) {
        List<List<InlineKeyboardButton>> buttons = Utils.buttonsConstruct(currencyModeService, message);
        return SendMessage.builder()
                .text("""
                        Выбери валюты для обмена\s
                         ПРОДАТЬ    |    КУПИТЬ\s
                        затем введи сумму в поле ввода""")
                .chatId(message.getChatId().toString())
                .replyMarkup(InlineKeyboardMarkup.builder().keyboard(buttons).build())
                .build();
    }

}
