package comp3350.spa.application;

import comp3350.spa.persistence.Database;
import comp3350.spa.persistence.SQLDatabase;

public class CommonAccess {

    private static Database database;

    public static Database createDatabase(String dbName) {
        if (database == null) {
            database = new SQLDatabase(dbName);
            database.open(Main.getDBPathName());
        }

        return database;
    }

    public static Database createDatabase(Database alternateDatabaseService) {
        if (database == null) {
            database = alternateDatabaseService;
            database.open(Main.getDBPathName());
        }

        return database;
    }

    public static Database getDatabase(String dbName) {
        if (database == null) {
            System.out.println("Connection to data access has not been established.");
            System.exit(1);
        }

        return database;
    }

    public static void closeDatabase() {
        if (database != null)
            database.close();

        database = null;
    }
}
