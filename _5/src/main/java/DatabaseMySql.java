import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseMySql implements InterfaceDatabase {

    private static DatabaseMySql instance = null;

    public static DatabaseMySql getInstance() {
        if (instance == null) {
            instance = new DatabaseMySql();
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
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/obiektowa", "root", "niemiecki3");
        } catch (SQLException e) {
            System.out.println(e);
        }
        return connection;
    }
}
