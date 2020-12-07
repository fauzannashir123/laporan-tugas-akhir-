package com.example.daftar_mobil;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class MyDatabase extends SQLiteOpenHelper {
    private static int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "db_mobil";
    private static final String tb_jenis_mobil = "tb_jenis_mobil";
    private static final String tb_jenis_id = "id";
    private static final String tb_jenis_image = "image";
    private static final String tb_jenis_merk = "merk";
    private static final String tb_jenis_nama = "nama";
    private static final String tb_jenis_harga = "harga";
    private static final String tb_jenis_warna = "warna";
    private static final String CREATE_TABLE_JENIS_MOBIL = "CREATE TABLE " +
            tb_jenis_mobil + "("
            + tb_jenis_id + " INTEGER PRIMARY KEY ,"
            + tb_jenis_image + " BLOB,"
            + tb_jenis_merk + " TEXT,"
            + tb_jenis_nama + " TEXT,"
            + tb_jenis_harga + " TEXT,"
            + tb_jenis_warna + " TEXT " + ")";

    public MyDatabase (Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_JENIS_MOBIL);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public void CreateSpesifikasi (Spesifikasi mdNotif) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(tb_jenis_id, mdNotif.get_id());
        values.put(tb_jenis_image, mdNotif.get_image());
        values.put(tb_jenis_merk, mdNotif.get_merk());
        values.put(tb_jenis_nama, mdNotif.get_nama());
        values.put(tb_jenis_harga, mdNotif.get_harga());
        values.put(tb_jenis_warna, mdNotif.get_warna());
        db.insert(tb_jenis_mobil, null, values);
        db.close();
    }

    public List<Spesifikasi> ReadSpesifikasi() {
        List<Spesifikasi> judulModelList = new ArrayList<Spesifikasi>();
        String selectQuery = "SELECT * FROM " + tb_jenis_mobil;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()) {
            do {
                Spesifikasi mdKontak = new Spesifikasi();
                mdKontak.set_id(cursor.getString(0));
                mdKontak.set_image(cursor.getBlob(1));
                mdKontak.set_merk(cursor.getString(2));
                mdKontak.set_nama(cursor.getString(3));
                mdKontak.set_harga(cursor.getString(4));
                mdKontak.set_warna(cursor.getString(5));
                judulModelList.add(mdKontak);
            } while (cursor.moveToNext());
        }
        db.close();
        return judulModelList;
    }
    public int UpdateSpesifikasi (Spesifikasi mdNotif) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(tb_jenis_image, mdNotif.get_image());
        values.put(tb_jenis_merk, mdNotif.get_merk());
        values.put(tb_jenis_nama, mdNotif.get_nama());
        values.put(tb_jenis_harga, mdNotif.get_harga());
        values.put(tb_jenis_warna, mdNotif.get_warna());
        return db.update(tb_jenis_mobil, values, tb_jenis_id + " = ?",
                new String[] { String.valueOf(mdNotif.get_id())});
    }
    public void DeleteSpesifikasi (Spesifikasi mdNotif) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(tb_jenis_mobil, tb_jenis_id+ " = ?",
                new String[]{String.valueOf(mdNotif.get_id())});
        db.close();
    }
}


