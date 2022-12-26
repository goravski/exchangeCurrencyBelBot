package org.goravski.exchangeCurrencyBelBot.util;

import com.vdurmont.emoji.EmojiParser;

public enum Emoji {
    PRICE(EmojiParser.parseToUnicode(":dollar:")),
    CHART(EmojiParser.parseToUnicode("\uD83D\uDCCA")),
    COUNT(EmojiParser.parseToUnicode("\uD83E\uDDEE")),
    TURN(EmojiParser.parseToUnicode("↩")),
    CHEK(EmojiParser.parseToUnicode("✅")),
    BANK(EmojiParser.parseToUnicode("\uD83C\uDFE6"));

    private final String emojiName;

    Emoji(String emojiName) {
        this.emojiName = emojiName;
    }

    @Override
    public String toString() {
        return emojiName;
    }
}
