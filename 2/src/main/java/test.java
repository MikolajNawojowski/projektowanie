public class test {
    public static void main(String[] args) {
        DatabaseAdapter databaseHandler = new DatabaseAdapter(DatabaseAdapter.Database.sqlite);
        DatabaseAdapter databaseHandler2 = new DatabaseAdapter(DatabaseAdapter.Database.mysql);
        DatabaseAdapter databaseHandler3 = new DatabaseAdapter(DatabaseAdapter.Database.postgresql);
        //databaseHandler3.getAll();
        //databaseHandler3.getByKey(2);
        //żeby jednoznacznie określić jakąś wartość potrzbne jest id. W przypadku tylko imienia nie różni się to
        //od getByKey. Więc zrobiłem to po imieniu, ale wtedy może zwrqcać więcej niż jeden rekord
        //databaseHandler3.getOne("Agata");
        //databaseHandler3.insert("Agata", 35);
        //databaseHandler3.delete(3);
        //databaseHandler3.update(8, 80);
        //databaseHandler3.getAll();
    }
}
