package comp3350.spa.application;

public class Main {

	private static final String dbName = "SC";
	private static String dbPathName = "database/SC";

	public static void main(String[] args) {
		System.out.println("All done");
	}

	public static void startUp() {
		System.out.println("Startup called..");
		CommonAccess.createDatabase(dbName);
	}

	public static void shutDown() {
		CommonAccess.closeDatabase();
	}

	public static String getDBPathName() {
		String database = dbPathName;
		if (database == null)
			database = dbName;

		return database;
	}

	public static void setDBPathName(String pathName) {
		System.out.println("Setting DB path to: " + pathName);
		dbPathName = pathName;
	}

	public static String getDBName() {
		return dbName;
	}

}
