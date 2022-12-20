package org.goravski.exchangeCurrencyBelBot.telegram.handlers;

import org.goravski.exchangeCurrencyBelBot.telegram.keyboard.KeyBoardFactory;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboard;

public class BankMessageHandler extends AbstractMessageHandler {

    @Override
    public SendMessage getSendMessage(Update update) {
        ReplyKeyboard keyBoard = KeyBoardFactory.getKeyBoardFromFactory(update).getKeyBoard(update);
        return SendMessage.builder()
                .chatId(update.getCallbackQuery().getMessage().getChatId())
                .text("Выбери банк")
                .replyMarkup(keyBoard)
                .build();
    }
}
