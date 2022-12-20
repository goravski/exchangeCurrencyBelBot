package org.goravski.exchangeCurrencyBelBot.telegram.handlers;

import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.math.NumberUtils;
import org.goravski.exchangeCurrencyBelBot.entity.CurrencyName;
import org.goravski.exchangeCurrencyBelBot.service.CurrencyConversionService;
import org.goravski.exchangeCurrencyBelBot.service.HashMapCurrencyModeService;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;

@Slf4j
public class ExchangeCurrencyMessageHandler extends AbstractMessageHandler {
    private final CurrencyConversionService conversionService ;
    private final HashMapCurrencyModeService currencyModeService = HashMapCurrencyModeService.getInstance();


    @Override
    public BotApiMethod<?> getSendMessage(Update update) {
        Message message = update.getMessage();
        if (NumberUtils.isDigits(message.getText())) {
            Double value = NumberUtils.createDouble(message.getText());
            CurrencyName originalCurrency = currencyModeService.getOriginalCurrency(message.getChatId());
            log.debug("get original currency {} from", message.getFrom().getFirstName());
            CurrencyName targetCurrency = currencyModeService.getTargetCurrency(message.getChatId());
            log.debug("get target currency {} from", message.getFrom().getFirstName());
            double conversionRatio = conversionService.getConversionRatio(originalCurrency, targetCurrency);
            return SendMessage.builder()
                    .chatId(message.getChatId())
                    .text(String.format(
                            "%4.2f %s = %4.2f %s"
                            , value, originalCurrency
                            , value * conversionRatio, targetCurrency))
                    .build();
        } else {
            log.debug("Message text is not digit; {}", message.getText());
            return SendMessage.builder()
                    .chatId(message.getChatId())
                    .text("Введённый текст не является числом\n" +
                            "Введите число")
                    .build();
        }
    }
}
