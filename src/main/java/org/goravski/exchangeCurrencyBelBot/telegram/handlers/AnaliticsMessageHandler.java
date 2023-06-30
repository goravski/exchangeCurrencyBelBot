package org.goravski.exchangeCurrencyBelBot.telegram.handlers;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.binary.Base64;
import org.goravski.exchangeCurrencyBelBot.util.Images;
import org.telegram.telegrambots.meta.api.methods.send.SendPhoto;
import org.telegram.telegrambots.meta.api.objects.InputFile;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.io.ByteArrayInputStream;

/**
 * Builds analytic message
 */
@Slf4j
public class AnaliticsMessageHandler extends AbstractMessageHandler{
    @Override
    public SendPhoto getSendMessage(Update update) {
        log.info("AnaliticMessageHandler send Message");
        return SendPhoto.builder()
                .photo( new InputFile(
                        new ByteArrayInputStream(Base64.decodeBase64(Images.ANALITIC)), "analitic.png"))
                .chatId(update.getCallbackQuery().getMessage().getChatId())
                .caption("Здесь пока ничего нет")
                .build();
    }
}
