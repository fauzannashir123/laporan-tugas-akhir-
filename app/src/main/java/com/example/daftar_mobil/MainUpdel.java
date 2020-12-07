package com.example.daftar_mobil;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import com.theartofdev.edmodo.cropper.CropImage;
import com.theartofdev.edmodo.cropper.CropImageView;

import java.io.ByteArrayOutputStream;

public class MainUpdel extends AppCompatActivity {
    private MyDatabase db;

    private ImageView Eimage;
    private String Sid, Smerk, Snama, Sharga, Swarna;
    private EditText Emerk, Enama, Eharga, Ewarna;
    private byte[] Simage;
    final int REQUEST_CODE_GALLERY = 999;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_updel);
        db = new MyDatabase(this);
        Intent i = this.getIntent();
        Sid = i.getStringExtra("Iid");
        Simage = i.getByteArrayExtra("Iimage");
        Smerk = i.getStringExtra("Imerk");
        Snama = i.getStringExtra("Inama");
        Sharga = i.getStringExtra("Iharga");
        Swarna = i.getStringExtra("Iwarna");
        Eimage = (ImageView) findViewById(R.id.updel_iconid);
        Emerk = (EditText) findViewById(R.id.updel_merk);
        Enama = (EditText) findViewById(R.id.updel_nama);
        Eharga = (EditText) findViewById(R.id.updel_harga);
        Ewarna = (EditText) findViewById(R.id.updel_warna);

        Eimage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ActivityCompat.requestPermissions(
                        MainUpdel.this,
                        new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                        REQUEST_CODE_GALLERY
                );
            }
        });
        Bitmap bitmap = BitmapFactory.decodeByteArray(Simage, 0, Simage.length);
        Eimage.setImageBitmap(bitmap);
        Emerk.setText(Smerk);
        Enama.setText(Snama);
        Eharga.setText(Sharga);
        Ewarna.setText(Swarna);
        Button btnUpdate = (Button) findViewById(R.id.btn_up);
        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Simage = imageViewToByte(Eimage);
                Smerk = String.valueOf(Emerk.getText());
                Snama = String.valueOf(Enama.getText());
                Sharga = String.valueOf(Eharga.getText());
                Swarna = String.valueOf(Ewarna.getText());
                if (Smerk.equals("")){
                    Emerk.requestFocus();
                    Toast.makeText(MainUpdel.this, "Silahkan isi Merk ",
                            Toast.LENGTH_SHORT).show();
                } else if (Snama.equals("")){
                    Enama.requestFocus();
                    Toast.makeText(MainUpdel.this, "Silahkan isi Nama mobil",
                            Toast.LENGTH_SHORT).show();
                } else if (Sharga.equals("")){
                    Eharga.requestFocus();
                    Toast.makeText(MainUpdel.this, "Silahkan isi Harga",
                            Toast.LENGTH_SHORT).show();
                } else if (Swarna.equals("")){
                    Ewarna.requestFocus();
                    Toast.makeText(MainUpdel.this, "Silahkan isi Warna",
                            Toast.LENGTH_SHORT).show();
                } else {
                    db.UpdateSpesifikasi(new Spesifikasi(Sid, Simage, Smerk, Snama, Sharga, Swarna));
                    Toast.makeText(MainUpdel.this, "Data telah diupdate",
                            Toast.LENGTH_SHORT).show();
                    finish();
                }
            }

        });
        Button btnDelete = (Button) findViewById(R.id.btn_del);
        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                db.DeleteSpesifikasi(new Spesifikasi(Sid, Simage, Smerk, Snama, Sharga, Swarna));
                Toast.makeText(MainUpdel.this, "Data telah dihapus",
                        Toast.LENGTH_SHORT).show();
                finish();
            }
        });
    }
    private byte[] imageViewToByte(ImageView mImageView) {
        Bitmap bitmap = ((BitmapDrawable) mImageView.getDrawable()).getBitmap();
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 50, stream);
        byte[] byteArray = stream.toByteArray();
        return byteArray;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == REQUEST_CODE_GALLERY){
            if (grantResults.length>0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                //gallery intent
                Intent galleryIntent = new Intent(Intent.ACTION_GET_CONTENT);
                galleryIntent.setType("image/*");
                startActivityForResult(galleryIntent, REQUEST_CODE_GALLERY);
            }
            else {
                Toast.makeText(this, "Don't have permission to access file location", Toast.LENGTH_SHORT).show();
            }
            return;
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (requestCode == REQUEST_CODE_GALLERY && resultCode == RESULT_OK){
            Uri imageUri = data.getData();
            CropImage.activity(imageUri)
                    .setGuidelines(CropImageView.Guidelines.ON) //enable image guidlines
                    .setAspectRatio(1,1)// image will be square
                    .start(this);
        }
        if (requestCode == CropImage.CROP_IMAGE_ACTIVITY_REQUEST_CODE){
            CropImage.ActivityResult result =CropImage.getActivityResult(data);
            if (resultCode == RESULT_OK){
                Uri resultUri = result.getUri();
                //set image choosed from gallery to image view
                Eimage.setImageURI(resultUri);
            }
            else if (resultCode == CropImage.CROP_IMAGE_ACTIVITY_RESULT_ERROR_CODE){
                Exception error = result.getError();
            }
        }

        super.onActivityResult(requestCode, resultCode, data);
    }
}

