package org.goravski.exchangeCurrencyBelBot.telegram.handlers;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.math.NumberUtils;
import org.goravski.exchangeCurrencyBelBot.entity.CurrencyName;
import org.goravski.exchangeCurrencyBelBot.service.ConversionFactory;
import org.goravski.exchangeCurrencyBelBot.service.HashMapBankModeService;
import org.goravski.exchangeCurrencyBelBot.service.HashMapCurrencyModeService;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.send.SendPhoto;
import org.telegram.telegrambots.meta.api.objects.InputFile;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.io.File;

@Slf4j
@Service
@RequiredArgsConstructor
public class ExchangeCurrencyMessageHandler extends AbstractMessageHandler {
    private final HashMapCurrencyModeService currencyModeService = HashMapCurrencyModeService.getInstance();
    private final HashMapBankModeService mapBankModeService = HashMapBankModeService.getInstance();


    @Override
    public SendPhoto getSendMessage(Update update) {
        Message message = update.getMessage();
        if (NumberUtils.isDigits(message.getText())) {
            Double value = NumberUtils.createDouble(message.getText());
            CurrencyName originalCurrency = currencyModeService.getOriginalCurrency(message.getChatId());
            CurrencyName targetCurrency = currencyModeService.getTargetCurrency(message.getChatId());
            double conversionRatio = ConversionFactory.getConversionService(
                    mapBankModeService.getBankEntity(message.getChatId())
            ).getConversionRatio(originalCurrency, targetCurrency);
            log.info("ExchangeCurrencyMessageHandler Count rate index = {}, send message result", conversionRatio);
            return SendPhoto.builder()
                    .chatId(message.getChatId())
                    .photo(new InputFile(
                            new File("D:\\IDEA_Projects\\exchangeCurrencyBelBot\\assests\\done.jpg")))
                    .caption(String.format(
                            "%4.2f %s = %4.2f %s  "
                            , value, originalCurrency
                            , value * conversionRatio, targetCurrency))
                    .build();
        } else {
            log.info("ExchangeCurrencyMessageHandler Message text is not digit; {}", message.getText());
            return SendPhoto.builder()
                    .chatId(message.getChatId())
                    .photo(new InputFile(
                            new File("D:\\IDEA_Projects\\exchangeCurrencyBelBot\\assests\\error.jpg")))
                    .caption("Введённый текст не является числом\n" +
                            "Введите число")
                    .build();
        }
    }

}
