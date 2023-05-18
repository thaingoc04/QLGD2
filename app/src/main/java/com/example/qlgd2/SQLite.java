package com.example.qlgd2;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public class SQLite extends SQLiteOpenHelper {

    public final static String tableName = "GiaoDich4";
    public final static String Id = "Id";
    public final static String Content = "Content";
    public final static String Date = "Date";
    public final static String Type = "Type";
    public final static String Name = "Name";
    public final static String Cost = "Cost";

    public SQLite(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sqlCreate = "Create table if not exists "+ tableName+ " ("
                + Id +" Integer primary key autoincrement, "
                + Content +" Text,"
                + Date + " Text,"
                + Type +" Integer,"
                + Name +" Text, "
                + Cost +" Integer)";
        db.execSQL(sqlCreate);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("Drop table if exists "+ tableName);
        onCreate(db);
    }

//    public void addContact(GiaoDich item){
//        SQLiteDatabase db = this.getWritableDatabase();
//        ContentValues values = new ContentValues();
//        values.put(Content, item.getContent());
//        values.put(Date, item.getDate());
//        values.put(Type, item.getType());
//        values.put(Name, item.getName());
//        values.put(Cost, item.getCost());
//        db.insert(tableName, null, values);
//        db.close();
//    }
public void addContact(GiaoDich item) {
    SQLiteDatabase db = this.getWritableDatabase();
    ContentValues values = new ContentValues();
    values.put(Content, item.getContent());
    values.put(Date, item.getDate());
    values.put(Type, item.getType());
    values.put(Name, item.getName());
    values.put(Cost, item.getCost());
    long rowId = db.insert(tableName, null, values);
    if (rowId != -1) {
        // Data inserted successfully
        // Update the Id of the inserted item
        item.setId((int) rowId);
    }
    db.close();
}



    public void upgradeContact(int id, GiaoDich item){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(Id, item.getId());
        values.put(Content, item.getContent());
        values.put(Date, item.getDate());
        values.put(Type, item.getType());
        values.put(Name, item.getName());
        values.put(Cost, item.getCost());
        db.update(tableName, values, Id +"=?", new String[]{String.valueOf(id)});
        db.close();
    }

    public void deleteContact(int id){
        SQLiteDatabase db = this.getWritableDatabase();
        String sql = "Delete from "+ tableName + " Where Id= " +id;
        db.execSQL(sql);
        db.close();
    }

    public ArrayList<GiaoDich> getAllContact(){
        ArrayList<GiaoDich> list = new ArrayList<>();
        String sql = "SELECT * FROM " + tableName ;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(sql, null);
        if(cursor != null){
            while (cursor.moveToNext()){

                GiaoDich item = new GiaoDich(cursor.getInt(0), cursor.getString(1), cursor.getString(2), cursor.getInt(3), cursor.getString(4),
                        cursor.getInt(5));
                list.add(item);
            }
        }
        return list;
    }
}
