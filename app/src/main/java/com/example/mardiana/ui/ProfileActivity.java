package com.example.mardiana.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Button;
import android.widget.Toast;

import com.example.mardiana.model.UserModel;

import androidx.appcompat.app.AppCompatActivity;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import com.example.mardiana.R;
import com.example.mardiana.network.APIService;
import com.example.mardiana.util.SessionUtils;

public class ProfileActivity extends AppCompatActivity {
    String username, email, userid;
    TextView txt_username, txt_email, txt_password, txt_ganti_password;
    Button btn_confirm;

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profil);

        username = getIntent().getStringExtra("username");
        email = getIntent().getStringExtra("email");
        userid = getIntent().getStringExtra("id");

        txt_username = findViewById(R.id.profil_username);
        txt_email = findViewById(R.id.profile_email);
        txt_password = findViewById(R.id.profil_password);
        txt_ganti_password = findViewById(R.id.ganti_profil_password);
        btn_confirm = findViewById(R.id.btn_confirm);

        txt_username.setText(username);
        txt_email.setText(email);

        btn_confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = txt_username.getText().toString();
                String email = txt_email.getText().toString();
                String password = txt_password.getText().toString();
                String gantipassword = txt_ganti_password.getText().toString();
                String id = userid;

                Call<UserModel.UserDataModel> api = APIService.Factory.create().postUpdateProfil(id,
                        username,password,gantipassword,email);
                api.enqueue(new Callback<UserModel.UserDataModel>() {
                    @Override
                    public void onResponse(Call<UserModel.UserDataModel> call, Response<UserModel.UserDataModel> response) {
                        if(response.isSuccessful()){
                            String message = response.body().getMessage();
                            if(message.equalsIgnoreCase("Berhasil")){
                                SessionUtils.logout(ProfileActivity.this);
                                Intent intent = new Intent(ProfileActivity.this, LoginActivity.class);
                                startActivity(intent);
                            }
                        }
                    }

                    @Override
                    public void onFailure(Call<UserModel.UserDataModel> call, Throwable t) {
                        Toast.makeText(ProfileActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });

    }
}
