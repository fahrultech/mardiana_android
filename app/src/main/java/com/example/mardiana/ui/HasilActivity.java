package com.example.mardiana.ui;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.mardiana.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class HasilActivity extends AppCompatActivity {

    TextView txt_gejala;
    TextView txt_nama;
    TextView txt_prob;
    TextView txt_hasil;
    FloatingActionButton btn_hasil;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hasil);

        String nama = getIntent().getStringExtra("name");
        String gejala = getIntent().getStringExtra("gejala");
        String prob = getIntent().getStringExtra("prob");

        txt_gejala = findViewById(R.id.txt_gejala);
        txt_nama = findViewById(R.id.txt_nama);
        txt_prob = findViewById(R.id.prob);
        btn_hasil = findViewById(R.id.lihat_hasil);
        txt_hasil = findViewById(R.id.text_hasil);

        txt_gejala.setText(gejala);
        txt_nama.setText(nama);
        txt_prob.setText(prob);

        if(txt_gejala.getText().equals("Gejala Ringan") ){
            txt_hasil.setText("Anak bapak/ibu kecanduan di level ringan, mohon untuk bapak ibu tetap mempertahankan durasi penggunaan gadget terhadap anak.");
        }else if(txt_gejala.getText().equals("Gejala Sedang")){
            txt_hasil.setText("Anak bapak/ibu kecanduan level sedang, mohon untuk lebih diperhatikan lagi dalam penggunaan gadgetnya");
        }else if(txt_gejala.getText().equals("Gejala Berat")){
            txt_hasil.setText("Anak bapak/ibu kecanduan level berat, apabila ada hal hal yang merugikan mental anak, segera berkonsultasi ke dokter psikolog terdekat.");
        }

        btn_hasil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(),"Lihat Hasil",Toast.LENGTH_SHORT).show();
            }
        });
    }
}
