package org.goravski.exchangeCurrencyBelBot.telegram.keyboard;


import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboard;


public interface KeyBoardInterface {
    ReplyKeyboard getKeyBoard(Update update);
}
