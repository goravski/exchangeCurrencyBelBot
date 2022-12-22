package org.goravski.exchangeCurrencyBelBot.telegram.keyboard;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.goravski.exchangeCurrencyBelBot.util.Validator;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.EntityType;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.util.Locale;
import java.util.function.Supplier;

@Component
@RequiredArgsConstructor
@Slf4j
public class KeyBoardFactory {
    private enum KeyBoardEnum {
        START(StartKeyBoard::new),
        BANKS(BankKeyBoard::new),
        SET_CURRENCY (SetCurrencyKeyBoard::new);

        KeyBoardEnum(Supplier<KeyBoardInterface> keyBoard) {
            this.keyBoard = keyBoard.get();
        }

        private final KeyBoardInterface keyBoard;

        public KeyBoardInterface getKeyBoard() {
            return keyBoard;
        }
    }

    public static KeyBoardInterface getKeyBoardFromFactory(Update update){
        log.info("KeyBoardFactory started");
        if (Validator.chekCallBackQuery(update)){
            String[] params = update.getCallbackQuery().getData().split(":");
            return KeyBoardEnum.valueOf((params[0]).toUpperCase(Locale.ROOT))
                    .getKeyBoard();
        }
        Message message = update.getMessage();
        if (Validator.chekEntityType(message, EntityType.BOTCOMMAND)) {
            return KeyBoardEnum.valueOf(message.getText().substring(1).toUpperCase(Locale.ROOT))
                    .getKeyBoard();
        } else {
            return KeyBoardEnum.valueOf("START")
                    .getKeyBoard();
        }
    }
}
