package com.example.mardiana.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.example.mardiana.model.Quisioner;
import com.example.mardiana.model.GetQuisioner;
import com.example.mardiana.model.HasilQuisioner;
import com.example.mardiana.network.APIService;
import com.example.mardiana.util.SessionUtils;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import com.example.mardiana.R;

import org.json.JSONArray;

public class QuisionerActivity extends AppCompatActivity {

    ListView simpleList;
    List<Quisioner> questions;
    //String[] questions;
    Button submit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quisioner);
        Call<GetQuisioner> api = APIService.Factory.create().getQuisioner();

        api.enqueue(new Callback<GetQuisioner>() {
            @Override
            public void onResponse(Call<GetQuisioner> call, Response<GetQuisioner> response) {
                //questions = getResources().getStringArray(R.array.questions);
                questions = response.body().getResults();
                simpleList = findViewById(R.id.quisioner_list_view);
                submit = (Button) findViewById(R.id.submit_button);
                //Set Adapter to fill the data in the ListView
                Toast.makeText(getApplicationContext(),"Berhasil",Toast.LENGTH_LONG).show();
                Old_CustomAdapter customAdapter = new Old_CustomAdapter(getApplicationContext(),questions);
                simpleList.setAdapter(customAdapter);

                // perform setOnClickListener event on Button
                submit.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String message = "";
                        // get the value of selected answers from custom adapter
                        if(Old_CustomAdapter.selectedAnswers.contains("Not Attempted")){
                            Toast.makeText(getApplicationContext(),"Masih Ada Yang Kosong", Toast.LENGTH_LONG).show();
                        }else{
                            String user = getIntent().getStringExtra("iduser");
                            HasilQuisioner hasil = new HasilQuisioner(
                              user, Old_CustomAdapter.selectedAnswers
                            );
                            //JSONArray jsArray = new JSONArray(Old_CustomAdapter.selectedAnswers);
                            Call<HasilQuisioner> api = APIService.Factory.create().postGejala(hasil);
                            api.enqueue(new Callback<HasilQuisioner>() {
                                @Override
                                public void onResponse(Call<HasilQuisioner> call, Response<HasilQuisioner> response) {
                                   if(response.isSuccessful()){
                                       String gejala = response.body().getResults().get(0).toString();
                                       String name = response.body().getName();
                                       Intent intent = new Intent(QuisionerActivity.this, HasilActivity.class);
                                       intent.putExtra("gejala",gejala);
                                       intent.putExtra("name",name);
                                       startActivity(intent);
                                   }
                                }
                                @Override
                                public void onFailure(Call<HasilQuisioner> call, Throwable t) {
                                    Toast.makeText(QuisionerActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
                                }
                            });
                        }
                    }
                });
            }

            @Override
            public void onFailure(Call<GetQuisioner> call, Throwable t) {
                Toast.makeText(QuisionerActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}

