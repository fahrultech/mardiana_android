package com.example.mardiana.model;

import java.util.List;

public class UserModel {
    private String id;
    private String nama;
    private String jk;
    private String usia;
    private String level;

    public String getId(){
        return id;
    }
    public void setId(String id){
        this.id = id;
    }
    public String getNama(){
        return nama;
    }
    public void setNama(String nama){
        this.nama = nama;
    }
    public String getJk(){
        return jk;
    }
    public void setJk(String jk){
        this.jk = jk;
    }
    public String getUsia(){
        return usia;
    }
    public void setUsia(String usia){
        this.usia = usia;
    }

    public class UserDataModel extends MessageModel {
        private List<UserModel> results;

        public List<UserModel> getResults(){
            return results;
        }

        public void setResults(List<UserModel> results){this.results = results;}
    }
}

