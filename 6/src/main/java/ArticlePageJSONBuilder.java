import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import java.io.FileWriter;
import java.io.IOException;

public class ArticlePageJSONBuilder implements Builder {
    private JSONArray jsonArray;

    ArticlePageJSONBuilder(JSONArray jsonArray) {
        this.jsonArray = jsonArray;
    }

    public void buildTemplate() throws IOException {
        JSONArray arr = new JSONArray();
        for(int i=0; i< this.jsonArray.size(); i++){
            JSONObject obj = (JSONObject)this.jsonArray.get(i);
            JSONObject result = new JSONObject();
            String title = ((String)obj.get("title") == null) ? "No title": (String)obj.get("title");
            result.put("header", "<header align=\"center\">"+title+"</header>");
            String category = ((String)obj.get("category") == null) ? "No category specified": (String)obj.get("category");
            result.put("p1", "<p1>"+category+"</p1>");
            String article = ((String)obj.get("article") == null) ? "No article": (String)obj.get("article");
            result.put("h1", "<h1 align=\"center\">"+article+"</h1>");
            arr.add(result);
        }

        try (FileWriter file = new FileWriter(getTemplateName())) {
            file.write(arr.toJSONString());
        }
    }
    @Override
    public String getTemplateName() {
        return "articlePageTemplate.json";
    }
}

