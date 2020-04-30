package com.example.mardiana.ui;

import android.widget.BaseAdapter;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mardiana.model.Quisioner;

import com.example.mardiana.R;

import java.util.List;
import java.util.ArrayList;


public class CustomAdapter extends BaseAdapter {
    List<Quisioner> questionsList;
    Context context;
    LayoutInflater inflter;

    public static ArrayList<String> selectedAnswers;

    public CustomAdapter(Context applicationContext, List<Quisioner> questionsList){
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
        RadioButton yes = (RadioButton) view.findViewById(R.id.yes);
        RadioButton no = (RadioButton) view.findViewById(R.id.no);
        // perform setOnCheckedChangeListener event on yes button
        yes.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
         // set Yes values in ArrayList if RadioButton is checked
                if (isChecked)
                    selectedAnswers.set(i, "Yes");
            }
        });
        // perform setOnCheckedChangeListener event on no button
        no.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        // set No values in ArrayList if RadioButton is checked
                if (isChecked)
                    selectedAnswers.set(i, "No");
            }
        });
        // set the value in TextView
        question.setText(questionsList.get(i).getGejala());
        return view;
    }
}
