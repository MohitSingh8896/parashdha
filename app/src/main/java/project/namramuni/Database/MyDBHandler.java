package project.namramuni.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class MyDBHandler extends SQLiteOpenHelper {

    public MyDBHandler(Context context) {
        super(context, Handler.DATABASE_NAME, null, Handler.DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(Handler.CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + Handler.TABLE_NAME);
        onCreate(db);
    }

    public long addData(String date, String resp) {
        try {
            SQLiteDatabase db = this.getWritableDatabase();
            ContentValues values = new ContentValues();
            values.put(Handler.COLUMN_DATE, date);
            values.put(Handler.COLUMN_RESP, resp);
            long id = db.insert(Handler.TABLE_NAME, null, values);
            db.close();
            return id;
        } catch (Exception e) {
            e.getMessage();
        }
        return 0;
    }
    public void TrunkatTable() {
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL("DELETE FROM " + Handler.TABLE_NAME);
    }
    public void DeleteSingleData(String id) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(Handler.TABLE_NAME, Handler.COLUMN_ID + " = ?", new String[]{id});
        db.close();
    }
    public String getSingle(String date) {
        Boolean status = false;
        SQLiteDatabase db = getWritableDatabase();
        String selectQuery = "SELECT  * FROM " + Handler.TABLE_NAME + " where " + Handler.COLUMN_DATE + " < " + date;
        Cursor cursor = db.rawQuery(selectQuery, null);
        if (cursor != null)
            try {
                if (cursor.moveToFirst()) {
                    String a = cursor.getString(cursor.getColumnIndex(Handler.COLUMN_RESP));
                    cursor.close();
                    return a;
                }
            } catch (Exception e) {
                cursor.close();
                return "";
            }
        cursor.close();

        return "";
    }
}
