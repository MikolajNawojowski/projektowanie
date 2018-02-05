import java.io.File;
import java.io.IOException;
import org.apache.commons.io.FileUtils;

public class Article extends Page {
    public String page = "";

    public Article() {
        try {
            this.page += FileUtils.readFileToString(new File("articlePageTemplate.html"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @Override
    String getPage() {
        return this.page;
    }

}
