package com.example.mardiana.ui;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.mardiana.R;
import com.example.mardiana.model.UserModel;
import com.example.mardiana.network.APIService;
import com.example.mardiana.util.SessionUtils;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegisterActivity extends AppCompatActivity {
    ProgressDialog pDialog;
    Button btn_register;
    EditText txt_username, txt_password, txt_email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        btn_register = findViewById(R.id.btn_register);
        txt_email = findViewById(R.id.email_register);
        txt_username = findViewById(R.id.username_register);
        txt_password = findViewById(R.id.password_registers);

        btn_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = txt_username.getText().toString();
                String email = txt_email.getText().toString();
                String password = txt_password.getText().toString();

                if(username.trim().length() > 0 && password.trim().length() > 0 && email.trim().length() > 0){
                    processRegister(email,username,password);
                }else{
                    Toast.makeText(getApplicationContext(), "Ada Field Yang Kosong",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void processRegister(final String email, final String username, final String password){
        pDialog = new ProgressDialog(this);
        pDialog.setCancelable(false);
        pDialog.setMessage("Logging in ...");
        showDialog();

        Call<UserModel.UserDataModel> api = APIService.Factory.create().postRegister(username, password, email);
        api.enqueue(new Callback<UserModel.UserDataModel>() {
            @Override
            public void onResponse(Call<UserModel.UserDataModel> call, Response<UserModel.UserDataModel> response) {
                hideDialog();
                if(response.isSuccessful()){
                    if(response.body().getMessage().equalsIgnoreCase("Berhasil")){
                        Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                        startActivity(intent);
                        finish();
                    }else{
                        Toast.makeText(RegisterActivity.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }
            }

            @Override
            public void onFailure(Call<UserModel.UserDataModel> call, Throwable t) {
                hideDialog();
                Toast.makeText(RegisterActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
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
