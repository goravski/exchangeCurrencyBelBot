package org.goravski.exchangeCurrencyBelBot.telegram.handlers;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.math.NumberUtils;
import org.goravski.exchangeCurrencyBelBot.entity.CurrencyName;
import org.goravski.exchangeCurrencyBelBot.service.ConversionFactory;
import org.goravski.exchangeCurrencyBelBot.service.HashMapBankModeService;
import org.goravski.exchangeCurrencyBelBot.service.HashMapCurrencyModeService;
import org.goravski.exchangeCurrencyBelBot.util.Emoji;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;

@Slf4j
@Service
@RequiredArgsConstructor
public class ExchangeCurrencyMessageHandler extends AbstractMessageHandler {
    private final HashMapCurrencyModeService currencyModeService = HashMapCurrencyModeService.getInstance();
    private final HashMapBankModeService mapBankModeService = HashMapBankModeService.getInstance();


    @Override
    public BotApiMethod<?> getSendMessage(Update update) {
        Message message = update.getMessage();
        if (NumberUtils.isDigits(message.getText())) {
            Double value = NumberUtils.createDouble(message.getText());
            CurrencyName originalCurrency = currencyModeService.getOriginalCurrency(message.getChatId());
            CurrencyName targetCurrency = currencyModeService.getTargetCurrency(message.getChatId());
            double conversionRatio = ConversionFactory.getConversionService(
                    mapBankModeService.getBankName(message.getChatId())
            ).getConversionRatio(originalCurrency, targetCurrency);
            log.info("ExchangeCurrencyMessageHandler Count rate index = {}, send message result", conversionRatio);
            return SendMessage.builder()
                    .chatId(message.getChatId())
                    .text(String.format(
                            "%4.2f %s = %4.2f %s  " + Emoji.COUNT
                            , value, originalCurrency
                            , value * conversionRatio, targetCurrency))
                    .build();
        } else {
            log.info("ExchangeCurrencyMessageHandler Message text is not digit; {}", message.getText());
            return SendMessage.builder()
                    .chatId(message.getChatId())
                    .text("Введённый текст не является числом\n" +
                            "Введите число")
                    .build();
        }
    }

}
