import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.function.Function;
import java.util.regex.Pattern;

public class Title2Base64 {
    public static void main(String[] args) {
        // 输入待转换文字
        String title = """
                                
                04_靠近
                                
                """.trim();

        mkdir(title, Title2Base64::encode);
    }


    public static String encode(String str) {
        byte[] encode = Base64.getEncoder().encode(str.getBytes(StandardCharsets.UTF_8));
        return new String(encode);
    }

    @SuppressWarnings("unused")
    public static String decode(String str) {
        byte[] decode = Base64.getDecoder().decode(str.getBytes(StandardCharsets.UTF_8));
        return new String(decode);
    }

    public static void mkdir(String title, Function<String, String> function) {
        if(!check(title)) {
            System.out.println("# \"" + title + "\" ain't a valid title! Form: [Date]_[Title]");
            return;
        }

        String newTitle = function.apply(title).concat(".md");
        System.out.printf("# Original Title：\"%s\"\n# Transformed To：\"%s\"\n", title, newTitle);
        System.out.println();
        File file = new File("CHAOS/" + newTitle);


        if(file.exists()) {
            System.out.println("\n# \"" + newTitle + "\" has been existed!");
        } else {
            try {
                @SuppressWarnings("unused")
                boolean b = file.createNewFile();
            } catch(IOException e) {
                throw new RuntimeException(e);
            }
            System.out.println("\n# \"" + newTitle + "\" has been created!");
            System.out.println("[" + title.substring(3) + "](../../CHAOS/" + newTitle + ")");
        }
        System.out.println(file.toURI());
    }

    public static boolean check(String title) {
        return Pattern.compile("\\d{1,2}_.+").matcher(title).find();
    }
}
