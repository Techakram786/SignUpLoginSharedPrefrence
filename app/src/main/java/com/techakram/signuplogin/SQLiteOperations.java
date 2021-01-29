package com.techakram.signuplogin;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class SQLiteOperations extends SQLiteOpenHelper {

    private static final String dbName="MYDEB";
    private static final String TABLE_NAME="DETAILS";
    private static final int VERSION=1;

    public SQLiteOperations(Context context) {
        super(context, dbName,null,VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String sql="CREATE TABLE "+TABLE_NAME+"(First text,Last text,Email text PRIMARY KEY,Password text)";
        sqLiteDatabase.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME+";");
        onCreate(sqLiteDatabase);
    }

    public void insertData(String First,String Last,String email,String password)
    {
        SQLiteDatabase database=this.getWritableDatabase();
        ContentValues values=new ContentValues();
        values.put("First",First);
        values.put("Last",Last);
        values.put("Email",email);
        values.put("Password",password);
        database.insert(TABLE_NAME,null,values);
    }

    public Cursor readData()
    {
        SQLiteDatabase database=this.getReadableDatabase();

        Cursor cursor=database.rawQuery("SELECT * FROM "+TABLE_NAME+";",null);
        return cursor;
    }

    public Cursor queryLogin(String email,String password)
    {

        SQLiteDatabase database=this.getReadableDatabase();
        Cursor cursor=database.rawQuery("SELECT First,Last,Email FROM "+TABLE_NAME+" WHERE Email='"+email+"' and Password='"+password+"';",null);

        return cursor;
    }
}
