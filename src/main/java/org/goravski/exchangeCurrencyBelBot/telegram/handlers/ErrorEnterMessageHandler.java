package org.goravski.exchangeCurrencyBelBot.telegram.handlers;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.binary.Base64;
import org.goravski.exchangeCurrencyBelBot.util.Images;
import org.goravski.exchangeCurrencyBelBot.util.Validator;
import org.telegram.telegrambots.meta.api.methods.send.SendPhoto;
import org.telegram.telegrambots.meta.api.objects.InputFile;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.io.ByteArrayInputStream;
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
                    .photo( new InputFile(
                            new ByteArrayInputStream(Base64.decodeBase64(Images.ERROR)), "error.png"))
                    .chatId(update.getCallbackQuery().getMessage().getChatId())
                    .caption("Введите корректную сумму")
                    .build();
        }else {
            return SendPhoto.builder()
                    .photo( new InputFile(
                            new ByteArrayInputStream(Base64.decodeBase64(Images.ERROR)), "error.png"))
                    .chatId(update.getMessage().getChatId())
                    .caption("Введите корректную сумму")
                    .build();
        }
    }
}
