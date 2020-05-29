package com.example.mardiana.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class LihatQuisioner {
    private String id;
    private String gejala;
    private String score;
    @SerializedName("nama")
    @Expose
    private String nama;

    public LihatQuisioner(String id){
      this.id = id;
    }

    public String getGejala() {
        return gejala;
    }

    public String getScore() {
        return score;
    }

    public String getNama() {
        return nama;
    }
}
