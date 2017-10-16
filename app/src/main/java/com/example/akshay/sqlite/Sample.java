package com.example.akshay.sqlite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Akshay on 10/12/2017.
 */

public class Sample extends SQLiteOpenHelper {

    public static final String DATABASE_NAME="Student.db";
    public static final String TABLE_NAME="student_table";
    public static final String COL_1="ID";
    public static final String COL_2="NAME";
    public static final String COL_3="SURNAME";
    public static final String COL_4="MARKS";

    public Sample(Context context) {
        super(context, DATABASE_NAME, null, 1);
        SQLiteDatabase databse=this.getWritableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("create table "+ TABLE_NAME + "(ID INTEGER PRIMARY KEY AUTOINCREMENT ,NAME TEXT,SURNAME TEXT,MARKS INTEGER)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS"+TABLE_NAME);
        onCreate(sqLiteDatabase);

    }

    public boolean insertdata(String name,String surname,String marks){

        SQLiteDatabase database=this.getWritableDatabase();

        ContentValues contentValues=new ContentValues();

        contentValues.put(COL_2,name);
        contentValues.put(COL_3,surname);
        contentValues.put(COL_4,marks);

        long result=database.insert(TABLE_NAME,null,contentValues);

        if(result == -1)
            return false;
        else
            return true;
    }


    public Cursor getAllData(){

        SQLiteDatabase database=this.getWritableDatabase();

        Cursor res=database.rawQuery("select * from "+TABLE_NAME,null);
        return res;

    }

    public boolean updatData(String id,String name,String surname,String marks){

        SQLiteDatabase database=this.getWritableDatabase();

        ContentValues contentValues=new ContentValues();

        contentValues.put(COL_1,id);
        contentValues.put(COL_2,name);
        contentValues.put(COL_3,surname);
        contentValues.put(COL_4,marks);

        database.update(TABLE_NAME,contentValues,"ID = ?",new String[] { id });
        return true;

    }

    public Integer deletData(String id){

        SQLiteDatabase database=this.getWritableDatabase();

        return database.delete(TABLE_NAME,"ID = ?",new String[] { id });


    }
}
