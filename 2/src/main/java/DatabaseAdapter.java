import java.sql.Connection;
import java.sql.ResultSet;

public class DatabaseAdapter implements InterfaceDatabase {
        public enum Database {
            mysql,
            sqlite,
            postgresql
        }

        private InterfaceDatabase plugin;

        public DatabaseAdapter(Database d) {
            switch (d) {
                case mysql:
                    this.plugin =  DatabaseMySql.getInstance();
                    break;
                case sqlite:
                    this.plugin = DatabaseSqlite.getInstance();
                    break;
                case postgresql:
                    this.plugin = DatabasePostgreSql.getInstance();
                    break;
                default:
                    System.out.println("Database not supplied!");

            }
        }

        @Override
        public Connection getDriverManager(){
            if (this.plugin != null)
                return this.plugin.getDriverManager();
            return null;
        }

        @Override
        public ResultSet getAll() {
            if (this.plugin != null)
                return this.plugin.getAll();
            return null;
        }
        @Override
        public ResultSet getOne(String student_name) {
            if (this.plugin != null)
                return this.plugin.getOne(student_name);
            return null;
        }
        @Override
        public ResultSet getByKey(Integer key) {
            if (this.plugin != null)
                return this.plugin.getByKey(key);
            return null;
        }
        @Override
        public void insert(String name, Integer score){
            if (this.plugin != null)
                this.plugin.insert(name, score);
        }
        @Override
        public void delete(Integer idToDelete){
            if (this.plugin != null)
                this.plugin.delete(idToDelete);
        }
        @Override
        public void update(Integer idToUpdate, Integer updatedScoreValue){
            if (this.plugin != null)
                this.plugin.update(idToUpdate, updatedScoreValue);
        }
}
