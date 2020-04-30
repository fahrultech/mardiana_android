package com.example.mardiana.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Quisioner {
    @SerializedName("id")
    @Expose
    private String id;

    @SerializedName("gejala")
    @Expose
    private String gejala;

    public Quisioner(){}

    public Quisioner(String id, String gejala){
        this.id = id;
        this.gejala = gejala;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getGejala() {
        return gejala;
    }
    public void setGejala(String gejala) {
        this.gejala = gejala;
    }


}
