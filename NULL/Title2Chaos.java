import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.function.Function;
import java.util.regex.Pattern;

@SuppressWarnings("unused")
public class Title2Chaos {
    public static void main(String[] args) {
        // 输入待转换文字   TIPS: 请不要输入[.md]
        mkdir("""
                                
                25_秋
                                
                """.trim(), Title2Chaos::encode);
    }


    public static String encode(String str) {
        byte[] encode = Base64.getEncoder().encode(str.getBytes(StandardCharsets.UTF_8));
        return new String(encode);
    }

    public static String decode(String str) {
        byte[] decode = Base64.getDecoder().decode(str.getBytes(StandardCharsets.UTF_8));
        return new String(decode);
    }

    public static void mkdir(String title, Function<String, String> function) {
        System.out.println();
        String s;

        switch(checkType(title)){
            case 1-> s="../";
            case 2-> s="";
            case -2->{
                System.out.println("# Don't end with '.md'");
                return;
            }
            default -> {
                System.out.println("# \"" + title + "\" ain't a valid title!\n# Form: [Date]_[Title] or [Title]");
                return;
            }
        }
        String newTitle = function.apply(title).concat(".md");
        System.out.printf("# Original Title：\"%s\"\n# Transformed To：\"%s\"\n", title, newTitle);
        System.out.println();
        File file = new File("CHAOS/" + newTitle);


        if(file.exists()) {
            System.out.println("# \"" + newTitle + "\" has been existed!");
        } else {
            try {
                @SuppressWarnings("unused")
                boolean b = file.createNewFile();
            } catch(IOException e) {
                throw new RuntimeException(e);
            }
            System.out.println("# \"" + newTitle + "\" has been created!");
            System.out.println("[" + title.substring(3) + "](" + s + "../CHAOS/" + newTitle + ")");
        }
        System.out.println(file.toURI());
    }

    public static byte checkType(String title) {
        if(title.endsWith(".md")) return -2;

        if(Pattern.compile("\\d{1,2}_\\S+").matcher(title).find()) return 1;
        else if(Pattern.compile("\\S{1,3}").matcher(title).find()) return 2;
        return -1;
    }
}
