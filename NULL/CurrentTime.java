public class CurrentTime {
    public static void main(String[] args) {
        long time = System.currentTimeMillis() / 1000;
        System.out.println("CurrentTime: "+ time);
        System.out.println("Markdown: "+"![](../../IMAGES/.../"+time+".)");
        System.out.println("# 先复制再复制图片");
    }
}

enum ImageType{
    PNG,
    JPG,
}
enum ImagePath{
    MEME,
    PHOTO,
}