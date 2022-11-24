package org.goravski.exchangeCurrencyBelBot.util;

import com.vdurmont.emoji.EmojiParser;
import org.goravski.exchangeCurrencyBelBot.entity.CurrencyName;
import org.goravski.exchangeCurrencyBelBot.service.CurrencyModeService;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Utils {
    public static List<List<InlineKeyboardButton>> buttonsConstruct(CurrencyModeService currencyModeService, Message message) {
        List<List<InlineKeyboardButton>> buttons = new ArrayList<>();
        CurrencyName originalCurrency = currencyModeService.getOriginalCurrency(message.getChatId());
        CurrencyName targetCurrency = currencyModeService.getTargetCurrency(message.getChatId());
        for (CurrencyName currency : CurrencyName.values()) {
            buttons.add(Arrays.asList(
                    InlineKeyboardButton.builder()
                            .text(getCurrencyButton(originalCurrency, currency))
                            .callbackData("Продажа:" + currency)
                            .build(),
                    InlineKeyboardButton.builder()
                            .text(getCurrencyButton(targetCurrency, currency))
                            .callbackData("Покупка:" + currency)
                            .build()

            ));
        }
        return buttons;
    }

    public static String getCurrencyButton(CurrencyName saved, CurrencyName current) {
        return saved == current ? current + EmojiParser.parseToUnicode(":heart:") : current.name();
    }
}
