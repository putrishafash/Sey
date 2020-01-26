package com.shafaseyo.bikesrents.helper;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class DataHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "rentalsepeda.db";
    private static final int DATABASE_VERSION = 1;

    public DataHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);

    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL("PRAGMA foreign_keys=ON");
        db.execSQL("create table penyewa (" +
                "nama text," +
                "alamat text," +
                "no_hp text," +
                "primary key(nama)" +
                ");" +
                "");
        db.execSQL("create table sepeda(" +
                "merk text," +
                "harga int," +
                "primary key(merk)" +
                ");" +
                "");
        db.execSQL("create table sewa(" +
                "merk text," +
                "nama text," +
                "promo int," +
                "lama int," +
                "total double," +
                "foreign key(merk) references sepeda (merk), " +
                "foreign key(nama) references penyewa (nama) " +
                ");" +
                "");

        db.execSQL("insert into sepeda values (" +
                "'Cannondale'," +
                "400000" +
                ");" +
                "");
        db.execSQL("insert into sepeda values (" +
                "'Giant'," +
                "350000" +
                ");" +
                "");
        db.execSQL("insert into sepeda values (" +
                "'GT'," +
                "300000" +
                ");" +
                "");
        db.execSQL("insert into sepeda values (" +
                "'Polygon'," +
                "200000" +
                ");" +
                "");
        db.execSQL("insert into sepeda values (" +
                "'Wimcycle'," +
                "100000" +
                ");" +
                "");
        db.execSQL("insert into sepeda values (" +
                "'United'," +
                "95000" +
                ");" +
                "");
        db.execSQL("insert into sepeda values (" +
                "'Santa Cruz'," +
                "90000" +
                ");" +
                "");
    }

    public List<String> getAllCategories() {
        List<String> categories = new ArrayList<String>();
        String selectQuery = "select * from sepeda";
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                categories.add(cursor.getString(0));
            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();

        return categories;
    }

    @Override
    public void onUpgrade(SQLiteDatabase arg0, int arg1, int arg2) {

    }

}