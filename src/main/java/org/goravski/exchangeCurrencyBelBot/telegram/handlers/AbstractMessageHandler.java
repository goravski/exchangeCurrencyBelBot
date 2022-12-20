package org.goravski.exchangeCurrencyBelBot.telegram.handlers;

import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.objects.Update;

public abstract class AbstractMessageHandler {

    public abstract BotApiMethod<?> getSendMessage(Update update);
}
