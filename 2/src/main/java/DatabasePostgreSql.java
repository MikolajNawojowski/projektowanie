import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabasePostgreSql implements InterfaceDatabase  {
    private static DatabasePostgreSql instance = null;

    public static DatabasePostgreSql getInstance() {
        if (instance == null) {
            instance = new DatabasePostgreSql();
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
            connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres", "postgres", "niemiecki3");
        } catch (SQLException e) {
            System.out.println(e);
        }
        return connection;
    }
}
