import org.json.simple.parser.ParseException;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabasePostgreSql extends AbstractClassDatabase {
    private static DatabasePostgreSql instance = null;
    private static DBConfig config = null;

    public static DatabasePostgreSql getInstance(DBConfig dbconfig) {
        if (instance == null) {
            instance = new DatabasePostgreSql();
            config = dbconfig;
        }
        return instance;
    }

    public static void main(String[] args) {
        //getByKey(2);
        //getOne("Mikolaj");
        //insert("Rafal", 25);
        //delete("Rafal");
        //update("Agata", 35);
        //getAll();
    }
    @Override
    public Connection getDriverManager() throws IOException, ParseException {
        Connection connection = null;
        try {
            //user postgres
            connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres", config.getValue("user"), config.getValue("password"));
        } catch (SQLException e) {
            System.out.println(e);
        }
        return connection;
    }
}
