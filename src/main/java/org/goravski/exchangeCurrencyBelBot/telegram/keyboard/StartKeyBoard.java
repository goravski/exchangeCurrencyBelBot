package org.goravski.exchangeCurrencyBelBot.telegram.keyboard;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.goravski.exchangeCurrencyBelBot.util.Emoji;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Stream;

@Slf4j
public class StartKeyBoard implements KeyBoardInterface {
    @Getter
    @AllArgsConstructor
    enum Menu {
        BANKS("БАНКИ  " + Emoji.BANK),
        ANALYTICS("АНАЛИТИКА  " + Emoji.CHART);

        private String nameStart;
    }

    @Override
    public InlineKeyboardMarkup getKeyBoard(Update update) {
        log.info("StartKeyBoard construct");
        return getInlineMessageButtons();
    }

    public InlineKeyboardMarkup getInlineMessageButtons() {
        List<List<InlineKeyboardButton>> rowList = new ArrayList<>();
        Stream.of(Menu.values()).forEach(o -> rowList.add(getButton(o)));

        InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();
        inlineKeyboardMarkup.setKeyboard(rowList);
        return inlineKeyboardMarkup;
    }

    private List<InlineKeyboardButton> getButton(Menu menu) {
        return new ArrayList<>(Collections.singleton(InlineKeyboardButton.builder()
                .text(menu.getNameStart())
                .callbackData(menu + ":" + ":")
                .build()));
    }

}
