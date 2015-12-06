package com.coconuts.ariel.thelovedareproject.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.coconuts.ariel.thelovedareproject.model.ReflectionInfo;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Ariel on 11/26/2015.
 */
public class ReflectionDB {

    public static final int DB_VERSION = 1;
    public static final String DB_NAME = "ReflectionDB.db";

    private ReflectionDBHelper mReflectionDBHelper;
    private SQLiteDatabase mSQLiteDatabase;



    public ReflectionDB(Context context) {

        mReflectionDBHelper = new ReflectionDBHelper(context, DB_NAME, null, DB_VERSION);
        mSQLiteDatabase = mReflectionDBHelper.getWritableDatabase();

    }

    public boolean insertReflection(String day, String reflection) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("day", day);
        contentValues.put("reflection", reflection);

        long rowId = mSQLiteDatabase.insert("DayReflection", null, contentValues);
        return rowId != -1;
    }

    public boolean findDayReflection(String dayGiven) {
        Log.e("FINDReflect", dayGiven);
        String Query = "SELECT day FROM DayReflection WHERE day=" + dayGiven;
        Log.e("HERE I AM", Query);
        Cursor cursor = mSQLiteDatabase.rawQuery(Query, null);
        Log.e("HERE I AM", Integer.toString(cursor.getCount()));
        if(cursor.getCount() <= 0){
            cursor.close();
            return false;
        }
        cursor.close();
        return true;
    }

    public void deleteReflection(String day) {
        mSQLiteDatabase.delete("DayReflection", "day=?", new String[]{day});
    }

    public void saveReflection(String day, String reflection) {
        ContentValues values = new ContentValues();
        values.put("reflection", reflection);

        mSQLiteDatabase.update("DayReflection", values, "day=?", new String[] {day});

    }

    public void closeDB(){
        mSQLiteDatabase.close();
    }

    public List<ReflectionInfo> selectDayReflection(){
        String[] columns = {
                "day", "reflection"
        };


        Cursor c = mSQLiteDatabase.query(
                "DayReflection",  // The table to query
                columns,                               // The columns to return
                null,                                // The columns for the WHERE clause
                null,                            // The values for the WHERE clause
                null,                                     // don't group the rows
                null,                                     // don't filter by row groups
                null                                 // The sort order
        );
        c.moveToFirst();
        List<ReflectionInfo> list = new ArrayList<ReflectionInfo>();
        for (int i=0; i<c.getCount(); i++) {
            String day = c.getString(0);
            String reflection = c.getString(1);
            ReflectionInfo reflectionInfo = new ReflectionInfo(day, reflection);
            list.add(reflectionInfo);
            c.moveToNext();
        }

        return list;

    }
}

class ReflectionDBHelper extends SQLiteOpenHelper {


    private static final String CREATE_REFLECT_SQL =
            "CREATE TABLE IF NOT EXISTS DayReflection (day TEXT PRIMARY KEY, reflection TEXT)";

    private static final String DROP_REFLECT_SQL =
            "DROP TABLE IF EXISTS DayReflection";

    public ReflectionDBHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
        Log.e("HERE I AM: ", "In Constructor");
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        Log.e("HERE I AM: ", "Before Create");
        sqLiteDatabase.execSQL(CREATE_REFLECT_SQL);
        Log.e("HERE I AM: ", "Created");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL(DROP_REFLECT_SQL);
        onCreate(sqLiteDatabase);
    }
}
