package org.goravski.exchangeCurrencyBelBot.telegram.handlers;

import org.telegram.telegrambots.meta.api.methods.send.SendPhoto;
import org.telegram.telegrambots.meta.api.objects.Update;
/**
 * Implement method handler according received content
 */
public abstract class AbstractMessageHandler {

    public abstract SendPhoto getSendMessage(Update update);
}
