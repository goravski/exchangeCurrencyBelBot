package org.goravski.exchangeCurrencyBelBot.command;

import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;

/**
 * You must implement building SendMessage for exacted BotCommandFactory object
 */
public interface BotCommandInterface {
    SendMessage getSendMessageByCommand(Message message);
}
