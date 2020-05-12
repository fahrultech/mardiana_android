package com.example.mardiana.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class HasilQuisioner {
    private String id;
    private ArrayList<String> quisioner;

    private ArrayList results;

    @SerializedName("name")
    @Expose
    private String name;

    public String getName() {
        return name;
    }

    public void setResults(ArrayList results) {
        this.results = results;
    }

    public ArrayList getResults() {
        return results;
    }

    public HasilQuisioner(String id, ArrayList<String> quisioner){
        this.id = id;
        this.quisioner = quisioner;
    }
}
