import org.json.simple.parser.ParseException;

import java.io.IOException;
import java.sql.*;

public class DatabaseMySql extends AbstractClassDatabase {
    private static DatabaseMySql instance = null;
    private static DBConfig config = null;

    public static DatabaseMySql getInstance(DBConfig dbconfig) {
        if (instance == null) {
            instance = new DatabaseMySql();
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
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/obiektowa", config.getValue("user"), config.getValue("password"));
        } catch (SQLException e) {
            System.out.println(e);
        }
        return connection;
    }
}
