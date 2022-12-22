package org.goravski.exchangeCurrencyBelBot.telegram.keyboard;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.goravski.exchangeCurrencyBelBot.util.LocalConstant;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Stream;

@Slf4j
public class BankKeyBoard implements KeyBoardInterface {
    @AllArgsConstructor
    @Getter
    enum Bank {
        SBERBANK("СБЕРБАНК"),
        BELARUSBANK("БЕЛАРУСБАНК"),
        BACK("НАЗАД");

        private String nameBank;
    }



    @Override
    public InlineKeyboardMarkup getKeyBoard(Update update) {
        log.info("BankKeyBoard construct");
        return getInlineMessageButtons();
    }

    public InlineKeyboardMarkup getInlineMessageButtons() {
        List<List<InlineKeyboardButton>> rowList = new ArrayList<>();
        Stream.of(Bank.values()).forEach(bank -> rowList.add(getButton(bank)));

        InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();
        inlineKeyboardMarkup.setKeyboard(rowList);
        return inlineKeyboardMarkup;
    }

    private List<InlineKeyboardButton> getButton(Bank bank) {
        return new ArrayList<>(Collections.singleton(InlineKeyboardButton.builder()
                .text(bank.getNameBank())
                .callbackData( LocalConstant.SET_CURRENCY + ":" + bank + ":")
                .build()));
    }
}
