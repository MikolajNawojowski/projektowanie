import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class DBConfig {
    final String CONFIG = "conf.json";

    public void writeConfig(String type) throws IOException {
        JSONObject config = new JSONObject();
        if (type.equals("sqlite")) {
            config.put("url", "jdbc:sqlite:projektowanie.sqlite");
            config.put("type", "sqlite");
            config.put("port", 3166);
            config.put("username", "root");
            config.put("password", "niemiecki3");
            config.put("database", "projektowanie.sqlite");
        }
        else if (type.equals("postgres")) {
            config.put("url", "jdbc:postgresql://localhost:5432/projektowanie");
            config.put("type", "postgres");
            config.put("port", 5432);
            config.put("username", "mnawojow");
            config.put("password", "niemiecki3");
            config.put("database", "projektowanie");
        }
        else if (type.equals("mysql")) {
            //config.put("url", "jdbc:mysql://localhost:3306/projektowanie.mysql");
            config.put("url","jdbc:mysql://localhost/projektowanie.mysql");
            config.put("type", "mysql");
            config.put("port", 3306);
            config.put("user", "root");
            config.put("password", "niemiecki3");
            config.put("database", "projektowanie.mysql");
        }

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

    public void readConfig() throws IOException, ParseException {
        JSONParser parser = new JSONParser();
        FileReader configFile = null;
        try {
            configFile = new FileReader(CONFIG);
            JSONObject obj = (JSONObject) parser.parse(configFile);
        } catch(IOException e) {
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
