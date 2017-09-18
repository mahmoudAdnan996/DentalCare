package dental.mahmoud.adnan.dental;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

/**
 * Created by mahmoud adnan on 3/26/2017.
 */

public class DatabaseAdapter {

    static final String DATABASE_NAME = "users.db";
    static final int DATABASE_VERSION = 1;
    public static final int NAME_COLUMN = 1;

    // login and register table
    static final String DATABASE_CREATE = "create table "+"USERS"+
            "( " +"ID"+" integer primary key autoincrement,"+ "USERNAME  text,EMAIL text,PASSWORD text,AGE integer,NUMBER integer); ";


    public SQLiteDatabase db;
    private final Context context;
    private DataBaseHelper helper;

    public DatabaseAdapter(Context context) {
        this.context = context;
        helper = new DataBaseHelper(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
    public  DatabaseAdapter open() throws SQLException
    {
        db = helper.getWritableDatabase();
        return this;
    }
    public void close()
    {
        db.close();
    }
    public  SQLiteDatabase getDatabaseInstance()
    {
        return db;
    }
    // insert into register and login database
    public void insertEntry(String email, String userName, String password, int age, int num)
    {
        ContentValues values = new ContentValues();

        values.put("USERNAME", userName);
        values.put("PASSWORD", password);
        values.put("EMAIL", email);
        values.put("AGE", age);
        values.put("NUMBER", num);

        db.insert("USERS", null, values);

    }

    public String getSinlgeEntry(String userName)
    {
        Cursor cursor=db.query("USERS", null, " USERNAME=?", new String[]{userName}, null, null, null);
        if(cursor.getCount()<1) // UserName Not Exist
        {
            cursor.close();
            return "NOT EXIST";
        }
        cursor.moveToFirst();
        String password= cursor.getString(cursor.getColumnIndex("PASSWORD"));
        cursor.close();
        return password;
    }

    /*
    public void  updateEntry(String userName,String password,String email, int age, int num)
    {
        ContentValues updatedValues = new ContentValues();

        updatedValues.put("USERNAME", userName);
        updatedValues.put("PASSWORD", password);
        updatedValues.put("EMAIL", email);
        updatedValues.put("AGE", age);
        updatedValues.put("NUMBER", num);

        String where="USERNAME = ?";
        db.update("USERS",updatedValues, where, new String[]{userName});
    }
    public int deleteEntry(String UserName)
    {
        String where="USERNAME=?";
        int numberOFEntriesDeleted= db.delete("USERS", where, new String[]{UserName}) ;
        // Toast.makeText(context, "Number fo Entry Deleted Successfully : "+numberOFEntriesDeleted, Toast.LENGTH_LONG).show();
        return numberOFEntriesDeleted;
    }
    */

}
