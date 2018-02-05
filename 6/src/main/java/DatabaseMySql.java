import org.json.simple.parser.ParseException;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseMySql extends StrategySolution{

    private static DatabaseMySql instance = null;

    public static DatabaseMySql getInstance() {
        if (instance == null) {
            instance = new DatabaseMySql();
        }
        return instance;
    }

    @Override
    public Connection getDriverManager() throws IOException, ParseException {
        DBConfig config = new DBConfig();
        config.writeConfig("mysql");
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(
                    config.getValue("url"),
                    config.getValue("user"),
                    config.getValue("password"));
        } catch (SQLException e) {
            System.out.println(e);
        }
        return connection;
    }
}
