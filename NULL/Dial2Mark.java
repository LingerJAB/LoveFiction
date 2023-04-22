import Chat.AbstractChat;
import Chat.ChatChain;

import java.io.File;

/**
 * 将QQ聊天记录转换为Markdown形式
 * @author Lin
 */
public class Dial2Mark extends AbstractChat {
    public static final File input = new File("NULL/InputChat.txt");
    public static final File output = new File("NULL/OutputChat.txt");
    public static void main(String[] args){
        ChatChain.run(input,output);
    }
}