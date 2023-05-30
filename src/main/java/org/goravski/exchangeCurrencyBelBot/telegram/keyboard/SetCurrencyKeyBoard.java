package org.goravski.exchangeCurrencyBelBot.telegram.keyboard;

import lombok.extern.slf4j.Slf4j;
import org.goravski.exchangeCurrencyBelBot.entity.CurrencyName;
import org.goravski.exchangeCurrencyBelBot.service.HashMapCurrencyModeService;
import org.goravski.exchangeCurrencyBelBot.util.Emoji;
import org.goravski.exchangeCurrencyBelBot.util.LocalConstant;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboard;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Builds keyboard to offer currency choice
 */
@Slf4j
public class SetCurrencyKeyBoard implements KeyBoardInterface {
    private final HashMapCurrencyModeService currencyModeService = HashMapCurrencyModeService.getInstance();

    @Override
    public ReplyKeyboard getKeyBoard(Update update) {
        log.info("SetCurrencyKeyBoard construct");
        List<List<InlineKeyboardButton>> rowButtons = new ArrayList<>();
        Long chatId = update.getCallbackQuery().getMessage().getChatId();
        CurrencyName originalCurrency = currencyModeService.getOriginalCurrency(chatId);
        CurrencyName targetCurrency = currencyModeService.getTargetCurrency(chatId);
        rowButtons.add(Arrays.asList(
                InlineKeyboardButton.builder()
                        .text("ПРОДАТЬ")
                        .callbackData(LocalConstant.SET_CURRENCY + ":" + "Продажа" + ":" + "")
                        .build(),
                InlineKeyboardButton.builder()
                        .text("КУПИТЬ")
                        .callbackData(LocalConstant.SET_CURRENCY + ":" + "Покупка" + ":" + "")
                        .build()));
        for (CurrencyName currency : CurrencyName.values()) {
            rowButtons.add(Arrays.asList(
                    InlineKeyboardButton.builder()
                            .text(getCurrencyButton(originalCurrency, currency))
                            .callbackData(LocalConstant.SET_CURRENCY + ":" + "Продажа" + ":" + currency)
                            .build(),
                    InlineKeyboardButton.builder()
                            .text(getCurrencyButton(targetCurrency, currency))
                            .callbackData(LocalConstant.SET_CURRENCY + ":" + "Покупка" + ":" + currency)
                            .build()
            ));
        }
        rowButtons.add(List.of(
                InlineKeyboardButton.builder()
                        .text("НАЗАД")
                        .callbackData(LocalConstant.BANKS + ":" + "Back" + ":" + "Back")
                        .build()));
        InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();
        inlineKeyboardMarkup.setKeyboard(rowButtons);
        log.info("Draw keyboard with active  Buy:{} Sale:{}", originalCurrency, targetCurrency);
        return inlineKeyboardMarkup;
    }

    public static String getCurrencyButton(CurrencyName saved, CurrencyName current) {
        return saved == current ? current + "  " + Emoji.CHEK : current.name();
    }

}
