import org.apache.commons.io.FileUtils;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
import java.io.File;
import java.io.IOException;

public class ArticlePage extends Decorator {
    private String page;

    public ArticlePage(Page page) {
        this.page = page.getPage();
    }
    @Override
    public String getPage() {
        final String headerRegex = "(<header>)(</header>)";
        final String paragraphRegex = "(<p[0-9]*>)(</p[0-9]*>)";
        final String hRegex = "(<h[0-9]*>)(</h[0-9]*>)";
        final String restRegex = "(</article>)";

        JSONParser parser = new JSONParser();

        JSONArray obj = null;
        try {
            obj = (JSONArray)parser.parse(new FileReader("articlePageTemplate.json"));
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        JSONObject firstJsonObject = (JSONObject) obj.get(0);
        String header = (String) firstJsonObject.get("header");
        String p1 = (String) firstJsonObject.get("p1");
        String h1 = (String) firstJsonObject.get("h1") + "<br><br><br><br><br><br>";
        ;
        this.page = this.page.replaceAll(headerRegex, header);
        this.page = this.page.replaceAll(paragraphRegex, p1);
        this.page = this.page.replaceAll(hRegex, h1);

        String rest = "";
        for(int i=1; i< obj.size(); i++) {
            JSONObject jsonObject = (JSONObject) obj.get(i);
            rest += "<br><br><br><br>";
            rest += (String) jsonObject.get("header");
            rest += (String) jsonObject.get("p1");
            rest += (String) jsonObject.get("h1");
            rest += "<br><br><br><br>";
        }
        this.page = this.page.replaceAll(restRegex, rest+"$1");
        return page;
    }

}
