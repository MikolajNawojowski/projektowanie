import java.io.IOException;

public class test {
    public static void main(String[] args) throws IOException {
        Page page = new ArticlePage(new Article());
        System.out.println(page.getPage());
    }
}
