package Chat;

import java.util.regex.Matcher;

public class SingleChat extends AbstractChat {
    private final String sender;
    private final String date;
    private final String time;
    private final String content;
    private final int order;
    public String mark;

    public SingleChat(String singleChat, int order) {
        int index = singleChat.indexOf("\n");
        String info = singleChat.substring(0, index);
        Matcher dateMatcher = DATE_REGEX.matcher(info);
        Matcher timeMatcher = TIME_REGEX.matcher(info);
        this.time = timeMatcher.find() ? timeMatcher.group() : null;
        if(dateMatcher.find()) {
            this.date = dateMatcher.group();
            this.sender = info.substring(0, dateMatcher.start() - 1);
        } else {
            this.date = null;
            this.sender = info.substring(0, timeMatcher.start() - 1);
        }
        this.order = order;
        this.content = singleChat.substring(index + 1);
        this.mark = toMark();
    }

    @Override
    public String toString() {
        return "%s %s %s\n%s\n".formatted(sender, date, time, content);
    }


    public String toMark() {
        return toMark(null);
    }

    public String toMark(SingleChat lastChat) {
        if(lastChat==null) {
            // 第一个消息
            return "> > %s %s  \n> > %s  ".formatted(sender, time, content);
        } else if(lastChat.sender.equals(this.sender) & this.order>=lastChat.order) {
            // 连续消息
            return "\n> > " + content+"  ";
        } else {
            // 下一个发送者
            String time = this.time.substring(0, this.time.lastIndexOf(':'));
            String content = "> > " + this.content.replaceAll("\n", "\n> > ");
            return "\n>\n> > %s %s  \n%s  ".formatted(sender, time, content);
        }
    }
}