package com.example.mardiana.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MenuInflater;
import android.view.View;
import com.example.mardiana.model.UserModel;
import com.example.mardiana.model.LihatQuisioner;
import android.widget.Toast;

import com.example.mardiana.network.APIService;
import com.example.mardiana.util.SessionUtils;

import com.example.mardiana.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    FloatingActionButton btn_quisioner, btn_hasil, btn_profil;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn_quisioner = findViewById(R.id.btn_gejala);
        btn_hasil = findViewById(R.id.btn_hasil);
        btn_profil = findViewById(R.id.btn_profil);

        final String user = getIntent().getStringExtra("iduser");
        if(user == null){
            SessionUtils.logout(MainActivity.this);
            Intent intent = new Intent(MainActivity.this, LoginActivity.class);
            startActivity(intent);
        }

        btn_quisioner.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String user = getIntent().getStringExtra("iduser");
                Intent intent = new Intent(MainActivity.this, QuisionerActivity.class);
                intent.putExtra("iduser",user);
                startActivity(intent);
            }
        });

        btn_hasil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final LihatQuisioner userid = new LihatQuisioner(user);
                Call<LihatQuisioner> api = APIService.Factory.create().postLihatHasil(userid);
                api.enqueue(new Callback<LihatQuisioner>() {
                    @Override
                    public void onResponse(Call<LihatQuisioner> call, Response<LihatQuisioner> response) {
                        if(response.isSuccessful()){
                            String gejala = response.body().getGejala();
                            String score = response.body().getScore();
                            String nama = response.body().getNama();
                            Intent intent = new Intent(MainActivity.this, HasilActivity.class);
                            intent.putExtra("gejala",gejala);
                            intent.putExtra("prob",score);
                            intent.putExtra("name",nama);
                            startActivity(intent);

                        }
                    }

                    @Override
                    public void onFailure(Call<LihatQuisioner> call, Throwable t) {
                        Toast.makeText(MainActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });

        btn_profil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              final UserModel userid = new UserModel(user);
              Call<UserModel.UserDataModel> api = APIService.Factory.create().postId(userid);
              api.enqueue(new Callback<UserModel.UserDataModel>() {
                  @Override
                  public void onResponse(Call<UserModel.UserDataModel> call, Response<UserModel.UserDataModel> response) {
                     if(response.isSuccessful()){
                         String username = response.body().getResults().get(0).getUsername();
                         String email = response.body().getResults().get(0).getEmail();

                         Intent intent = new Intent(MainActivity.this, ProfileActivity.class);
                         intent.putExtra("id",user);
                         intent.putExtra("username",username);
                         intent.putExtra("email",email);
                         startActivity(intent);
                     }
                  }

                  @Override
                  public void onFailure(Call<UserModel.UserDataModel> call, Throwable t) {
                      Toast.makeText(MainActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
                  }
              });
            }
        });
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
//            case R.id.menu_profil:
//                Intent intents = new Intent(MainActivity.this, ProfilActivity.class);
//                startActivity(intents);
//                break;
            case R.id.menu_logout:
                SessionUtils.logout(MainActivity.this);
                Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                startActivity(intent);
                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
