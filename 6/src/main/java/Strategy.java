import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;

import java.io.IOException;
import java.sql.*;

public interface Strategy {
    Connection getDriverManager() throws IOException, ParseException;
    JSONObject getAll() throws IOException, ParseException;
    JSONObject getOne(String title) throws IOException, ParseException;
    JSONObject getByKey(String column, String category) throws IOException, ParseException;
    void insert(String title, String article) throws IOException, ParseException;
    void delete(String title) throws IOException, ParseException;
    void update(String titleToUpdate, String updatedArticleValue) throws IOException, ParseException;
}
