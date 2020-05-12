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

public class LoginActivity extends AppCompatActivity {

    ProgressDialog pDialog;
    Button btn_login, btn_daftar;
    EditText txt_username, txt_password;
    TextView txt_lupa;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        btn_login = findViewById(R.id.btn_login);
        btn_daftar = findViewById(R.id.daftar);
        txt_username = findViewById(R.id.username);
        txt_password = findViewById(R.id.password);

        if(SessionUtils.isLoggedIn(this)){
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
            finish();
        }

        btn_login.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                String username = txt_username.getText().toString();
                String password = txt_password.getText().toString();

                // Cek Kolom Yang Kosong
                if(username.trim().length() > 0 && password.trim().length() > 0){
                    checkLogin(username,password);
                }else{
                    Toast.makeText(getApplicationContext(), "Ada Field Yang Kosong",Toast.LENGTH_SHORT).show();
                }
            }
        });
        btn_daftar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(intent);
            }
        });
    }

    private void checkLogin(final String username, final String password){
        pDialog = new ProgressDialog(this);
        pDialog.setCancelable(false);
        pDialog.setMessage("Logging in ...");
        showDialog();

        Call<UserModel.UserDataModel> api = APIService.Factory.create().postLogin(username, password);
        api.enqueue(new Callback<UserModel.UserDataModel>() {
            @Override
            public void onResponse(Call<UserModel.UserDataModel> call, Response<UserModel.UserDataModel> response) {
                hideDialog();
                if(response.isSuccessful()){
                    if(response.body().getMessage().equalsIgnoreCase("Berhasil")){
                        if(SessionUtils.login(LoginActivity.this, response.body().getResults().get(0))){
                            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                            intent.putExtra("iduser", response.body().getResults().get(0).getId());
                            startActivity(intent);
                            finish();
                        }
                    }else{
                        Toast.makeText(LoginActivity.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }
            }

            @Override
            public void onFailure(Call<UserModel.UserDataModel> call, Throwable t) {
                hideDialog();
                Toast.makeText(LoginActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

    }
    private void showDialog() {
        if (!pDialog.isShowing())
            pDialog.show();
    }

    private void hideDialog() {
        if (pDialog.isShowing())
            pDialog.dismiss();
    }
}
