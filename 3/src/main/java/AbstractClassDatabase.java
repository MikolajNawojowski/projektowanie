import org.json.simple.parser.ParseException;

import java.io.IOException;
import java.sql.*;

public abstract class AbstractClassDatabase {
    abstract Connection getDriverManager() throws IOException, ParseException;

    public ResultSet getAll() throws IOException, ParseException {
        ResultSet result = null;
        Connection connection = null;
        try {
            connection = getDriverManager();
            Statement statement = connection.createStatement();
            //statement.setQueryTimeout(30);

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

    public ResultSet getOne(String student_name) throws IOException, ParseException {
        ResultSet result = null;
        Connection connection = null;
        try {
            connection = getDriverManager();
            Statement statement = connection.createStatement();
            //statement.setQueryTimeout(30);

            result = statement.executeQuery("SELECT * FROM students WHERE student_name = '"+student_name+"';");
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

    public ResultSet getByKey(Integer key) throws IOException, ParseException {
        ResultSet result = null;
        Connection connection = null;
        try {
            connection = getDriverManager();
            Statement statement = connection.createStatement();
            //statement.setQueryTimeout(30);

            result = statement.executeQuery("SELECT * FROM students WHERE id = '"+key+"';");
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


    public void insert(String name, Integer score) throws IOException, ParseException {
        Connection connection = null;
        try {
            connection = getDriverManager();
            Statement statement = connection.createStatement();
            //statement.setQueryTimeout(30);

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

    public void delete(Integer idToDelete) throws IOException, ParseException {
        Connection connection = null;
        try {
            connection = getDriverManager();
            Statement statement = connection.createStatement();
            //statement.setQueryTimeout(30);

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

    public void update(Integer idToUpdate, Integer updatedScoreValue) throws IOException, ParseException {
        Connection connection = null;
        try {
            connection = getDriverManager();
            Statement statement = connection.createStatement();
            //statement.setQueryTimeout(30);

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
