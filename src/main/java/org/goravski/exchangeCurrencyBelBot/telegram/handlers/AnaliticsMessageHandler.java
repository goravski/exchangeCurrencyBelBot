package org.goravski.exchangeCurrencyBelBot.telegram.handlers;

import lombok.extern.slf4j.Slf4j;
import org.telegram.telegrambots.meta.api.methods.send.SendPhoto;
import org.telegram.telegrambots.meta.api.objects.InputFile;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.io.File;
/**
 * Builds analytic message
 */
@Slf4j
public class AnaliticsMessageHandler extends AbstractMessageHandler{
    @Override
    public SendPhoto getSendMessage(Update update) {
        log.info("AnaliticMessageHandler send Message");
        return SendPhoto.builder()
                .photo(new InputFile(
                        new File("src/main/resources/assets/analitics.jpg")))
                .chatId(update.getCallbackQuery().getMessage().getChatId())
                .caption("Здесь пока ничего нет")
                .build();
    }
}
