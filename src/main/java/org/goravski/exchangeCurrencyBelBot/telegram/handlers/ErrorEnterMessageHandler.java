package org.goravski.exchangeCurrencyBelBot.telegram.handlers;

import lombok.extern.slf4j.Slf4j;
import org.goravski.exchangeCurrencyBelBot.util.Validator;
import org.telegram.telegrambots.meta.api.methods.send.SendPhoto;
import org.telegram.telegrambots.meta.api.objects.InputFile;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.io.File;
/**
 * Shape error message
 */
@Slf4j
public class ErrorEnterMessageHandler extends AbstractMessageHandler {
    @Override
    public SendPhoto getSendMessage(Update update) {
        log.info("ErrorEnterMessageHandler send Message");
        if (Validator.chekCallBackQuery(update)){
            return SendPhoto.builder()
                    .photo(new InputFile(
                            new File("assets\\error.jpg")))
                    .chatId(update.getCallbackQuery().getMessage().getChatId())
                    .caption("Введите корректную сумму")
                    .build();
        }else {
            return SendPhoto.builder()
                    .photo(new InputFile(
                            new File("src/main/resources/assets/error.jpg")))
                    .chatId(update.getMessage().getChatId())
                    .caption("Введите корректную сумму")
                    .build();
        }
    }
}
