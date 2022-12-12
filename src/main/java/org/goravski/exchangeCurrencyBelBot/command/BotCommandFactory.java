package org.goravski.exchangeCurrencyBelBot.command;

import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.MessageEntity;

import java.util.Locale;

/**
 * Returns defined object from factory.
 */
public class BotCommandFactory {
    public static BotCommandInterface getCommandFromFactory(Message botMessage, MessageEntity messageEntity) {
        return BotCommandEnum.valueOf(
                botMessage
                        .getText()
                        .substring(messageEntity.getOffset()+1, messageEntity.getLength()).toUpperCase(Locale.ROOT))
                .createCommand();
    }
}
