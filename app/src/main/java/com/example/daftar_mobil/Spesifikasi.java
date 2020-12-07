package com.example.daftar_mobil;

public class Spesifikasi {
    private String _id, _merk, _nama,_harga, _warna ;
    private byte[] _image;
    public Spesifikasi (String id, byte[] image, String merk, String nama,String harga, String warna ) {
        this._id = id;
        this._image = image;
        this._merk = merk;
        this._nama = nama;
        this._harga = harga;
        this._warna = warna;

    }
    public Spesifikasi() {
    }
    public byte[] get_image() {
        return _image;
    }
    public void set_image(byte[] _image) {
        this._image = _image;
    }
    public String get_id() {
        return _id;
    }
    public void set_id(String _id) {
        this._id = _id;
    }
    public String get_merk() {
        return _merk;
    }
    public void set_merk(String _merk) {
        this._merk = _merk;
    }
    public String get_nama() {
        return _nama;
    }
    public void set_nama(String _nama) {
        this._nama = _nama;
    }
    public String get_harga() {
        return _harga;
    }
    public void set_harga(String _harga) {
        this._harga = _harga;
    }
    public String get_warna() {
        return _warna;
    }
    public void set_warna(String _warna) {
        this._warna = _warna;
    }

}

