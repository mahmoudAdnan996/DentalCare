package dental.mahmoud.adnan.dental;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by mahmoud adnan on 4/20/2017.
 */

public class SymptomsDatabaseAdapter extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "disease_symptoms.db";
    public static final String TABLE_NAME = "symptoms";
    public static final String COL1 = "ID";
    public static final String COL2 = "SYMPTOM";

    public SymptomsDatabaseAdapter(Context context) {
        super(context, DATABASE_NAME, null, 3);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {

        String createTable = "create table "+TABLE_NAME+
                "( " +"ID"+" integer primary key autoincrement,"+ "SYMPTOM text); ";
        db.execSQL(createTable);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("DROP IF TABLE EXISTS " + TABLE_NAME);
        onCreate(db);
    }
    public void addData(String symptom){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL2, symptom);
        db.insert(TABLE_NAME, null, contentValues);
    }
    public Cursor getListContents(){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor data = db.rawQuery("SELECT * FROM " + TABLE_NAME, null);
        return data;
    }
}
