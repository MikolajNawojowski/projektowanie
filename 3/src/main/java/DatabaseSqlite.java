import org.json.simple.parser.ParseException;

import java.io.IOException;
import java.sql.*;

/**
 * Created by macblady on 25.10.2017.
 */
public class DatabaseSqlite extends AbstractClassDatabase  {
    private static DatabaseSqlite instance = null;
    private static DBConfig config = null;

    public static DatabaseSqlite getInstance(DBConfig dbconfig)  {
        if (instance == null) {
            instance = new DatabaseSqlite();
            config = dbconfig;
        }
        return instance;
    }

    public static void main(String[] args) {
    }

    @Override
    public Connection getDriverManager() throws IOException, ParseException {
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(config.getValue("url"));
        } catch (SQLException e) {
            System.out.println(e);
        }
        return connection;
    }
}
