import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.io.IOException;
import org.json.simple.parser.ParseException;

public class DatabaseSqlite extends StrategySolution {
    private static DatabaseSqlite instance = null;

    public static DatabaseSqlite getInstance() {
        if (instance == null) {
            instance = new DatabaseSqlite();
        }
        return instance;
    }

    @Override
    public Connection getDriverManager() throws IOException, ParseException {
        DBConfig config = new DBConfig();
        config.writeConfig("sqlite");
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(config.getValue("url"));
        } catch (SQLException e) {
            System.out.println(e);
        }
        return connection;
    }
}
