import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class TemplateJSON {
    final String CONFIG = "template.json";

    public void writeConfig() throws IOException {
        JSONObject config = new JSONObject();
        config.put("title", "Pierwsza strona");
        config.put("script", "js/scripts.js");
        config.put("header", "Naglowek pierwszy");
        config.put("paragraph", "To pierwszy paragraf");

        FileWriter configFile = null;
        try {
            configFile = new FileWriter(CONFIG);
            configFile.write(config.toJSONString());
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            configFile.close();
        }
    }

    public String getValue(String search_value) throws IOException, ParseException {
        JSONParser parser = new JSONParser();
        FileReader configFile = null;
        JSONObject obj = null;
        try {
            configFile = new FileReader(CONFIG);
            obj = (JSONObject) parser.parse(configFile);
        } catch(IOException e) {
            e.printStackTrace();
        } finally {
            configFile.close();
        }
        return (String) obj.get(search_value);
    }
}

