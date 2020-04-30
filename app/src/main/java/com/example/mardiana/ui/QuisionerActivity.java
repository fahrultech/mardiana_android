package com.example.mardiana.ui;

import androidx.appcompat.app.AppCompatActivity;
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
import com.example.mardiana.network.APIService;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import com.example.mardiana.R;

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
                simpleList = (ListView) findViewById(R.id.quisioner_list_view);
                submit = (Button) findViewById(R.id.submit_button);
                //Set Adapter to fill the data in the ListView
                Toast.makeText(getApplicationContext(),"Berhasil",Toast.LENGTH_LONG).show();
                CustomAdapter customAdapter = new CustomAdapter(getApplicationContext(),questions);
                simpleList.setAdapter(customAdapter);

                // perform setOnClickListener event on Button
                submit.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String message = "";
                        // get the value of selected answers from custom adapter
                        for (int i = 0; i < CustomAdapter.selectedAnswers.size(); i++) {
                            message = message + "\n" + (i + 1) + " " + CustomAdapter.selectedAnswers.get(i);
                        }
                        // display the message on screen with the help of Toast.
                        Toast.makeText(getApplicationContext(), message, Toast.LENGTH_LONG).show();
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

