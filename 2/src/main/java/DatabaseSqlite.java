import java.sql.*;

/**
 * Created by macblady on 25.10.2017.
 */
public class DatabaseSqlite implements InterfaceDatabase {
    private static DatabaseSqlite instance = null;

    public static DatabaseSqlite getInstance() {
        if (instance == null) {
            instance = new DatabaseSqlite();
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
    public Connection getDriverManager() {
        Connection connection = null;
        try {
            connection = DriverManager.getConnection("jdbc:sqlite:projektowanie.sqlite");
        } catch (SQLException e) {
            System.out.println(e);
        }
        return connection;
    }
}
