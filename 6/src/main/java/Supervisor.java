import java.io.IOException;
import org.json.simple.JSONArray;

public class Supervisor {
    void buildMainPage(JSONArray jsonArray) throws IOException {
        Builder[] builders = new Builder[2];
        builders[0] = new MainPageHTMLBuilder();
        builders[1] = new MainPageJSONBuilder(jsonArray);
        for (Builder builder: builders) {
            builder.buildTemplate();
        }
    }

    void buildArticlePage(JSONArray jsonArray) throws IOException {
        Builder[] builders = new Builder[2];
        builders[0] = new ArticlePageHTMLBuilder();
        builders[1] = new ArticlePageJSONBuilder(jsonArray);
        for (Builder builder: builders) {
            builder.buildTemplate();
        }
    }
}
