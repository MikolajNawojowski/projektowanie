import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import java.io.FileWriter;
import java.io.IOException;

public class MainPageJSONBuilder implements Builder {
    private JSONArray jsonArray;

    MainPageJSONBuilder(JSONArray jsonArray) {
        this.jsonArray = jsonArray;
    }

    public void buildTemplate() throws IOException {
        JSONObject obj = new JSONObject();
        JSONObject html = new JSONObject();
        JSONObject head = new JSONObject();
        JSONObject body = new JSONObject();

        head.put("meta", "<meta charset=\\\"UTF-8\\\">");
        head.put("title", "<title></title>");

        body.put("header", "<header></header>");
        body.put("p","<p1></p1>");

        html.put("body", body);
        html.put("head", head);

        obj.put("html", html);

        try (FileWriter file = new FileWriter(getTemplateName())) {
            file.write(obj.toJSONString());
        }
    }

    @Override
    public String getTemplateName() {
        return "mainPageTemplate.json";
    }
}

