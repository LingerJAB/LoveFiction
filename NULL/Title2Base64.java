import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.function.Function;

public class Title2Base64 {
    public static void main(String[] args) {
        // 输入待转换文字
        String title = """
                                
                nihao
                                
                """;

        encode(title);
        mkdir(title, Title2Base64::encode);
    }

    public static String encode(String str) {
        str = str.trim();
        byte[] encode = Base64.getEncoder().encode(str.getBytes(StandardCharsets.UTF_8));
        String result = new String(encode);
        System.out.println(result);
        return result;
    }

    public static void mkdir(String title, Function<String, String> function) {
        String newTitle = function.apply(title.trim());
        File file = new File("CHAOS/" + newTitle + ".md");
        if(file.exists()) {
            System.out.println("已存在");
        } else {
            try {
                file.createNewFile();
            } catch(IOException e) {
                throw new RuntimeException(e);
            }
            System.out.println("succ");
        }
    }

    public static String decode(String str) {
        str = str.trim();
        byte[] decode = Base64.getDecoder().decode(str.getBytes(StandardCharsets.UTF_8));
        String result = new String(decode);
        System.out.println(result);
        return result;
    }
}
