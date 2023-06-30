package org.goravski.exchangeCurrencyBelBot.telegram.handlers.factory;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.math.NumberUtils;
import org.goravski.exchangeCurrencyBelBot.telegram.handlers.*;
import org.goravski.exchangeCurrencyBelBot.util.Validator;
import org.telegram.telegrambots.meta.api.methods.send.SendPhoto;
import org.telegram.telegrambots.meta.api.objects.EntityType;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.io.IOException;
import java.util.Arrays;
import java.util.Locale;
import java.util.function.Supplier;
/**
 * Handler factory
 */
@Slf4j
public class MessageHandlerFactory {
    private enum MessageEnum {
        BANKS(BankMessageHandler::new),
        START(StartHandler::new),
        ANALYTICS(AnaliticsMessageHandler::new),
        SET_CURRENCY(SetCurrencyMessageHandler::new),
        EXCHANGE_CURRENCY(ExchangeCurrencyMessageHandler::new),
        ERROR_MESSAGE(ErrorEnterMessageHandler::new);

        MessageEnum(Supplier<AbstractMessageHandler> messageHandler) {
            this.messageHandler = messageHandler.get();
        }

        private final AbstractMessageHandler messageHandler;

        public AbstractMessageHandler createMessageSend() {
            return messageHandler;
        }
    }
    /**
     * Get type handler according received content
     */
    public static SendPhoto getUpdateMethodFromFactory(Update update) throws IOException {
        log.info("MessageHandlerFactory started");
        if (Validator.chekCallBackQuery(update)){
            String[] params = update.getCallbackQuery().getData().split(":");
            log.info("CallBackQueryHandler params= {}", Arrays.toString(params));
            return MessageEnum.valueOf((params[0]).toUpperCase(Locale.ROOT))
                    .createMessageSend().getSendMessage(update);
        }
        Message message = update.getMessage();
        if (Validator.chekEntityType(message, EntityType.BOTCOMMAND)) {
            log.info("MessageHandler for bot command = {}", message.getText());
            return MessageEnum.valueOf(message.getText().substring(1).toUpperCase(Locale.ROOT))
                    .createMessageSend().getSendMessage(update);
        } else if ( NumberUtils.isDigits(message.getText())){
            log.info("MessageHandler for digit = {}", message.getText());
            return MessageEnum.EXCHANGE_CURRENCY
                    .createMessageSend().getSendMessage(update);
        }else {
            log.info("MessageHandler for text = {}", message.getText());
            return MessageEnum.ERROR_MESSAGE
                    .createMessageSend().getSendMessage(update);
        }
    }


}
