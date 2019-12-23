package project.namramuni.Database;

public class Handler {
    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "vmcdemo.db";
    public static final String TABLE_NAME = "dashboardTable";
    public static final String COLUMN_ID = "ID";
    public static final String COLUMN_DATE = "dates";
    public static final String COLUMN_RESP = "response";
    public static final String COLUMN_TIMESTAMP = "Date";
    public static final String CREATE_TABLE = "CREATE TABLE IF NOT EXISTS " + TABLE_NAME + "("
            + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," + COLUMN_DATE + " TEXT ," + COLUMN_RESP + " TEXT ,"
            + COLUMN_TIMESTAMP + " DATETIME DEFAULT CURRENT_TIMESTAMP)";
}
