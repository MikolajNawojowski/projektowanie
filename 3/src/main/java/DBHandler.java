import org.json.simple.parser.ParseException;
import java.io.IOException;

public class DBHandler {
    public static void main(String[] args) throws IOException, ParseException {
        DBConfig config = new DBConfig();
        //config.writeConfig();
        DatabaseAdapter databaseHandler = new DatabaseAdapter(config);
        databaseHandler.getAll();
    }
}
