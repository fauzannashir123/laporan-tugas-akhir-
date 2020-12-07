package com.example.daftar_mobil;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;
public class MainRead extends AppCompatActivity implements
        AdapterView.OnItemClickListener{
    private ListView mListView;
    private CustomListAdapter adapter_off;
    private MyDatabase db;
    private List<Spesifikasi> ListSpesifikasi = new ArrayList<Spesifikasi>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_read);
        db = new MyDatabase(this);
        adapter_off = new CustomListAdapter(this, ListSpesifikasi );
        mListView = (ListView) findViewById(R.id.list_spesifikasi);
        mListView.setAdapter(adapter_off);
        mListView.setOnItemClickListener(this);
        mListView.setClickable(true);
        ListSpesifikasi.clear();
        List<Spesifikasi> contacts = db.ReadSpesifikasi();
        for (Spesifikasi cn : contacts) {
            Spesifikasi judulModel = new Spesifikasi();
            judulModel.set_id(cn.get_id());
            judulModel.set_image(cn.get_image());
            judulModel.set_merk(cn.get_merk());
            judulModel.set_nama(cn.get_nama());
            judulModel.set_harga(cn.get_harga());
            judulModel.set_warna(cn.get_warna());
            ListSpesifikasi.add(judulModel);
            if ((ListSpesifikasi.isEmpty()))
                Toast.makeText(MainRead.this, "Tidak ada data",
                        Toast.LENGTH_SHORT).show();
            else {
            }
        }
    }
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int i, long l) {
        Object o = mListView.getItemAtPosition(i);
        Spesifikasi obj_itemDetails = (Spesifikasi)o;
        String Sid = obj_itemDetails.get_id();
        byte[] Simage = obj_itemDetails.get_image();
        String Smerk = obj_itemDetails.get_merk();
        String Snama = obj_itemDetails.get_nama();
        String Sharga = obj_itemDetails.get_harga();
        String Swarna = obj_itemDetails.get_warna();
        Intent goUpdel = new Intent(MainRead.this, MainUpdel.class);
        goUpdel.putExtra("Iid", Sid);
        goUpdel.putExtra("Iimage", Simage);
        goUpdel.putExtra("Imerk", Smerk);
        goUpdel.putExtra("Inama", Snama);
        goUpdel.putExtra("Iharga", Sharga);
        goUpdel.putExtra("Iwarna", Swarna);
        startActivity(goUpdel);
    }
    @Override
    protected void onResume() {
        super.onResume();
        ListSpesifikasi.clear();
        mListView.setAdapter(adapter_off);
        List<Spesifikasi> contacts = db.ReadSpesifikasi();
        for (Spesifikasi cn : contacts) {
            Spesifikasi judulModel = new Spesifikasi();
            judulModel.set_id(cn.get_id());
            judulModel.set_image(cn.get_image());
            judulModel.set_merk(cn.get_merk());
            judulModel.set_nama(cn.get_nama());
            judulModel.set_harga(cn.get_harga());
            judulModel.set_warna(cn.get_warna());
            ListSpesifikasi.add(judulModel);
            if ((ListSpesifikasi.isEmpty()))
                Toast.makeText(MainRead.this, "Tidak ada data",
                        Toast.LENGTH_SHORT).show();
            else {
            }
        }
    }
}
