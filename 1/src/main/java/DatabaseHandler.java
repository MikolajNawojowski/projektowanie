import java.sql.*;

/**
 * Created by macblady on 25.10.2017.
 */
public class DatabaseHandler {
    private static DatabaseHandler instance = null;

    private DatabaseHandler(){}

    public static DatabaseHandler getInstance() {
        if (instance == null) {
            instance = new DatabaseHandler();
        }
        return instance;
    }

    public static void main(String[] args) {
        //getByKey(2);
       // getOne("Mikolaj");
        //insert("Rafal", 25);
        //delete("Rafal");
        //update("Agata", 35);
        //getAll();
    }

    private static Connection getDriverManager() {
        Connection connection = null;
        try {
            connection = DriverManager.getConnection("jdbc:sqlite:projektowanie.sqlite");
        } catch (SQLException e) {
            System.out.println(e);
        }
        return connection;
    }

    public static ResultSet getAll() {
        ResultSet result = null;
        Connection connection = null;
        try {
            connection = getDriverManager();
            Statement statement = connection.createStatement();
            statement.setQueryTimeout(30);

            result = statement.executeQuery("SELECT * FROM students;");
            ResultSetMetaData rsmd = result.getMetaData();
            int columnsNumber = rsmd.getColumnCount();
            while (result.next()) {
                for (int i = 1; i <= columnsNumber; i++) {
                    if (i > 1) System.out.print(",  ");
                    String columnValue = result.getString(i);
                    System.out.print(columnValue + " " + rsmd.getColumnName(i));
                }
                System.out.println("");
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        finally {
            try {
                connection.close();
            } catch (SQLException e) {
                System.out.println("Connection closing nie bangla");
            }
        }
        return result;
    }

    public static ResultSet getOne(String student_name) {
        ResultSet result = null;
        Connection connection = null;
        try {
            connection = getDriverManager();
            Statement statement = connection.createStatement();
            statement.setQueryTimeout(30);

            result = statement.executeQuery("SELECT * FROM students WHERE student_name = '"+student_name+"';");

        } catch (SQLException e) {
            System.out.println(e);
        }
        finally {
            try {
                connection.close();
            } catch (SQLException e) {
                System.out.println("Connection closing nie bangla");
            }
        }
        return result;
    }

    public static ResultSet getByKey(Integer key) {
        ResultSet result = null;
        Connection connection = null;
        try {
            connection = getDriverManager();
            Statement statement = connection.createStatement();
            statement.setQueryTimeout(30);

            result = statement.executeQuery("SELECT * FROM students WHERE id = '"+key+"';");
        } catch (SQLException e) {
            System.out.println(e);
        }
        finally {
            try {
                connection.close();
            } catch (SQLException e) {
                System.out.println("Connection closing nie bangla");
            }
        }
        return result;
    }

    public static void insert(String name, Integer score) {
        Connection connection = null;
        try {
            connection = getDriverManager();
            Statement statement = connection.createStatement();
            statement.setQueryTimeout(30);

            statement.executeUpdate("INSERT INTO students (student_name, student_score) VALUES ('"+name+"', '" + score+ "');");
        } catch (SQLException e) {
            System.out.println(e);
        }
        finally {
            try {
                connection.close();
            } catch (SQLException e) {
                System.out.println("Connection closing nie bangla");
            }
        }
    }

    public static void delete(Integer idToDelete) {
        Connection connection = null;
        try {
            connection = getDriverManager();
            Statement statement = connection.createStatement();
            statement.setQueryTimeout(30);

            statement.executeUpdate("DELETE FROM students WHERE id = '" + idToDelete + "';");
        } catch (SQLException e) {
            System.out.println(e);
        }
        finally {
            try {
                connection.close();
            } catch (SQLException e) {
                System.out.println("Connection closing nie bangla");
            }
        }
    }

    public static void update(Integer idToUpdate, Integer updatedScoreValue) {
        Connection connection = null;
        try {
            connection = getDriverManager();
            Statement statement = connection.createStatement();
            statement.setQueryTimeout(30);

            statement.executeUpdate("UPDATE students SET student_score= '"+updatedScoreValue+"' WHERE id = '" + idToUpdate + "';" );
        } catch (SQLException e) {
            System.out.println(e);
        }
        finally {
            try {
                connection.close();
            } catch (SQLException e) {
                System.out.println("Connection closing nie bangla");
            }
        }
    }
}
