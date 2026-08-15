package ru.thevalidator.telegramtoxicbot.message.util;

public enum TelegramMessageEntityType {

    /*
    Type of the entity.
    Currently, can be
    - “mention” (@username),
    - “hashtag” (#hashtag or #hashtag@chatusername),
    - “cashtag” ($USD or $USD@chatusername),
    - “bot_command” (/start@jobs_bot),
    - “url” (https://telegram.org),
    - “email” (do-not-reply@telegram.org),
    - “phone_number” (+1-212-555-0123),
    - “bold” (bold text),
    - “italic” (italic text),
    - “underline” (underlined text),
    - “strikethrough” (strikethrough text),
    - “spoiler” (spoiler message),
    - “blockquote” (block quotation),
    - “expandable_blockquote” (collapsed-by-default block quotation),
    - “code” (monowidth string),
    - “pre” (monowidth block),
    - “text_link” (for clickable text URLs),
    - “text_mention” (for users without usernames),
    - “custom_emoji” (for inline custom emoji stickers)
    */

    CODE_BLOCK("pre");

    private final String textValue;

    TelegramMessageEntityType(String textValue) {
        this.textValue = textValue;
    }

    public String textValue() {
        return textValue;
    }
}
