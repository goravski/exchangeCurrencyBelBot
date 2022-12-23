package org.goravski.exchangeCurrencyBelBot.telegram.keyboard;

import lombok.extern.slf4j.Slf4j;
import org.goravski.exchangeCurrencyBelBot.entity.BanksType;
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

    @Override
    public InlineKeyboardMarkup getKeyBoard(Update update) {
        log.info("BankKeyBoard construct");
        return getInlineMessageButtons();
    }

    public InlineKeyboardMarkup getInlineMessageButtons() {
        List<List<InlineKeyboardButton>> rowList = new ArrayList<>();
        Stream.of(BanksType.values()).forEach(bank -> rowList.add(getButton(bank)));

        InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();
        inlineKeyboardMarkup.setKeyboard(rowList);
        return inlineKeyboardMarkup;
    }

    private List<InlineKeyboardButton> getButton(BanksType bank) {
        return new ArrayList<>(Collections.singleton(InlineKeyboardButton.builder()
                .text(bank.getNameBank())
                .callbackData(chekButton(bank) + ":" + bank + ":")
                .build()));
    }

    private String chekButton(BanksType bank) {
        if (bank.equals(BanksType.BACK)) {
            return LocalConstant.START;
        } else return LocalConstant.SET_CURRENCY;
    }
}
