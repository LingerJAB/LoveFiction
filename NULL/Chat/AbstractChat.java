package Chat;

import java.util.regex.Pattern;

public abstract class AbstractChat {
    public static final Pattern DATE_REGEX = Pattern.compile("20\\d{1,2}/\\d{1,2}/\\d{1,2}");
    public static final Pattern TIME_REGEX = Pattern.compile("\\d{1,2}:\\d{1,2}:\\d{1,2}");
}