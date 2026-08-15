package ru.thevalidator.telegramtoxicbot.data.model;

public class TelegramEntityType {

    /*
    org.telegram.telegrambots.meta.api.objects.MessageEntity

    Type of the entity. Currently, can be
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

    public static final String MENTION = "mention";

    public static final String HASHTAG = "hashtag";

    public static final String CASHTAG = "cashtag";

    public static final String BOT_COMMAND = "bot_command";

    public static final String URL = "url";

    public static final String EMAIL = "email";

    public static final String PHONE_NUMBER = "phone_number";

    public static final String BOLD = "bold";

    public static final String ITALIC = "italic";

    public static final String UNDERLINE = "underline";

    public static final String STRIKETHROUGH = "strikethrough";

    public static final String SPOILER = "spoiler";

    public static final String BLOCKQUOTE = "blockquote";

    public static final String EXPANDABLE_BLOCKQUOTE = "expandable_blockquote";

    public static final String CODE = "code";

    public static final String PRE = "pre";

    public static final String TEXT_LINK = "text_link";

    public static final String TEXT_MENTION = "text_mention";

    public static final String CUSTOM_EMOJI = "custom_emoji";

    private TelegramEntityType() {
    }

}
