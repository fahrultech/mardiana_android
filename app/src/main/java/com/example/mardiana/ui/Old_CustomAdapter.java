package com.example.mardiana.ui;

import android.provider.MediaStore;
import android.util.Log;
import android.widget.BaseAdapter;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import com.example.mardiana.model.Quisioner;

import com.example.mardiana.R;

import org.w3c.dom.Text;

import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;


public class Old_CustomAdapter extends BaseAdapter {
    List<Quisioner> questionsList;

    Context context;
    RadioGroup Radiogroup;
    LayoutInflater inflter;

    public static ArrayList<String> selectedAnswers;

    public Old_CustomAdapter(Context applicationContext, List<Quisioner> questionsList){
        this.context = context;
        this.questionsList = questionsList;

        // initialize arraylist and add static string for all the questions
        selectedAnswers = new ArrayList<>();
        for (int i = 0; i < questionsList.size(); i++) {
            selectedAnswers.add("Not Attempted");
        }
        inflter = (LayoutInflater.from(applicationContext));
    }

    @Override
    public int getCount() {
        return questionsList.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(final int i, View view, ViewGroup viewGroup) {

        view = inflter.inflate(R.layout.quisioner_list,null);
        // get the reference of TextView and Button's
        TextView question = (TextView) view.findViewById(R.id.question);
        final RadioButton ss = (RadioButton) view.findViewById(R.id.ss);
        final RadioButton s = (RadioButton) view.findViewById(R.id.s);
        final RadioButton ts = (RadioButton) view.findViewById(R.id.ts);
        final RadioButton sts = (RadioButton) view.findViewById(R.id.sts);
        final RadioGroup rg = (RadioGroup) view.findViewById(R.id.radio_group);

        // perform setOnCheckedChangeListener event on yes button
        ss.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                // set Yes values in ArrayList if RadioButton is checked
                if (isChecked){
                    selectedAnswers.set(i, "4");
                    questionsList.get(i).setStatusGejala("4");
                    Log.d("Status","SS Checked");
                }
            }
        });
        // perform setOnCheckedChangeListener event on no button
        s.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                // set No values in ArrayList if RadioButton is checked
                if (isChecked){
                    selectedAnswers.set(i, "3");
                    questionsList.get(i).setStatusGejala("3");
                    Log.d("Status","S Checked");
                }
            }
        });
        ts.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                // set No values in ArrayList if RadioButton is checked
                if (isChecked){
                    selectedAnswers.set(i, "2");
                    questionsList.get(i).setStatusGejala("2");
                    Log.d("Status","TS Checked");
                }
            }
        });
        sts.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                // set No values in ArrayList if RadioButton is checked
                if (isChecked){
                    selectedAnswers.set(i, "1");
                    questionsList.get(i).setStatusGejala("1");
                    Log.d("Status","STS Checked");
                }
            }
        });
        if(selectedAnswers.get(i) != null){
           if(selectedAnswers.get(i).equals("4")){
               ss.setChecked(true);
           }else if(selectedAnswers.get(i).equals("3")){
               s.setChecked(true);
           }else if(selectedAnswers.get(i).equals("2")){
               ts.setChecked(true);
           }else if(selectedAnswers.get(i).equals("1")){
               sts.setChecked(true);
           }
        }else{
            rg.clearCheck();
        }
        // set the value in TextView
        question.setText(questionsList.get(i).getGejala());
        return view;
    }
}
