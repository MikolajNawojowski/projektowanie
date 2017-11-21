public class test {
    public static void main(String[] args) {
        DatabaseHandler databaseHandler = DatabaseHandler.getInstance();
        databaseHandler.getAll();
        databaseHandler.getByKey(2);
        databaseHandler.getOne("Mikolaj");
        databaseHandler.insert("Rafal", 25);
        databaseHandler.delete(15);
        databaseHandler.update(15, 35);
        databaseHandler.getAll();
    }
}
