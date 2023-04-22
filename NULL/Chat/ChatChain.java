package Chat;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;

public class ChatChain extends AbstractChat {
    ArrayList<SingleChat> list = new ArrayList<>();

    public static void run(File input, File output) {
        long curTime = System.currentTimeMillis();
        String inputStr;
        try {
            inputStr = Files.readString(input.toPath(), StandardCharsets.UTF_8);
            ChatChain chain = new ChatChain(inputStr);
            String outputStr = chain.toMark();
            Files.writeString(output.toPath(), outputStr, StandardCharsets.UTF_8, StandardOpenOption.WRITE);
            System.out.println(outputStr);
            System.out.printf("\n------------------\n#total %d chats created for %dms\n",
                    System.currentTimeMillis() - curTime,
                    chain.list.size());
            System.out.println(output.toURI());
        } catch(IOException e) {
            throw new RuntimeException(e);
        }

    }

    public ChatChain(String chatChain) {
        String[] singleChats = chatChain.trim().replaceAll("\r\n", "\n").split("\n\n");
        for(int i = 0, singleChatsLength = singleChats.length; i<singleChatsLength; i++) {
            String singleChat = singleChats[i];
            list.add(new SingleChat(singleChat, i));
        }
    }

    @Override
    public String toString() {
        StringBuilder chain = new StringBuilder();
        for(SingleChat singleChat : list) {
            chain.append(singleChat);
        }
        return chain.toString();
    }

    public String toMark() {
        StringBuilder chain = new StringBuilder();
        SingleChat lastChat;
        for(int index = 0; index<list.size(); index++) {
            try {
                lastChat = list.get(index - 1);
            } catch(IndexOutOfBoundsException e) {
                lastChat = null;
            }
            SingleChat currChat = list.get(index);
            chain.append(currChat.toMark(lastChat));
        }
        return chain.toString();
    }
}