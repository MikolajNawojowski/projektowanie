import java.sql.*;

public interface InterfaceDatabase {
    Connection getDriverManager();

    default ResultSet getAll() {
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

    default ResultSet getOne(String student_name) {
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

    default ResultSet getByKey(Integer key) {
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


    default void insert(String name, Integer score) {
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

    default void delete(Integer idToDelete) {
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

    default void update(Integer idToUpdate, Integer updatedScoreValue) {
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
