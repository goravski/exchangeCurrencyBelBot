package org.goravski.exchangeCurrencyBelBot.telegram.keyboard;


import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboard;

/**
 * Implement keyboard building method according received content
 */
public interface KeyBoardInterface {
    ReplyKeyboard getKeyBoard(Update update);
}
