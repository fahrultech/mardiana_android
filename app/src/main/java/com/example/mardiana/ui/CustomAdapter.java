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


public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.ViewHolder> {
    public static List<Quisioner> questionsList;
    private List<Quisioner> getQuestionsListFiltered;

    Context context;
    RadioGroup Radiogroup;
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
    public int getItemCount() {
        return questionsList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        public TextView question;
        RadioGroup rg;
        RadioButton yes, no;
        public ViewHolder(View itemView){
            super(itemView);
            question = (TextView) itemView.findViewById(R.id.question);
            rg = itemView.findViewById(R.id.radio_group);
            yes = itemView.findViewById(R.id.ss);
            no = itemView.findViewById(R.id.s);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                }
            });
            yes.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    yes.setChecked(true);
                    questionsList.get(getAdapterPosition()).setGejala("Yes");

                }
            });

            no.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    no.setChecked(true);
                    questionsList.get(getAdapterPosition()).setGejala("No");
                }
            });
        }
    }
    @Override
    public CustomAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.quisioner_list, parent, false);
        return new CustomAdapter.ViewHolder(v);
    }
    @Override
    public void onBindViewHolder  (final CustomAdapter.ViewHolder holder, final int position) {
        final Quisioner questionsList = getQuestionsListFiltered.get(position);

        holder.question.setText(questionsList.getGejala());

        final String id =questionsList.getId();


        holder.rg.setOnCheckedChangeListener(null);
        if(questionsList.getGejala().equalsIgnoreCase("Yes")) {
            holder.yes.setChecked(true);
        } else {
            holder.yes.setChecked(false);
        }

        //Add listener here and remove from holder
        holder.rg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                if(holder.yes.isChecked()) {
                    getQuestionsListFiltered.get(position).setGejala("present");
                } else {
                    getQuestionsListFiltered.get(position).setGejala("absent");
                }
                notifyDataSetChanged();
            }
        });
    }
    public interface AttendanceAdapterListner {
        void onAttendanceAdapterSelected(Quisioner model);  // sending cardview to say the dialoge and model for sending context
    }
}
