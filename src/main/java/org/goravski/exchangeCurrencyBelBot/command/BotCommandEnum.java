package org.goravski.exchangeCurrencyBelBot.command;

import java.util.function.Supplier;

public enum BotCommandEnum {
    SET_CURRENCY(SetCurrencyCommand::new);

    private final BotCommandInterface command;

    BotCommandEnum(Supplier<BotCommandInterface> command) {
        this.command = command.get();
    }

   public BotCommandInterface createCommand (){
        return command;
    }
}
