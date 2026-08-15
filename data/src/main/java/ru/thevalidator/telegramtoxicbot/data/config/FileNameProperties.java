package ru.thevalidator.telegramtoxicbot.data.config;

import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Setter
@ConfigurationProperties(prefix = "reply-phrases")
public class FileNameProperties implements FileNameProvider {

    private String noReply;

    private String shortNoReply;

    private String aahReply;

    private String botMentionReply;

    private String abusiveNames;

    private String whereReply;

    private String yesReply;

    private String threeHundredReply;

    private String replyToBotReply;

    @Override
    public String noReplyPhrasesFile() {
        return noReply;
    }

    @Override
    public String shortNoReplyPhrasesFile() {
        return shortNoReply;
    }

    @Override
    public String aahReplyPhrasesFile() {
        return aahReply;
    }

    @Override
    public String whereReplyPhrasesFile() {
        return whereReply;
    }

    @Override
    public String yesReplyPhrasesFile() {
        return yesReply;
    }

    @Override
    public String threeHundredReplyPhrasesFile() {
        return threeHundredReply;
    }

    @Override
    public String replyToBotReplyPhrasesFile() {
        return replyToBotReply;
    }

    @Override
    public String abusiveNamePhrasesFile() {
        return abusiveNames;
    }

    @Override
    public String botMentionReplyPhrasesFile() {
        return botMentionReply;
    }

}
