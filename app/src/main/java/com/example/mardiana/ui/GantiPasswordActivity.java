package com.example.mardiana.ui;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mardiana.R;
import com.example.mardiana.model.UserModel;
import com.example.mardiana.network.APIService;
import com.example.mardiana.util.SessionUtils;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class GantiPasswordActivity extends AppCompatActivity {

    EditText txt_username, txt_password_baru, txt_confirm_password_baru;
    Button btn_ganti_password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lupa_password);

        txt_username = findViewById(R.id.username);
        txt_password_baru = findViewById(R.id.password_baru);
        txt_confirm_password_baru = findViewById(R.id.confirm_password_baru);
        btn_ganti_password = findViewById(R.id.btn_ganti_password);

        btn_ganti_password.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = txt_username.getText().toString();
                String passwordbaru = txt_password_baru.getText().toString();
                String confirmpasswordbaru = txt_confirm_password_baru.getText().toString();

                if(!passwordbaru.equalsIgnoreCase(confirmpasswordbaru)){
                   Toast.makeText(GantiPasswordActivity.this,"Password Tidak Sesuai",Toast.LENGTH_SHORT).show();
                }else if(username.trim().length() > 0 && passwordbaru.trim().length() > 0 && confirmpasswordbaru.trim().length() >0){
                     Call<UserModel.UserDataModel> api = APIService.Factory.create().postGantiPassword(username,passwordbaru);
                     api.enqueue(new Callback<UserModel.UserDataModel>() {
                         @Override
                         public void onResponse(Call<UserModel.UserDataModel> call, Response<UserModel.UserDataModel> response) {
                             if(response.isSuccessful()){
                                 if(response.body().getMessage().equalsIgnoreCase("Berhasil")){
                                     Intent intent = new Intent(GantiPasswordActivity.this, LoginActivity.class);
                                     startActivity(intent);
                                     finish();
                                 }else{
                                     Toast.makeText(GantiPasswordActivity.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                                 }
                             }
                         }

                         @Override
                         public void onFailure(Call<UserModel.UserDataModel> call, Throwable t) {

                         }
                     });
                }else {
                    Toast.makeText(getApplicationContext(), "Ada Field Yang Kosong",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
