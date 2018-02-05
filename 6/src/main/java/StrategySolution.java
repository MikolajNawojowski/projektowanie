import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

abstract class StrategySolution implements Strategy {
    @Override
    public JSONObject getAll() throws IOException, ParseException {
        ResultSet result = null;
        Connection connection = null;
        JSONObject response = null;
        Converter converter = new Converter();
        try {
            connection = getDriverManager();
            Statement statement = connection.createStatement();
            //statement.setQueryTimeout(30);

            result = statement.executeQuery("SELECT * FROM articles;");
            try {
                response = converter.convertToJSON(result);
            }
            catch (Exception e) {
                System.out.println(e);
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
        return response;
    }
    public JSONObject getOne(String title) throws IOException, ParseException {
        ResultSet result = null;
        Connection connection = null;
        JSONObject response = null;
        Converter converter = new Converter();
        try {
            connection = getDriverManager();
            Statement statement = connection.createStatement();
            result = statement.executeQuery("SELECT * FROM articles WHERE title = '"+title+"';");
            try {
                response = converter.convertToJSON(result);
            }
            catch (Exception e) {
                System.out.println(e);
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
        return response;
    }

    public JSONObject getByKey(String column, String category) throws IOException, ParseException {
        ResultSet result = null;
        Connection connection = null;
        JSONObject response = null;
        Converter converter = new Converter();
        try {
            connection = getDriverManager();
            Statement statement = connection.createStatement();

            result = statement.executeQuery("SELECT * FROM articles WHERE "+column+" = '"+category+"';");
            try {
                response = converter.convertToJSON(result);
            }
            catch (Exception e) {
                System.out.println(e);
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
        return response;
    }


    public void insert(String title, String article) throws IOException, ParseException {
        Connection connection = null;
        try {
            connection = getDriverManager();
            Statement statement = connection.createStatement();

            statement.executeUpdate("INSERT INTO articles (title, article) VALUES ('"+title+"', '" + article+ "');");
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

    public void delete(String title) throws IOException, ParseException {
        Connection connection = null;
        try {
            connection = getDriverManager();
            Statement statement = connection.createStatement();

            statement.executeUpdate("DELETE FROM articles WHERE title = '" + title + "';");
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

    public void update(String titleToUpdate, String updatedArticleValue) throws IOException, ParseException {
        Connection connection = null;
        try {
            connection = getDriverManager();
            Statement statement = connection.createStatement();

            statement.executeUpdate("UPDATE articles SET article= '"+updatedArticleValue+"' WHERE title = '" + titleToUpdate + "';" );
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
