package com.shafaseyo.bikesrents.activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.shafaseyo.bikesrents.R;
import com.shafaseyo.bikesrents.helper.DataHelper;

public class DetailSepedaActivity extends AppCompatActivity {

    protected Cursor cursor;
        String sMerk, sHarga, sGambar;
        DataHelper dbHelper;

        @SuppressLint("SetTextI18n")
        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_detail_sepeda);

            Bundle terima = getIntent().getExtras();

            dbHelper = new DataHelper(this);
            Intent intent = getIntent();

            String merk = terima.getString("merk");

            SQLiteDatabase db = dbHelper.getReadableDatabase();
            cursor = db.rawQuery("select * from sepeda where merk = '" + merk + "'", null);
            cursor.moveToFirst();
            if (cursor.getCount() > 0) {
                sMerk = cursor.getString(0);
                sHarga = cursor.getString(1);
            }

            if (sMerk.equals("Cannondale")) {
                sGambar = "cannondale";
            } else if (sMerk.equals("Giant")) {
                sGambar = "giant";
            } else if (sMerk.equals("GT")) {
                sGambar = "gt";
            } else if (sMerk.equals("Polygon")) {
                sGambar = "polygon";
            } else if (sMerk.equals("Wimcycle")) {
                sGambar = "wimcycle";
            } else if (sMerk.equals("United")) {
                sGambar = "united";
            } else if (sMerk.equals("Santa Cruz")) {
                sGambar = "santacruz";
            }

        ImageView ivGambar = findViewById(R.id.ivMobil);
        TextView tvMerk = findViewById(R.id.JMobil);
        TextView tvHarga = findViewById(R.id.JHarga);

        tvMerk.setText(sMerk);
        ivGambar.setImageResource(getResources().getIdentifier(sGambar, "drawable", getPackageName()));
        tvHarga.setText("Rp. " + sHarga);

        if (getSupportActionBar() !=null) {
            getSupportActionBar().setTitle(R.string.detail_sepeda);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

}
