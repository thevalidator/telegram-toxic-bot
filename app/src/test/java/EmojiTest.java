import com.vdurmont.emoji.EmojiParser;
import org.junit.jupiter.api.Test;

class EmojiTest {

    @Test
    void test() {
        String middleFinger = EmojiParser.parseToUnicode("\uD83E\uDD2E");
        String middleFinger1 = EmojiParser.parseToUnicode("\uD83D\uDCA9");
        String middleFinger2 = EmojiParser.parseToAliases(":middle_finger:");
        String middleFinger3 = EmojiParser.parseToUnicode("\uD83D\uDD95");

        System.out.println(middleFinger);
        System.out.println(middleFinger1);
        System.out.println(middleFinger2);
        System.out.println(middleFinger3);
    }

}
