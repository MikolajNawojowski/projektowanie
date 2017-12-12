import org.json.simple.parser.ParseException;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;

public class DatabaseAdapter extends AbstractClassDatabase {

        public enum Database {
            mysql,
            sqlite,
            postgresql
        }

        private AbstractClassDatabase plugin;

        public DatabaseAdapter(DBConfig config) throws IOException, ParseException {
            switch (config.getValue("type")) {
                case "mysql":
                    this.plugin = DatabaseMySql.getInstance(config);
                    break;
                case "sqlite":
                    this.plugin = DatabaseSqlite.getInstance(config);
                    break;
                case "postgresql":
                    this.plugin = DatabasePostgreSql.getInstance(config);
                    break;
                default:
                    System.out.println("Database not supplied!");

            }
        }

        @Override
        public Connection getDriverManager() throws IOException, ParseException {
            if (this.plugin != null)
                return this.plugin.getDriverManager();
            return null;
        }

        @Override
        public ResultSet getAll() throws IOException, ParseException {
            if (this.plugin != null)
                return this.plugin.getAll();
            return null;
        }
        @Override
        public ResultSet getOne(String student_name) throws IOException, ParseException {
            if (this.plugin != null)
                return this.plugin.getOne(student_name);
            return null;
        }
        @Override
        public ResultSet getByKey(Integer key) throws IOException, ParseException {
            if (this.plugin != null)
                return this.plugin.getByKey(key);
            return null;
        }
        @Override
        public void insert(String name, Integer score) throws IOException, ParseException {
            if (this.plugin != null)
                this.plugin.insert(name, score);
        }
        @Override
        public void delete(Integer idToDelete) throws IOException, ParseException {
            if (this.plugin != null)
                this.plugin.delete(idToDelete);
        }
        @Override
        public void update(Integer idToUpdate, Integer updatedScoreValue) throws IOException, ParseException {
            if (this.plugin != null)
                this.plugin.update(idToUpdate, updatedScoreValue);
        }
}
