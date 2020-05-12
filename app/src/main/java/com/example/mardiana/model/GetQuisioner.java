package com.example.mardiana.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class GetQuisioner {
    @SerializedName("results")
    @Expose
    List<Quisioner> results;

    public List<Quisioner> getResults() { return results; }

    public void setResults(List<Quisioner> results) { this.results = results; }
}
