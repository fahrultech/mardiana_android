package com.example.mardiana.ui;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.mardiana.R;

public class HasilActivity extends AppCompatActivity {

    TextView txt_gejala;
    TextView txt_nama;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hasil);

        String nama = getIntent().getStringExtra("name");
        String gejala = getIntent().getStringExtra("gejala");

        txt_gejala = findViewById(R.id.txt_gejala);
        txt_nama = findViewById(R.id.txt_nama);

        txt_gejala.setText(gejala);
        txt_nama.setText(nama);
    }
}
