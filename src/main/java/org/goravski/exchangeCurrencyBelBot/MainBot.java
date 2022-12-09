package org.goravski.exchangeCurrencyBelBot;

import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.goravski.exchangeCurrencyBelBot.command.BotCommandFactory;
import org.goravski.exchangeCurrencyBelBot.config.BotConfig;
import org.goravski.exchangeCurrencyBelBot.entity.CurrencyName;
import org.goravski.exchangeCurrencyBelBot.service.CurrencyConversionService;
import org.goravski.exchangeCurrencyBelBot.service.CurrencyModeService;
import org.goravski.exchangeCurrencyBelBot.util.Utils;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;

import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageReplyMarkup;
import org.telegram.telegrambots.meta.api.objects.*;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

import java.util.List;
import java.util.Optional;


@Component
@AllArgsConstructor
@Slf4j
public class MainBot extends TelegramLongPollingBot {

    private final BotConfig config;

    private final CurrencyModeService currencyModeService = CurrencyModeService.getInstance();

    private final CurrencyConversionService conversionService;


    @Override
    public String getBotUsername() {
        return config.getBotName();
    }

    @Override
    public String getBotToken() {
        return config.getBotToken();
    }

    @SneakyThrows
    @Override
    public void onUpdateReceived(Update update) {
        if (update.hasCallbackQuery()) {
            execute(handleCallBack(update.getCallbackQuery()));
        } else {
            execute(handleMessage(update));
        }

    }


    public SendMessage handleMessage(Update update) {
        Message message = update.getMessage();
        if (message.hasText() && message.hasEntities()) {
            Optional<MessageEntity> commandEntity =
                    message.getEntities().stream().filter(e -> e.getType().equals(EntityType.BOTCOMMAND)).findFirst();
            if (commandEntity.isPresent()) {
                return BotCommandFactory.getCommandFromFactory(message, commandEntity.get())
                        .getSendMessageByCommand(message);
            }
        }
        if (message.hasText()) {
            Optional<Double> value = parserDouble(message.getText());
            CurrencyName originalCurrency = currencyModeService.getOriginalCurrency(message.getChatId());
            log.info("get original currency {} from", message.getFrom().getFirstName());
            CurrencyName targetCurrency = currencyModeService.getTargetCurrency(message.getChatId());
            log.info("get target currency {} from", message.getFrom().getFirstName());
            double conversionRatio = conversionService.getConversionRatio(originalCurrency, targetCurrency);
            if (value.isPresent()) {
                return SendMessage.builder()
                        .chatId(message.getChatId())
                        .text(String.format(
                                "%4.2f %s = %4.2f %s"
                                , value.get(), originalCurrency
                                , value.get() * conversionRatio, targetCurrency))
                        .build();
            } else return SendMessage.builder().chatId(message.getChatId()).text("Введите сумму").build();
        }
        return SendMessage.builder().chatId(message.getChatId()).text("Команда не распознана").build();
    }

    private Optional<Double> parserDouble(String text) {
        try {
            return Optional.of(Double.parseDouble(text));
        } catch (Exception e) {
            return Optional.empty();
        }
    }

    @SneakyThrows
    private EditMessageReplyMarkup handleCallBack(CallbackQuery callbackQuery) {
        log.info("handleCallBac () user = {}", callbackQuery.getFrom().getFirstName());
        Message message = callbackQuery.getMessage();
        String[] params = callbackQuery.getData().split(":");
        String action = params[0];
        CurrencyName newCurrencyName = CurrencyName.valueOf(params[1]);
        switch (action) {
            case "Продажа" -> {
                currencyModeService.setOriginalCurrency(message.getChatId(), newCurrencyName);
                log.info("set original currency {} {}", message.getFrom().getFirstName(), newCurrencyName);
            }
            case "Покупка" -> {
                currencyModeService.setTargetCurrency(message.getChatId(), newCurrencyName);
                log.info("set target currency {} {}", message.getFrom().getFirstName(), newCurrencyName);
            }
        }
        List<List<InlineKeyboardButton>> buttons = Utils.buttonsConstruct(currencyModeService, message);
        return EditMessageReplyMarkup.builder()
                .chatId(message.getChatId())
                .messageId(message.getMessageId())
                .replyMarkup(InlineKeyboardMarkup.builder().keyboard(buttons).build())
                .build();
    }
}
